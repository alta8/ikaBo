package de.alta.ikariamBot;

public enum Resource {
	Holz("Baumaterial"), Stein("Stein"), Wein("Wein"), Kristal("Kristal"), Schwefel("Schwefel");
	
	String mStrVal;
	
	private Resource(String str)
	{
		mStrVal = str;
	}
	
	public String toString() {
		return mStrVal;
	}
	
}
