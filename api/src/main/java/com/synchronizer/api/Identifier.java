package com.synchronizer.api;

public interface Identifier<T> {

    long getMessageId(T message);
}
