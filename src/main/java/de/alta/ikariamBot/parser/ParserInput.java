package de.alta.ikariamBot.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import net.htmlparser.jericho.Source;

public class ParserInput {

	private String mUrl;
	private InputStream inputStream;

	public ParserInput(String url) {
		mUrl = url;
	}

	public ParserInput(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Source source()
	{
		Source source = null;
		try {
			if (mUrl != null) {
				source = new Source(new URL(mUrl));
			} 
			else
			{
//				int c = 0;
//				final StringBuilder source2 = new StringBuilder();
//				while ((c = inputStream.read()) != -1)
//					source2.append((char)c);
//				String content = source2.toString();
				source = new Source(inputStream);
			}
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
