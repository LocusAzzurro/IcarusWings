package org.mineplugin.locusazzurro.icaruswings.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nanzhi_pigeon  - 菜鸡
 * @author LocusAzzurro   - 修正了若干个bug，提出需求
 */

public class BindList<K, V> {

    public interface Func<T, E> {
        void apply ( T t, E e ) ;
    }

    /** 用于查找表，找不到时返回一个空白的新表（新表不会被记录）
     * @see KVTab#key   - 存储表绑定的值
     * */
    public static class KVTab<K, V> {
        public final K key;
        private final List<V> SR = new ArrayList<>() ;
        public KVTab(K key ) {
            this.key = key ;
        }
        /** 放入值，不会避免重复
         * */
        @SafeVarargs
        public final KVTab<K, V> put(V... val ) {
            Collections.addAll(SR, val);
            return this ;
        }
        /** 放入值，会避免重复
         * */
        @SafeVarargs
        public final KVTab<K, V> set(V... val ) {
            for ( V v : val ) if ( !SR.contains(v) ) SR.add(v) ;
            return this ;
        }
        /** 遍历
         * @param callback   - void ( 表绑定的值, 表存储的所有值 ) -> { }
         * */
        public KVTab<K, V> forEach (Func<K, V> callback ) {
            for ( V v : SR ) callback.apply( this.key, v ) ;
            return this ;
        }
        /** 返回所有值
         * @return List<值的类型>
         * */
        public List<V> values ( ) {
            return SR ;
        }
    }

    private final List<KVTab<K, V>> LS = new ArrayList<>() ;
    private V VAL = null ;

    /** 创建一个存储表，如果值已被绑定在某个存储表，那么会返回其所绑定的表
     * @param key   - 表需要绑定的值
     * @return 存储表
     * */
    public KVTab<K, V> buildEntry(K key ) {
        KVTab<K, V> s = this.find( key ) ;
        LS.add( s ) ;
        return s ;
    }
    /** 用于查找绑定了值的存储表，找不到时返回一个空白的新表（新表不会被记录）
     * @param key   - 值
     * @return 存储表
     * */
    public KVTab<K, V> find (K key ) {
        for ( KVTab<K, V> s : LS ) {
            if ( s.key.equals( key ) ) return s ;
        }
        return new KVTab<>( key ) ;
    }
    /** 获取所有存储表
     * @return 存储表的数组
     * */
    public List<KVTab<K, V>> values ( ) {
        return LS ;
    }
    /** 遍历当前所有存储表
     * @param callback   - void ( BindList, 存储表 ) -> { }
     * @return BindList
     * */
    public BindList<K, V> forEach ( Func<BindList<K, V>, KVTab<K, V>> callback ) {
        for ( KVTab<K, V> e : LS ) callback.apply( this, e ) ;
        return this ;
    }
    /**用于设置需要放进存储表的值，新的值会 “覆盖” 旧值
     * @param payloadValue   - 需要
     */
    public BindList<K, V> setPayload ( V payloadValue ) {
        this.VAL = payloadValue ;
        return this ;
    }
    /** 用于设置的值放进存储表里，不会避免重复
     * @param kvtabs   - 需要放入的值
     * @return BindList
     * */
    @SafeVarargs
    public final BindList<K, V> putInto( KVTab<K, V>... kvtabs ) {
        if ( this.VAL != null ) for ( KVTab<K, V> s : kvtabs ) s.put( this.VAL) ;
        return this ;
    }
    /** 传入键的版本
     * */
    @SafeVarargs
    public final BindList<K, V> putInto( K... key ) {
        if ( this.VAL != null ) for ( K k : key ) this.buildEntry( k ).put( this.VAL ) ;
        return this ;
    }
    /** 同上，但会避免重复
     * */
    @SafeVarargs
    public final BindList<K, V> setInto( KVTab<K, V>... kvtabs ) {
        if ( this.VAL != null ) for ( KVTab<K, V> s : kvtabs ) s.set( this.VAL) ;
        return this ;
    }
    /** 传入键的版本
     * */
    @SafeVarargs
    public final BindList<K, V> setInto( K... key ) {
        if ( this.VAL != null ) for ( K k : key ) this.buildEntry( k ).set( this.VAL ) ;
        return this ;
    }

}
