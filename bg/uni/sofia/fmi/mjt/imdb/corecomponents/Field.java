<<<<<<< HEAD
package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private String name;
	private List<String> attributes = new ArrayList<>();
	
	public Field(String name) {
		this.name = name;
	}
	
	public void addAttribute(String attribute) {
		attributes.add(attribute.toLowerCase());
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getAttributes() {
		return attributes;
	}
}
=======
package bg.uni.sofia.fmi.mjt.imdb.corecomponents;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private String name;
	private List<String> attributes = new ArrayList<>();
	
	public Field(String name) {
		this.name = name;
	}
	
	public void addAttribute(String attribute) {
		attributes.add(attribute.toLowerCase());
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getAttributes() {
		return attributes;
	}
}
>>>>>>> 3d073b38f6e2da80cf7ee58169d9aae5b7b9ef48
