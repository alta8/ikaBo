package de.alta.ikariamBot;

public class Util {
	static final int MIN_SLEEP_DURATION_IN_SEC = 1;
	static final int MAX_SLEEP_DURATION_OFFSET_IN_SEC = 3;

	public static void sleep(final int duration) {
		try {
			final int durationInMSec = duration * 1000;
			Thread.sleep(durationInMSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int sleepDuration()
	{
		final int duration = (int)(Util.MIN_SLEEP_DURATION_IN_SEC 
				+ Util.MAX_SLEEP_DURATION_OFFSET_IN_SEC * Math.random());
		return duration;
	}
}
