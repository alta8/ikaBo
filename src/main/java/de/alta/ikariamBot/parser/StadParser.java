package de.alta.ikariamBot.parser;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import de.alta.ikariamBot.Gebaeude;
import de.alta.ikariamBot.Stadt;

public class StadParser extends Parser{

	private static final String GEBAEUDE_ID_PREFIX = "js_CityPosition";
	private static final String GEBAEUDE_ID_POSTFIX = "Link";

	public StadParser(ParserInput parserInput) {
		super(parserInput);
	}

	public Stadt parseStadt() {
		final Stadt stadt = new Stadt();
		
		final List<Gebaeude> gebaeudeListe = parseGebaeude();
		stadt.add(gebaeudeListe);
		
		return stadt;
	}

	private List<Gebaeude> parseGebaeude() {
		final List<Gebaeude> gebaeudeListe = new ArrayList<Gebaeude>();
		
		for (int cnt = 1; cnt<=17; cnt++) 
		{
		final String gebaeudeLinkID = GEBAEUDE_ID_PREFIX + cnt + GEBAEUDE_ID_POSTFIX;
//		final Element gebaeudeElment = mSource
//				.getElementById(gebaeudeID);
		
		final Element gebaeudeLink = mSource.getElementById(gebaeudeLinkID);
		final String url = gebaeudeLink.getAttributeValue("href");
		final Gebaeude gebaeude = createGebaeude(url);
		
		gebaeudeListe.add(gebaeude);
		}
		
		return gebaeudeListe;
	}

	/**
	 * Erstellt anhand der URL das passende Gebauede
	 * @param url
	 * @return
	 */
	private Gebaeude createGebaeude(String url) {
		final String view = Parser.urlParamValue(url, "view");
		final String position = Parser.urlParamValue(url, "position");
		final String cityId = Parser.urlParamValue(url, "cityId");
		final Gebaeude gebaeude = new Gebaeude();
		return gebaeude;
	}

}
