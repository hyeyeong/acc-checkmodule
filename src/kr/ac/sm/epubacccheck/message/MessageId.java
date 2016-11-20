package kr.ac.sm.epubacccheck.message;

public enum MessageId
{
	// MESSAGE IDs FOR OPF / EPUBTYPE / TOC
	TOC_001			("TOC-001"),
	TOC_001_W		("TOC-001"), // ID for warning
	TOC_002			("TOC-002"),
	TOC_003			("TOC-003"),
	TOC_004			("TOC-004"),
	TOC_005			("TOC-005"),
	TOC_006			("TOC-006"),
	EPUBTYPE_001	("EPUBTYPE-001"),
	EPUBTYPE_002	("EPUBTYPE-002"),
	OPF_001			("OPF-001"),
	OPF_002			("OPF-002"),
	OPF_002_W		("OPF-002"), // ID for warning
	
	// MESSAGE IDs FOR XHTML
	LANG_001		("LANG-001"),
	LANG_002		("LANG-002"),
	TITLE_001		("TITLE-001"),
	TITLE_002		("TITLE-002"),
	LINK_001		("LINK-001"),
	STYLE_001		("STYLE-001"),
	STYLE_002		("STYLE-002"),
	STYLE_003		("STYLE-003"),
	TABLE_001		("TABLE-001"),
	TABLE_002		("TABLE-002"),
	TABLE_003		("TABLE-003"),
	IMG_001			("IMG-001"),
	IMG_002			("IMG-002"),
	IMG_003			("IMG-003"),
	MEDIA_001		("MEDIA-001"),
	MEDIA_002		("MEDIA-002"),
	JSARIA_001		("JSARIA-001"),
	JSARIA_002		("JSARIA-002"),
	JSARIA_003		("JSARIA-003"),
	REF_001			("REF-001"),
	
	// MESSAGE IDs FOR CSS
	CSS_001			("CSS-001"),
	CSS_002			("CSS-002"),
	CSS_003			("CSS-003"),
	CSS_004			("CSS-004"),
	CSS_005			("CSS-005"),
	CSS_005_W		("CSS-005"), // ID for warning
	CSS_006			("CSS-006"),
	CSS_007			("CSS-007"),
	CSS_008			("CSS-008"),
	CSS_009			("CSS-009"),
	CSS_010			("CSS-010"),
	CSS_011			("CSS-011"),
	
	// MESSAGE IDs FOR SVG, SMIL
	SVG_001			("SVG-001"),
	SVG_002			("SVG-002"),
	SMIL_001		("SMIL-001"),
	SMIL_002		("SMIL-001");

	private final String messageId;
	
	MessageId(String feature)
	{
		this.messageId = feature;
	}
	
	public String toString()
	{
		return messageId;
	}
}
