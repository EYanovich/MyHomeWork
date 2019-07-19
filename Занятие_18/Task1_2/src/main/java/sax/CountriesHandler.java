package sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Emails;

public class CountriesHandler extends DefaultHandler
{
    private List<Emails> emails;
    private Emails email;
    boolean bSendTo = false;
    boolean bTheme = false;
    boolean bText = false;

    public List<Emails> getEmails()
    {
        return emails;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

        if (qName.equalsIgnoreCase("Email"))
        {
            String id = attributes.getValue("id");
            email = new Emails();
            email.setId(Integer.parseInt(id));
            if (emails == null)
            {
                emails = new ArrayList<>();
            }
        }
        else if (qName.equalsIgnoreCase("Sendto"))
        {
            bSendTo = true;
        }
        else if (qName.equalsIgnoreCase("Theme"))
        {
            bTheme = true;
        }
        else if (qName.equalsIgnoreCase("Text"))
        {
            bText = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equalsIgnoreCase("Email"))
        {
            emails.add( email );
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException
    {

        if (bSendTo)
        {
            email.setSendTo(new String(ch, start, length));
            bSendTo = false;
        }
        else if (bTheme)
        {
            email.setTheeme(new String(ch, start, length));
            bTheme = false;
        }
        else if (bText)
        {
            email.setText(new String(ch, start, length));
            bText = false;
        }
    }
}
