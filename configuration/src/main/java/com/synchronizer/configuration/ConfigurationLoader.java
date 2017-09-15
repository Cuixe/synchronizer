package com.synchronizer.configuration;

import com.synchronizer.api.network.vo.SynchronizedNode;
import com.synchronizer.api.network.vo.SynchronizedNodes;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class ConfigurationLoader {

    private static SynchronizedNodes synchronizedNodes;

    public static void loadConfiguration(String fileName) throws Exception {
        InputStream inputStream = ConfigurationLoader.class.getClassLoader().getResourceAsStream(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);
        doc.getDocumentElement().normalize();
        synchronizedNodes = createInstance(doc);
        createNodes(doc, synchronizedNodes);
    }

    protected static void createNodes(Document doc, SynchronizedNodes synchronizedNodes) {
        NodeList nodes = doc.getElementsByTagName("synchronizedNode");
        for(int i = 0; i< nodes.getLength(); i++) {
            Node node = nodes.item(i);
            NamedNodeMap attributes = node.getAttributes();
            String id = attributes.getNamedItem("id").getNodeValue();
            String quorumId = attributes.getNamedItem("quorumId").getNodeValue();
            String port = attributes.getNamedItem("port").getNodeValue();
            String host = attributes.getNamedItem("host").getNodeValue();
            SynchronizedNode synchronizedNode = new SynchronizedNode(id, Integer.valueOf(quorumId), Integer.valueOf(port), host);
            synchronizedNodes.addNewNode(synchronizedNode);
        }
    }

    protected static SynchronizedNodes createInstance(Document document) {
        Node node = document.getElementsByTagName("synchronizedNodes").item(0);
        NamedNodeMap attributes = node.getAttributes();
        String nodeId = null;
        SynchronizedNodes.SyncMode syncMode = SynchronizedNodes.SyncMode.SYNC;
        int attempts = 3;
        for(int i =0; i< attributes.getLength(); i++) {
            Node next = attributes.item(i);
            switch (next.getNodeName()) {
                case "nodeId":
                    nodeId = next.getNodeValue();
                    break;
                case "syncMode":
                    String mode = next.getNodeValue();
                    syncMode = SynchronizedNodes.SyncMode.valueOf(mode.toUpperCase());
                    break;
                case "attemptsToFail":
                    attempts = Integer.valueOf(next.getNodeValue());
                    break;
            }
        }

        return new SynchronizedNodes(nodeId, attempts, syncMode);
    }

    public static SynchronizedNodes getSynchronizedNodes() {
        return synchronizedNodes;
    }
}
