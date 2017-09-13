package com.synchronizer.api;

public interface SyncCallback <T> {

    void receive(T message);
}
