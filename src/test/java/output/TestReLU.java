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
package output;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Fibo
 */
public class TestReLU {
    
    @Test
    public void ValueTest() {
        OutputFunction outputFunction = new ReLU();
        assertEquals(0, outputFunction.getValue(0), 0);
        assertEquals(0, outputFunction.getValue(-1), 0);
        assertEquals(1, outputFunction.getValue(1), 0);
        assertEquals(4, outputFunction.getValue(4), 0);
    }
    
    @Test
    public void DerivativeTest() {
        OutputFunction outputFunction = new ReLU();
        assertEquals(0, outputFunction.getDerivativeValue(0), 0);
        assertEquals(0, outputFunction.getDerivativeValue(-1), 0);
        assertEquals(1, outputFunction.getDerivativeValue(1), 0);
        assertEquals(1, outputFunction.getDerivativeValue(4), 0);
    }
}
