package knowledge.base.service;

import java.util.ArrayList;
import java.util.List;

import knowledge.base.dao.ClassTableDao;
import knowledge.base.dao.EntityDao;
import knowledge.base.pojo.ClassAndEntity;
import knowledge.base.pojo.ClassTable;
import knowledge.base.pojo.EntityAndAttributeData;

public class FindService {
	
	
	public List<ClassAndEntity> getClassAndEntitiesByClass(String class_)throws Exception{
		
		ClassTableDao classTableDao=new ClassTableDao();
		
		return classTableDao.getClassAndEntity(class_);
		
	}

	public List<EntityAndAttributeData> getEntityAndAttributeDatasByObject(String object){
		
		EntityDao entityDao=new EntityDao();
		
		return entityDao.getEntityAndAttributeDates(object);
	}

}
