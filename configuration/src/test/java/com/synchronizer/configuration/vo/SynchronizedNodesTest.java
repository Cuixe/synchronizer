package com.synchronizer.configuration.vo;

import com.synchronizer.api.network.vo.SynchronizedNode;
import com.synchronizer.api.network.vo.SynchronizedNodes;
import com.synchronizer.api.network.exceptions.ConfigurationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SynchronizedNodesTest {

    private SynchronizedNodes synchronizedNodes;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        synchronizedNodes = new SynchronizedNodes("Test", 1, SynchronizedNodes.SyncMode.SYNC);
    }

    @Test
    public void newNodes() {
        SynchronizedNode node1 = new SynchronizedNode("1", 1,1001,"");
        SynchronizedNode node2 = new SynchronizedNode("2", 2,1001,"");
        SynchronizedNode node3 = new SynchronizedNode("3", 3,1001,"");

        synchronizedNodes.addNewNode(node3);
        synchronizedNodes.addNewNode(node2);
        synchronizedNodes.addNewNode(node1);
        Assert.assertEquals(3, synchronizedNodes.getSynchronizedNodes().size());
        Assert.assertEquals(node1, synchronizedNodes.getSynchronizedNodes().get(0));
        Assert.assertEquals(node2, synchronizedNodes.getSynchronizedNodes().get(1));
        Assert.assertEquals(node3, synchronizedNodes.getSynchronizedNodes().get(2));
    }

    @Test
    public void duplicatedNodeId() {
        SynchronizedNode node1 = new SynchronizedNode("1", 1,1001,"");
        SynchronizedNode node2 = new SynchronizedNode("1", 2,1001,"");
        SynchronizedNode node3 = new SynchronizedNode("3", 3,1001,"");
        synchronizedNodes.addNewNode(node1);
        expectedException.expect(ConfigurationException.class);
        expectedException.expectMessage("Duplicated NodeId 1");
        synchronizedNodes.addNewNode(node2);
        synchronizedNodes.addNewNode(node3);
    }

    @Test
    public void duplicatedQuorumId() {
        SynchronizedNode node1 = new SynchronizedNode("1", 1,1001,"");
        SynchronizedNode node2 = new SynchronizedNode("2", 1,1001,"");
        SynchronizedNode node3 = new SynchronizedNode("3", 3,1001,"");
        synchronizedNodes.addNewNode(node1);
        expectedException.expect(ConfigurationException.class);
        expectedException.expectMessage("Duplicated QuorumId 1");
        synchronizedNodes.addNewNode(node2);
        synchronizedNodes.addNewNode(node3);
    }

}