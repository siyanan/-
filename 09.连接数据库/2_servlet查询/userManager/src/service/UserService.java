package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.DBConnection;

public class UserService {
	DBConnection db=new DBConnection();
	//添加
	public int insert(String[] param) {
		String sql="insert into user(name,password,sex,age) values(?,?,?,?)";
		db.doSQL(param, sql);//执行命令
		int i=db.getUpCount();//获得执行结果
		db.getClose();
		return i;
	}
	//修改
	public int update(String[] param) {
		String sql="update user set name=?,password=?,sex=?,age=? where id=?";
		db.doSQL(param, sql);//执行命令
		int i=db.getUpCount();//获得执行结果
		db.getClose();
		return i;
	}
	//删除
	public int delete(String[] param) {
		String sql="delete from user where id=?";
		db.doSQL(param, sql);//执行命令
		int i=db.getUpCount();//获得执行结果
		db.getClose();
		return i;
	}
	//查询
	/*
	 * 
	 * mysql> select * from  user;
		+----+------+----------+------+------+
		| id | name | password | age  | sex  |
		+----+------+----------+------+------+
		|  2 | 张三 | 1111     |   23 | 女   |
		|  3 | 李四 | 3242342  |   23 | 女   |
		|  4 | 王五 | 3242342  |   22 | 女   |
		+----+------+----------+------+------+
		3 rows in set (0.00 sec)
	 */
	public List<Map<String,String>> select(String[] param) {
		String sql="select * from user";
		db.doSQL(param, sql);//执行命令
		ResultSet rs=db.getRS();
		
		List<Map<String,String>> list=null;
		try {
			if (rs!=null) {//查询语句 正常执行: 可能有数据  可能没有数据
			rs.last();//将指针移动到最后1行
			int rowNum=rs.getRow();
			rs.beforeFirst();//将指针移动到首行
			if (rowNum>0) {//表示结果集中有数据
				list=new ArrayList<Map<String,String>>();
				while(rs.next()){//将指针向下移动1次,若有下1行,返回true. 否则返回false
					String id=rs.getString("id");
					String name=rs.getString("name");
					String password=rs.getString("password");
					String sex=rs.getString("sex");
					String age=rs.getString("age");
					//System.out.println("id="+id+" name="+name);
					Map<String,String> map=new HashMap<String,String>();
					map.put("id", id);
					map.put("name", name);
					map.put("password", password);
					map.put("sex", sex);
					map.put("age", age);
					list.add(map);
				}
			} else {//结果集中没有数据
				System.out.println("没有数据...");
			}
			} else {//查询语句执行错误
				System.out.println("查询执行错误!");
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.getClose();
		return list;
	}
	
}
