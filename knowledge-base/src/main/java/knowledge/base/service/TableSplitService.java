package knowledge.base.service;

import java.util.List;

import knowledge.base.dao.AgendaDao;
import knowledge.base.dao.AttributeDataDao;
import knowledge.base.dao.ClassAttributeDao;
import knowledge.base.dao.ClassTableDao;
import knowledge.base.dao.EntityDao;
import knowledge.base.dao.ImposeDao;
import knowledge.base.dao.ObjectAttributeDao;
import knowledge.base.pojo.AttributeData;
import knowledge.base.pojo.ClassAttribute;
import knowledge.base.pojo.ClassTable;
import knowledge.base.pojo.Entity;
import knowledge.base.pojo.ImposeTable;
import knowledge.base.pojo.ObjectAttribute;

public class TableSplitService {
	
	public void insertImpose(String tableName)throws Exception{
		
		AgendaDao agendaDao=new AgendaDao();
		
		ImposeDao imposeDao=new ImposeDao();
		
		List<ImposeTable> imposeTables=agendaDao.getImposeInAgenda(tableName);
		
		imposeDao.insertBatchImpose(imposeTables);
		
	}
	
	public void insertObjectAttribute(String tableName)throws Exception{
		
		String objType="ObjectProperty";
		
		ObjectAttributeDao objectAttributeDao=new ObjectAttributeDao();
		
		AgendaDao agendaDao=new AgendaDao();
		
		List<ObjectAttribute> objectAttributes=
				(List<ObjectAttribute>) agendaDao.getsubpropertysInAgenda(
						agendaDao.getSuperpropertysInAgenda(
								agendaDao.getPropertysInAgenda(tableName, objType),
								tableName, objType), tableName, objType);
		
		objectAttributeDao.insertBatchObjectAttribute(objectAttributes);
		
	}
	
	public void insertClassAttribute(String tableName)throws Exception{
		
		String objType="DatatypeProperty";
		
		AgendaDao agendaDao=new AgendaDao();
		
		ClassAttributeDao classAttributeDao=new ClassAttributeDao();
		
		List<ClassAttribute> classAttributes=(List<ClassAttribute>) agendaDao.getsubpropertysInAgenda(
				agendaDao.getSuperpropertysInAgenda(
						agendaDao.getPropertysInAgenda(tableName, objType),
						tableName, objType), tableName, objType);
		
		classAttributeDao.insertBatchClassAttribute(classAttributes);
		
	}
	
	public void insertClassTable(String tableName)throws Exception{
		
		AgendaDao agendaDao=new AgendaDao();
		
		ClassTableDao classTableDao=new ClassTableDao();
		
		List<ClassTable> classTables=
				agendaDao.getsubclassInAgenda(agendaDao.getSuperclassInAgenda
						(agendaDao.getClassInAgenda(tableName), tableName), tableName);
		
		classTableDao.insertBatch(classTables);
		
	}
	
	public void insertEntity(String tableName)throws Exception{
		
		AgendaDao agendaDao=new AgendaDao();
		
		EntityDao entityDao=new EntityDao();
		
		List<Entity> entities=agendaDao.getEntityByClass(
				agendaDao.getObjectInAgenda(tableName), tableName);
		
		entityDao.insertBatch(entities);
		
	}
	
	public void insertAttributeData(String tableName)throws Exception{
		
		AgendaDao agendaDao=new AgendaDao();
		
		AttributeDataDao attributeDataDao=new AttributeDataDao();
		
		List<Entity> entities=agendaDao.getObjectInAgenda(tableName);
		
		List<AttributeData> attributeDatas=agendaDao.getAttributeByEntity(entities,tableName);
		
		attributeDataDao.insertBatch(attributeDatas);
		
	}

}
