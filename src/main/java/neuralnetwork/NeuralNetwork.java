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
    
    private final List<Layer> hiddenLayers;
    
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
            Layer inputLayer, Layer outputLayer, InputFunction inputFunction, 
            OutputFunction outputFunction) {
        if (inputLayer == null) {
            throw new IllegalArgumentException("Input Layer can't be null.");
        }
        if (outputLayer == null) {
            throw new IllegalArgumentException("Output Layer can't be null.");
        }
        if (numberLayers != numberNeurons.length) {
            throw new IllegalArgumentException("Should have as much Layers as number of Neurons.");
        }
        if (inputFunction == null) {
            throw new IllegalArgumentException("Input Function can't be null");
        }
        if (outputFunction == null) {
            throw new IllegalArgumentException("Output Function can't be null");
        }
        
        for (int incrementLayers = 0; incrementLayers < numberLayers; incrementLayers++) {
            Layer layer = new Layer(this);
            for (int incrementNeurons = 0; incrementNeurons < numberNeurons[incrementLayers]; incrementNeurons++) {
                layer.getNeurons().add(new Neuron(layer, inputFunction, outputFunction));
            }
            hiddenLayers.add(layer);
        }
        this.inputLayer = inputLayer;
        this.outputLayer = outputLayer;
        connectLayers();
        randomizeWeights();
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
}
