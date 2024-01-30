
import impl.TrainNNImpl;
import org.opencv.core.MatOfInt;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Layer;
import org.opencv.dnn.Net;

import java.io.File;
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
    public boolean model_exists(String model_path)
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
        if (model_exists(path)) {
            if (f.canRead() && p.endsWith(".h5"))
                return f.getName();
            else System.out.println("the file that have specified doesn't seem to be a model");
        }
        else throw new FileNotFoundException();
        return "";
    }
    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void get_validation_info(String path)
    {
        try {
            String mod =  model_info(path);
            if(!mod.isEmpty())
                System.out.println("model : " + mod);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String NNTrain(String path)
    {
        if (model_exists(path)) {
            Net net = Dnn.readNetFromONNX(path);
            if (!(net.empty())) {
                outliers = net.getUnconnectedOutLayers();
                List<String> layer_names = net.getLayerNames();
                for (int i = 0; i < layer_names.size(); i++) {
                    System.out.println(net.getParam(layer_names.get(i)));
                    return layer_names.get(i);
                }
            }
        }
        return "null";
    }
    String LayerID(String path)
    {
        List<Layer> layers;
        int layer_id = 0;
        if (model_exists(path)) {
            Net net = Dnn.readNetFromONNX(path);
            if (!(net.empty())) {
                outliers = net.getUnconnectedOutLayers();
                List<String>layer_names = net.getLayerNames();
                for(int i = 0; i < layer_names.size(); i++)
                {
                    layer_id =  net.getLayerId(layer_names.get(i));
                }


            }
        }
        return String.valueOf(layer_id);
    }

}
