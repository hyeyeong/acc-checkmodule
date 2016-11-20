package kr.ac.sm.epubacccheck.message;

import java.util.HashMap;

public class MessageSeverityMap
{
	private HashMap<MessageId, MessageSeverity> severities = new HashMap<MessageId, MessageSeverity>();
	
	public void initMessageSeverities()
	{
		severities.put(MessageId.TOC_001, MessageSeverity.ERROR);
		severities.put(MessageId.TOC_001_W, MessageSeverity.WARNING);
	}
}
