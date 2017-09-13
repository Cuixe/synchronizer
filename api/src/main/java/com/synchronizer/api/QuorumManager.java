package com.synchronizer.api;

import com.synchronizer.api.messaging.vo.SyncMessage;

public interface QuorumManager <T> {

    void send(SyncMessage<T> syncMessage);
}
