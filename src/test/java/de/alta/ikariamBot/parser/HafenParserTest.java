package de.alta.ikariamBot.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.alta.ikariamBot.Hafen;

public class HafenParserTest {

	@Test
	public void testBlockiert()
	{
    	final HafenParser hp = new HafenParser(new ParserInput("file:src/test/resources/pages/index.php_hafen_blockiert_1.html"));
    	final Hafen hafen = hp.parseHafen();
    	
    	assertTrue(hafen.isBlockiert());
    	assertEquals(8000, hafen.getSchmuggelkontingent());
    	assertEquals(8000, hafen.getSchmuggelkontingentVerbleibend());
    	assertEquals(8+5/6, hafen.getSchmugelkontingentVerbleibendHour(), 0.01);
    	
	}
	
	public void testNichtBlockiert()
	{
		final HafenParser hp = new HafenParser(new ParserInput("file:src/test/resources/pages/index-php_hafen.html"));
		final Hafen hafen = hp.parseHafen();
		
		assertTrue(!hafen.isBlockiert());
	}
}
