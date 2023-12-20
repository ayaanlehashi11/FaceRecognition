package impl;

import java.io.FileNotFoundException;

public interface TrainNNImpl {

    public boolean check_model(String model_path);
    public String model_info(String path) throws FileNotFoundException;
    public void get_validation_info(String path);
    public int NNTrain(boolean model_exitst ,  String path);
}
