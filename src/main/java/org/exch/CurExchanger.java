package org.exch;

public interface CurExchanger {
    void init();

    int exchange(Currencies from, Currencies to, int value);
}
