/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxWriter {

    public XMLEventWriter writer;
    public XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

    public void StoreDD_Objekt(XMLEventWriter eventWriter, String CGSElementname, String name1, String value1, String name2, String value2, int tabcnt) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent zeilenumbruch = eventFactory.createDTD("\n");
        XMLEvent tab1 = eventFactory.createDTD("\t");
        XMLEvent tab2 = eventFactory.createDTD("\t");
        XMLEvent tab3 = eventFactory.createDTD("\t");
        XMLEvent tab4 = eventFactory.createDTD("\t");

        switch (tabcnt) {

            case 1:
                eventWriter.add(tab1);
                break;
            case 2:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                break;
            case 3:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                break;
            case 4:
                eventWriter.add(tab1);
                eventWriter.add(tab2);
                eventWriter.add(tab3);
                eventWriter.add(tab4);
                break;
            default:
                break;
        }
        StartElement CGSElementStart = eventFactory.createStartElement("", "", CGSElementname);
        eventWriter.add(CGSElementStart);
        eventWriter.add(eventFactory.createAttribute(name1, value1));
        eventWriter.add(eventFactory.createAttribute(name2, value2));

        EndElement eElement = eventFactory.createEndElement("", "", CGSElementname);
        eventWriter.add(eElement);
        eventWriter.add(zeilenumbruch);
        System.out.println(CGSElementStart + "" + eElement);
    }

}
