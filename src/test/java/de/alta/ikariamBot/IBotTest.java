
package de.alta.ikariamBot;

import org.junit.Test;

import static de.alta.ikariamBot.IBot.MAX_SLEEP_DURATION_OFFSET_IN_SEC;
import static de.alta.ikariamBot.IBot.MIN_SLEEP_DURATION_IN_SEC;
import static org.junit.Assert.assertTrue;

public class IBotTest {

	@Test
	public void testSleepDuration()
	{
		final int duration = IBot.sleepDuration();
		
		assertTrue(MIN_SLEEP_DURATION_IN_SEC*60 <= duration);
		assertTrue(duration <= MAX_SLEEP_DURATION_OFFSET_IN_SEC);
	}
}
