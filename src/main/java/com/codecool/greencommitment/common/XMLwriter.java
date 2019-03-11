package com.codecool.greencommitment.common;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLwriter {
    public void xmlWrite(Measurements measurements){
        List<Measurement> measurements1 = measurements.getMeasurement();

        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("measurements");
            doc.appendChild(rootElement);
            //root attr
            Attr attr = doc.createAttribute("id");
            attr.setValue(String.valueOf(measurements.getId()));
            rootElement.setAttributeNode(attr);
            //child(root) measurement
            for (int i = 0; i < measurements1.size() ; i++) {
                Element measurement = doc.createElement("measurement");
                rootElement.appendChild(measurement);

                //child time
                Element time = doc.createElement("time");
                time.appendChild(doc.createTextNode(String.valueOf(measurements1.get(i).getTime())));
                measurement.appendChild(time);

                //child value
                Element value = doc.createElement("value");
                value.appendChild(doc.createTextNode(String.valueOf(measurements1.get(i).getValue())));
                measurement.appendChild(value);

                //child type
                Element type = doc.createElement("type");
                type.appendChild(doc.createTextNode(measurements1.get(i).getType()));
                measurement.appendChild(type);
            }
            //file writer
            Integer id = measurements.getId();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(""+ id +".xml"));

            transformer.transform(source, result);
            System.out.println("Saved");




        }
        catch (ParserConfigurationException a ){
            a.printStackTrace();
        }catch (TransformerException e ){
            e.printStackTrace();
        }
    }
}
