/*
 * The MIT License
 *
 * Copyright 2018 Fibo.
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
package input;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Fibo
 */
public class TestLinearCombination {
    
    @Test
    public void ValueTest() {
        double[] inputs1 = {0, 0, 0};
        double[] inputs2 = {0, 0.1, 0.8};
        double[] weights1 = {0, 0, 0};
        double[] weights2 = {0.4, 0.2, 0.5};
        InputFunction inputFunction = new LinearCombination();
        assertEquals(0, inputFunction.getValue(inputs1, weights1), 0.1);
        assertEquals(0.4, inputFunction.getValue(inputs2, weights2), 0.1);
    }
}
