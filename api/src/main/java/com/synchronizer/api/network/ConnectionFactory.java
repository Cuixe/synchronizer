package com.synchronizer.api.network;

import com.synchronizer.api.configuration.Configurator;

public interface ConnectionFactory<T> {

    Connection<T> getNewConnection(Configurator configurator);
}
