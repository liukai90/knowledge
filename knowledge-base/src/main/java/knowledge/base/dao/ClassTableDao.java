package knowledge.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import knowledge.base.pojo.ClassAndEntity;
import knowledge.base.pojo.ClassTable;
import knowledge.base.util.DButil;

public class ClassTableDao {
	
	public Integer getClassTableByClass(String class_){
		
		String sql="SELECT id FROM class_table WHERE class_=?";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			ps.setString(1, class_);
			
			rs=ps.executeQuery();
			
			if(rs.next()){
				
				return rs.getInt("id");
				
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			
			DButil.closeConnection(con);
		}
		
		return null;
		
		
	}
	
	public void insertBatch(List<ClassTable> classTables){
		
		String sql="INSERT INTO class_table(class_,superclass,subclass) VALUES(?,?,?)";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		try {
			
			con=DButil.getConnection();
			
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			
			for(ClassTable classTable:classTables){
				
				ps.setString(1, classTable.getClass_());
				
				ps.setString(2, classTable.getSuperclass());
				
				ps.setString(3, classTable.getSubclass());
				
				ps.addBatch();
			}
			
			ps.executeBatch();
			
			con.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DButil.closeConnection(con);
		}
	}
	
	
	public List<ClassAndEntity> getClassAndEntity(String class_){
		
		String sql="SELECT ct.class_ class_,ct.superclass superclass,ct.subclass subclass,et.object object"+
		" FROM class_table ct LEFT OUTER JOIN entity et ON ct.id=et.class_id WHERE ct.class_=? ";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			List<ClassAndEntity> classAndEntities=new ArrayList<ClassAndEntity>();
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			ps.setString(1, class_);
			
			rs=ps.executeQuery();
			
			while(rs.next()){
				
				ClassAndEntity classAndEntity=new ClassAndEntity();
				
				classAndEntity.setEntity(rs.getString("object"));
				
				classAndEntity.setSuperclass(rs.getString("superclass"));
				
				classAndEntity.setSubclass(rs.getString("subclass"));
				
				classAndEntity.setClass_(rs.getString("class_"));
				
				classAndEntities.add(classAndEntity);
				
			}
			
			return classAndEntities;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			DButil.closeConnection(con);
			
		}
		
		return null;
		
	}

}
