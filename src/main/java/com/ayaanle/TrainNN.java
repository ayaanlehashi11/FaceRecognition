
import impl.TrainNNImpl;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;

import java.io.File;
import java.io.FilePermission;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public class TrainNN  implements TrainNNImpl {
    public static int allowed_model_size;

    List<String> layers;
    MatOfInt outliers;
    public TrainNN(long add)
    {
        super();

    }
    File f;
    @Override
    public boolean check_model(String model_path)
    {
        f = new File(model_path);
        if(!f.exists())
            return false;
        return true;

    }

    @Override
    public String model_info(String path)throws FileNotFoundException
    {
        f = new File(path);
        Path p = f.toPath();
        if (check_model(path)) {
            if (f.canRead() && p.endsWith(".h5"))
                return f.getName();
       throw new FileNotFoundException();
        }
        return "";

    }
    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void get_validation_info(String path)
    {
        try {
            String mod =  model_info(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int NNTrain(boolean model_exist , String path)
    {
        Net net = Dnn.readNetFromTorch(path);
        if(!(net.empty())) {
            outliers = net.getUnconnectedOutLayers();
            net.getLayerNames();
        }
        return 0;
    }
}
