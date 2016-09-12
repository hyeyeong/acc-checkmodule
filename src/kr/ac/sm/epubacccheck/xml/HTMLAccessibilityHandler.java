package kr.ac.sm.epubacccheck.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HTMLAccessibilityHandler extends DefaultHandler 
{
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		// CSS
		if (attributes.getValue("style") != null)
		{
			System.out.println("error: can not use style attribute");
		}

		// LANG-001
		if (qName.equals("html"))
		{
			if (attributes.getValue("xml:lang") == null && attributes.getValue("lang") == null)
			{
				System.out.println("error: no lang & xml:lang attribute");
			}
		}

		// TITLE-001 / TITLE-002
		if (qName.equals("section"))
		{
			if (attributes.getValue("epub:type") == null || attributes.getValue("aria-label") == null)
			{
				System.out.println("error: no epub:type or aria-label");
			}
		}
	}
}
