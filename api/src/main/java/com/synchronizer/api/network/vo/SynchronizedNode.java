package com.synchronizer.api.network.vo;

public class SynchronizedNode {

    private String id;
    private int quorumId;
    private int port;
    private String host;

    public SynchronizedNode(String id, int quorumId, int port, String host) {
        this.id = id;
        this.quorumId = quorumId;
        this.port = port;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public int getQuorumId() {
        return quorumId;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    @Override
    public int hashCode() {
        return this.quorumId;
    }
}
