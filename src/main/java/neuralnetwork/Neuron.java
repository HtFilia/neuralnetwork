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
    
    private List<Connection> inConnections;
    
    private List<Connection> outConnections;
    
    private InputFunction inFunction;
    
    private OutputFunction outFunction;
    
    private double inputTotal;
    
    private double output;
    
    private double error;
    
    public Neuron() {
        this.inConnections = new ArrayList<>();
        this.outConnections = new ArrayList<>();
        this.inFunction = new LinearCombination();
        this.outFunction = new ReLU();
    }
    
    public Neuron(InputFunction inFunction, OutputFunction outFunction) {
        if (inFunction == null) {
            throw new IllegalArgumentException("Input Function can't be null.");
        }
        if (outFunction == null) {
            throw new IllegalArgumentException("Output Function can't be null.");
        }
        
        this.inConnections = new ArrayList<>();
        this.outConnections = new ArrayList<>();
        this.inFunction = inFunction;
        this.outFunction = outFunction;
    }
    
    public double getOutput() {
        return this.output;
    }
    
    public void addConnection(Layer layer) {
        layer.getNeurons().forEach((neuron) -> {
            this.addConnection(neuron);
        });
    }
    
    public void addConnection(Neuron neuron) {
        Connection connection = new Connection(this, neuron);
        this.addOutConnection(connection);
        neuron.addInConnection(connection);
    }
    
    public void addInConnection(Connection connection) {
        this.inConnections.add(connection);
    }
    
    public void addOutConnection(Connection connection) {
        this.outConnections.add(connection);
    }
    
    public Connection getConnection(final Neuron neuron) {
        Connection connection = null;
        for (Connection inConnection : this.inConnections) {
            //TODO
        }
        return connection;
    }

        
    public void calculate() {
        this.inputTotal = this.inFunction.getValue(this.inConnections);
        this.output = this.outFunction.getValue(this.inputTotal);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.layer);
        hash = 89 * hash + Objects.hashCode(this.inConnections);
        hash = 89 * hash + Objects.hashCode(this.outConnections);
        hash = 89 * hash + Objects.hashCode(this.inFunction);
        hash = 89 * hash + Objects.hashCode(this.outFunction);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Neuron other = (Neuron) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.layer, other.layer);
    }
}
