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

import output.ReLU;
import output.OutputFunction;
import input.LinearCombination;
import input.InputFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lucas HtFilia Lebihan
 */
public class Neuron {
    
    private String name;
    
    private Layer layer;
    
    private double output;
    
    private List<Connection> inputConnections;
    
    private List<Connection> outputConnections;
    
    private double error;
    
    private InputFunction inputFunction;
    
    private OutputFunction outputFunction;
    
    public Neuron(Layer layer, InputFunction inputFunction, 
            OutputFunction outputFunction) {
        if (layer == null) {
            throw new IllegalArgumentException("Layer can't be null.");
        }
        if (inputFunction == null) {
            throw new IllegalArgumentException("Input Function can't be null.");
        }
        if (outputFunction == null) {
            throw new IllegalArgumentException("Output Function can't be null.");
        }
        this.layer = layer;
        this.inputFunction = inputFunction;
        this.outputFunction = outputFunction;
        this.inputConnections = new ArrayList<>();
        this.outputConnections = new ArrayList<>();
        String name = "Neuron #" + layer.getNeurons().size() + ", " + layer.getName();
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
