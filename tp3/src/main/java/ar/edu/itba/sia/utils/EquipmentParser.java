package ar.edu.itba.sia.utils;

import ar.edu.itba.sia.model.equipment.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EquipmentParser {

    public static List<Equipment> parse(final String fileName, EquipmentType type) throws IOException {

        List<Equipment> equipment = new LinkedList<>();

        FileInputStream fileReader = new FileInputStream(fileName);
        HSSFWorkbook workbook = new HSSFWorkbook(fileReader);
        HSSFSheet sheet = workbook.getSheetAt(0);

        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();

            if(row.getRowNum() != 0) {
                switch (type) {
                    case ARMOR:
                        equipment.add(new Armor(row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue(),
                                row.getCell(3).getNumericCellValue(), row.getCell(5).getNumericCellValue(),
                                row.getCell(4).getNumericCellValue()));
                        break;
                    case BOOTS:
                        equipment.add(new Boots(row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue(),
                                row.getCell(3).getNumericCellValue(), row.getCell(5).getNumericCellValue(),
                                row.getCell(4).getNumericCellValue()));
                        break;
                    case GLOVES:
                        equipment.add(new Gloves(row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue(),
                                row.getCell(3).getNumericCellValue(), row.getCell(5).getNumericCellValue(),
                                row.getCell(4).getNumericCellValue()));
                        break;
                    case HELMET:
                        equipment.add(new Helmet(row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue(),
                                row.getCell(3).getNumericCellValue(), row.getCell(5).getNumericCellValue(),
                                row.getCell(4).getNumericCellValue()));
                        break;
                    case WEAPON:
                        equipment.add(new Weapon(row.getCell(1).getNumericCellValue(), row.getCell(2).getNumericCellValue(),
                                row.getCell(3).getNumericCellValue(), row.getCell(5).getNumericCellValue(),
                                row.getCell(4).getNumericCellValue()));
                        break;
                }
            }
        }

        return equipment;
    }
}
