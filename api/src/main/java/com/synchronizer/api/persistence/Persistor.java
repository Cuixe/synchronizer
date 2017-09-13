package com.synchronizer.api.persistence;

import com.synchronizer.api.messaging.vo.SyncMessage;

public interface Persistor<T> {

    void persist(SyncMessage<T> message);

    SyncMessage<T> lastPersisted();

}
