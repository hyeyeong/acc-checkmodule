package kr.ac.sm.epubacccheck.message;

import java.util.ArrayList;
import java.util.List;

public class Message
{
	private MessageId messageId;
	private MessageSeverity severity;
	private String message;
	private List<EPUBLocation> locations;
	
	public Message()
	{
		locations = new ArrayList<EPUBLocation>();
	}

	public void createMessage(MessageId messageId)
	{
		this.messageId = messageId;
	}
}
