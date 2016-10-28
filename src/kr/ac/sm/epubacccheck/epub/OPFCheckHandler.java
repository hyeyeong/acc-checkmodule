package kr.ac.sm.epubacccheck.epub;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kr.ac.sm.epubacccheck.util.EpubInfo;
import kr.ac.sm.epubacccheck.util.FileExt;

public class OPFCheckHandler extends DefaultHandler
{
	private Locator locator;
	private ArrayList<EpubFile> epubFileList;
	private EpubFile epubFile;
	
	private boolean hasDcLanguage = false;
	private boolean hasSpine = false;
	
	private int xhtmlFileCount = 0;
	
	public OPFCheckHandler()
	{
		this.epubFileList = new ArrayList<EpubFile>();
	}
	
	public void setDocumentLocator(Locator locator)
	{
        this.locator = locator;
    }
	
	public void startDocument()
	{
		System.out.println("document start");
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		String value = null;
		epubFile = new EpubFile();

		if (qName.equals("item"))
		{
			value = attributes.getValue("href");
			epubFile.setFilePath(value);

			if (value.contains(".css"))
			{
				epubFile.setExt(FileExt.CSS);
			}
			else if (value.contains(".svg"))
			{
				epubFile.setExt(FileExt.SVG);
			}
			else if (value.contains(".xhtml"))
			{
				if (attributes.getValue("properties") != null && attributes.getValue("properties").equals("nav"))
				{
					epubFile.setExt(FileExt.NAV);
				}
				else
				{
					xhtmlFileCount++;
					epubFile.setExt(FileExt.XHTML);
				}
			}
			else
			{
				epubFile.setExt(FileExt.NONE);
			}

			epubFileList.add(epubFile);
		}
		
		// OPF-001
		if (qName.equals("dc:language"))
		{
			hasDcLanguage = true;
		}
		
		// OPF-002
		if (qName.equals("spine"))
		{
			hasSpine = true;
			if (attributes.getValue("toc") == null || attributes.getValue("toc").equals(""))
			{
				System.out.println("error: no toc attribute - " + locator.getLineNumber());
			}
		}
	}
	
	public void endDocument()
	{
		// OPF-001
		if (!hasDcLanguage)
		{
			System.out.println("error: no dc:language - " + locator.getLineNumber());
		}
		
		// OPF-002
		if (!hasSpine)
		{
			System.out.println("error: no spine - " + locator.getLineNumber());
		}
		
		EpubInfo.epubFileCount = xhtmlFileCount;
	}

	public ArrayList<EpubFile> getFileList()
	{
		return this.epubFileList;
	}
}
