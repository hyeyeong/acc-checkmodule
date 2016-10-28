package kr.ac.sm.epubacccheck.xhtml;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HTMLAccessibilityHandler extends DefaultHandler 
{
	private Locator locator;
	
	private boolean isSection = false;
	private boolean isTable = false;
	private boolean isFigure = false;

	private boolean hasCaptionInTable = false;
	private boolean hasFigCaptionInFigure = false;

	private int headingCountInSection = 0;
	private int thCountInTable = 0;
	private int figCaptionCountInFigure = 0;
	
	private int startingElementLineNumber = 0;

	
	public void setDocumentLocator(Locator locator)
	{
        this.locator = locator;
    }

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		// LANG-001, EPUBTYPE-002
		if (qName.equals("html"))
		{
			if (attributes.getValue("xml:lang") == null && attributes.getValue("lang") == null)
			{
				System.out.println("error: no lang & xml:lang attribute");
				System.out.println("line: " + locator.getLineNumber());
			}
			
			if (attributes.getValue("xmlns:epub") == null)
			{
				System.out.println("error: no epub namespace");
			}
		}
		
		// LANG-002
		if (qName.equals("body"))
		{
			if (attributes.getValue("xml:lang") == null && attributes.getValue("lang") == null)
			{
				System.out.println("warning: no lang & xml:lang attribute");
				System.out.println("line: " + locator.getLineNumber());
			}
		}
		
		// TITLE-001, TITLE-002, EPUBTYPE-001
		if (qName.equals("section"))
		{
			isSection = true;
			startingElementLineNumber = locator.getLineNumber();

			if (attributes.getValue("epub:type") == null || attributes.getValue("aria-label") == null || attributes.getValue("title") == null)
			{
				System.out.println("error: no section title");
			}
		}

		if (isSection)
		{
			if (qName.equals("h1") || qName.equals("h2") || qName.equals("h3") || qName.equals("h4") || qName.equals("h5") || qName.equals("h6"))
			{
				headingCountInSection++;
			}
		}
		
		// LINK-001
		if (qName.equals("a"))
		{
			if (attributes.getValue("title") == null)
			{
				System.out.println("error: no title attribute");
			}
		}
		
		// STYLE-001, STYLE-002
		if (qName.equals("i") || qName.equals("b"))
		{
			System.out.println("error: can not use i tag");
		}
		
		// STYLE-003 noise background: css -> if selector is div, span, body, p ... etc, background-image is error
		
		// TABLE-001, TABLE-002, TABLE-003
		if (qName.equals("table"))
		{
			isTable = true;
			startingElementLineNumber = locator.getLineNumber();
		}
		
		if (isTable)
		{
			if (qName.equals("caption"))
			{
				hasCaptionInTable = true;
			}
			
			if (qName.equals("th"))
			{
				thCountInTable++;
				if (attributes.getValue("scope") == null || attributes.getValue("scope").equals(""))
				{
					System.out.println("error: no scope attribute");
				}
			}
		}
		
		// IMG-001
		if (qName.equals("figure"))
		{
			isFigure = true;
			startingElementLineNumber = locator.getLineNumber();
		}
		
		if (isFigure)
		{
			if (qName.equals("figcaption"))
			{
				hasFigCaptionInFigure = true;
				figCaptionCountInFigure++;
			}
		}
		
		// IMG-002, IMG-003
		if (qName.equals("area"))
		{
			if (attributes.getValue("alt") == null || attributes.getValue("alt").isEmpty() || attributes.getValue("alt").equals(""))
			{
				System.out.println("error: no alt attribute");
			}
		}
		
		// IMG-003, JSARIA-003
		if (qName.equals("img"))
		{
			if (attributes.getValue("alt") == null || attributes.getValue("alt").isEmpty() || attributes.getValue("alt").equals(""))
			{
				System.out.println("error: no alt attribute");
			}
			
			if (attributes.getValue("aria-role") == null || attributes.getValue("aria-role").isEmpty() || attributes.getValue("aria-role").equals(""))
			{
				System.out.println("warning: recommend to use aria role attribute");
			}
		}
		
		// MEDIA-001, MEDIA-002
		if (qName.equals("audio") || qName.equals("video"))
		{
			if (attributes.getValue("controls") == null)
			{
				System.out.println(("error: no controls attribute"));
			}
			else
			{
				if (attributes.getValue("controls").isEmpty() || !attributes.equals("controls"))
				{
					System.out.println("error: controls attribute has wrong value");
				}
			}
		}
		
		// JSARIA-001
		if (attributes.equals("aria-hidden") || attributes.equals("hidden"))
		{
			if (attributes.getValue("aria-hidden").equals("true") || attributes.getValue("hidden").equals("hidden"))
			{
				// warning
				System.out.println("warning: hidden content");	
			}
		}

		// JSARIA-002 javascript
		
		// NOTE-001 (with CSS)
		if (qName.equals("sup"))
		{
			System.out.println("error: using epub:type for note");
		}
		
		// CSS-001
		if (attributes.getValue("style") != null)
		{
			System.out.println("error: can not use style attribute");
			System.out.println("line: " + locator.getLineNumber());
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		if (qName.equals("section"))
		{
			if (headingCountInSection > 1)
			{
				System.out.println("error: heading is too much! line: " + startingElementLineNumber);
			}
			
			isSection = false;
			headingCountInSection = 0;
		}
		
		// TABLE-001, TABLE-003
		if (qName.equals("table"))
		{
			if (thCountInTable == 0)
			{
				System.out.println("error: no th tag in table / line: " + startingElementLineNumber);
			}
			
			if (!hasCaptionInTable)
			{
				System.out.println("error: no caption tag in table / line: " + startingElementLineNumber);
			}
			
			isTable = false;
			thCountInTable = 0;
		}
		
		// IMG-001
		if (qName.equals("figure"))
		{
			if (!hasFigCaptionInFigure || figCaptionCountInFigure > 1)
			{
				System.out.println("error: figcaption tag / line: " + startingElementLineNumber);
			}
		}
	}
	
	public void endDocument()
	{
		System.out.println("parsing complete");
	}
}