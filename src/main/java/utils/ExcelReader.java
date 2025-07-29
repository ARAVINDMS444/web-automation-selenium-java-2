package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
  public static String getCellValue(String filePath, String sheetName, int rowIndex, int colIndex)
      throws IOException {
    try (FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook =
            filePath.toLowerCase().endsWith(".xls")
                ? new HSSFWorkbook(fis)
                : new XSSFWorkbook(fis)) {

      Sheet sheet = workbook.getSheet(sheetName);
      if (sheet == null) {
        throw new IllegalArgumentException("Sheet '" + sheetName + "' does not exist.");
      }

      Row row = sheet.getRow(rowIndex);
      if (row == null) return "";

      Cell cell = row.getCell(colIndex);
      if (cell == null) return "";

      return getStringCellValue(cell);
    }
  }

  private static String getStringCellValue(Cell cell) {
    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue().toString();
        } else {
          return String.valueOf(cell.getNumericCellValue());
        }
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case FORMULA:
        return cell.getCellFormula();
      case BLANK:
        return "";
      default:
        return cell.toString();
    }
  }
}
