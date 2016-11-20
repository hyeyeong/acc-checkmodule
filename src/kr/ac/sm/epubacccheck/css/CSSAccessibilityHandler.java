package kr.ac.sm.epubacccheck.css;

import java.util.List;

import org.idpf.epubcheck.util.css.CssContentHandler;
import org.idpf.epubcheck.util.css.CssErrorHandler;
import org.idpf.epubcheck.util.css.CssExceptions.CssException;
import org.idpf.epubcheck.util.css.CssGrammar;
import org.idpf.epubcheck.util.css.CssGrammar.CssAtRule;
import org.idpf.epubcheck.util.css.CssGrammar.CssDeclaration;
import org.idpf.epubcheck.util.css.CssGrammar.CssSelector;

public class CSSAccessibilityHandler implements CssContentHandler, CssErrorHandler
{
	private boolean hasVisibility = false;

	@Override
	public void error(CssException e) throws CssException
	{
		// TODO Auto-generated method stub
		e.printStackTrace();
	}

	@Override
	public void startDocument()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void startAtRule(CssAtRule atRule)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void endAtRule(String name)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void selectors(List<CssSelector> selectors)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void endSelectors(List<CssSelector> selectors)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void declaration(CssDeclaration declaration)
	{
		// TODO Auto-generated method stub
		String cssAttribute = declaration.getName().get();

		// CSS-002
		if (cssAttribute.equals("cursor"))
		{
			System.out.println("line: " + declaration.getLocation().getLine());
		}
		
		// CSS-003
		if (cssAttribute.equals("overflow"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || "hidden".equals(cssc.toCssString()))
				{
					System.out.println("error: overflow hidden - " + cssc.getLocation().getLine());
				}
			}
		}
		
		// CSS-004
		if (cssAttribute.equals("width"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || "0px".equals(cssc.toCssString()))
				{
					System.out.println("error: width 0px - " + cssc.getLocation().getLine());
				}
			}
		}
		
		// CSS-005
		if (cssAttribute.equals("background-image"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().equals("") || cssc.toCssString().equals("url('')") || cssc.toCssString().equals("url(\"\")") || cssc.toCssString().equals("none"))
				{
					System.out.println("error: background image - " + cssc.getLocation().getLine());
				}
			}
		}
		
		// CSS-006
		if (cssAttribute.equals("font-size"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().contains("pt") || cssc.toCssString().contains("px") || cssc.toCssString().contains("x-small"))
				{
					System.out.println("error: font size - " + cssc.getLocation().getLine());
				}
			}
		}
		
		// CSS-007
		if (cssAttribute.equals("text-align"))
		{
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().equals("justify"))
				{
					System.out.println("error: text align - " + cssc.getLocation().getLine());
				}
			}
		}
		
		// CSS-008 sup - with NOTE-001
		// CSS-009 fixed layout

		// CSS-010 background-color contrast - STYLE-003
		if (cssAttribute.equals("background-color"))
		{
			// WCAG20 contrast ratio
			;
		}
		
		// CSS-011
		if (cssAttribute.equals("visibility") || cssAttribute.equals("display"))
		{
			hasVisibility = true;
			
			for (CssGrammar.CssConstruct cssc : declaration.getComponents())
			{
				if (cssc.toCssString() == null || cssc.toCssString().equals("hidden") || cssc.toCssString().equals("none"))
				{
					System.out.println("error: visibility - " + cssc.getLocation().getLine());
				}
			}
		}
	}
	
	@Override
	public void endDocument()
	{
		// TODO Auto-generated method stub
		if (!hasVisibility)
		{
			//System.out.println(x);
		}
	}
}
