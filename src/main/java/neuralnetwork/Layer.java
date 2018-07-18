/*
 * The MIT License
 *
 * Copyright 2018 Lucas Lebihan <HtFilia @ github.com>.
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
import java.util.ArrayList;
import java.util.List;
import output.OutputFunction;

/**
 *
 * @author Lucas HtFilia Lebihan
 */
public class Layer {
    
    private String name;
    
    private NeuralNetwork neuralNetwork;
    
    private List<Neuron> neurons;
    
    public Layer(NeuralNetwork neuralNetwork) {
        if (neuralNetwork == null) {
            throw new IllegalArgumentException("Neural Network can't be null.");
        }
        this.neuralNetwork = neuralNetwork;
        this.neurons = new ArrayList<>();
        String name = "Layer #" + neuralNetwork.getHiddenLayers().size();
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Neuron> getNeurons() {
        return neurons;
    }
    
    public void addNeuron(InputFunction inputFunction, OutputFunction outputFunction) {
        neurons.add(new Neuron(this, inputFunction, outputFunction));
    }
    
    public void addNeurons(int numberNeurons, InputFunction inputFunction, OutputFunction outputFunction) {
        for (int count = 0; count < numberNeurons; count++) {
            addNeuron(inputFunction, outputFunction);
        }
    }
    
    protected void connectOutLayer(Layer outLayer) {
        neurons.forEach((Neuron neuron) -> {
            neuron.connectOutLayer(outLayer);
        });
    }
    
    protected void calculateValues() {
        neurons.forEach((neuron) -> {
            neuron.calculateValue();
        });
    }
}
