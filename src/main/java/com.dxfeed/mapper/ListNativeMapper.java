package com.dxfeed.mapper;

import org.graalvm.word.PointerBase;

import java.util.List;

public interface ListNativeMapper<V, T extends PointerBase> {
    T nativeObject(final List<V> jObjects);

    void delete(final T cObject, final int size);
}