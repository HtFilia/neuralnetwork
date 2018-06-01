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
package main;

import output.ReLU;
import output.OutputFunction;
import input.LinearCombination;
import input.InputFunction;
import java.util.ArrayList;
import java.util.List;

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
        this.name = "";
        this.layer = null;
        this.inConnections = new ArrayList<>();
        this.outConnections = new ArrayList<>();
        this.inFunction = new LinearCombination();
        this.outFunction = new ReLU();
        this.inputTotal = 0;
        this.output = 0;
        this.error = 0;
    }
    
    public Neuron(InputFunction inFunction, OutputFunction outFunction) {
        if (inFunction == null) {
            throw new IllegalArgumentException("Input Function can't be null.");
        }
        if (outFunction == null) {
            throw new IllegalArgumentException("Output Function can't be null.");
        }
        this.name = "";
        this.layer = null;
        this.inConnections = new ArrayList<>();
        this.outConnections = new ArrayList<>();
        this.inFunction = inFunction;
        this.outFunction = outFunction;
        this.inputTotal = 0;
        this.output = 0;
        this.error = 0;
    }
    
    public double getOutput() {
        return this.output;
    }
    
    public void calculate() {
        this.inputTotal = this.inFunction.getValue(this.inConnections);
        this.output = this.outFunction.getValue(this.inputTotal);
    }
}
