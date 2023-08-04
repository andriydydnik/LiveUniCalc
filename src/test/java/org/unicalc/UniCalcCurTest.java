package org.unicalc;

import org.exception.UknownCurrencyException;
import org.exch.CurExchanger;
import org.exch.Currencies;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class UniCalcCurTest {

    @Test
    public void testCurExchange() throws UknownCurrencyException {
        //  Given

        CurExchanger exch = mock(CurExchanger.class);
        when(exch.exchange(any(), anyInt())).thenReturn(100);

        UniCalc calc = new UniCalc();

        //  When

        int result = calc.exchange(exch, Currencies.UAH, 1);

        //  Then

        int expected = 100;
        assertEquals(expected, result);
    }

    @Test
    public void testUnknownCurrency(){
        //  Given

        UniCalc calc = new UniCalc();

        //  When & Then

        assertThrows(UknownCurrencyException.class,
                ()->calc.exchange(null, Currencies.UNDEFINED, 1));
    }

    @Test
    public void testCESingleInitializing(){
        //  Given

        CurExchanger exch = mock(CurExchanger.class);
        when(exch.init()).thenReturn(100);

        UniCalc calc = new UniCalc();

        //  When

        int result = calc.exchange(exch, Currencies.UAH, 1);

        //  Then

        int expected = 100;
        assertEquals(expected, result);

        Mockito.verify(calc, times(1))
                .init();
    }
}
