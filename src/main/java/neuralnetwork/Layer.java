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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas HtFilia Lebihan
 */
public class Layer {
    
    private String name;
    
    private List<Neuron> neurons;
    
    public Layer() {
        this.neurons = new ArrayList<>();
    }
    
    public void addNeuron(Neuron neuron) {
        this.neurons.add(neuron);
    }
    
    public void addNeurons(List<Neuron> neurons) {
        neurons.forEach((neuron) -> {
            this.neurons.add(neuron);
        });
    }
    
    public List<Neuron> getNeurons() {
        return this.neurons;
    }
    
    public Neuron getNeuron(int index) {
        return this.neurons.get(index);
    }
    
    public void addConnection(Layer layer) {
        this.neurons.forEach((neuron) -> {
            neuron.addConnection(layer);
        });
    }
    
    public void addConnection(Neuron outNeuron) {
        this.neurons.forEach((neuron) -> {
            neuron.addConnection(outNeuron);
        });
    }
    
    public void calculate() {
        this.neurons.forEach((neuron) -> {
            neuron.calculate();
        });
    }
}
