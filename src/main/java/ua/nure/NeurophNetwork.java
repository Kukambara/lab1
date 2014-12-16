package ua.nure;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class NeurophNetwork {


    private MultiLayerPerceptron neuralNetwork;

    public NeurophNetwork() {
        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 58, 10, 10, 10, 2);
    }

    public void trainNN(DataSet dataset) {
        neuralNetwork.learn(dataset);

        neuralNetwork.save("myMlPerceptron.nnet");
    }

    public void load() {
        neuralNetwork = (MultiLayerPerceptron) NeuralNetwork.createFromFile("myMlPerceptron.nnet");
    }
}
