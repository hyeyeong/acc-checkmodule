package kr.ac.sm.epubacccheck.api;

import java.util.ArrayList;

import kr.ac.sm.epubacccheck.css.CSSChecker;
import kr.ac.sm.epubacccheck.epub.EpubFile;
import kr.ac.sm.epubacccheck.epub.NavChecker;
import kr.ac.sm.epubacccheck.epub.OPFChecker;
import kr.ac.sm.epubacccheck.svg.SVGChecker;
import kr.ac.sm.epubacccheck.util.FileExt;
import kr.ac.sm.epubacccheck.xhtml.HTMLChecker;

public class EpubAccessibilityValidator 
{
	private String filePath;

	public void validate(String path)
	{
		filePath = path;
		FileExt fileExtension = getFileExtension(path);
		
		// input - OPF: validating all files
		if (fileExtension == FileExt.OPF)
		{
			runOPFCheck();
		}
		else // input - unit file
		{
			runCheck(fileExtension);
		}
	}
	
	private void runOPFCheck()
	{
		OPFChecker opfChecker = new OPFChecker();
		ArrayList<EpubFile> epubFileList = new ArrayList<EpubFile>();
		
		opfChecker.check(filePath);
		epubFileList = opfChecker.getEpubFileList();
		
		for (int i = 0; i < epubFileList.size(); i++)
		{
			EpubFile file = epubFileList.get(i);
			filePath = file.getFilePath();
			//System.out.println("----- (validator) path: " + filePath + " - ext: " + file.getFileExt().toString());
			
			runCheck(file.getFileExt());
		}
	}
	
	// execute validation
	private void runCheck(FileExt fileExt)
	{
		switch (fileExt)
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
			
			case NAV:
				checkNav();
			break;
			
			default:
			break;
		}
	}
	
	private void checkXHTML()
	{
		new HTMLChecker().check(filePath);
	}
	
	private void checkCSS()
	{
		new CSSChecker().check(filePath);
	}
	
	private void checkSVG()
	{
		new SVGChecker().check(filePath);
	}
	
	private void checkNav()
	{
		new NavChecker().check(filePath);
	}
	
	private FileExt getFileExtension(String path)
	{
		System.out.println(path);
		String[] splitted = path.split("\\.");
		String ext = splitted[splitted.length - 1];
		System.out.println(splitted[splitted.length - 1]);

		if (ext.equals("xhtml"))
			return FileExt.XHTML;
		else if (ext.equals("css"))
			return FileExt.CSS;
		else if (ext.equals("svg"))
			return FileExt.SVG;
		else if (ext.equals("opf"))
			return FileExt.OPF;
		else
			return FileExt.NONE;
	}
}