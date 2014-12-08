package de.alta.ikariamBot;

import java.util.ArrayList;
import java.util.List;

public class Stadt {

	final private List<Gebaeude> mGebaeudeListe = new ArrayList<Gebaeude>();
	
	/**
	 * Liefert Liste aller Gebaeude incl. freien Bauplaetze.
	 * @return
	 */
	public List<Gebaeude> getGebaeudeListe() {
		return mGebaeudeListe;
	}

	public void add(List<Gebaeude> gebaeudeListe) {
		mGebaeudeListe.addAll(gebaeudeListe);
	}
	
}
