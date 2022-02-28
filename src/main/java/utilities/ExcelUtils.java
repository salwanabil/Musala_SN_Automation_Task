package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * This class aims to manage excel file.
 * Read from excel file.
 **/

public class ExcelUtils {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;

    public Object[][] getExcelData(String filePath, String sheetName) throws IOException
    {
        FileInputStream ExcelFile = new FileInputStream(filePath);
        // Access the required test data sheet
        ExcelWBook = new XSSFWorkbook(ExcelFile);
        ExcelWSheet = ExcelWBook.getSheet(sheetName);
        Iterator<Row> rows= ExcelWSheet.iterator();// sheet is collection of rows
        Row firstrow= rows.next();

        int rowCount = ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
        int columnCount = firstrow.getLastCellNum();
        Object[][] tabArray=new Object[rowCount][columnCount];

        Iterator<Cell> firstCell=firstrow.cellIterator();//row is collection of cells

        int i=0;
        int j = 0;
        ////once coloumn is identified then scan entire testcase coloum to identify purcjhase testcase row
        while(rows.hasNext())
        {
            Row r=rows.next();
            Iterator<Cell>  cv=r.cellIterator();
            j=0;
            while(cv.hasNext())
            {
                Cell c= cv.next();
                if(c.getCellType() == CellType.STRING)
                {
                    tabArray[i][j] = c.getStringCellValue();
                }
                else{
                    DataFormatter formatter = new DataFormatter();
                    tabArray[i][j] = formatter.formatCellValue(c);
                }
                j++;
            }
            i++;
        }
        return tabArray;
    }

}
