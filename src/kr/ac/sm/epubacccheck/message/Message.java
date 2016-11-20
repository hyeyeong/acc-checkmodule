package kr.ac.sm.epubacccheck.message;

import java.util.ArrayList;
import java.util.List;

import kr.ac.sm.epubacccheck.report.EPUBLocation;

public class Message
{
	private MessageId messageId;
	private MessageSeverity severity;
	private List<EPUBLocation> locations;
	
	public Message()
	{
		locations = new ArrayList<EPUBLocation>();
	}

	public void createMessage(MessageId messageId)
	{
		this.messageId = messageId;
		this.severity = MessageSeverityMap.getMessgeSeverity(messageId);
		this.locations = MessageLocationMap.getAllEPUBLocation(messageId);
	}

	public MessageId getMessageId()
	{
		return messageId;
	}

	public MessageSeverity getSeverity()
	{
		return severity;
	}
	
	public EPUBLocation getLocation(int index)
	{
		return locations.get(index);
	}
}
