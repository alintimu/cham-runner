package Util;

import java.awt.*;

/**
 * Created by alin.timu on 8/13/2014.
 */
public class ImageUtils {
    public static Image resizeImage(final Image image, final Integer width, final Integer height) {
        if (image == null) {
            return null;
        }

        if (width == null) {
            return image;
        }

        if (height == null) {
            return image.getScaledInstance(width, width, Image.SCALE_SMOOTH);
        }

        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
