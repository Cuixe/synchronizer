package com.synchronizer.api.messaging.vo;

public class SyncMessage<T> {

    private long sequence;
    private long idMessage;
    private T message;

    public SyncMessage(long sequence, long idMessage, T message) {
        this.sequence = sequence;
        this.idMessage = idMessage;
        this.message = message;
    }

    public long getIdMessage() {
        return idMessage;
    }

    public long getSequence() {
        return sequence;
    }

    public T getMessage() {
        return message;
    }
}
