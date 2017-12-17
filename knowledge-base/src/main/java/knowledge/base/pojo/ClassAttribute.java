package knowledge.base.pojo;

import java.io.Serializable;

public class ClassAttribute implements Serializable {
	
	private Integer id;
	
	private String property;
	
	private String superproperty;
	
	private String subproperty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getSuperproperty() {
		return superproperty;
	}

	public void setSuperproperty(String superproperty) {
		this.superproperty = superproperty;
	}

	public String getSubproperty() {
		return subproperty;
	}

	public void setSubproperty(String subproperty) {
		this.subproperty = subproperty;
	}
	
	

}
