package com.web.core.utility;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-4-14
 * Time: 下午12:37
 * To change this template use File | Settings | File Templates.
 */
public class QRCodeImageBuffer implements QRCodeImage
{
    BufferedImage bufImg;

    public QRCodeImageBuffer(BufferedImage bufImg)
    {
        this.bufImg = bufImg;
    }

    public int getHeight()
    {
        return bufImg.getHeight();
    }
    public int getPixel(int x, int y)
    {
        return bufImg.getRGB(x, y);
    }
    public int getWidth()
    {
        return bufImg.getWidth();
    }
}
