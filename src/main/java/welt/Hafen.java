package welt;

import java.util.Calendar;

public class Hafen {

	private boolean mBlockiert;
	private int mSchmuggelkontingent;
	private double mSchmuggelkontingentVerbleibendHour;
	private int mSchmuggelkontingentVerbleibend;

	public boolean isBlockiert() {
		return mBlockiert;
	}

	public void setBlockiert(boolean blockiert) {
		mBlockiert = blockiert;
	}

	public int getSchmuggelkontingent() {
		return mSchmuggelkontingent;
	}
	
	public void setSchmuggelkontingent(int schmuggelkontingent)
	{
		mSchmuggelkontingent = schmuggelkontingent;
	}

	public double getSchmugelkontingentVerbleibendHour()
	{
		return mSchmuggelkontingentVerbleibendHour;
	}
	
	public void setSchmuggelkontingentVerbleibendHour(double schmuggelkontingentVerbleibendHour) {
		mSchmuggelkontingentVerbleibendHour = schmuggelkontingentVerbleibendHour;
	}

	public int getSchmuggelkontingentVerbleibend()
	{
		return mSchmuggelkontingentVerbleibend;
	}
	
	public void setSchmuggelkontingentVerbleibend(int schmugelkontingentVerbleibend) {
		mSchmuggelkontingentVerbleibend = schmugelkontingentVerbleibend;
	}

}
