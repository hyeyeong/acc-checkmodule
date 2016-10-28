package kr.ac.sm.epubacccheck.svg;

import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class SVGChecker implements FileChecker
{
	@Override
	public void check(String filePath)
	{
		// TODO Auto-generated method stub
		SVGAccessibilityHandler svgHandler = new SVGAccessibilityHandler();
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, svgHandler);
	}
}
