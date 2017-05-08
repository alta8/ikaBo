
package de.alta.ikariamBot;

import org.junit.Test;

import de.alta.ikariamBot.Util;

import static org.junit.Assert.assertTrue;

public class UtilTest {

	@Test
	public void testSleepDuration()
	{
		final int duration = Util.sleepDuration();
		
		assertTrue(Util.MIN_SLEEP_DURATION_IN_SEC <= duration);
		assertTrue(duration <= Util.MIN_SLEEP_DURATION_IN_SEC + Util.MAX_SLEEP_DURATION_OFFSET_IN_SEC);
	}
}
