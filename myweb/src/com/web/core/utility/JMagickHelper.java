package com.web.core.utility;

import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-4-15
 * Time: 下午3:26
 * To change this template use File | Settings | File Templates.
 */
public class JMagickHelper
{
    private static final Log log = LogFactory.getLog(JMagickHelper.class);

    static
    {
        log.info(System.getProperty("java.library.path"));
        System.out.println(System.getProperty("java.library.path"));
        System.setProperty("jmagick.systemclassloader", "no");// 在jsp,servlet 必须加上这一句
        System.load("/usr/local/jmagick/lib/libJMagick.so");
        //System.loadLibrary("jmagick");
    }

    /**
     * 获取图片的尺寸[长，宽]
     *
     * @param img 图片路径
     * @return {x,y}
     */
    public static int[] getImageXY(String img)
    {
        Dimension dimension = null;

        try
        {
            ImageInfo imageInfo = new ImageInfo(img);
            MagickImage imageReaded = new MagickImage(imageInfo);

            dimension = imageReaded.getDimension();

            if (dimension != null)
            {
                int origX = (int) dimension.getWidth();
                int origY = (int) dimension.getHeight();
                return new int[]{origX, origY};
            }
            else
            {
                return new int[]{0, 0};
            }

        }
        catch (MagickException e)
        {
            log.error("获取图片尺寸出错！", e);
            return new int[]{0, 0};
        }
    }

    /**
     * 计算新图片的尺寸
     *
     * @param srcSize   原始尺寸{x,y}
     * @param destSize  目标尺寸{x,y}
     * @param keepRatio 是否保持比例
     * @return 新图片的尺寸{x,y}
     */
    public static int[] calcNewSize(int[] srcSize, int[] destSize, boolean keepRatio)
    {
        if (srcSize[0] <= destSize[0] && srcSize[1] <= destSize[1])
        {
            return srcSize;
        }

        int[] newSize = new int[2];

        if (keepRatio)
        {
            if (srcSize[0] > srcSize[1])
            {
                newSize[0] = destSize[0];
                newSize[1] = srcSize[1] * destSize[0] / srcSize[0];
            }
            else
            {
                newSize[1] = destSize[1];
                newSize[0] = srcSize[0] * destSize[1] / srcSize[1];
            }
        }
        else
        {
            newSize[0] = destSize[0];
            newSize[1] = destSize[1];
        }

        return newSize;
    }

    /**
     * resize一个图片
     *
     * @param origFile  原图片
     * @param newFile   新图片
     * @param width     新宽度x
     * @param height    新高度y
     * @param keepRatio 是否保持比例
     * @throws magick.MagickException JMagick发生错误
     */
    public static void resizeImage(String origFile, String newFile, int width, int height, boolean keepRatio) throws MagickException
    {
        ImageInfo imageInfo = new ImageInfo(origFile);
        MagickImage imageReaded = new MagickImage(imageInfo);

        Dimension dimension = imageReaded.getDimension();
        int origX = (int) dimension.getWidth();
        int origY = (int) dimension.getHeight();

        int[] newSize = calcNewSize(new int[]{origX, origY}, new int[]{width, height}, keepRatio);

        int newX = newSize[0];
        int newY = newSize[1];

        if (origX == newX && origY == newY)
        {
            File origFileObj = new File(origFile);

            try
            {
                FileUtils.copyFile(origFileObj, new File(newFile));
            }
            catch (IOException e)
            {
                log.error("resize图片发生错误！", e);
            }

            return;
        }

        if (imageReaded.hasFrames() || imageReaded.getImageFormat().equalsIgnoreCase("gif"))
        {
            resizeGIF(origFile, newFile, newX, newY);
        }
        else
        {
            MagickImage newImage = imageReaded.scaleImage(newX, newY);
            newImage.setFileName(newFile);
            newImage.writeImage(new ImageInfo());
        }
    }

    /**
     * 生成缩略图
     *
     * @param srcFile  源文件
     * @param destFile 目标文件
     * @param width    缩略图宽x
     * @param height   缩略图高y
     * @throws java.io.IOException IO异常
     */
    public static void resizeImage(File srcFile, File destFile, int width, int height) throws IOException
    {
        if (!srcFile.exists())
        {
            throw new FileNotFoundException("源图片文件不存在！");
        }

        try
        {
            resizeImage(srcFile.getAbsolutePath(), destFile.getAbsolutePath(), width, height, true);
        }
        catch (MagickException e)
        {
            throw new IOException("ImageMagick生成缩略图失败！");
        }
    }

