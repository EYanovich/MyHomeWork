package dom;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Countries;

public class DomParser {
    public List<Countries> parse(Document document) throws FileNotFoundException, XMLStreamException {
        NodeList nodeList = document.getElementsByTagName( "Country" );
        List<Countries> countries = new ArrayList<Countries>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            countries.add( getEmployee( nodeList.item( i ) ) );
        }
        return countries;
    }

    private static Countries getEmployee(Node node) {
        Countries countries = new Countries();
        Element element = (Element) node;
        countries.setId( Integer.parseInt( element.getAttribute( "id" ) ) );
        countries.setCode( getTagValue( "Code", element ) );
        countries.setName( getTagValue( "Name", element ) );
        countries.setDescription( getTagValue( "Description", element ) );
        return countries;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName( tag ).item( 0 ).getChildNodes();
        Node node = nodeList.item( 0 );
        return node.getNodeValue();
    }
}
