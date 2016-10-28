package kr.ac.sm.epubacccheck.epub;

import java.util.ArrayList;

import kr.ac.sm.epubacccheck.util.FileChecker;
import kr.ac.sm.epubacccheck.util.XMLDocParser;

public class OPFChecker implements FileChecker
{
	private OPFCheckHandler handler;

	@Override
	public void check(String filePath)
	{
		// TODO Auto-generated method stub
		handler = new OPFCheckHandler();
		XMLDocParser parser = new XMLDocParser();
		parser.parse(filePath, handler);
	}
	
	public ArrayList<EpubFile> getEpubFileList()
	{
		return handler.getFileList();
	}
}
