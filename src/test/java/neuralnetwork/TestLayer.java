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
public class TestLayer {
    
    public static NeuralNetwork neural;
    
    public static InputFunction inputFunction;
    
    public static OutputFunction outputFunction;
    
    @BeforeClass
    public static void initNetwork() {
        neural = new NeuralNetwork();
        int[] numberLayers = {2, 1};
        inputFunction = new LinearCombination();
        outputFunction = new Sigmoid();
        neural.init(0, numberLayers, inputFunction, outputFunction);
    }
    
    @Test
    public void CreationLayerTest() {
        Layer layer = new Layer(neural, "hidden");
        assertNotEquals(null, layer);
        neural.reset();
        assertEquals("Layer #0", layer.getName());
    }
    
    @Test
    public void NeuronInLayerTest() {
        Layer layer = new Layer(neural, "hidden");
        layer.addNeuron(new Neuron(layer, inputFunction, outputFunction));
        assertEquals(1, layer.getNeurons().size());
        Neuron newNeuron = new Neuron(layer, inputFunction, outputFunction);
        layer.addNeuron(newNeuron);
        assertEquals(2, layer.getNeurons().size());
    }
}
