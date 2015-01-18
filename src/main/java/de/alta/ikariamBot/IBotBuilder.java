package de.alta.ikariamBot;

import welt.MilitaerBerater;
import de.alta.ikariamBot.client.IkariamClient;

public class IBotBuilder {

	private IBot bot;
	
	public static IBotBuilder createInstance()
	{
		final IBotBuilder builder = new IBotBuilder();
		
		final IkariamClient client = new IkariamClient();
		builder.bot = new IBot(client);
				
		return builder;
	}
	
	public IBotBuilder addMilitaerBerater(MilitaerBerater militaerBerater)
	{
		bot.setMilitaerBerater(militaerBerater);
		return this;
	}
	
	public IBot build()
	{
		return bot;
	}
}
