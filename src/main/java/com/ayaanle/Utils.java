import impl.UtilsImpl;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageWriteParam;

public class Utils extends ImageWriteParam implements UtilsImpl
{
    int num_threads;
    int num_cpus;

    long tick_count;
    public static final int MIN_VERSION = Core.VERSION_MINOR;
    public static final String VERSION = Core.VERSION;

    // image essential parameters

    int compression;

    COMPRESSION_TYPE compression_type;

    @Override
    public int image_info(Imgcodecs imgcodecs) {
        return 0;
    }

    @Override
    public boolean system_compatibility(Core core) {
        return false;
    }

    @Override
    public int image_info(String compression, String dim) {
        /*ImageWriteParam img_params;
        img_params = new ImageWriteParam();
        switch (compression_type)
        {
            case JPG:
                //"jpg";
                img_params.getCompressionType();
                break;
            case BMP:
                //"bmp";
                img_params.getCompressionType();
                break;
            case  PNG:
                //"PNG"
                img_params.getCompressionType();
            case TIFF:
                //"tiff"
                img_params.getCompressionType();
        }*/
        return 0;
    }

    public enum COMPRESSION_TYPE
    {
        JPG,
        PNG,
        TIFF,
        BMP,
    }

    @Override
    public boolean system_compatibility()
    {
        if(Core.VERSION_MAJOR <= MIN_VERSION)
            return true;
        return true;
    }

    @Override
    public String log_spec() {
        num_threads = Core.getNumThreads();
        num_cpus = Core.getNumberOfCPUs();
        tick_count = Core.getCPUTickCount();
        return String.valueOf(num_threads + "\n" + num_cpus);
    }
}
