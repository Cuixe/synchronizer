package com.synchronizer.api;

public interface Sequencer<T> {

    long getSequence(T message);
}
