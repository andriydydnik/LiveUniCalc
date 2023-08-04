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
        when(exch.exchange(any(), any(), anyInt())).thenReturn(100);

        UniCalc calc = new UniCalc(exch);

        //  When

        int result = calc.exchange(Currencies.UAH, 1);

        //  Then

        int expected = 100;
        assertEquals(expected, result);
    }

    @Test
    public void testUnknownCurrency() {
        //  Given
        CurExchanger exch = mock(CurExchanger.class);

        UniCalc calc = new UniCalc(exch);

        //  When & Then

        assertThrows(UknownCurrencyException.class,
                () -> calc.exchange(Currencies.UNDEFINED, 1));
    }

    @Test
    public void testCESingleInitializing() throws UknownCurrencyException {
        //  Given

        CurExchanger exch = mock(CurExchanger.class);
        doNothing().when(exch).init();  //  Тут ми мокаємо поведінку ініціатора для Ексченджера, який є нашою залежністю

        UniCalc calc = new UniCalc(exch);

        //  When - Так як, нам потрібно пеерсвідчиися що ініціалізація Ексченджера виконується тільки один раз, ми
        //  робимо два виклики методів, які потенційно можуть ініціалізувати Ексченджер, і нам не важливай зараз
        //  результат цих методів

        calc.exchange(Currencies.USD, 1);
        calc.exchangeBack(Currencies.EUR, 1);

        //  Then - Тут ми перевіряємо ща метод ініціалізації Ексченджера викликаний тільки один раз, не зважаючи на те
        //  скільки разів ми викликали залежні від нього методи нашого юніта

        Mockito.verify(exch, times(1))
                .init();
    }
}
