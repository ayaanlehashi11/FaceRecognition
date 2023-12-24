import impl.NNParamsBuilder;


import java.util.ArrayList;

import impl.TrainNNImpl;
import org.opencv.core.Mat;
import org.opencv.dnn.*;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.TrainData;


public class NNParams {

   // Net net;
    NNParams np;


    protected NNParams() {

    }

    public enum LOSS
    {
        MSE,
        MSA,
        Accuracy,
    }

    public enum OPT
    {
        Adaboost,
        AdaGrad,
        RMSProp,
    }

}

class Train extends TrainData implements NNParamsBuilder
{

    protected Train(long addr) {
        super(addr);
    }

    Train train;
    Mat samples , train_samples , test_samples ;
    Mat weight , samples_weight , train_weight , test_weight;

    Mat[] mat;
    @Override
    public Mat[] getNNParams()
    {
        train = new Train(143);
        train_samples = train.getTrainSamples();
        test_samples = train.getTestSamples();
        samples = train.getSamples();
        mat = new Mat[]{train_samples, test_samples, samples};
        return mat;
    }
    @Override
    public void log_trainparams()
    {
        Mat [] m = getNNParams();
        for(int i = 1;i < m.length;i++)
        {
            System.out.println("dim: " +m[i].dims());
            System.out.println("rows: " + m[i].rows());
            System.out.println("col: "+ m[i].cols());
        }
    }

    @Override
    public Mat[] getWeights()
    {
        Mat []matrix;
        train = new Train(143);
        test_weight = train.getTestSampleWeights();
        train_weight = train.getTrainSampleWeights();
        samples_weight = train.getSampleWeights();
        matrix = new Mat[]{train_weight ,  test_weight , samples_weight};
        return matrix;
    }
    @Override
    public void log_weights()
    {

    }

}
