package impl;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.*;

public interface UtilsImpl
{
    public int image_info(Imgcodecs imgcodecs);
    public boolean system_compatibility(Core core);

    int image_info(String compression, String dim);

    boolean system_compatibility();

    public String log_spec();

}
