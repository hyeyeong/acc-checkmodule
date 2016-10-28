package kr.ac.sm.epubacccheck.epub;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kr.ac.sm.epubacccheck.util.EpubInfo;

public class NavCheckHandler extends DefaultHandler
{
	private Locator locator;
	
	private boolean isNav = false;
	private boolean isLiInNav = false;
	
	private boolean hasATag = false;
	private boolean hasSpanTag = false;
	private boolean hasLoI = false;
	private boolean hasLoT = false;
	private boolean hasLoV = false;
	private boolean hasLoA = false;

	private int liCountInNav = 0;
	private int navLineNumber = 0;
	
	public void setDocumentLocator(Locator locator)
	{
        this.locator = locator;
    }
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		// NAV-001
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("toc"))
		{
			isNav = true;
			navLineNumber = locator.getLineNumber();
		}
		
		// NAV-002
		if (isNav)
		{
			if (qName.equals("li"))
			{
				isLiInNav = true;
				liCountInNav++;
				System.out.println("nav: li count > " + liCountInNav);
			}
			
			if (isLiInNav)
			{
				if (qName.equals("a"))
				{
					hasATag = true;
				}
				if (qName.equals("span"))
				{
					hasSpanTag = true;
					System.out.println("warning: use a tag - " + locator.getLineNumber());
				}
			}
		}
		
		// NAV-003
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("loi"))
		{
			hasLoI = true;
		}
		
		// NAV-004
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("lot"))
		{
			hasLoT = true;
		}
		
		// NAV-005
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("lov"))
		{
			hasLoV = true;
		}
		
		// NAV-006
		if (qName.equals("nav") && attributes.getValue("epub:type").equals("loa"))
		{
			hasLoA = true;
		}
	}
	
	public void endElement(String uri, String localName, String qName)
	{
		if (qName.equals("li"))
		{
			isLiInNav = false;
		}
		
		if (qName.equals("nav"))
		{
			isNav = false;
		}
	}
	
	public void endDocument()
	{
		System.out.println("nav: file count > " + EpubInfo.epubFileCount);
		
		if (liCountInNav != EpubInfo.epubFileCount)
		{
			System.out.println("error: uncompleted index - " + navLineNumber);
		}
		
		if (!hasATag && !hasSpanTag)
		{
			System.out.println("error: need 'a' tag or 'span' tag in li - " + navLineNumber);
		}
		
		if (!hasLoI)
		{
			System.out.println("warning: need list of image");
		}
		
		if (!hasLoT)
		{
			System.out.println("warning: need list of table");
		}
		
		if (!hasLoV)
		{
			System.out.println("warning: need list of video");
		}
		
		if (!hasLoA)
		{
			System.out.println("warning: need list of audio");
		}
	}
}
