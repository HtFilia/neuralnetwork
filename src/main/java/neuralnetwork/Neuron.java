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

import output.OutputFunction;
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
    
    private List<Connection> inputConnections;
    
    private List<Connection> outputConnections;
    
    private double input;
    
    private double activation;
    
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
    
    public double getActivation() {
        return activation;
    }
    
    public void setActivation(double activation) {
        this.activation = activation;
    }
    
    public double getInput() {
        return input;
    }
    
    public double getError() {
        return error;
    }
    
    public void setError(double error) {
        this.error = error;
    }
    
    public List<Connection> getInputConnections() {
        return inputConnections;
    }
    
    public List<Connection> getOutputConnections() {
        return outputConnections;
    }
    
    public InputFunction getInputFunction() {
        return inputFunction;
    }
    
    public OutputFunction getOutputFunction() {
        return outputFunction;
    }
    
    public void connectOutNeuron(Neuron outNeuron) {
        Connection connection = new Connection(this, outNeuron);
        outputConnections.add(connection);
        outNeuron.getInputConnections().add(connection);
    }
    
    public void connectOutLayer(Layer outLayer) {
        for (int countNeuron = 0; countNeuron < outLayer.getNeurons().size() - 1; countNeuron++) {
            connectOutNeuron(outLayer.getNeurons().get(countNeuron));
        }
    }
    
    public double[] getOutputWeights() {
        double[] weights = new double[outputConnections.size() - 1];
        for (int currentConnection = 0; currentConnection < outputConnections.size() - 1; currentConnection++) {
            weights[currentConnection] = outputConnections.get(currentConnection).getWeight().getValue();
        }
        return weights;
    }
    
    public double[] getOutputErrors() {
        double[] errors = new double[outputConnections.size() - 1];
        for (int currentConnection = 0; currentConnection < outputConnections.size() - 1; currentConnection++) {
            Neuron outNeuron = outputConnections.get(currentConnection).getOutNeuron();
            errors[currentConnection] = outNeuron.getError();
        }
        return errors;
    }
    
    protected void calculateValue() {
        try {
            double[] activations = new double[inputConnections.size() - 1];
            double[] weights = new double[inputConnections.size() - 1];
            for (int compteur = 0; compteur < inputConnections.size() - 1; compteur++) {
                activations[compteur] = inputConnections.get(compteur).getInNeuron().getActivation();
                weights[compteur] = inputConnections.get(compteur).getWeight().getValue();
            }
            input = inputFunction.getValue(activations, weights);
            activation = outputFunction.getValue(input);
        } catch (Exception e) {
            // Nothing
        }
    }
}
