package com.synchronizer.api;

import com.synchronizer.api.messaging.vo.SyncMessage;

public interface SyncMessageFactory<T> {

    SyncMessage<T> createInstance(long idMessage, long sequence, T message);


}
