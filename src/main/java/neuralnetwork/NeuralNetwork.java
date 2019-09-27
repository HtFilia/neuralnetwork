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

import input.InputFunction;
import java.util.ArrayList;
import java.util.List;
import output.OutputFunction;

/**
 *
 * @author Lucas HtFilia Lebihan
 */
public class NeuralNetwork {
    
    private Layer inputLayer;
    
    private List<Layer> hiddenLayers;
    
    private Layer outputLayer;
    
    public NeuralNetwork() {
        this.hiddenLayers = new ArrayList<>();
    }
    
    public List<Layer> getHiddenLayers() {
        return hiddenLayers;
    }
    
    public Layer getInputLayer() {
        return inputLayer;
    }
    
    public Layer getOutputLayer() {
        return outputLayer;
    }
    
    public void init(int numberLayers, int[] numberNeurons,
            Layer inputLayer,
            Layer outputLayer,
            InputFunction inputFunction, 
            OutputFunction outputFunction) {
        if (numberLayers + 2 != numberNeurons.length) {
            throw new IllegalArgumentException("Should have as much Layers as number of Neurons.");
        }
        if (inputFunction == null) {
            throw new IllegalArgumentException("Input Function can't be null");
        }
        if (outputFunction == null) {
            throw new IllegalArgumentException("Output Function can't be null");
        }
        if (inputLayer == null) {
            throw new IllegalArgumentException("Input Layer can't be null");
        }
        if (outputLayer == null) {
            throw new IllegalArgumentException("Output Layer can't be null");
        }
        
        setInputLayer(inputLayer);
        inputLayer.addNeurons(numberNeurons[0], inputFunction, outputFunction);
        for (int incrementLayers = 0; incrementLayers < numberLayers + 1; incrementLayers++) {
            Layer layer = new Layer(this, "hidden");
            layer.addNeurons(numberNeurons[incrementLayers + 1], inputFunction, outputFunction);
            hiddenLayers.add(layer);            
        }
        setOutputLayer(outputLayer);
        outputLayer.addNeurons(numberNeurons[numberLayers + 1], inputFunction, outputFunction);
        addBiases();
        connectLayers();
        randomizeWeights();
    }
    
    void setInputLayer(Layer layer) {
        this.inputLayer = layer;
    }
    
    void addHiddenLayer(Layer layer) {
        this.hiddenLayers.add(layer);
    }
    
    void setOutputLayer(Layer layer) {
        this.outputLayer = layer;
    }
    
    void setInputs(double[] inputs) {
        this.inputLayer.setInputs(inputs);
    }
    
    private void connectLayers() {
        
        // Input Layer to Hidden Layer
        inputLayer.connectOutLayer(hiddenLayers.get(0));
        
        // Hidden Layers between themselves
        for (int compteur = 0; compteur < hiddenLayers.size() - 2; compteur++) {
            hiddenLayers.get(compteur).connectOutLayer(hiddenLayers.get(compteur + 1));
        }
        
        // Hidden Layer to Output Layer
        hiddenLayers.get(hiddenLayers.size() - 1).connectOutLayer(outputLayer);
    }
    
    private void randomizeWeights() {
        
        // Input Layer 
        inputLayer.getNeurons().forEach((Neuron neuron) -> {
            neuron.getOutputConnections().forEach((connection) -> {
                connection.getWeight().randomize();
            });
        });
        
        // Hidden Layers
        hiddenLayers.forEach((layer) -> {
            layer.getNeurons().forEach((Neuron neuron) -> {
                neuron.getOutputConnections().forEach((connection) -> {
                    connection.getWeight().randomize();
                });
            });
        });
        
        // No need for Output Layer cause already done
    }
    
    public void calculateValues() {
        
        // No need for Input Layer
        
        // Hidden Layers
        hiddenLayers.forEach((layer) -> {
            layer.getNeurons().forEach((Neuron neuron) -> {
                neuron.calculateValue();
            });
        });
        
        // Output Layer
        outputLayer.calculateValues();
    }
    
    private void addBiases() {
        //TODO
    }
    
    private void calculateErrors(double[] expected) {
        calculateOutputErrors(expected);
        calculateHiddenErrors();
    }
    
    private void calculateOutputErrors(double[] expected) {
        for (int compteur = 0; compteur < outputLayer.getNeurons().size() - 1; compteur++) {
            Neuron neuron = outputLayer.getNeurons().get(compteur);
            double newError = neuron.getOutputFunction().getDerivativeValue(neuron.getInput()) *
                    (neuron.getActivation() - expected[compteur]);
            outputLayer.getNeurons().get(compteur).setError(newError);
        }
    }
    
    private void calculateHiddenErrors() {
        for (int countLayer = hiddenLayers.size(); countLayer == 0; countLayer--) {
            Layer currentLayer = hiddenLayers.get(countLayer);
            for (int countNeuron = 0; countNeuron < currentLayer.getNeurons().size() - 1; countNeuron++) {
                Neuron currentNeuron = currentLayer.getNeurons().get(countNeuron);
                double[] weights = currentNeuron.getOutputWeights();
                double[] errors = currentNeuron.getOutputErrors();
                double newError = currentNeuron.getOutputFunction().getDerivativeValue(currentNeuron.getInput()) *
                        currentNeuron.getInputFunction().getValue(errors, weights);
                currentLayer.getNeurons().get(countNeuron).setError(newError);
            }
        }
    }
    
    public void setInputValues(double[] inputs) {
        if (inputs.length != inputLayer.getNeurons().size()) {
            throw new IllegalArgumentException("Should have same number of Neurons than inputs.");
        }
        for (int compteur = 0; compteur < inputs.length; compteur++) {
            inputLayer.getNeurons().get(compteur).setActivation(inputs[compteur]);
        }
    }
    
    private void resetInputLayer() {
        inputLayer.reset();
    }
    
    private void resetOutputLayer() {
        outputLayer.reset();
    }
    
    private void resetHiddenLayers() {
        hiddenLayers.forEach((layer) -> {
            layer.reset();
        });
    }
    
    public void reset() {
        resetInputLayer();
        resetHiddenLayers();
        resetOutputLayer();
    }
}
