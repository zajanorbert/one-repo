package com.codecool.greencommitment.common;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLread {

    public Measurements xmlParser(String path) {
        List<Measurements> measure = new ArrayList<>();
        List<Measurement> measurementList = new ArrayList<>();
        Integer id = 0;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            try {
                Document doc = builder.parse(path);
                doc.getDocumentElement().normalize();
                Element root = doc.getDocumentElement();
                id = Integer.valueOf(root.getAttribute("id"));
                NodeList allMeasure = root.getChildNodes();
                for (int i = 0; i < allMeasure.getLength() ; i++) {
                    Node node = allMeasure.item(i);
                    if (node.getNodeType() == node.ELEMENT_NODE){

                        NodeList parameters = node.getChildNodes();
                        Element nElement = (Element) parameters;
                        NodeList timeList = nElement.getElementsByTagName("time");
                        NodeList valueList = nElement.getElementsByTagName("value");
                        NodeList typeList = nElement.getElementsByTagName("type");
                        Long time = Long.valueOf(timeList.item(0).getTextContent());
                        int value = Integer.valueOf(valueList.item(0).getTextContent());
                        String type = typeList.item(0).getTextContent();
                        Measurement measurement = new Measurement(time,value,type);
                        measurementList.add(measurement);
                    }
                }

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Measurements measurements = new Measurements(id,measurementList);
        measure.add(measurements);
        return measure.get(0);

    }
}
