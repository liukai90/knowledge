package knowledge.base.pojo;

import java.io.Serializable;

public class Entity implements Serializable {
	
	private Integer id;
	
	private Integer class_id;
	
	private String object;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClass_id() {
		return class_id;
	}

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}
	
	

}
