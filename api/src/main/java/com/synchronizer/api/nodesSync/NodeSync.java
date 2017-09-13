package com.synchronizer.api.nodesSync;

import com.synchronizer.api.messaging.vo.SyncMessage;

public interface NodeSync<T> {

    void sinchronize(SyncMessage<T> syncMessage);

}
