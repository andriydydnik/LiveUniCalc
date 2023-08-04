package org.unicalc;

import org.exception.UknownCurrencyException;
import org.exch.CurExchanger;
import org.exch.Currencies;

import java.util.Arrays;
import java.util.List;

import static org.exch.Currencies.*;

public class UniCalc {
    List<Currencies> knownCurrencies = Arrays.asList(UAH, USD, EUR, GBF);
    Currencies defaultCurrencies = UAH;

    CurExchanger exch;
    boolean alreadyInitedExchanger;

    public UniCalc() {
    }

    public UniCalc(CurExchanger exch) {
        this.exch = exch;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int add(float a, float b) {
        return Math.round(a + b);
    }

    public int divInt(int a, int b) throws IllegalArgumentException {
        if (b == 0)
            throw new IllegalArgumentException();
        return a / b;
    }

    public int divFloat(float a, float b) throws IllegalArgumentException {
        if (b == 0)
            throw new IllegalArgumentException();
        return Math.round(a / b);
    }

    public int exchangeBack(Currencies cur, int value) throws UknownCurrencyException {
        if (!checkIfCurrencyIsKnown(cur))
            throw new UknownCurrencyException();

        initExchangerIfNeeded();
        return exch.exchange(defaultCurrencies, cur, value);
    }

    public int exchange(Currencies cur, int value) throws UknownCurrencyException {
        if (!checkIfCurrencyIsKnown(cur))
            throw new UknownCurrencyException();

        initExchangerIfNeeded();
        return exch.exchange(cur, defaultCurrencies, value);
    }

    private void initExchangerIfNeeded() {
        if (!alreadyInitedExchanger) {
            exch.init();
            alreadyInitedExchanger = true;
        }
    }

    private boolean checkIfCurrencyIsKnown(Currencies cur) {
        return knownCurrencies.contains(cur);
    }
}
