package com.orangehrm.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.orangehrm.library.OrangeHrmLibrary;

public class OrangeHRMKeywordDrivenTest 
{

@Test	
public void keywordDrivenTest() throws IOException, InterruptedException
{

	OrangeHrmLibrary hrm=new OrangeHrmLibrary();
	int tccount,tscount;
	String keyword,tcid,tstcid,tcexeflg;
	String stepres=null;
	String tcres="Pass";
	FileInputStream fi=new FileInputStream("D:\\HRMS\\HRMS\\src\\com\\orangehrm\\keywords\\Keywords.xlsx");
	XSSFWorkbook wb=new XSSFWorkbook(fi);
	XSSFSheet ws1=wb.getSheet("TestCases");
	XSSFSheet ws2=wb.getSheet("TestSteps");
	tccount=ws1.getLastRowNum();
	tscount=ws2.getLastRowNum();
	XSSFRow row; 
	
	for (int i = 1; i <= tccount; i++) 
	{
		row=ws1.getRow(i);
		tcexeflg=row.getCell(2).getStringCellValue();
		if (tcexeflg.equalsIgnoreCase("Y")) 
		{
			tcid=row.getCell(0).getStringCellValue();
			for (int j = 1; j <= tscount; j++) 
			{
				row=ws2.getRow(j);
				tstcid=row.getCell(0).getStringCellValue();
				if (tcid.equalsIgnoreCase(tstcid)) 
				{
					keyword=row.getCell(4).getStringCellValue();
					switch (keyword) 
					{
					case "LaunchApp":
						stepres=hrm.launchApp(hrm.url);  	
						break;
					case "AdminLogin":
						stepres=hrm.adminLogin(hrm.Uid,hrm.Pwd);  	
						break;		
					case "Logout":
						stepres=hrm.adminLogout(); 
						hrm.closeApp();
						break;
					case "EmpReg":
						stepres=hrm.empReg("anand", "abcd");  	
						break;	
					case "UserReg":
						stepres=hrm.userReg("anand abcd", "anand1", "demo");  	
						break;
					case "EmpLogin":
						stepres=hrm.userLogin("anand1", "demo");  	
						break;	
					}
					row.createCell(3).setCellValue(stepres);
					
					
					
						if (stepres.equalsIgnoreCase("FAIL")) 
						{
							tcres="Fail";
						}
					ws1.getRow(i).createCell(3).setCellValue(tcres);
					
					if (tcres.equalsIgnoreCase("PASS")) 
					{
						CellStyle passstyle=wb.createCellStyle();
						passstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
						passstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						ws1.getRow(i).getCell(3).setCellStyle(passstyle);
						
					} else 
					{
						CellStyle Failstyle=wb.createCellStyle();
						Failstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
						Failstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						ws1.getRow(i).getCell(3).setCellStyle(Failstyle);
					}
					
					
					
					}
			}
			
			
			
		} else 
		{
			row.createCell(3).setCellValue("Blocked");
			CellStyle Failstyle=wb.createCellStyle();
			Failstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			Failstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			row.getCell(3).setCellStyle(Failstyle);
		}
	}


	FileOutputStream fo=new FileOutputStream("D:\\HRMS\\HRMS\\src\\com\\orangehrm\\log\\keywordresults.xlsx");
	wb.write(fo);
	wb.close();

}


}
