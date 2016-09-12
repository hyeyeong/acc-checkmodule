package kr.ac.sm.epubacccheck.xml;

import kr.ac.sm.epubacccheck.util.FileChecker;

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

	@Override
	public int getStatus()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
