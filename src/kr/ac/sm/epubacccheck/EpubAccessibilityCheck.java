package kr.ac.sm.epubacccheck;

import kr.ac.sm.epubacccheck.api.EpubAccessibilityValidator;

public class EpubAccessibilityCheck
{
	public static void main(String[] args)
	{
		EpubAccessibilityValidator validator = new EpubAccessibilityValidator();
		validator.validate("/Users/hyeyeong/Documents/workspace/test.xhtml");
	}
}
