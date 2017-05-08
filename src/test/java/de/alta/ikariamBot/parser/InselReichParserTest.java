package de.alta.ikariamBot.parser;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class InselReichParserTest {
	private InselReichParser parser;
	
	@Mock
	private InselParser inselParser;
	
	@Before 
	public void init()
	{
		parser = new InselReichParser(inselParser);
	}

	@Test
	public void whenSize_givenAll_then5() throws IOException
	{
		Assert.assertEquals(5, parser.parse().size());
	}
}
