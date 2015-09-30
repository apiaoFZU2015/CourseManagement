package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.controller.*;

import java.sql.*;
import java.util.ArrayList;
public class View extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//window.alert('pageNow'+pageNow.value);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function gotoPageNow(){var pageNow=document.getElementById('pageNow');window.open('/czg/View?pageNow='+pageNow.value,'_self')}");
		out.println("</script>");
		//定义分页需要的变量
		int pageNow=1;//当前页
		//接收用户的pageNow;
		String pagenow=request.getParameter("pageNow");
		if(pagenow!=null)
		{
			pageNow=Integer.parseInt(pagenow);
		}
			
		int pageSize=22;//指定每页显示15条记录
		int pageCount=1;//总页数，计算出来
		int rowCount=1;//总记录数，查询数据库得到。
		
		
		UsersService userservice=new UsersService();
		ArrayList<User> al=new ArrayList();
		
		//得到总的页数...
		pageCount=userservice.getPageCount(pageSize);
		
		//
		al=userservice.getUserByPage(pageNow, pageSize);
			
		//cellspacing=0			
		out.println("<table border=1 bordercolor=black  width=0px>");
		out.println("<tr><th>年级</th><th>专业</th><th>专业人数</th><th>课程名称</th><th>选秀类型</th>" +
				"<th>学分</th><th>学时</th><th>实验学时</th><th>上机学时</th><th>起讫周序</th><th>任课老师</th><th>备注</th></tr>");
		for(User u:al)
		{
			out.println("<tr><td>"+u.getGrade()+
					"</td><td>"+u.getZhuanye()+
					"</td><td>"+u.getNum()+
					"</td><td>"+u.getCoursename()+
					"</td><td>"+u.getType()+
					"</td><td>"+u.getXuefen()+
					"</td><td>"+u.getStutime()+
					"</td><td>"+u.getTime()+
					"</td><td>"+u.getSztime()+
					"</td><td>"+u.getStar()+
					"</td><td>"+u.getTeacher()+
					"</td><td>"+u.getBz()+
					"</td></tr>");
										
		}
		out.println("<table><br/>");
		//显示上一页
		if(pageNow!=1)
		{
			out.println("<a href='/czg/View?pageNow="+(pageNow-1)+"'>上一页</a>");
		}			
		//显示分页
	for(int i=1;i<=pageCount;i++)
		{
			out.println("<a href='/czg/View?pageNow="+i+"'><"+i+"></a>");
		}			
		//显示下一页
		if(pageNow!=2)
		{
			out.println("<a href='/czg/View?pageNow="+(pageNow+1)+"'>下一页</a>");
		}	
		//显示分页信息
		out.println("&nbsp;&nbsp;&nbsp;当前页"+pageNow+"/总页数"+pageCount);
		out.println("<br/>");			
		//跳转到哪一页
		//out.println("跳转到 <input type='text' id='pageNow' name='pageNow'/><input type='button' onClick='gotoPageNow()' value='跳'><br/>");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

