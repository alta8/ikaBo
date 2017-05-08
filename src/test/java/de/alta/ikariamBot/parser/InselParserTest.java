package de.alta.ikariamBot.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import welt.Insel;

public class InselParserTest {

	InselParser ip;
	Insel insel;
	
//	@Before
//	public void init()
//	{
//		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_03_98_id5721.html"));
//		insel = ip.parse();
//	}
	
	@Test
	public void testName()
	{
		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_03_98_id5721.html"));
		insel = ip.parse();
		assertEquals("Smeedios", insel.getName());
		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_43_53_id87.html"));
		insel = ip.parse();
		assertEquals("Creyios", insel.getName());
	}

	@Test
	public void testX()
	{
		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_03_98_id5721.html"));
		insel = ip.parse();
		assertEquals(3, insel.getX());
		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_43_53_id87.html"));
		insel = ip.parse();
		assertEquals(43, insel.getX());
	}

	@Test
	public void testY()
	{
		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_03_98_id5721.html"));
		insel = ip.parse();
		assertEquals(98, insel.getY());
	}
	
	@Test
	public void testCity()
	{
		ip = new InselParser(new ParserInput("file:src/test/resources/pages/insel_43_53_id87.html"));
		insel = ip.parse();
		assertEquals(17, insel.getCities().length);
		assertTrue(insel.getCities()[5].getName().equals("Jolis (i)"));
		assertTrue(insel.getCities()[5].isInaktive());
	}
}
