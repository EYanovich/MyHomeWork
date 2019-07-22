package junitcucumber.parsers;

import junitcucumber.page.Emails;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DomParserFacade {
    public List<Emails> parse(Document document) throws FileNotFoundException, XMLStreamException
    {
        NodeList nodeList = document.getElementsByTagName("Email");
        List<Emails> employees = new ArrayList<Emails>();
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            employees.add(getEmployee(nodeList.item(i)));
        }
        return employees;
    }

    private static Emails getEmployee(Node node)
    {
        Emails emails = new Emails();
        Element element = (Element) node;
        emails.setId(Integer.parseInt( element.getAttribute( "id" )));
        emails.setUserId(Integer.parseInt( getTagValue("Userid", element)));
        emails.setSendTo(getTagValue("Sendto", element));
        emails.setTheeme(getTagValue("Theme", element));
        emails.setText(getTagValue("Text", element));
        return emails;
    }

    private static String getTagValue(String tag, Element element)
    {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
