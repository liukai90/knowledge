package knowledge.base.pojo;

import java.io.Serializable;

public class EntityAndAttributeData implements Serializable {
	
	private String object;
	
	private String property;
	
	private String value;

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
