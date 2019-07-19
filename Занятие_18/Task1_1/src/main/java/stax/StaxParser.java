package stax;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import model.Countries;

public class StaxParser {
    private static final String ID = "id";
    private static final String CODE = "Code";
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String EMPLOYEE = "Country";
    private Countries employee;
    List<Countries> countries = new ArrayList<>();

    public List<Countries> parse(XMLEventReader xmlEventReader) throws FileNotFoundException, XMLStreamException {
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            proceedStartElement( xmlEvent, xmlEventReader );
            proceedEndElement( xmlEvent );
        }
        return countries;
    }

    private void proceedStartElement(XMLEvent xmlEvent, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (xmlEvent.isStartElement()) {
            StartElement startElement = xmlEvent.asStartElement();
            if (isTagNameEqual( startElement, EMPLOYEE )) {
                employee = new Countries();
                Attribute attribute = startElement.getAttributeByName( new QName( ID ) );
                if (attribute != null) {
                    employee.setId( Integer.parseInt( attribute.getValue() ) );
                }
            }
            // set the other varibles from xml elements
            else if (isTagNameEqual( startElement, CODE )) {
                employee.setCode( xmlEventReader.nextEvent().asCharacters().getData() );
            } else if (isTagNameEqual( startElement, NAME )) {
                employee.setName( xmlEventReader.nextEvent().asCharacters().getData() );
            } else if (isTagNameEqual( startElement, DESCRIPTION )) {
                employee.setDescription( xmlEventReader.nextEvent().asCharacters().getData() );
            }
        }
    }

    private void proceedEndElement(XMLEvent xmlEvent) {
        if (xmlEvent.isEndElement()) {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals( EMPLOYEE )) {
                countries.add( employee );
            }
        }
    }

    private boolean isTagNameEqual(StartElement startElement, String tagName) {
        return startElement.getName().getLocalPart().equals( tagName );
    }
}
