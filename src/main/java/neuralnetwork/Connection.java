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

import java.util.Objects;

/**
 *
 * @author Lucas HtFilia Lebihan
 */
public class Connection {
    
    private double weight;
    
    private Neuron inNeuron;
    
    private Neuron outNeuron;
    
    public Connection(Neuron inNeuron, Neuron outNeuron) {
        if (inNeuron == null) {
            throw new IllegalArgumentException("Input Neuron can't be null.");
        }
        if (outNeuron == null) {
            throw new IllegalArgumentException("Output Neuron can't be null.");
        }
        
        this.inNeuron = inNeuron;
        this.outNeuron = outNeuron;
    }
    
    public Connection(Neuron inNeuron, Neuron outNeuron, double weight) {
        if (inNeuron == null) {
            throw new IllegalArgumentException("Input Neuron can't be null.");
        }
        if (outNeuron == null) {
            throw new IllegalArgumentException("Output Neuron can't be null.");
        }
        
        this.inNeuron = inNeuron;
        this.outNeuron = outNeuron;
        this.weight = weight;
    }
    
    public double getWeightedInput() {
        return weight * this.inNeuron.getOutput();
    }
    
    public Neuron getInNeuron() {
        return this.inNeuron;
    }
    
    public Neuron getOutNeuron() {
        return this.outNeuron;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Connection)) {
            return false;
        }
        
        Connection connection = (Connection) o;
        if (this.inNeuron.equals(connection.getInNeuron())) {
            if (this.outNeuron.equals(connection.getOutNeuron())) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.inNeuron);
        hash = 17 * hash + Objects.hashCode(this.outNeuron);
        return hash;
    }
}
