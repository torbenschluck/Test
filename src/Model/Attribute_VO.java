package Model;

import java.sql.Connection;

public class Attribute_VO {
	
	private String attribute_name = "";
	private int attribute_id = 0;

public Attribute_VO(){
		
	}
	
	public Attribute_VO(String attribute_name){
		this.setAttribute_name(attribute_name);
	}

	public void setAttribute_name(String attribute_name) {
		this.attribute_name = attribute_name;
	}

	public String getAttribute_name() {
		return attribute_name;
	}

	public void setAttribute_id(int attribute_id) {
		this.attribute_id = attribute_id;
	}

	public int getAttribute_id() {
		return attribute_id;
	}		
}
