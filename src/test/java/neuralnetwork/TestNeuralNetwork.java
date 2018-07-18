/*
 * The MIT License
 *
 * Copyright 2018 Fibo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package neuralnetwork;

import input.InputFunction;
import input.LinearCombination;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import output.OutputFunction;
import output.Sigmoid;

/**
 *
 * @author Fibo
 */
public class TestNeuralNetwork {
    
    private static InputFunction inputFunction;
    
    private static OutputFunction outputFunction;
    
    @BeforeClass
    public static void InitFunctions() {
        inputFunction = new LinearCombination();
        outputFunction = new Sigmoid();
    }
    
    @Test
    public void CreationNeuralNetworkTest() {
        NeuralNetwork neural = new NeuralNetwork();
        assertNotEquals(null, neural);
    }
    
    @Test
    public void RandomizedWeigthsTest() {
        NeuralNetwork neural = new NeuralNetwork();
        Layer inputLayer = new Layer(neural);
        Layer outputLayer = new Layer(neural);
        int[] numberNeurons = {4, 5};
        neural.init(2, numberNeurons, inputLayer, outputLayer, inputFunction, outputFunction);
        neural.getHiddenLayers().forEach((Layer layer) -> {
            layer.getNeurons().forEach((Neuron neuron) -> {
                neuron.getOutputConnections().forEach((Connection connection) -> {
                    assertNotEquals(0, connection.getWeight().getValue());
                });
            });
        });
    }
    
    @Test
    public void ErrorsTest() {
        NeuralNetwork neural = new NeuralNetwork();
        Layer inputLayer = new Layer(neural);
        inputLayer.addNeurons(2, inputFunction, outputFunction);
        Layer outputLayer = new Layer(neural);
        outputLayer.addNeuron(inputFunction, outputFunction);
        int[] numberNeurons = {2};
        double[] inputs = {0.0, 1.0};
        neural.init(1, numberNeurons, inputLayer, outputLayer, inputFunction, outputFunction);
        neural.setInputLayer(inputs);
        neural.calculateValues();
        double[] expected = {1};
        neural.calculateErrors(expected);
        System.out.println("output: " + neural.getOutputLayer().getNeurons().get(0).getActivation());
        System.out.println("error: " + neural.getOutputLayer().getNeurons().get(0).getError());
    }
}
