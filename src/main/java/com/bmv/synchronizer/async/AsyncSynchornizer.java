package com.bmv.synchronizer.async;

import com.bmv.synchronizer.api.*;
import com.bmv.synchronizer.api.vo.SyncMessage;

public class AsyncSynchornizer<T> implements Synchronizer<T> {

    private Identifier<T> identifier;
    private Sequencer<T> sequencer;
    private Persistor<T> persistor;
    private SyncMessageFactory<T> messageFactory;
    private QuorumManager<T> quorumManager;
    private SyncCallback<T> syncCallback;


    @Override
    public void syncrhonize(T message) {
        long idMessage = identifier.getMessageId(message);
        long sequence = sequencer.getSequence(message);
        SyncMessage<T> syncMessage = messageFactory.createInstance(idMessage, sequence, message);
        persistor.persist(syncMessage);
        quorumManager.send(syncMessage);
        syncCallback.receive(message);
    }

    public void setSequencer(Sequencer<T> sequencer) {
        this.sequencer = sequencer;
    }

    public void setPersistor(Persistor<T> persistor) {
        this.persistor = persistor;
    }

    public void setMessageFactory(SyncMessageFactory<T> messageFactory) {
        this.messageFactory = messageFactory;
    }

    public void setIdentifier(Identifier<T> identifier) {
        this.identifier = identifier;
    }

    public void setQuorumManager(QuorumManager<T> quorumManager) {
        this.quorumManager = quorumManager;
    }
}
