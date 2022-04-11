package org.mineplugin.locusazzurro.icaruswings.utils;

import java.util.function.Supplier;

/**
 * @author DustW
 **/
public class IWLazy<TYPE> {
    Supplier<TYPE> provider;
    boolean init;
    TYPE value;

    private IWLazy(Supplier<TYPE> provider) {
        this.provider = provider;
    }

    public static <TYPE> IWLazy<TYPE> of(Supplier<TYPE> provider) {
        return new IWLazy<>(provider);
    }

    public TYPE get() {
        if (!init) {
            value = provider.get();
            init = true;
        }

        return value;
    }
}
