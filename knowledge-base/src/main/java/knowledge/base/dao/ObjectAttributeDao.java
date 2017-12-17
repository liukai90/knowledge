package knowledge.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import knowledge.base.pojo.ObjectAttribute;
import knowledge.base.util.DButil;

public class ObjectAttributeDao {
	
	public void insertBatchObjectAttribute(List<ObjectAttribute> objectAttributes){
		
		String sql="INSERT INTO object_attribute(property,superproperty,subproperty) VALUES(?,?,?)";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		try {
			
			con=DButil.getConnection();
			
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			
			for(ObjectAttribute objectAttribute:objectAttributes){
				
				ps.setString(1, objectAttribute.getProperty());
				
				ps.setString(2, objectAttribute.getSuperproperty());
				
				ps.setString(3, objectAttribute.getSubproperty());
				
				ps.addBatch();
				
			}
			
			ps.executeBatch();
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("批量插入数据失败！");
			
		}finally {
			
			DButil.closeConnection(con);
		}
		
	}

}
