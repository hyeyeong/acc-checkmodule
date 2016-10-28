package kr.ac.sm.epubacccheck.xhtml;

import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class HTMLChecker implements FileChecker
{
	@Override
	public void check(String filePath)
	{
		// TODO Auto-generated method stub
		HTMLAccessibilityHandler handler = new HTMLAccessibilityHandler();
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, handler);
	}
}
