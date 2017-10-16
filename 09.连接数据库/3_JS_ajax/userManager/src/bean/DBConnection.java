package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class DBConnection {
	public static String DirverClass="com.mysql.jdbc.Driver";
	private String user="root";
	private String password="123456";
	private String url="jdbc:mysql://localhost:3306/myuser";
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	//加载JDBC驱动器
	static{
		try {
			Class.forName(DirverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getConnect() {
		try {
			//尝试通过驱动器连接mysql数据库,连接成功将获得1个对象
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn!=null) {
			System.out.println("数据库连接成功!");
		} else {
			System.out.println("数据库连接失败!");
		}
	}
	//通用的执行方法: 执行SQL命令
	public void doSQL(String[] param,String sql) {
		try {
			//获得数据库连接
			getConnect();
			//创建执行SQL对象
			pstmt=conn.prepareStatement(sql);
			//对占位符赋值: select  *  from  t1 where id=?
			for (int i = 0; i < param.length; i++) {
				pstmt.setString(i+1, param[i]);
			}
			//使用执行对象 执行命令
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获得影响行数: 添加 修改 删除
	public int getUpCount() {
		int i=-1;
		try {
			i=pstmt.getUpdateCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	//获得结果集对象: 查询
	public ResultSet getRS() {
		try {
			rs=pstmt.getResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//关闭资源: 执行对象 结果集 连接
	public void getClose() {
		try {
			if (rs!=null) {
				rs.close();//清除结果集
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if (pstmt!=null) {
					pstmt.close();//关闭执行对象
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}finally{
				try {
					if (conn!=null) {
						conn.close();//关闭连接
					}
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}
	}
	
}
