package service;

import bean.DBConnection;

public class UserService {
	DBConnection db=new DBConnection();
	//添加
	public int insert(String[] param) {
		String sql="insert into user(name,password,sex,age) values(?,?,?,?)";
		db.doSQL(param, sql);//执行命令
		int i=db.getUpCount();//获得执行结果
		return i;
	}
	//修改
	public int update(String[] param) {
		String sql="update user set name=?,password=?,sex=?,age=? where id=?";
		db.doSQL(param, sql);//执行命令
		int i=db.getUpCount();//获得执行结果
		return i;
	}
	//删除
	public int delete(String[] param) {
		String sql="delete from user where id=?";
		db.doSQL(param, sql);//执行命令
		int i=db.getUpCount();//获得执行结果
		return i;
	}
	//查询
	
}
