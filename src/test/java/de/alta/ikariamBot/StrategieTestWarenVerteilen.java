package de.alta.ikariamBot;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.alta.ikariamBot.Action;
import de.alta.ikariamBot.Environment;

public class StrategieTestWarenVerteilen 
{

	// TODO wie Mock ich meine Umgebung
	// TODO wie pack ich meine Configuration rein?
	// TODO workflow, wie führ ich die Aktionen durch?
	// TODO eigener Strategie Provider?
	
	Environment mEnvironment = new Environment();
	
	@Before
	public void init()
	{
//		mEnvironment;
	}
	
    @Test
    public void testKeinbedarf()
    {	
    	List<Action> actions = mEnvironment.verteileWaren();
    	
    	// so will ich das eher doch nicht... die methode selbst soll die Aktionen durchführen
    	// environment bekommt einen Prozessor... der die Aktionen durchführt
    	// den ich ggf. mocken kann.
    	
    	// Staedte setzen mit Bedarf
    	// nach aufruf... Staedte sind ok...
    	
    	Assert.assertNotNull("Aktionen Container ist null", actions);
    	
    }
    
}
