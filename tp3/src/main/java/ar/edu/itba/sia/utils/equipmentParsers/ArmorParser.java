package ar.edu.itba.sia.utils.equipmentParsers;

import ar.edu.itba.sia.model.equipment.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArmorParser extends EquipmentParser {

    public static List<Armor> parse(final String fileName) throws IOException {

        List<Armor> armors = new ArrayList<>();

        FileInputStream fileReader = new FileInputStream(fileName);
        HSSFWorkbook workbook = new HSSFWorkbook(fileReader);
        HSSFSheet sheet = workbook.getSheetAt(0);

        Iterator rows = sheet.rowIterator();

        //header row (ignored)
        rows.next();

        while (rows.hasNext()) {
            HSSFRow row = (HSSFRow) rows.next();

            armors.add(new Armor((int)row.getCell(ID_POS).getNumericCellValue(), row.getCell(STRENGTH_POS).getNumericCellValue(), row.getCell(AGILITY_POS).getNumericCellValue(),
                    row.getCell(DEXTERITY_POS).getNumericCellValue(), row.getCell(HEALTH_POS).getNumericCellValue(),
                    row.getCell(RESISTANCE_POS).getNumericCellValue()));
        }

        return armors;
    }
}
