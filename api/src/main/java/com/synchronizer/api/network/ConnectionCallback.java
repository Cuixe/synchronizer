package com.synchronizer.api.network;

import com.synchronizer.api.messaging.vo.SyncMessage;

public interface ConnectionCallback<T> {

    void process(SyncMessage<T> syncMessage);
}
