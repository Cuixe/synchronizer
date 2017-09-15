package com.synchronizer.configuration;

import com.synchronizer.api.network.vo.SynchronizedNode;
import com.synchronizer.api.network.vo.SynchronizedNodes;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ConfigurationLoaderTest {

    private static Document document;

    @BeforeClass
    public static void setup() throws Exception {
        InputStream inputStream = ConfigurationLoader.class.getClassLoader().getResourceAsStream("config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.parse(inputStream);
        document.getDocumentElement().normalize();
    }

    @Test
    public void createSynchronizedNodes() {
        SynchronizedNodes synchronizedNodes = ConfigurationLoader.createInstance(document);
        Assert.assertEquals("1", synchronizedNodes.getNodeId());
        Assert.assertEquals(SynchronizedNodes.SyncMode.ASYNC, synchronizedNodes.getSyncMode());
        Assert.assertEquals(5, synchronizedNodes.getAttemptsToFail());
    }

    @Test
    public void createSyncNodes() {
        SynchronizedNodes synchronizedNodes = new SynchronizedNodes("TEST", 1, SynchronizedNodes.SyncMode.ASYNC);
        ConfigurationLoader.createNodes(document, synchronizedNodes);
        assertEquals(3, synchronizedNodes.getSynchronizedNodes().size());
    }

    @Test
    public void loadConfiguration() throws Exception {
        ConfigurationLoader.loadConfiguration("config.xml");
        SynchronizedNodes synchronizedNodes = ConfigurationLoader.getSynchronizedNodes();
        assertNotNull(synchronizedNodes);
        assertEquals(3, synchronizedNodes.getSynchronizedNodes().size());
        SynchronizedNode syncNode = synchronizedNodes.getSynchronizedNodes().get(0);
        assertEquals(1, syncNode.getQuorumId());
        assertEquals(10001, syncNode.getPort());
    }

}