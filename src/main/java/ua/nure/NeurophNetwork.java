package ua.nure;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import java.util.ArrayList;
import java.util.List;

public class NeurophNetwork {


    private final NeuralNetwork neuralNetwork;

    public NeurophNetwork() {
        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 58, 10, 10, 10, 10, 2);
    }

    public void trainNN() {
        DataSet dataSet = new DataSet(100, 2);
    }
}
