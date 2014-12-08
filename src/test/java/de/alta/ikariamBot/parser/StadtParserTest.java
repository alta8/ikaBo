package de.alta.ikariamBot.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.alta.ikariamBot.Stadt;

public class StadtParserTest {

	@Test
	public void testGebaeude()
	{
		final StadParser hp = new StadParser(new ParserInput(ParserTest.PROTOCOLL + ":" + ParserTest.PAGES_DIR + "index.php_stadt_besetzt_blockiert_1.html"));
    	final Stadt stadt = hp.parseStadt();
    	
    	assertEquals(17, stadt.getGebaeudeListe().size());
	}
}
