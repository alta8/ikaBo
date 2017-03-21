package de.alta.ikariamBot.parser;

import net.htmlparser.jericho.Element;
import welt.Insel;

public class InselParser extends Parser {

	private final Insel insel;
	
	public InselParser(ParserInput parserInput) {
		super(parserInput);
		insel = new Insel();
	}

	public Insel parse() {
		initName();
		initKoordinaten();
		return insel;
	}

	private void initName() {
		final Element nameElem = mSource.getElementById("js_islandBreadName");
		final String name = nameElem.getContent().toString();
		insel.setName(name);
	}

	private void initKoordinaten()
	{
		final Element xyElem = mSource.getElementById("js_islandBreadCoords");
		final String xy = xyElem.getContent().toString();
		final String[] parts = xy.split(":");
		final int x = Integer.parseInt(parts[0].substring(1));
		insel.setX(x);
		final int y = Integer.parseInt(parts[1].substring(0, parts.length));
		insel.setY(y);
	}
}
