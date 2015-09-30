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
		//�����ҳ��Ҫ�ı���
		int pageNow=1;//��ǰҳ
		//�����û���pageNow;
		String pagenow=request.getParameter("pageNow");
		if(pagenow!=null)
		{
			pageNow=Integer.parseInt(pagenow);
		}
			
		int pageSize=22;//ָ��ÿҳ��ʾ15����¼
		int pageCount=1;//��ҳ�����������
		int rowCount=1;//�ܼ�¼������ѯ���ݿ�õ���
		
		
		UsersService userservice=new UsersService();
		ArrayList<User> al=new ArrayList();
		
		//�õ��ܵ�ҳ��...
		pageCount=userservice.getPageCount(pageSize);
		
		//
		al=userservice.getUserByPage(pageNow, pageSize);
			
		//cellspacing=0			
		out.println("<table border=1 bordercolor=black  width=0px>");
		out.println("<tr><th>�꼶</th><th>רҵ</th><th>רҵ����</th><th>�γ�����</th><th>ѡ������</th>" +
				"<th>ѧ��</th><th>ѧʱ</th><th>ʵ��ѧʱ</th><th>�ϻ�ѧʱ</th><th>��������</th><th>�ο���ʦ</th><th>��ע</th></tr>");
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
		//��ʾ��һҳ
		if(pageNow!=1)
		{
			out.println("<a href='/czg/View?pageNow="+(pageNow-1)+"'>��һҳ</a>");
		}			
		//��ʾ��ҳ
	for(int i=1;i<=pageCount;i++)
		{
			out.println("<a href='/czg/View?pageNow="+i+"'><"+i+"></a>");
		}			
		//��ʾ��һҳ
		if(pageNow!=2)
		{
			out.println("<a href='/czg/View?pageNow="+(pageNow+1)+"'>��һҳ</a>");
		}	
		//��ʾ��ҳ��Ϣ
		out.println("&nbsp;&nbsp;&nbsp;��ǰҳ"+pageNow+"/��ҳ��"+pageCount);
		out.println("<br/>");			
		//��ת����һҳ
		//out.println("��ת�� <input type='text' id='pageNow' name='pageNow'/><input type='button' onClick='gotoPageNow()' value='��'><br/>");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

