package com.synchronizer.api.network;

import com.synchronizer.api.configuration.Configurator;
import com.synchronizer.api.network.vo.SynchronizedNode;

public interface ConnectionFactory<T> {

    Connection<T> getNewConnection(SynchronizedNode synchronizedNode);
}
