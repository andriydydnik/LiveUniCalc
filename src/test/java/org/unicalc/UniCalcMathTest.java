package org.unicalc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UniCalcMathTest {

    @Test
    public void testAddInt() {
        //  Given

        UniCalc calc = new UniCalc();

        //  When

        int result = calc.add(1, 2);

        //  Then

        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void testAddFloat() {
        //  Given

        UniCalc calc = new UniCalc();

        //  When

        int result = calc.add(1f, 2f);

        //  Then

        int expected = 3;
        assertEquals(expected, result);
    }

    @Test
    public void testDivInt() {
        //  Given

        UniCalc calc = new UniCalc();

        //  When

        int result = calc.divInt(1, 2);

        //  Then

        int expected = 1 / 2;
        assertEquals(expected, result);
    }

    @Test
    public void testDivByZeroInt() {
        //  Given

        UniCalc calc = new UniCalc();

        //  When & Then

        int expected = 3;
        assertThrows(IllegalArgumentException.class,
                () -> calc.divInt(1, 0));
    }

    @Test
    public void testDivFloat() {
        //  Given

        UniCalc calc = new UniCalc();

        //  When

        int result = calc.divFloat(1f, 2f);

        //  Then

        int expected = Math.round(1f / 2f);
        assertEquals(expected, result);
    }

    @Test
    public void testDivByZeroFloat(){
        //  Given

        UniCalc calc = new UniCalc();

        //  When & Then

        int expected = 3;
        assertThrows(IllegalArgumentException.class,
                () -> calc.divFloat(1f, 0f));
    }

    public void testVoid(){}
}
