package welt;

import de.alta.ikariamBot.IBot;

public class City {

	private String mName;
	private Resource mResource;
	private String mIsland;
	private int mX;
	private int mY;
	private boolean inaktive;
	
	public City(String name)
	{
		mName = name;
	}
	
	public CharSequence getName() {
		return mName;
	}

	public Resource getResource() {
		return mResource;
	}
	
	public void setResource(Resource resource) {
		mResource = resource;
	}

	public String getIsland() {
		return mIsland;
	}

	public void setIsland(String islandLoc) {
		mIsland = islandLoc;
	}

	public int getX() {
		return mX;
	}
	
	public void setX(int x)
	{
		mX = x;
	}

	public int getY() {
		return mY;
	}
	
	public void setY(int y)
	{
		mY = y;
	}

	public int getActionPoints() {
		// TODO Auto-generated method stub
		return 99;
	}

	public void setInaktive(boolean inaktive) {
		this.inaktive = inaktive;
	}
	
	public boolean isInaktive() {
		return this.inaktive;
	}

}
