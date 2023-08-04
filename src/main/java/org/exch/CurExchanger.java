package org.exch;

public interface CurExchanger {
    int init();

    int exchange(Currencies cur, int src);
}
