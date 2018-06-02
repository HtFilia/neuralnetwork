/*
 * The MIT License
 *
 * Copyright 2018 Lucas HtFilia Lebihan.
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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas HtFilia Lebihan
 */
public class NeuralNetwork {
    
    private List<Layer> layers;
    
    private List<Neuron> inNeurons;
    
    private List<Neuron> outNeurons;
    
    public NeuralNetwork() {
        this.layers = new ArrayList<>();
        this.inNeurons = new ArrayList<>();
        this.outNeurons = new ArrayList<>();
    }
    
    public void addLayer(Layer layer) {
        this.layers.add(layer);
    }
    
    public void addLayers(List<Layer> layers) {
        layers.forEach((layer) -> {
            this.layers.add(layer);
        });
    }
    
    public void addInputNeuron(Neuron neuron) {
        this.inNeurons.add(neuron);
    }
    
    public void addInputNeurons(List<Neuron> neurons) {
        neurons.forEach((neuron) -> {
            this.inNeurons.add(neuron);
        });
    }
    
    public void addOutputNeuron(Neuron neuron) {
        this.outNeurons.add(neuron);
    }
    
    public void addOutputNeurons(List<Neuron> neurons) {
        neurons.forEach((neuron) -> {
            this.outNeurons.add(neuron);
        });
    }
    
    public void addConnectionsToLayers() {
        if (this.inNeurons == null) {
            throw new NullPointerException("Can't connect to Input Neurons.");
        }
        if (this.outNeurons == null) {
            throw new NullPointerException("Can't connect to Output Neurons.");
        }
        if (this.layers == null) {
            throw new NullPointerException("Can't connect to Layers of Neural Network.");
        }
        
        for (int i = 0; i < this.layers.size() - 1; ++i) {
            this.layers.get(i).addConnection(this.layers.get(i+1));
        }
        this.inNeurons.forEach((neuron) -> {
            neuron.addConnection(this.layers.get(0));
        });
        this.outNeurons.forEach((neuron) -> {
            this.layers.get(this.layers.size()-1).addConnection(neuron);
        });
    }
}
