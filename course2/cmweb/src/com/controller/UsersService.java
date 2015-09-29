package com.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.SqlHelper;
import com.domain.User;

public class UsersService {

	
	//���շ�ҳ����ȡ�û�...
	//ResultSet->User����->ArrayList(����)
	public ArrayList getUserByPage(int pageNow,int pageSize)
	{
		ArrayList<User> al=new ArrayList<User>();
		//��ѯsql
		String sql="select top "+pageSize+" * from Course where (Coursename not in(select top "+pageSize*(pageNow-1)+" Coursename from Course )) ";
		ResultSet rs=null;
		SqlHelper sqlhelper=new SqlHelper();
		String parameters[]={};
		rs=sqlhelper.executeQuery(sql, parameters);
		//���η�װ
		try {
			while(rs.next())
			{
				//���User��while��
				User u=new User();
				u.setGrade(rs.getString(1));
				u.setZhuanye(rs.getString(2));
				u.setNum(rs.getString(3));
				u.setCoursename(rs.getString(4));
				u.setType(rs.getString(5));
				u.setXuefen(rs.getString(6));
				u.setStutime(rs.getString(7));
				u.setTime(rs.getString(8));
				u.setSztime(rs.getString(9));
				u.setStar(rs.getString(10));
				u.setTeacher(rs.getString(11));
				u.setBz(rs.getString(12));
				//��u����ArrayList
				al.add(u);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			sqlhelper.close();
		}
		return al;
	}
	
	//��ȡpageCount
	public int getPageCount(int pageSize)
	{
		String sql="select count(*) from Course";
		ResultSet rs=null;
		SqlHelper sqlhelper=new SqlHelper();
		String parameters[]={};
		int rowCount=0;
		rs=sqlhelper.executeQuery(sql, parameters);
		try {
			rs.next();
			rowCount=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			sqlhelper.close();
		}
		return (rowCount-1)/pageSize+1;

	}
}
