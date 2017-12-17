package knowledge.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import knowledge.base.pojo.ClassAndEntity;
import knowledge.base.pojo.Entity;
import knowledge.base.pojo.EntityAndAttributeData;
import knowledge.base.util.DButil;

public class EntityDao {
	
	public Integer getIdByObject(String object){
		
		String sql="SELECT id FROM entity WHERE object=?";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		
		try {
			
			con=DButil.getConnection();
			
			ps=con.prepareStatement(sql);
			
			ps.setString(1, object);
			
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
	
	public void insertBatch(List<Entity> entities){
		
		String sql="INSERT INTO entity(class_id,object) VALUES(?,?)";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		try {
			
			con=DButil.getConnection();
			
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			
			for(Entity entity:entities){
				
				ps.setInt(1, entity.getClass_id());
				
				ps.setString(2, entity.getObject());
				
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
	
	public List<EntityAndAttributeData> getEntityAndAttributeDates(String object){
		
		String sql="SELECT et.object object,ab.property property,ab.`value` value"+
				" FROM entity et LEFT OUTER JOIN attribute_data ab "+
				 "ON et.id=ab.entity_id WHERE et.object=?";
		
				Connection con=null;
				
				PreparedStatement ps=null;
				
				ResultSet rs=null;
				
				try {
					
					List<EntityAndAttributeData> entityAndAttributeDatas=
							new ArrayList<EntityAndAttributeData>();
					
					con=DButil.getConnection();
					
					ps=con.prepareStatement(sql);
					
					ps.setString(1, object);
					
					rs=ps.executeQuery();
					
					while(rs.next()){
						
						EntityAndAttributeData entityAndAttributeData=new EntityAndAttributeData();
						
						entityAndAttributeData.setObject(rs.getString("object"));
						
						entityAndAttributeData.setProperty(rs.getString("property"));
						
						entityAndAttributeData.setValue(rs.getString("value"));
												
						entityAndAttributeDatas.add(entityAndAttributeData);
						
					}
					
					return entityAndAttributeDatas;
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}finally {
					
					DButil.closeConnection(con);
					
				}
				
				return null;
	}

}
