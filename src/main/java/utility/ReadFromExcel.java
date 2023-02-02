package utility;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromExcel {
    FileInputStream excelFile;//reader and buffer
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String filPath;
    String sheetName;
    public ReadFromExcel(String filPath, String sheetName){
        this.filPath = filPath;
        this.sheetName = sheetName;
    }
    public String getDataFromCell(int row, int column) {
        String data = null;
        try {
            excelFile = new FileInputStream(filPath);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);
            data = sheet.getRow(row).getCell(column).getStringCellValue();
            excelFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    public List<String> getEntireColumnData(int rowStart, int column) {
        List<String> data = new ArrayList<>();
        try {
            excelFile = new FileInputStream(filPath);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {// but if we put i=rowStart we able iterating from any point we want
                // it iterates through the column without including the header
                data.add(sheet.getRow(i).getCell(column).getStringCellValue());
            }
            excelFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }


    public String getCellValueForGivenHeaderAndKey(String header, String key){//this work best when give it a key to give us the attribute value
        String data = null;
        int i = 0;
        while(getDataFromCell(0, i) != null){
            if (getDataFromCell(0, i).equalsIgnoreCase(header)){
                for (int j = 0; j < getEntireColumnData(1, i).size(); j++) {
                    if (getEntireColumnData(1, i).get(j).equalsIgnoreCase(key)){
                        data = getEntireColumnData(1, i+1).get(j);
                    }

                }
            }
            break;
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
//to use this class
        String filePath=System.getProperty("user.dir")+ File.separator+"data"+File.separator+"orangehrmData.xlsx";
        ReadFromExcel readFromExcel=new ReadFromExcel(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"orangehrmData.xlsx","sheet1");
        // ReadFromExcel readFromExcel=new ReadFromExcel("C:\\Users\\Malika Refsi\\IdeaProjects\\AutomationFrameWork\\data\\titles.xlsx","sheet1");
        // ReadFromExcel readFromExcel=new ReadFromExcel("C:\\Users\\nabil\\IdeaProjects\\projectframework\\data\\titles.xlsx","sheetcred");
        //ReadFromExcel readFromExcel=new ReadFromExcel("C:\\Users\\12016\\eclipse-workspace\\Final_exam\\data\\titles.xlsx","test data");
        //System.out.println(readFromExcel.getCellValueForGivenHeaderAndKey("key","homepage title"));
        System.out.println(readFromExcel.getCellValueForGivenHeaderAndKey("value","home page title"));

    }

}
