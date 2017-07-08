package de.alta.ikariamBot.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import net.htmlparser.jericho.Element;
import welt.City;
import welt.Insel;
import welt.Resource;

public class InselParser extends Parser {

	private final Insel insel;
	
	public InselParser(ParserInput parserInput) {
		super(parserInput);
		insel = new Insel();
	}

	public Insel parse() {
		initID();
		initName();
		initKoordinaten();
		initCities();
		initWald();
		initTradegood();
		return insel;
	}

	private void initID() {
		final List<Element> elements = mSource.getAllElements("name", "currentIslandId", false);
		final Optional<Integer> idOpt = elements.stream().filter(e -> e.getAttributeValue("value") != null).findFirst().map(e -> e.getAttributeValue("value")).map(str -> Integer.parseInt(str));
		idOpt.ifPresent(id -> insel.setID(id));
	}

	private void initName() {
		final Element nameElem = mSource.getElementById("js_islandBreadName");
		final String name = nameElem.getContent().toString();
		insel.setName(name);
	}

	private void initKoordinaten()
	{
		final Element xyElem = mSource.getElementById("js_islandBreadCoords");
		final String xy = xyElem.getContent().toString().trim();
		final String[] parts = xy.split(":");
		try {
			final int x = Integer.parseInt(parts[0].substring(1));
			insel.setX(x);
			int endIdx = parts[1].length();
			final char endChar = parts[1].charAt(endIdx-1);
			if (endChar < '0' || endChar > '9') {
				endIdx = parts[1].length()-1;
			}
			final int y = Integer.parseInt(parts[1].substring(0, endIdx));
			insel.setY(y);
		} catch (NumberFormatException e) {
			System.out.println(xy + " kann nicht weiterverarbeitet werden:");
			e.printStackTrace();
		}
	}
	
	private void initCities() {
		final List<City> cities = new ArrayList<>();
		IntStream.rangeClosed(0, 16).mapToObj(i -> initCity(i)).forEach(c -> cities.add(c));
		insel.setCities(cities.toArray(new City[1]));
	}

	private City initCity(int idx) {
		final Element cityElem = mSource.getElementById("js_cityLocation" + idx + "TitleText");
		final String name = cityElem.getContent().toString();
		final City city = new City(name);
		
		final List<Element> scriptsElem = mSource.getAllElements("script");
		for (Element script : scriptsElem)
		{
			final String content = script.getContent().toString();
			if (content.contains("updateBackgroundData"))
			{
				insel.setJSON(content);
			}
		}

		return city;
	}
	
	private void initWald() {
		insel.addResource(Resource.Holz);
	}
	
	private void initTradegood() {
		final Element elem = mSource.getElementById("islandtradegood");
		final String str = elem.getAttributeValue("class");
		insel.addResource(tradegoodToResource(str));
		
	}

	private Resource tradegoodToResource(String tradegoodString) {
		if (tradegoodString.toLowerCase().contains("crystal")) return Resource.Kristal;
		if (tradegoodString.toLowerCase().contains("sulfur")) return Resource.Schwefel;
		if (tradegoodString.toLowerCase().contains("marble")) return Resource.Stein;
		if (tradegoodString.toLowerCase().contains("wine")) return Resource.Wein;
		return null;
	}
}
