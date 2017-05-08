package welt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Insel {
	
	private String name;
	private int x;
	private int y;
	private City[] cities;
	private Set<Resource> resources = new HashSet<>();
	private int id;
	private String json;

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

	public void setCities(City[] cities) {
		this.cities = cities;
	}
	
	public City[] getCities() {
		return this.cities;
	}

	public void addResource(Resource resource) {
		resources.add(resource);
	}
	
	public Set<Resource> getResources() {
		return resources;
	}

	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return name + " " + x + "/" + y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Insel) {
			Insel other = (Insel) obj;
			return this.getName().equals(other.getName())
				&& this.getX() == other.getX()
				&& this.getY() == other.getY();
		}
		return false;
	}
	
	public String getJSON() {
		return json;
	}

	public void setJSON(String json) {
		this.json = json;
	}
 }
