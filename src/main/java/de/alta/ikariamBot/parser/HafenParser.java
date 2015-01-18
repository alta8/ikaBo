package de.alta.ikariamBot.parser;

import java.util.Calendar;
import java.util.List;

import welt.Hafen;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

public class HafenParser extends Parser{

	private Source mSource;

	public HafenParser(ParserInput parserInput) {
		super(parserInput);
	}

	public Hafen parseHafen() {
		final Hafen hafen = new Hafen();

		final List<Element> contentBoxes = mSource
				.getAllElementsByClass("contentBox01h");
		boolean blockiert = false;
		for (Element content : contentBoxes) {
			final List<Element> headers = content
					.getAllElementsByClass("header");
			for (Element header : headers) {
				blockiert |= header.getContent().toString().toLowerCase()
						.contains("schmuggelkontingent");
			}

			if (blockiert) {
				final List<Element> infoBoxes = content
						.getAllElementsByClass("warning red_box");
				for (Element infoBox : infoBoxes) {
					final String infotxt = infoBox.getContent().toString();
					final String[] words = infotxt.split("\\s");
					
					int idx = 0;
					final Calendar bis = Calendar.getInstance();
					for (String word : words) {
						try {
							if (word != null && !"".equals(word)
									&& Character.isDigit(word.charAt(0))) {
								System.out.println(word);
								final int val = Integer.parseInt(word);
								switch (idx) {
								case 0:
									hafen.setSchmuggelkontingentVerbleibend(val);
									break;
								case 1:
									hafen.setSchmuggelkontingent(val);
									break;
								case 2:
									hafen.setSchmuggelkontingentVerbleibendHour(val);
									break;
								case 3:
									final double hour = hafen.getSchmugelkontingentVerbleibendHour();
									hafen.setSchmuggelkontingentVerbleibendHour(hour+(1/60*val));
									break;
								case 4:
								default:
									break;
								}
								idx++;
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		hafen.setBlockiert(blockiert);

		return hafen;
	}

}
