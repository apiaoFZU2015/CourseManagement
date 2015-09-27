package aa;
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
		//filepath1�����ݿ��ͷ����Ϣ��ÿһ�д���һ�����ݿ����Ϣ...
		String filepath1="E:\\�½��ļ���\\�½��ļ���\\����.xls";
		//filepath2��ʾ���Ǳ������.������forѭ����i������ѡ���ĸ���,ֻҪ��xls�ı�����i��ؼ��ɡ�
		String filepath2="E:\\�½��ļ���\\�½��ļ���\\Book1.xls";
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
	//�����ݿ���������ݣ�
	public static void insertDB(String filepath,List ls) throws IOException
	{
		String filepath1=filepath;
		SqlHelper sp=null;
		int flag=-1;	
		/*String num="";
		String name="";
		String loc="";*/
		String str="";
		//HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File(filepath)))
	    FileInputStream file= new FileInputStream(filepath);
	    POIFSFileSystem ts= new POIFSFileSystem(file);
	    HSSFWorkbook workbook=new HSSFWorkbook(ts);//ȡ�ù�����
	    HSSFSheet sheet=workbook.getSheetAt(0);//ȡ�ñ�
	    HSSFRow row=null;//��
	    HSSFCell cell=null;//��Ԫ��  
	    int totalRow=sheet.getLastRowNum();//�õ����һ��
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
			//num=Integer.parseInt(cell.getStringCellValue());
			cell.setCellType(Cell.CELL_TYPE_STRING);
			str=cell.getStringCellValue().toString();
			lt.add(str);
			j++;
			}
			//System.out.println(k);

			
		/*	cell=row.getCell(0);
			//num=Integer.parseInt(cell.getStringCellValue());
			cell.setCellType(Cell.CELL_TYPE_STRING);
			num=cell.getStringCellValue().toString();
			lt.add(num);
		 
		    cell=row.getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			name=cell.getStringCellValue().toString();
			lt.add(name);

			cell=row.getCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			loc=cell.getStringCellValue().toString();
			lt.add(loc);*/

			sp=new SqlHelper();
			flag=sp.update(sql, lt);
			if(flag!=-1)
			{
				//System.out.println("����ɹ�");

			}else
			{
				System.out.println("����ʧ��"+i);
			}
			
		}
	}

	//�����ļ�������
	public static int rowNum(String filepath) throws IOException
	{
		int k=0;
	    FileInputStream file= new FileInputStream(filepath);
	    POIFSFileSystem ts= new POIFSFileSystem(file);
	    HSSFWorkbook workbook=new HSSFWorkbook(ts);//ȡ�ù�����
	    HSSFSheet sheet=workbook.getSheetAt(0);//ȡ�ñ�
	    k=sheet.getLastRowNum();//�õ����һ��
		return k;
	}
	//���ؽ�����������,numΪfilepath�����������
	public static List sqlDB(String filepath,int num) throws IOException
	{
		String sql="";
		String str="";
		String columnName="";
		SqlHelper sp=null;
		boolean flag=false;
	    FileInputStream file= new FileInputStream(filepath);
	    POIFSFileSystem ts= new POIFSFileSystem(file);
	    HSSFWorkbook workbook=new HSSFWorkbook(ts);//ȡ�ù�����
	    HSSFSheet sheet=workbook.getSheetAt(0);//ȡ�ñ�
	    HSSFRow row=null;//��
	    HSSFCell cell=null;//��Ԫ��  
		row=sheet.getRow(num);
		int k=row.getLastCellNum();//ÿ�еķ������
		cell=row.getCell(0);
		//num=Integer.parseInt(cell.getStringCellValue());
		cell.setCellType(Cell.CELL_TYPE_STRING);
		//�õ�����
		str=cell.getStringCellValue().toString();
		int j=0;
		List<String> lt=null;
		lt=new ArrayList();
		while(j<=(k-1))
		{
			cell=row.getCell(j);
			//num=Integer.parseInt(cell.getStringCellValue());
			cell.setCellType(Cell.CELL_TYPE_STRING);
			columnName=cell.getStringCellValue().toString();
			lt.add(columnName);
			j++;
		}

		sql="create table "+str+" ("+lt.get(1)+");";
	//	System.out.println(sql);
		sp=new SqlHelper();
		flag=sp.sqlCreate(sql);
		if(flag)
		{
			System.out.println("����ʧ��"+num);
		}else 
		{
			//System.out.println("����ɹ�1");
		}
		int x=lt.size();
		int i=2;
		while(i<x)
		{
			sql="alter table "+str+" add "+lt.get(i)+"";
			sp=new SqlHelper();
			flag=sp.sqlCreate(sql);
			if(flag)
			{
				System.out.println("����ʧ��"+num);
			}else 
			{
				//System.out.println("����ɹ�");
			}
			i++;
		}
		return lt;
	}
}
