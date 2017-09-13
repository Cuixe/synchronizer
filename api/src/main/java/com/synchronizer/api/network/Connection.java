package com.synchronizer.api.network;

import com.synchronizer.api.messaging.vo.SyncMessage;

public interface Connection<T> {

    void send(SyncMessage<T> syncMessage);

    void setCallback(ConnectionCallback connectionCallback);

}
