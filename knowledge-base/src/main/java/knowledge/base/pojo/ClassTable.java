package knowledge.base.pojo;

import java.io.Serializable;

public class ClassTable implements Serializable{
	
	private Integer id;
	
	private String class_;
	
	private String superclass;
	
	private String subclass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}
	
	
	
	
	

}
