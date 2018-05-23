package ar.edu.itba.sia.utils.equipmentParsers;

import ar.edu.itba.sia.model.equipment.Gloves;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GlovesParser extends EquipmentParser {

    public static List<Gloves> parse(final String fileName) throws IOException {

        List<Gloves> gloves = new LinkedList<>();

        FileInputStream fileReader = new FileInputStream(fileName);
        HSSFWorkbook workbook = new HSSFWorkbook(fileReader);
        HSSFSheet sheet = workbook.getSheetAt(0);

        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();

            gloves.add(new Gloves(row.getCell(STRENGTH_POS).getNumericCellValue(), row.getCell(AGILITY_POS).getNumericCellValue(),
                    row.getCell(DEXTERITY_POS).getNumericCellValue(), row.getCell(HEALTH_POS).getNumericCellValue(),
                    row.getCell(RESISTANCE_POS).getNumericCellValue()));
        }
        return gloves;
    }
}
