import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import com.example.zoo.service.ZooService;
import com.example.zoo.service.VetService;
import com.example.zoo.service.ConsoleService;
import com.example.zoo.service.TablePrinterService;
import com.example.zoo.controller.MenuController;
import com.example.zoo.model.Animal;
import com.example.zoo.model.Monkey;

class TablePrinterServiceTest {

    private TablePrinterService tablePrinterService;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    void setUp() {
        tablePrinterService = new TablePrinterService();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testPrintTable_withValidData() {
        List<String> headers = List.of("ID", "Name", "Age");
        List<List<String>> rows = List.of(
                List.of("1", "John Doe", "30"),
                List.of("2", "Jane Doe", "25")
        );

        tablePrinterService.printTable(headers, rows);

        // Проверяем, что вывод в консоль был выполнен
        assertTrue(outContent.toString().contains("+----+----------+-----+"));
        assertTrue(outContent.toString().contains("| ID | Name     | Age |"));
        assertTrue(outContent.toString().contains("+----+----------+-----+"));
        assertTrue(outContent.toString().contains("| 1  | John Doe | 30  |"));
        assertTrue(outContent.toString().contains("| 2  | Jane Doe | 25  |"));
        assertTrue(outContent.toString().contains("+----+----------+-----+"));
    }

    @Test
    void testPrintTable_withNullHeaders() {
        assertThrows(IllegalArgumentException.class, () -> {
            tablePrinterService.printTable(null, List.of(List.of("1", "John")));
        });
    }

    @Test
    void testPrintTable_withEmptyRows() {
        List<String> headers = List.of("ID", "Name");
        List<List<String>> rows = List.of(); // Пустая таблица

        tablePrinterService.printTable(headers, rows);

        // Проверяем, что пустая таблица не приводит к ошибке
        assertTrue(outContent.toString().contains("+----+------+"));
        assertTrue(outContent.toString().contains("| ID | Name |"));
        assertTrue(outContent.toString().contains("+----+------+"));
        assertTrue(outContent.toString().contains("+----+------+"));
    }

    @Test
    void testPrintTable_withNullRows() {
        List<String> headers = List.of("ID", "Name");
        assertThrows(IllegalArgumentException.class, () -> {
            tablePrinterService.printTable(headers, null);
        });
    }

    @Test
    void testPrintTable_withVaryingColumnWidths() {
        List<String> headers = List.of("ID", "Long Name", "Age");
        List<List<String>> rows = List.of(
                List.of("1", "Short Name", "30"),
                List.of("2", "A Much Longer Name", "25")
        );

        tablePrinterService.printTable(headers, rows);

        // Проверяем, что столбцы автоматически расширяются для длинных строк
        assertTrue(outContent.toString().contains("+----+--------------------+-----+"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut); // Восстанавливаем стандартный вывод
    }
}