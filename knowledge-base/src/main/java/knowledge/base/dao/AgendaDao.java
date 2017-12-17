package knowledge.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import knowledge.base.pojo.AttributeData;
import knowledge.base.pojo.ClassAttribute;
import knowledge.base.pojo.ClassTable;
import knowledge.base.pojo.Entity;
import knowledge.base.pojo.ImposeTable;
import knowledge.base.pojo.ObjectAttribute;
import knowledge.base.util.DButil;
import knowledge.base.util.StringUtil;

public class AgendaDao {
	
	public List<ImposeTable> getImposeInAgenda(String tableName){
		
		String sql="SELECT * FROM "+tableName+" WHERE prop='domain:' OR prop='range:'";
		
		Connection con=null;
		
		Statement stat=null;
		
		ResultSet rs=null;
				
		try {
			
			List<ImposeTable> list=new ArrayList<ImposeTable>();
			
			con=DButil.getConnection();
			
			stat=con.createStatement();
			
			rs=stat.executeQuery(sql);
			
			while(rs.next()){
				
				ImposeTable it=new ImposeTable();
				
				it.setSubj(rs.getString("subj"));
				
				it.setObj(rs.getString("obj"));
				
				list.add(it);
			}
			
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		
		return null;		
	}
	
	public List<?> getPropertysInAgenda(String tableName,String objType){
		
		String sql="SELECT subj FROM "+tableName+" WHERE obj="+"'"+objType+"'";
				
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			if(objType=="ObjectProperty"){
				
				List<ObjectAttribute> propertys=new ArrayList<ObjectAttribute>();
				
				while(rs.next()){
					
					ObjectAttribute objectAttribute=new ObjectAttribute();
					
					objectAttribute.setProperty(rs.getString("subj"));
					
					propertys.add(objectAttribute);
					
					
				}
				
				return propertys;
				
			}else{
				
				List<ClassAttribute> propertys=new ArrayList<ClassAttribute>();
				
				while(rs.next()){
					
					ClassAttribute classAttribute=new ClassAttribute();
					
					classAttribute.setProperty(rs.getString("subj"));
					
					propertys.add(classAttribute);
					
					
				}
				
				return propertys;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		return null;
	}
	
	public List<?> getSuperpropertysInAgenda(List<?> propertys,
			String tableName,String objType){
		
		String sql="SELECT obj FROM "+tableName+
				" WHERE prop='subPropertyOf:' AND subj=?";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			if(objType=="ObjectProperty"){
				
				List<ObjectAttribute> list=(List<ObjectAttribute>) propertys;
				
				List<ObjectAttribute> objectAttributes=new ArrayList<ObjectAttribute>();
				
				for(ObjectAttribute objectAttribute:list){
					
					ps.setString(1, objectAttribute.getProperty());
					
					rs=ps.executeQuery();
					
					if(rs.next()){
						
						objectAttribute.setSuperproperty(rs.getString("obj"));
						
						objectAttributes.add(objectAttribute);
					
						}else{
							
							objectAttribute.setSuperproperty("无");
							
							objectAttributes.add(objectAttribute);
					}
					
				}
				
				return objectAttributes;
				
			}else{
				
				List<ClassAttribute> list=(List<ClassAttribute>) propertys;
				
				List<ClassAttribute> classAttributes=new ArrayList<ClassAttribute>();
				
				for(ClassAttribute classAttribute:list){
					
					ps.setString(1, classAttribute.getProperty());
					
					rs=ps.executeQuery();
					
					if(rs.next()){
						
						classAttribute.setSuperproperty(rs.getString("obj"));
						
						classAttributes.add(classAttribute);
					
						}else{
							
							classAttribute.setSuperproperty("无");
							
							classAttributes.add(classAttribute);
					}
					
				}
				
				return classAttributes;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		
		return null;
	}
	
	public List<?> getsubpropertysInAgenda(List<?> propertys,String tableName,String objType){
		
		String sql="SELECT subj FROM "+tableName+
				" WHERE prop="+"'subPropertyOf:'"+" AND obj=?";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			if(objType=="ObjectProperty"){
				
				List<ObjectAttribute> list=(List<ObjectAttribute>) propertys;
				
				List<ObjectAttribute> objectAttributes=new ArrayList<ObjectAttribute>();
				
				for(ObjectAttribute objectAttribute:list){
					
					String property=StringUtil.strFomat(objectAttribute.getProperty());
					
					objectAttribute.setProperty(property);
					
					ps.setString(1,property);
					
					rs=ps.executeQuery();
					
					if(rs.next()){
						
						String subproperty="";
						
						do{
							
							subproperty=subproperty+StringUtil.strFomat(rs.getString("subj"))+" ";
							
						}while(rs.next());
						
						objectAttribute.setSubproperty(subproperty);
					}else{
						
						objectAttribute.setSubproperty("无");
					}
					
					objectAttributes.add(objectAttribute);
				}
				
				return objectAttributes;
				
			}else{
				
				List<ClassAttribute> list=(List<ClassAttribute>) propertys;
				
				List<ClassAttribute> classAttributes=new ArrayList<ClassAttribute>();
				
				for(ClassAttribute classAttribute:list){
					
					String property=StringUtil.strFomat(classAttribute.getProperty());
					
					classAttribute.setProperty(property);
					
					ps.setString(1,property);
					
					rs=ps.executeQuery();
					
					if(rs.next()){
						
						String subproperty="";
						
						do{
							
							subproperty=subproperty+StringUtil.strFomat(rs.getString("subj"))+" ";
							
						}while(rs.next());
						
						classAttribute.setSubproperty(subproperty);
					}else{
						
						classAttribute.setSubproperty("无");
					}
					
					classAttributes.add(classAttribute);
				}
				
				return classAttributes;
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		
		return null;
	}
	
	public List<ClassTable> getClassInAgenda(String tableName){
		
		String sql="SELECT subj FROM "+tableName+" WHERE obj='Class'";
		
		Connection con=null;
		
		Statement stat=null;
		
		ResultSet rs=null;
		
		try {
			
			List<ClassTable> classTables=new ArrayList<ClassTable>();
			
			con=DButil.getConnection();
			
			stat=con.createStatement();
			
			rs=stat.executeQuery(sql);
			
			while(rs.next()){
				
				ClassTable classTable=new ClassTable();
				
				classTable.setClass_(rs.getString("subj"));
				
				classTables.add(classTable);
				
			}
			
			return classTables;
						
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		return null;
		
	}
	
	public List<ClassTable> getSuperclassInAgenda(List<ClassTable> classes,String tableName){
		
		String sql="SELECT obj FROM "+tableName+" WHERE prop='subClassOf:' AND subj=?";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			List<ClassTable> classTables=new ArrayList<ClassTable>();
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			for(ClassTable classTable:classes){
				
				ps.setString(1, classTable.getClass_());
				
				rs=ps.executeQuery();
				
				if(rs.next()){
					
					classTable.setSuperclass(rs.getString("obj"));
					
				}else{
					
					classTable.setSuperclass("无");
				}
				
				classTables.add(classTable);
				
			}
			
			return classTables;
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DButil.closeConnection(con);
		}
		
		return null;
		
		
	}
	
	public List<ClassTable> getsubclassInAgenda(List<ClassTable> classes,String tableName){
		
		String sql="SELECT subj FROM "+tableName+" WHERE prop='subClassOf:' AND obj=?";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			List<ClassTable> classTables=new ArrayList<ClassTable>();
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			for(ClassTable classTable:classes){
				
				String class_=StringUtil.strFomat(classTable.getClass_());
				
				classTable.setClass_(class_);
				
				ps.setString(1, class_);
				
				rs=ps.executeQuery();
				
				if(rs.next()){
					
					String subclass="";
					
					do{
						
						subclass=subclass+StringUtil.strFomat(rs.getString("subj"))+" ";
						
					}while(rs.next());
					
					classTable.setSubclass(subclass);
					
				}else{
					
					classTable.setSubclass("无");
					
				}
				
				classTables.add(classTable);
			}
			
			return classTables;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		
		return null;
		
		
	}
	
	public List<Entity> getObjectInAgenda(String tableName){
		
		String sql="SELECT subj FROM "+tableName+" WHERE obj='NamedIndividual'";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			List<Entity> entitys=new ArrayList<Entity>();
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				
				Entity entity=new Entity();
				
				entity.setObject(rs.getString("subj"));
				
				entitys.add(entity);
				
			}
			
			return entitys;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DButil.closeConnection(con);
			
		}
		
		return null;
		
		
	}
	
	public List<Entity> getEntityByClass(List<Entity> entities,String tableName){
		
		String sql="SELECT obj FROM "+tableName
				+" WHERE subj=? AND prop='type:' AND obj!='NamedIndividual'";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			List<Entity> es=new ArrayList<Entity>();
			
			ClassTableDao classTableDao=new ClassTableDao();
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			for(Entity entity:entities){
				
				ps.setString(1, entity.getObject());
				
				rs=ps.executeQuery();
				
				if(rs.next()){
					
					entity.setClass_id(classTableDao.getClassTableByClass(rs.getString("obj")));
					
				}
				
				es.add(entity);
				
			}
			
			return es;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		
		return null;
	}
	
	public List<AttributeData> getAttributeByEntity(List<Entity> entities,String tableName){
		
		String sql="SELECT * FROM "+tableName+" WHERE prop!='type:' AND subj=?";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			EntityDao entityDao=new EntityDao();
			
			List<AttributeData> attributeDatas=new ArrayList<AttributeData>();
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			for(Entity entity:entities){
				
				ps.setString(1, entity.getObject());
				
				rs=ps.executeQuery();
				
				while(rs.next()){
					
					AttributeData attributeData=new AttributeData();
					
					attributeData.setEntity_id(entityDao.getIdByObject(entity.getObject()));
					
					attributeData.setProperty(rs.getString("prop"));
					
					attributeData.setValue(rs.getString("obj"));
					
					attributeDatas.add(attributeData);
					
				}
			}
			
			return attributeDatas;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DButil.closeConnection(con);
			
		}
		
		return null;
		
		
	}
}
