package de.alta.ikariamBot.parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.htmlparser.jericho.Source;

public class ParserInput {

	private String mUrl;

	public ParserInput(String url) {
		mUrl = url;
	}

	public Source source()
	{
		Source source = null;
		try {
			source = new Source(new URL(mUrl));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return source;
	}
	
	
}
