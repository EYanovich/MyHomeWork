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

import model.Emails;

public class StaxParser
{
    private static final String ID = "id";
    private static final String SENDTO = "Sendto";
    private static final String THEME = "Theme";
    private static final String TEXT = "Text";
    private static final String EMPLOYEE = "Email";
    private Emails employee;
    List<Emails> mails = new ArrayList<>();

    public List<Emails> parse(XMLEventReader xmlEventReader) throws FileNotFoundException, XMLStreamException
    {
        while (xmlEventReader.hasNext())
        {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            proceedStartElement(xmlEvent, xmlEventReader);
            proceedEndElement(xmlEvent);
        }
        return mails;
    }

    private void proceedStartElement(XMLEvent xmlEvent, XMLEventReader xmlEventReader) throws XMLStreamException
    {
        if (xmlEvent.isStartElement())
        {
            StartElement startElement = xmlEvent.asStartElement();
            if (isTagNameEqual(startElement, EMPLOYEE))
            {
                employee = new Emails();
                Attribute attribute = startElement.getAttributeByName(new QName(ID));
                if (attribute != null)
                {
                    employee.setId(Integer.parseInt(attribute.getValue()));
                }
            }
            // set the other varibles from xml elements
            else if (isTagNameEqual(startElement, SENDTO))
            {
                employee.setSendTo(xmlEventReader.nextEvent().asCharacters().getData());
            }
            else if (isTagNameEqual(startElement, THEME))
            {
                employee.setTheeme(xmlEventReader.nextEvent().asCharacters().getData());
            }
            else if (isTagNameEqual(startElement, TEXT))
            {
                employee.setText(xmlEventReader.nextEvent().asCharacters().getData());
            }
        }
    }

    private void proceedEndElement(XMLEvent xmlEvent)
    {
        if (xmlEvent.isEndElement())
        {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals(EMPLOYEE))
            {
                mails.add(employee);
            }
        }
    }

    private boolean isTagNameEqual(StartElement startElement, String tagName)
    {
        return startElement.getName().getLocalPart().equals(tagName);
    }
}
