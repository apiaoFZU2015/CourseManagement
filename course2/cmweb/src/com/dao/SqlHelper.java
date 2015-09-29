package com.dao;
import java.sql.*;
import java.util.*;
/**
 * ����һ���������ݿ�Ĺ�����...
 * @author Administrator
 *
 */
public class SqlHelper {

	public static PreparedStatement ps=null;
	public static Connection ct=null;
	public static ResultSet rs=null;
	String drivername="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//com.microsoft.sqlserver.jdbc.SQLServerDriver
	String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=czg";
	//jdbc:sqlserver://127.0.0.1:1433;DatavaseName=CourseManagementSystem
	String user="sa";
	String password="09170218";
	public SqlHelper()
	{
		try {
			Class.forName(drivername);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ct=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��ѯ���
	public static ResultSet executeQuery(String sql,String paras[])
	{
		try {
			ps=ct.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��sql�Ĳ�����ֵ
		for(int i=0;i<paras.length;i++)
		{
			try {
				ps.setString(i+1, paras[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//��ӣ�ɾ��,�޸����
	public  static int executeUpdate(String sql,String[]paras)
	{
		int flag=-1;
		try {
			ps=ct.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		//��sql�Ĳ�����ֵ
		for(int i=0;i<paras.length;i++)
		{
			try {
				ps.setString(i+1, paras[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			flag=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	//�ر���Դ�ķ���
	public static void close()
	{
		if(ps!=null)
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ct!=null)
		{
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}
