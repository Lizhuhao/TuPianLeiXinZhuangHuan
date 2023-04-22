package wuziqi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        //文件与BufferedImage间的转换
        BufferedImage bi=file2img("bj.jpg");  //读取图片
        BufferedImage bii=img_alpha(bi,150);
        img2file(bii,"PNG","test.png");  //生成图片

    }

    public static BufferedImage img_alpha(BufferedImage imgsrc,int alpha) {
        try {
            //创建一个包含透明度的图片,半透明效果必须要存储为png合适才行，存储为jpg，底色为黑色
            BufferedImage back=new BufferedImage(imgsrc.getWidth(), imgsrc.getHeight(), BufferedImage.TYPE_INT_ARGB);
            int width = imgsrc.getWidth();
            int height = imgsrc.getHeight();
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    int rgb = imgsrc.getRGB(i, j);
                    Color color = new Color(rgb);
                    Color newcolor = new Color(color.getRed(), color.getGreen(),color.getBlue(), alpha);
                    back.setRGB(i,j,newcolor.getRGB());
                }
            }
            return back;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //读取图片
    public static BufferedImage file2img(String imgpath) {
        try {
            BufferedImage bufferedImage= ImageIO.read(new File(imgpath));
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //保存图片,extent为格式，"jpg"、"png"等
    public static void img2file(BufferedImage img,String extent,String newfile) {
        try {
            ImageIO.write(img, extent, new File(newfile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
