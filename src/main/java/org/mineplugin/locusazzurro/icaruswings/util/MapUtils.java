package org.mineplugin.locusazzurro.icaruswings.util;

import java.util.HashMap;

public class MapUtils {

    public static class Builder {
        public static class PigeonMap<K,V> extends HashMap<K,V> {
            public PigeonMap<K,V> add(K key, V val ) {
                this.put( key, val ) ;
                return this ;
            } ;
        } ;
        public static <K,V> PigeonMap<K,V> add(K key, V val ) {
            return new PigeonMap<K,V>().add( key, val ) ;
        } ;
    } ;
}
