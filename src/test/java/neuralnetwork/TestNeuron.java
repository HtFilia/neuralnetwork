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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import output.OutputFunction;
import output.Sigmoid;

/**
 *
 * @author Fibo
 */
public class TestNeuron {
    
   public static NeuralNetwork neural;
   
   public static Layer layer;
   
   
  
   public static InputFunction inputFunction;
   
   public static OutputFunction outputFunction;
   
   @BeforeClass
   public static void initNetworkAndLayer() {
       neural = new NeuralNetwork();
       int[] numberNeurons = {2, 1};
       neural.init(0, numberNeurons, inputFunction, outputFunction);
       inputFunction = new LinearCombination();
       outputFunction = new Sigmoid();
   }
   
   @Test
   public void CreationNeuronTest() {
       Neuron neuron = new Neuron(layer, inputFunction, outputFunction);
       assertNotEquals(null, neuron);
       assertEquals("Neuron #0, Layer #0", neuron.getName());
   }
   
   @Test
   public void ConnectionBetweenNeuronsTest() {
       layer.reset();
       Neuron inNeuron = new Neuron(layer, inputFunction, outputFunction);
       Neuron outNeuron = new Neuron(layer, inputFunction, outputFunction);
       layer.addNeuron(inNeuron);
       layer.addNeuron(outNeuron);
       System.out.println(layer.getNeurons().size());
       assertFalse(inNeuron.hasInputConnectionFrom(outNeuron));
       assertFalse(inNeuron.hasOutputConnectionTo(outNeuron));
       inNeuron.connectOutNeuron(outNeuron);
       assertTrue(inNeuron.hasOutputConnectionTo(outNeuron));
       assertFalse(inNeuron.hasInputConnectionFrom(outNeuron));
   }
   
   @Test
   public void ConnectionNeuronToLayerTest() {
       layer.reset();
       System.out.println(layer.getNeurons().size());
   }
}
