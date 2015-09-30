package CourseManagement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class SqlHelper {

	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CourseManagement";
	String username="sa";
	String password="168432";
	
	public SqlHelper()
	{
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ct=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//插入数据到数据库中
	public int update(String sql,List<String> al)
	{
		int flag=-1;
		try {
			ps=ct.prepareStatement(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		//给sql语句赋值
		for(int i=0;i<al.size();i++)
		{
			try {
				String str=al.get(i);
				ps.setString(i+1, str.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		try {
			flag=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		al.clear();
		return flag;
	}
	//建表
	public boolean sqlCreate(String sql)
	{
		boolean flag=false;
		try {
			ps=ct.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			flag=ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
