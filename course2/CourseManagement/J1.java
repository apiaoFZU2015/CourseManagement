package CourseManagement;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;     
import java.io.IOException;     
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;     
import org.apache.poi.hssf.usermodel.HSSFCellStyle;     
import org.apache.poi.hssf.usermodel.HSSFDataFormat;     
import org.apache.poi.hssf.usermodel.HSSFRow;     
import org.apache.poi.hssf.usermodel.HSSFSheet;     
import org.apache.poi.hssf.usermodel.HSSFWorkbook;     
import org.apache.poi.hssf.util.HSSFColor; 
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
public class J1 {
	public static void main(String args[]) throws IOException
	{
		//filepath1是数据库表头的信息。每一行代表一个数据库的信息...
		String filepath1="E:\\新建文件夹\\新建文件夹\\表名.xls";
		//filepath2表示的是表的内容.可以用for循环的i来控制选择哪个表,只要让xls的表名和i相关即可。
		String filepath2="E:\\新建文件夹\\新建文件夹\\计算机1.xls";
		int a=0,i=0;
		List ls=null;
		a=rowNum(filepath1);
		for(i=0;i<=a;i++)
		{
			ls=new ArrayList();
			ls=sqlDB(filepath1,i);
			insertDB(filepath2,ls);
		}
	}
	//往数据库中添加数据，
	public static void insertDB(String filepath,List ls) throws IOException
	{
		String filepath1=filepath;
		SqlHelper sp=null;
		int flag=-1;	
		String str="";
	    FileInputStream file= new FileInputStream(filepath);
	    POIFSFileSystem ts= new POIFSFileSystem(file);
	    HSSFWorkbook workbook=new HSSFWorkbook(ts);//取得工作簿
	    HSSFSheet sheet=workbook.getSheetAt(0);//取得表
	    HSSFRow row=null;//行
	    HSSFCell cell=null;//单元格  
	    int totalRow=sheet.getLastRowNum();//得到最后一行
	    int x=ls.size()-1;
	    String sql="";
	    String sign="";
	    if(x==1)
	    {
	    	sql="insert into "+ls.get(0)+" values(?);";
	    }else if(x>1)
	    {
	    	for(int i=1;i<x;i++)
	    	{
	    		sign=sign+",?";
	    	}
	    	sql="insert into "+ls.get(0)+" values(?"+sign+");";

	    }
		List<String> lt=null;
		lt=new ArrayList();
		for(int i=0;i<=totalRow;i++)
		{
			row=sheet.getRow(i);
			int j=1;
			int k=row.getLastCellNum();
			while(j<=k)
			{
			cell=row.getCell(j-1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			str=cell.getStringCellValue().toString();
			lt.add(str);
			j++;
			}

			sp=new SqlHelper();
			/*flag=*/sp.update(sql, lt);
		}
	}
	//返回文件的行数
	public static int rowNum(String filepath) throws IOException
	{
		int k=0;
	    FileInputStream file= new FileInputStream(filepath);
	    POIFSFileSystem ts= new POIFSFileSystem(file);
	    HSSFWorkbook workbook=new HSSFWorkbook(ts);//取得工作簿
	    HSSFSheet sheet=workbook.getSheetAt(0);//取得表
	    k=sheet.getLastRowNum();//得到最后一行
		return k;
	}
	//返回建表所需的语句,num为filepath里面的行数。
	public static List sqlDB(String filepath,int num) throws IOException
	{
		String sql="";
		String str="";
		String columnName="";
		SqlHelper sp=null;
		boolean flag=false;
	    FileInputStream file= new FileInputStream(filepath);
	    POIFSFileSystem ts= new POIFSFileSystem(file);
	    HSSFWorkbook workbook=new HSSFWorkbook(ts);//取得工作簿
	    HSSFSheet sheet=workbook.getSheetAt(0);//取得表
	    HSSFRow row=null;//行
	    HSSFCell cell=null;//单元格  
		row=sheet.getRow(num);
		int k=row.getLastCellNum();//每行的方格个数
		cell=row.getCell(0);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		//得到表名
		str=cell.getStringCellValue().toString();
		int j=0;
		List<String> lt=null;
		lt=new ArrayList();
		while(j<=(k-1))
		{
			cell=row.getCell(j);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			columnName=cell.getStringCellValue().toString();
			lt.add(columnName);
			j++;
		}
		sql="create table "+str+" ("+lt.get(1)+");";
		sp=new SqlHelper();
		sp.sqlCreate(sql);
		int x=lt.size();
		int i=2;
		while(i<x)
		{
			sql="alter table "+str+" add "+lt.get(i)+"";
			sp=new SqlHelper();
			sp.sqlCreate(sql);
			i++;
		}
		return lt;
	}
}
