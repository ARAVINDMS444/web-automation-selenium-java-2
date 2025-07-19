package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    /**
     * Reads a specific cell from an Excel file and returns its value as a String.
     * @param filePath  Path to the Excel file
     * @param sheetName Sheet name to read from
     * @param rowIndex  Row number (0-based)
     * @param colIndex  Column number (0-based)
     * @return Cell value as String (empty string if cell is null)
     * @throws IOException If the file cannot be read
     */
    public static String getCellValue(String filePath, String sheetName, int rowIndex, int colIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' does not exist.");
            }

            Row row = sheet.getRow(rowIndex);
            if (row == null) return "";

            Cell cell = row.getCell(colIndex);
            if (cell == null) return "";

            return cell.getCellType() == CellType.STRING
                    ? cell.getStringCellValue()
                    : cell.toString();
        }
    }
}