    /**
     * 生成缩略图，并写回到原图片文件
     *
     * @param srcFile 原图片
     * @param width   新图片宽x
     * @param height  新图片高y
     * @throws java.io.IOException 发生错误
     */
    public static void resizeImage(File srcFile, int width, int height) throws IOException
    {
        if (!srcFile.exists())
        {
            throw new FileNotFoundException("源图片文件不存在！");
        }

        File tempFile = File.createTempFile("msnnext_", "_temp");
        boolean flag = false;

        try
        {
            FileUtils.copyFile(srcFile, tempFile);
            flag = true;
        }
        catch (Exception e)
        {
            log.error("resize图片发生错误！", e);
        }

        if (flag)
        {
            resizeImage(tempFile, srcFile, width, height);
        }

        try
        {
            FileUtils.forceDelete(tempFile);
        }
        catch (IOException e)
        {
            FileUtils.forceDeleteOnExit(tempFile);
        }
    }

    /**
     * resizeGIF格式的图片
     *
     * @param origFile 源文件
     * @param newFile  新文件
     * @param newX     新文件的宽x
     * @param newY     新文件的高y
     */
    public static void resizeGIF(String origFile, String newFile, int newX, int newY)
    {
        try
        {
            GifImage gifImage = GifDecoder.decode(new File(origFile));
            GifImage newGif = GifTransformer.resize(gifImage, newX, newY, true);
            GifEncoder.encode(newGif, new File(newFile), true);
        }
        catch (IOException e)
        {
            log.error("resize[GIF]图片发生错误！", e);
        }
    }


    /**
     * 从图片中间截取一个正方形区域，并将其边长变为x
     *
     * @param origFile 源文件
     * @param newFile  新文件
     * @param x        方形的边长
     * @throws java.io.IOException 异常
     */
    public static void resizeAndCutSquareImage(String origFile, String newFile, int x)
    {
        try
        {
            int[] origXY = getImageXY(origFile);
            int newX = origXY[0] > origXY[1] ? origXY[1] : origXY[0];

            ImageInfo imageInfo = new ImageInfo(origFile);
            MagickImage imageReaded = new MagickImage(imageInfo);

            if (imageReaded.hasFrames() || imageReaded.getImageFormat().equalsIgnoreCase("gif"))
            {
                try
                {
                    resizeAndCutSquareGif(origFile, newFile, x);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                return;
            }


            MagickImage newImage;
            Rectangle rect;

            if (origXY[0] > origXY[1])
            {
                rect = new Rectangle((int) (((double) origXY[0] - (double) origXY[1]) / 2), 0, newX, newX);
                newImage = imageReaded.cropImage(rect);
            }
            else if (origXY[1] > origXY[0])
            {
                rect = new Rectangle(0, (int) (((double) origXY[1] - (double) origXY[0]) / 2), newX, newX);
                newImage = imageReaded.cropImage(rect);
            }
            else
            {
                newImage = imageReaded;
            }

            if (newX > x)
            {
                newImage = newImage.scaleImage(x, x);
            }

            newImage.setFileName(newFile);
            newImage.writeImage(new ImageInfo());
        }
        catch (MagickException e)
        {
            String msg = "取图片中间方块区域出错！";
            log.error(msg, e);
            //throw new IOException(msg + e.getMessage());
        }
    }

    public static void resizeAndCutSquareGif(String origFile, String newFile, int x) throws IOException
    {
        int[] origXY = getImageXY(origFile);
        int newX = origXY[0] > origXY[1] ? origXY[1] : origXY[0];
        Rectangle rect = null;
        GifImage gifImage = GifDecoder.decode(new File(origFile));

        if (origXY[0] > origXY[1])
        {
            rect = new Rectangle((int) (((double) origXY[0] - (double) origXY[1]) / 2), 0, newX, newX);
        }
        else if (origXY[1] > origXY[0])
        {
            rect = new Rectangle(0, (int) (((double) origXY[1] - (double) origXY[0]) / 2), newX, newX);
        }

        GifImage newGif;

        if (null != rect)
        {
            newGif = GifTransformer.crop(gifImage, rect);
        }
        else
        {
            newGif = gifImage;
        }

        if (newX > x)
        {
            newGif = GifTransformer.resize(newGif, x, x, true);
        }

        GifEncoder.encode(newGif, new File(newFile), true);
    }
}
