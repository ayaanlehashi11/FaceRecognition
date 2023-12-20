package impl;

import org.opencv.core.Mat;
import org.opencv.dnn.Net;
import org.opencv.ml.TrainData;

import java.io.File;
import java.util.ArrayList;

public interface NNParamsBuilder{

    public Mat[] getNNParams();

    public void log_trainparams();

    public Mat[] getWeights();
    public void log_weights();
}
