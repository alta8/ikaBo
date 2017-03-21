package de.alta.ikariamBot.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import welt.Insel;

public class InselParserTest {

	InselParser ip;
	Insel insel;
	
	@Before
	public void init()
	{
		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_03_98_id5721.html"));
		insel = ip.parse();
	}
	
	@Test
	public void testName_thenSmeedios()
	{	
		assertEquals("Smeedios", insel.getName());
	}

	@Test
	public void testX_then3()
	{
		assertEquals(3, insel.getX());
	}

	@Test
	public void testY_then98()
	{
		assertEquals(98, insel.getY());
	}
}
