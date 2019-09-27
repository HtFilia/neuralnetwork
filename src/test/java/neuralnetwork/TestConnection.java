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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import output.OutputFunction;
import output.Sigmoid;

/**
 *
 * @author Fibo
 */
public class TestConnection {
    
    private static NeuralNetwork neural;
    
    private static InputFunction inputFunction;
    
    private static OutputFunction outputFunction;
    
    private static Layer layer;
    
    @BeforeClass
    public static void initNeuralNetwork() {
        neural = new NeuralNetwork();
        layer = new Layer(neural, "input");
        inputFunction = new LinearCombination();
        outputFunction = new Sigmoid();
    }
    
    @Test
    public void CreationConnectionTest() {
        Neuron inNeuron = new Neuron(layer, inputFunction, outputFunction);
        Neuron outNeuron = new Neuron(layer, inputFunction, outputFunction);
        Connection connection = new Connection(inNeuron, outNeuron);
        assertNotEquals(null, connection);
        assertEquals(inNeuron, connection.getInNeuron());
        assertEquals(outNeuron, connection.getOutNeuron());
        assertEquals(0, connection.getWeight().getError(), 0);
        assertEquals(0, connection.getWeight().getValue(), 0);
        connection.getWeight().randomize();
        assertNotEquals(0, connection.getWeight().getValue(), 0);
        connection.getWeight().setValue(10);
        connection.getWeight().setError(8);
        assertEquals(8, connection.getWeight().getError(), 0);
        assertEquals(10, connection.getWeight().getValue(), 0);
    }
    
    @Test
    public void ConnectNeuronsTest() {
        Neuron inNeuron = new Neuron(layer, inputFunction, outputFunction);
        Neuron outNeuron = new Neuron(layer, inputFunction, outputFunction);
        inNeuron.connectOutNeuron(outNeuron);
        assertEquals(1, inNeuron.getOutputConnections().size());
        assertEquals(1, outNeuron.getInputConnections().size());
        assertEquals(inNeuron.getOutputConnections().get(0), outNeuron.getInputConnections().get(0));
    }
    
    @Test
    public void ConnectNeuronToLayerTest() {
//        Layer layer2 = new Layer(neural);
//        Layer layer3 = new Layer(neural);
//        Neuron inNeuron = new Neuron(layer2, inputFunction, outputFunction);
//        Neuron outNeuron = new Neuron(layer3, inputFunction, outputFunction);
//        inNeuron.connectOutLayer(layer3);
//        assertEquals(1, inNeuron.getOutputConnections().size());
//        assertEquals(1, outNeuron.getInputConnections().size());
//        assertEquals(inNeuron.getOutputConnections().get(0), outNeuron.getInputConnections().get(0));
        Layer layer2 = new Layer(neural, "input");
        Layer layer3 = new Layer(neural, "output");
        Neuron inNeuron = new Neuron(layer2, inputFunction, outputFunction);
        Neuron outNeuron = new Neuron(layer3, inputFunction, outputFunction);
        layer2.addNeuron(inNeuron);
        layer3.addNeuron(outNeuron);
        inNeuron.connectOutLayer(layer3);
        inNeuron.showOutputConnections();
    }
}
