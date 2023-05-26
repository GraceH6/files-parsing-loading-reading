import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class ZipFolderParsingTest {
    ClassLoader cl = ZipFolderParsingTest.class.getClassLoader();

    @Test
    void pdfZipFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip")) {
            assert is != null;
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().equals("certificate.pdf")) {
                        PDF pdf = new PDF(zis);
                        assertEquals("ReportLab PDF Library - www.reportlab.com", pdf.producer);
                    }
                }
            }
        }
    }

    @Test
    void csvZipFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip")) {
            assert is != null;
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().equals("employees.csv")) {
                        CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                        List<String[]> content = csvReader.readAll();
                        assertArrayEquals(new String[] {"Manager", "Ayatov"}, content.get(1));
                    }
                }
            }
        }
    }

    @Test
    void xlsZipFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip")) {
            assert is != null;
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().equals("price.xls")) {
                        XLS xls = new XLS(zis);
                        assertEquals("Product", xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
                    }
                }
            }
        }
    }
}
