package kr.ac.sm.epubacccheck.epub;

import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class NavChecker implements FileChecker
{
	private NavCheckHandler handler;

	@Override
	public void check(String filePath)
	{
		// TODO Auto-generated method stub
		handler = new NavCheckHandler();
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, handler);
	}
}
