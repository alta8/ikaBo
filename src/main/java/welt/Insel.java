package welt;

public class Insel {
	
	public final int MIN_ID = 0;
	public final int MAX_ID = 5721;
	
	private String name;
	private int x;
	private int y;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
