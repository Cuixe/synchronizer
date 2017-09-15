package com.synchronizer.api.network.vo;

import com.synchronizer.api.network.exceptions.ConfigurationException;

import java.util.*;

public class SynchronizedNodes {

    public enum SyncMode {
        ASYNC, SYNC
    }
    private String nodeId;
    private int attemptsToFail;
    private SyncMode syncMode;
    private List<SynchronizedNode> synchronizedNodes = new ArrayList<>();

    public SynchronizedNodes(String nodeId, int attemptsToFail, SyncMode syncMode) {
        this.nodeId = nodeId;
        this.attemptsToFail = attemptsToFail;
        this.syncMode = syncMode;
    }

    public String getNodeId() {
        return nodeId;
    }

    public int getAttemptsToFail() {
        return attemptsToFail;
    }

    public SyncMode getSyncMode() {
        return syncMode;
    }

    public List<SynchronizedNode> getSynchronizedNodes() {
        return synchronizedNodes;
    }

    public void addNewNode(SynchronizedNode synchronizedNode) {
        for(SynchronizedNode synchronizedNodeTmp : synchronizedNodes) {
            if (synchronizedNode.getId().equals(synchronizedNodeTmp.getId())) {
                throw new ConfigurationException("Duplicated NodeId " + synchronizedNode.getId());
            } else if (synchronizedNode.getQuorumId() == synchronizedNodeTmp.getQuorumId()) {
                throw new ConfigurationException("Duplicated QuorumId " + synchronizedNode.getQuorumId());
            }
        }
        this.synchronizedNodes.add(synchronizedNode);
        this.synchronizedNodes.sort(Comparator.comparing(SynchronizedNode::getQuorumId));
    }
}
