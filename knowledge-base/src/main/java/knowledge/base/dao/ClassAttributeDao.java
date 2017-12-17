package knowledge.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import knowledge.base.pojo.ClassAttribute;
import knowledge.base.util.DButil;

public class ClassAttributeDao {
	
	public void insertBatchClassAttribute(List<ClassAttribute> classAttributes){
		
		String sql="INSERT INTO class_attribute(property,superproperty,subproperty) VALUES(?,?,?)";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		try {
			
			con=DButil.getConnection();
			
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			
			for(ClassAttribute classAttribute:classAttributes){
				
				ps.setString(1, classAttribute.getProperty());
				
				ps.setString(2, classAttribute.getSuperproperty());
				
				ps.setString(3, classAttribute.getSubproperty());
				
				ps.addBatch();
				
			}
			
			ps.executeBatch();
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("批量插入数据异常");
		}finally {
			
			DButil.closeConnection(con);
		}
		
	}

}
