package knowledge.base.pojo;

import java.io.Serializable;

public class ImposeTable implements Serializable {
	
	private Integer id;
	
	private String subj;
	
	private String obj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubj() {
		return subj;
	}

	public void setSubj(String subj) {
		this.subj = subj;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}
	
	

}
