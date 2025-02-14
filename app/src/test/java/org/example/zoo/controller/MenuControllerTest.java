import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;

import com.example.zoo.service.ZooService;
import com.example.zoo.service.ConsoleService;
import com.example.zoo.service.TablePrinterService;
import com.example.zoo.controller.MenuController;
import com.example.zoo.model.Monkey;

class MenuControllerTest {

    @Mock
    private ZooService zooService;
    
    @Mock
    private ConsoleService consoleService;
    
    @Mock
    private TablePrinterService tablePrinterService;
    
    @InjectMocks
    private MenuController menuController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartMenu_Exit() {
        when(consoleService.readInt(anyString())).thenReturn(5); // Выбор выхода из меню

        menuController.startMenu();

        verify(consoleService, times(1)).close();
    }

    @Test
    void testStartMenu_AddMonkey() {
        when(consoleService.readInt(anyString()))
            .thenReturn(1)  // Выбираем "Добавить животное"
            .thenReturn(1)  // Выбираем "Monkey"
            .thenReturn(10) // Вводим еду
            .thenReturn(2)  // Вводим количество
            .thenReturn(5)  // Вводим уровень доброты
            .thenReturn(1)  // Подтверждаем здоровье
            .thenReturn(5); // Выбираем "Выход"

        menuController.startMenu();

        verify(zooService, times(1)).addAnimal(any(Monkey.class));
    }

    @Test
    void testStartMenu_PrintAnimalsForContactZoo() {
        List<List<String>> animals = List.of(
            List.of("Monkey", "10", "2", "5"),
            List.of("Rabbit", "5", "3", "8")
        );
        when(consoleService.readInt(anyString())).thenReturn(3, 5); // Выбираем "Вывести список животных", затем "Выход"
        when(zooService.getAnimalsForContactZoo()).thenReturn(animals);

        menuController.startMenu();

        verify(tablePrinterService, times(1)).printTable(anyList(), eq(animals));
    }

    @Test
    void testStartMenu_PrintInventorization() {
        List<List<String>> inventory = List.of(
            List.of("Item1", "5"),
            List.of("Item2", "3")
        );
        when(consoleService.readInt(anyString())).thenReturn(4, 5); // Выбираем "Инвентаризация", затем "Выход"
        when(zooService.getZooInventory()).thenReturn(inventory);

        menuController.startMenu();

        verify(tablePrinterService, times(1)).printTable(anyList(), eq(inventory));
    }
}
