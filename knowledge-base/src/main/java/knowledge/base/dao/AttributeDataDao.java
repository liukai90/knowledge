package knowledge.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import knowledge.base.pojo.AttributeData;
import knowledge.base.util.DButil;

public class AttributeDataDao {
	
	public void insertBatch(List<AttributeData> attributeDatas){
		
		String sql="INSERT INTO attribute_data(entity_id,property,value) VALUES(?,?,?)";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		try {
			
			con=DButil.getConnection();
			
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			
			for(AttributeData attributeData:attributeDatas){
				
				ps.setInt(1, attributeData.getEntity_id());
				
				ps.setString(2, attributeData.getProperty());
				
				ps.setString(3, attributeData.getValue());
				
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

}
