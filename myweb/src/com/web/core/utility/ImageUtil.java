package com.web.core.utility;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.net.URI;

import javax.imageio.ImageIO;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-4-15
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class ImageUtil
{
    public static final int TYPE_USER = 0;
    public static final int TYPE_EVENT = 1;                  // 活动图片
    public static final int TYPE_GROUP = 2;
    public static final int TYPE_PROJECT = 3;                // 项目图片和 项目logo
    public static final int TYPE_PROJECT_TASK = 4;          // 项目Task Card图片
    public static final int TYPE_PROJECT_PUSH = 5;          // 推动者做项目Task Card证明图片
    public static final int TYPE_IDENTITY = 6;              // 身份照片
    public static String rootPath = Setting.getSetting("user.avater.root");

    /**
     * 上传用户，奖品，广告图片
     *
     * @param photo 上传图片
     * @param type  类别
     * @return path
     */
    public static String uploadImage(MultipartFile photo, int type)
    {
        String originalFile = photo.getOriginalFilename();
        String fileType = StringUtils.substringAfterLast(originalFile, ".");

        if (!StringUtils.containsIgnoreCase("jpg,gif,png,jpeg,bmp", fileType))
        {
            return "";
        }

        String fileKey = UUID.randomUUID().toString();
        Random random = new Random();
        int randomId = random.nextInt(999999999);
        String fileExt = StringUtils.substringAfterLast(photo.getOriginalFilename(), ".");
        String photoDir = getPhotoPath(randomId);
        String subDir = getSubDir(type);
        photoDir = subDir + photoDir;

        String photoUrl = photoDir;

        try
        {
            String photoSavePath = rootPath + photoDir;
            File file = new File(photoSavePath);

            if (!file.exists() || !file.isDirectory())
            {
                file.mkdirs();
            }

            photoSavePath = photoSavePath + fileKey + "." + fileExt;
            InputStream is = photo.getInputStream();
            OutputStream os = new FileOutputStream(photoSavePath);

            IOUtils.copy(is, os);
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
            photoUrl = photoDir + fileKey + "." + fileExt;

            if (type != TYPE_IDENTITY)
            {
                String iconUrl = photoDir + fileKey + "_icon." + fileExt;
                String avatarUrl = photoDir + fileKey + "_avatar." + fileExt;
                String photo_url = photoDir + fileKey + "_photo." + fileExt;

                String iconPath = rootPath + iconUrl;
                String avatarPath = rootPath + avatarUrl;
                String photoPath = rootPath + photo_url;

                int iconSizeX = getIconSizeX(type);
                int iconSizeY = getIconSizeY(type);
                int avatarSizeX = getAvatarSizeX(type);
                int avatarSizeY = getAvatarSizeY(type);
                int photoSizeX = getPhotoSizeX(type);
                int photoSizeY = getPhotoSizeY(type);

                JMagickHelper.resizeAndCutSquareImage(photoSavePath, iconPath, iconSizeX);
                JMagickHelper.resizeImage(photoSavePath, avatarPath, avatarSizeX, avatarSizeY, true);
                JMagickHelper.resizeImage(photoSavePath, photoPath, photoSizeX, photoSizeY, true);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return photoUrl;
    }

    private static String getSubDir(int type)
    {
        switch (type)
        {
            case TYPE_USER:
                return "/upload/portrait/";

            case TYPE_EVENT:
                return "/upload/event/";

            case TYPE_GROUP:
                return "/upload/group/";

            case TYPE_PROJECT:
                return "/upload/project/";

            case TYPE_PROJECT_TASK:
                return "/upload/task/";

            case TYPE_PROJECT_PUSH:
                return "/upload/push/";

            case TYPE_IDENTITY:
                return "/upload/identity/";

            default:
                return "/upload/portrait/";
        }
    }

    public static String getPhotoPath(long userId)
    {
        String orgId = Long.toString(userId);
        String mask = "000000000";
        int orgIdLen = orgId.trim().length();
        String newId = mask.substring(0, (9 - orgIdLen)) + orgId;
        String firstDir = newId.substring(0, 3);
        String secondDir = newId.substring(3, 6);
        String thirdDir = newId.substring(6, 9);

        return firstDir + "/" + secondDir + "/" + thirdDir + "/";
    }

    private static int getIconSizeX(int type)
    {
        String iconSizeStr = Setting.getSetting("file.photo.icon.width");
        return Integer.parseInt(iconSizeStr);
    }


    private static int getIconSizeY(int type)
    {
        String iconSizeStr = Setting.getSetting("file.photo.icon.height");

        return Integer.parseInt(iconSizeStr);
    }

    private static int getAvatarSizeX(int type)
    {
        String avatarSizeStr = Setting.getSetting("file.photo.avatar.width");

        return Integer.parseInt(avatarSizeStr);
    }

    private static int getAvatarSizeY(int type)
    {

        String avatarSizeStr = Setting.getSetting("file.photo.avatar.height");

        return Integer.parseInt(avatarSizeStr);
    }

    private static int getPhotoSizeX(int type)
    {
        String avatarSizeStr = Setting.getSetting("file.photo.photo.width");

        return Integer.parseInt(avatarSizeStr);
    }

    private static int getPhotoSizeY(int type)
    {
        String avatarSizeStr = Setting.getSetting("file.photo.photo.height");

        return Integer.parseInt(avatarSizeStr);
    }

    public static void compressImage(File file, String directoryFileName,
                                     int width, int height, boolean proportion) throws Exception {

        // 源文件
        FileInputStream fis = new FileInputStream(file);
        Image image = ImageIO.read(fis);

        // 计算长宽比
        int newWidth = 0;
        int newHeight = 0;
        if (image.getWidth(null) > width || image.getHeight(null) > height) {
            if (proportion) {
                int rate1 = image.getWidth(null) / width;
                int rate2 = image.getHeight(null) / height;
                int rate = rate1 > rate2 ? rate1 : rate2;
                newWidth = image.getWidth(null) / rate;
                newHeight = image.getHeight(null) / rate;
            } else {
                newWidth = width;
                newHeight = height;
            }
        } else {
            newWidth = image.getWidth(null);
            newHeight = image.getHeight(null);
        }

        // 输出压缩图片
        BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics()
                .drawImage(
                        image.getScaledInstance(newWidth, newHeight,
                                Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(bufferedImage, "jpeg", new File(new URI(directoryFileName)));
        bufferedImage.flush();
    }
}
