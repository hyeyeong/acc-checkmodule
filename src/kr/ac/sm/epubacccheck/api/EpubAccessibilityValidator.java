package kr.ac.sm.epubacccheck.api;

import kr.ac.sm.epubacccheck.svg.SVGAccessibilityHandler;
import kr.ac.sm.epubacccheck.xml.HTMLChecker;
import kr.ac.sm.epubacccheck.xml.XMLDocParser;

public class EpubAccessibilityValidator 
{
	private final int XHTML = 100;
	private final int CSS = 200;
	private final int SVG = 300;
	
	private String filePath;

	public void validate(String path)
	{
		this.filePath = path;
		switch (getFileExtension())
		{
			case XHTML:
				checkXHTML();
			break;
			
			case CSS:
				checkCSS();
			break;
			
			case SVG:
				checkSVG();
			break;
		}
	}
	
	private void checkXHTML()
	{
		new HTMLChecker().check(filePath);
	}
	
	private void checkCSS()
	{
		;
	}
	
	private void checkSVG()
	{
		SVGAccessibilityHandler svgHandler = new SVGAccessibilityHandler();
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, svgHandler);
	}
	
	private int getFileExtension()
	{
		String[] splitted = filePath.split(".");
		String ext = splitted[(splitted.length - 1)];
		
		if (ext.equals("xhtml"))
			return XHTML;
		else if (ext.equals("css"))
			return CSS;
		else if (ext.equals("svg"))
			return SVG;
		else
			return 0;
	}
}