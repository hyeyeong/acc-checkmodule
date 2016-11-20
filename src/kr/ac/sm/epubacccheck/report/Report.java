package kr.ac.sm.epubacccheck.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kr.ac.sm.epubacccheck.message.MessageId;
import kr.ac.sm.epubacccheck.message.MessageLocationMap;

public class Report
{
	private String reportPath;
	
	public Report()
	{
		reportPath = getDate() + ".json";
	}

	public void addMessage(MessageId messageId, EPUBLocation location)
	{
		MessageLocationMap.addLocation(messageId, location);
	}

	public void setReportPath(String reportPath)
	{
		this.reportPath = reportPath;
	}

	public String getReportPath()
	{
		return reportPath;
	}

	private String getDate()
	{
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyMMdd_HHmmss", Locale.KOREA ); 
		Date currentDate = new Date ( ); 
		return formatter.format (currentDate); 
	}
}
