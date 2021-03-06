package de.alta.ikariamBot.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import welt.City;
import welt.Lager;
import welt.Resource;


public class ParserTest {

	public static final String PROTOCOLL = "file";
	public static final String PAGES_DIR = "src/test/resources/pages/";

//	@Test
	public void testPalast()
	{
		Parser p = new Parser(new ParserInput(PROTOCOLL+":" + PAGES_DIR + "index-php_action_loginAvatar-function_login"));
		
		Assert.assertEquals(4, p.palast().size());

		int cnIdx = -1;
		final String[] cityNames = {"Dingsdorf", "Steinach", "Weinblatt", "Chimney"};
		final Resource[] resources = {Resource.Kristal, Resource.Stein, Resource.Wein, Resource.Schwefel};
		final String[] islands = {"Inaoeos", "Drodios", "Pewios", "Criroios"};
		final int[] coordX = {59, 58, 59, 58};
		final int[] coordY = {42, 41, 41, 40};
		
		for (City c : p.palast())
		{
//		    Assert.assertTrue(cityNames.contains(c.getName()));
		    ++cnIdx;
		    Assert.assertEquals(cityNames[cnIdx], c.getName());
		    Assert.assertEquals(resources[cnIdx], c.getResource());
		    Assert.assertEquals(islands[cnIdx], c.getIsland());
		    Assert.assertEquals(coordX[cnIdx], c.getX());
		    Assert.assertEquals(coordY[cnIdx], c.getY());
		}
	}
	
//	@Test
	public void testActionPoints()
	{
		Parser parser = new Parser(new ParserInput("file:src/test/resources/pages/index.php_lagerhaus.html"));
		
		Assert.assertEquals(parser.actionPoints(), 4);
		
	}
	
	@Test
	public void testImgAlt2Resource()
	{
		Assert.assertEquals(Resource.Stein, Parser.imgAlt2Resource("Steinbruch"));
		Assert.assertEquals(Resource.Wein, Parser.imgAlt2Resource("Weinnudel"));
		Assert.assertEquals(Resource.Kristal, Parser.imgAlt2Resource("Kristallmine"));
		Assert.assertEquals(Resource.Schwefel, Parser.imgAlt2Resource("Schwefelgrube"));
	}
	
	@Test
	public void testResourcen()
	{
//		new Parser(new ParserInput("index-php_action_loginAvatar-function_login"));
	}
	
//	@Test
	public void testSchiffe()
	{
		final int VERFUEGBAR_IDX = 0;
		final int GESAMMT_IDX = 1;
		
		final int[] schiffe = {14, 15};
		
		Parser parser = new Parser(new ParserInput("file:src/test/resources/pages/index.php_lagerhaus.html"));
		final int[] result = parser.schiffe();
		
		Assert.assertEquals(schiffe[VERFUEGBAR_IDX], result[VERFUEGBAR_IDX]);
		Assert.assertEquals(schiffe[GESAMMT_IDX], result[GESAMMT_IDX]);
	}
	
//	@Test
	public void testLager()
	{
		Parser parser = new Parser(new ParserInput("file:src/test/resources/pages/index.php_lagerhaus.html"));
		final Lager lager = parser.lager();
		
		Assert.assertEquals(5860, lager.getSicher());
		Assert.assertEquals(41199, lager.getHolz());
	}
	
	@Test
	public void testUrlParamValue()
	{
		String url = "http://s17-de.ikariam.gameforge.com/index.php?view=port&cityId=100351&position=1";
		final String view = Parser.urlParamValue(url, "view");
		assertEquals("port", view);
		final String cityId = Parser.urlParamValue(url, "cityId");
		assertEquals("100351", cityId);
		final String position = Parser.urlParamValue(url, "position");
		assertEquals("1", position);
		
	}
}
