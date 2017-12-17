package knowledge.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import knowledge.base.pojo.ImposeTable;
import knowledge.base.util.DButil;

public class ImposeDao {
	
	public void insertBatchImpose(List<ImposeTable> imposeTables){
		
		String sql="INSERT INTO impose_table(subj,obj) VALUES(?,?)";
		
		Connection con=null;
		
		PreparedStatement ps=null;
		
		try {
			
			con=DButil.getConnection();
			
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(sql);
			
			for(ImposeTable imposeTable:imposeTables){
				
				ps.setString(1, imposeTable.getSubj());
				
				ps.setString(2, imposeTable.getObj());
				
				ps.addBatch();
			}
			
			ps.executeBatch();
			
			con.commit();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			System.out.println("批量插入失败！");
		}finally {
			
			DButil.closeConnection(con);
		}
	}

}
