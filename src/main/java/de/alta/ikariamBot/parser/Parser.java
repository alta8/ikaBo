package de.alta.ikariamBot.parser;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.HTMLElements;
import net.htmlparser.jericho.Segment;
import de.alta.ikariamBot.City;
import de.alta.ikariamBot.Environment;
import de.alta.ikariamBot.Lager;
import de.alta.ikariamBot.Resource;
import net.htmlparser.jericho.Source;

public class Parser {

	Source mSource;

	public Parser(ParserInput parserInput) {
		mSource = parserInput.source();
		assert(null != mSource) : "Die Quelle sollte nicht null sein!";
	}
	

	public List<City> palast() {
		List<City> cityList = new ArrayList<City>();
		
		Element palace_div = mSource.getElementById("palace_c");
		
		List<Element> contentBoxes = palace_div.getAllElementsByClass("contentBox01h");
		
		assert (0 < contentBoxes.size()) : "mindestens ein ContentBox sollte vorhanden sein";
		
		for (Element e : contentBoxes)
		{
			List<Element> headers = e.getAllElementsByClass("header");
			List<Element> contents = e.getAllElementsByClass("content");
			
			assert(1 == headers.size()) : "Hätte hier nur einen Header erwartet";
			assert(1 == contents.size()) : "Hätte hier nur einen Content erwartet";
			
			if (headers.get(0).getContent().toString().toLowerCase().contains("städte deines imperiums"))
			{
				// ueber die Zeilen laufen
				final List<Element> trs = contents.get(0).getAllElements(HTMLElementName.TR);

	    		Map<Integer, String> headerMap = new HashMap<Integer, String>();
				for (int idx = 0; idx < trs.size(); idx++)
				{
					City city = null; 
					
					if (0 == idx)
					{
					    final List<Element> ths = trs.get(idx).getAllElements(HTMLElementName.TH);
					    
					    for (int thIdx = 0; thIdx < ths.size(); thIdx++)
					    {
					    	Element thEl = ths.get(thIdx);
					    	final Segment cntnt = thEl.getContent();
					    	if (null != cntnt && !"".equals(cntnt.toString()))
					    	{
					    		final String cnt = cntnt.toString();
					    		headerMap.put(thIdx, cnt);
					    	}
					    }
					}
					else
					{
						final List<Element> tds = trs.get(idx).getAllElements(HTMLElementName.TD);
						for (int tdIdx = 0; tdIdx < tds.size(); tdIdx++)
						{
							final String headerName = headerMap.get(tdIdx);
							if (null != headerName && !"".equals(headerName.trim()))
							{
								// Stadt Name
								if (1==tdIdx && headerName.equalsIgnoreCase("Stadt"))
									city = new City(tds.get(tdIdx).getContent().toString());
								
								// Location
								if (4==tdIdx && headerName.equalsIgnoreCase("Insel"))
								{
									final String islandLoc = tds.get(tdIdx).getContent().toString().trim();
									city.setIsland(islandLoc.split("\\s")[0]);
									String[] coords = islandLoc.split("\\s")[1].split(":");
									String coordStr = coords[0].substring(1);
									city.setX(Integer.parseInt(coordStr));
									coordStr = coords[1].substring(0, coords[1].length()-1);
									city.setY(Integer.parseInt(coordStr));
								}
								
								// Resource
								if (5==tdIdx && headerName.equalsIgnoreCase("Rohstoff"))
								{
									List<Element> children = tds.get(tdIdx).getChildElements();
									assert(1 == children.size()) :  "ich erwarte ein img-tag";
									final String alt = children.get(0).getAttributeValue("alt");

									Resource resource = imgAlt2Resource(alt);
									city.setResource(resource);
								}
							}
						}

						cityList.add(city);
					}
				}
			}
		}

		return cityList;
	}

	static protected Resource imgAlt2Resource(final String alt) {
		Resource resource = null;
		for (Resource res : Resource.values())
		{
		    resource = (alt.toLowerCase().contains(res.toString().toLowerCase()) ? res : resource);
	    }
		return resource;
	}

	/**
	 * 
	 * @return Array mit 2 Elementen, erstes Anzahl der verfügbaren, zweites Gesammt-Anzahl der Schiffe
	 */
	public int[] schiffe() {
		final int[] schiffeAmnt = {0, 0};

		assert(null != mSource) : "Quelle sollte nciht null sein.";
		
		Element freeTransporterTag = mSource.getElementById("js_GlobalMenu_freeTransporters");
		schiffeAmnt[0] = parseElement2Int(freeTransporterTag);
		
		Element allTransporterTag = mSource.getElementById("js_GlobalMenu_maxTransporters");
		schiffeAmnt[1] = parseElement2Int(allTransporterTag);
		
		return schiffeAmnt;
	}

	public Lager lager() {
		Lager lager = new Lager();
		
		List<Element> table01 = mSource.getAllElementsByClass("table01");
		assert (1 <= table01.size()) : "Hätte mindestens ein table01 erwartet";
		
		// lesen der Menge der nicht klaubaren Resourcen
		{
			List<Element> elements = table01.get(0).getAllElementsByClass("sicher");
			assert (1 <= elements.size()) : "Hätte mindestens ein sicher erwartet";
			
			elements = elements.get(0).getAllElementsByClass("secure");
			assert (1 <= elements.size()) : "Hätte mindestens ein secure erwartet";

			int sicher = parseElement2Int(elements.get(0));
			
			lager.setSicher(sicher);		
		}
		
		// lesen der gesammten Resourcen
		{
			List<Element> elements = table01.get(0).getAllElementsByClass("gesamt");
			assert (1 <= elements.size()) : "Hätte mindestens ein gesammt erwartet";
			
			elements = elements.get(0).getAllElementsByClass("amountTable");
			assert (1 == elements.size()) : "Hätte nur einen amountTable erwartet";
			
			List<Element> tds = elements.get(0).getAllElements(HTMLElementName.TD);
			assert (2 == tds.size()) : "Erwarte 2, eines fuer das img und eines fuer die Anzahl.";
			
			final int amnt = parseElement2Int(tds.get(1));
			
			List<Element> imgs = tds.get(0).getAllElements(HTMLElementName.IMG);
			assert (1 == imgs.size()) : "Erwarte nur ein img.";
			
			final String imgAlt = imgs.get(0).getAttributeValue("alt");
			if (Resource.Holz.toString().equalsIgnoreCase(imgAlt))
			{
			    lager.setHolz(amnt);
			}
		}
		
		return lager;
	}

	private int parseElement2Int(Element element) {
		int val = 0;
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		try {
			val = nf.parse(element.getContent().toString().trim()).intValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}

	public int actionPoints() {
		final Element actionPntTag = mSource.getElementById("js_GlobalMenu_maxActionPoints");
		final int actionPnts = parseElement2Int(actionPntTag);
		return actionPnts;
	}


	/**
	 * Extrahiert den Wert eines Parameters aus der URL.
	 * @param url
	 * @param param
	 * @return
	 */
	static String urlParamValue(String url, String param) {
		final String searchStr = param+"=";
		final int idxParam = url.indexOf(searchStr);
		final int idxParamEnd = url.indexOf("&", idxParam);
		final int endIdx;
		if (0>idxParamEnd)
			endIdx = url.length();
		else
			endIdx = idxParamEnd;
		final String value = (String) url.subSequence(idxParam+searchStr.length(), endIdx);
		return value;
	}

	
}
