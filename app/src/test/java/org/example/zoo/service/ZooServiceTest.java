import com.example.zoo.model.Animal;
import com.example.zoo.model.Herbo;
import com.example.zoo.model.Thing;
import com.example.zoo.repository.ZooRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.zoo.service.ZooService;
import com.example.zoo.service.VetService;
import com.example.zoo.service.ConsoleService;
import com.example.zoo.service.TablePrinterService;
import com.example.zoo.controller.MenuController;
import com.example.zoo.model.Animal;
import com.example.zoo.model.Monkey;

class ZooServiceTest {

    @Mock
    private ZooRepository zooRepository;

    @Mock
    private VetService vetService;

    @InjectMocks
    private ZooService zooService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAnimal_whenAnimalIsHealthy() {
        Animal healthyAnimal = mock(Animal.class);
        when(vetService.checkHealth(healthyAnimal)).thenReturn(true); // Мокируем поведение VetService

        zooService.addAnimal(healthyAnimal);

        // Проверяем, что метод addAnimal был вызван на репозитории
        verify(zooRepository, times(1)).addAnimal(healthyAnimal);
    }

    @Test
    void testAddAnimal_whenAnimalIsNotHealthy() {
        Animal sickAnimal = mock(Animal.class);
        when(vetService.checkHealth(sickAnimal)).thenReturn(false);

        zooService.addAnimal(sickAnimal);

        // Проверяем, что метод addAnimal не был вызван, если животное больное
        verify(zooRepository, never()).addAnimal(sickAnimal);
    }

    @Test
    void testGetTotalFoodConsumption() {
        Animal animal1 = mock(Animal.class);
        when(animal1.getFood()).thenReturn(10);

        Animal animal2 = mock(Animal.class);
        when(animal2.getFood()).thenReturn(20);

        when(zooRepository.getAnimals()).thenReturn(List.of(animal1, animal2));

        int totalFood = zooService.getTotalFoodConsumption();

        assertEquals(30, totalFood); // Проверяем, что сумма потребления пищи правильная
    }

    @Test
    void testGetTotalAnimals() {
        Animal animal1 = mock(Animal.class);
        Animal animal2 = mock(Animal.class);

        when(zooRepository.getAnimals()).thenReturn(List.of(animal1, animal2));

        int totalAnimals = zooService.getTotalAnimals();

        assertEquals(2, totalAnimals); // Проверяем, что количество животных правильное
    }

    @Test
    void testGetAnimalsForContactZoo() {
        Monkey monkey1 = mock(Monkey.class);
        when(monkey1.canInteractWithVisitors()).thenReturn(true);
        when(monkey1.getFood()).thenReturn(5);
        when(monkey1.getNumber()).thenReturn(3);
        when(monkey1.getKindnessLevel()).thenReturn(10);

        Monkey monkey2 = mock(Monkey.class);
        when(monkey2.canInteractWithVisitors()).thenReturn(false); // Этот животное не может взаимодействовать

        when(zooRepository.getAnimals()).thenReturn(List.of(monkey1, monkey2));

        List<List<String>> animalsForContactZoo = zooService.getAnimalsForContactZoo();

        // Проверяем, что только первый животное, которое может взаимодействовать с посетителями, включено
        assertEquals(1, animalsForContactZoo.size());
        assertEquals("Monkey", animalsForContactZoo.get(0).get(0));
        assertEquals("5", animalsForContactZoo.get(0).get(1));
        assertEquals("3", animalsForContactZoo.get(0).get(2));
        assertEquals("10", animalsForContactZoo.get(0).get(3));
    }

    @Test
    void testGetZooInventory() {
        Animal animal1 = mock(Animal.class);
        when(animal1.getNumber()).thenReturn(5);
    
        Thing thing1 = mock(Thing.class);
        when(thing1.getNumber()).thenReturn(2);
    
        when(zooRepository.getAnimals()).thenReturn(List.of(animal1));
        when(zooRepository.getThings()).thenReturn(List.of(thing1));
    
        List<List<String>> inventory = zooService.getZooInventory();
    
        assertEquals(2, inventory.size());
    
        assertEquals(List.of("Animal: " + animal1.getClass().getSimpleName(), "5"), inventory.get(0));
        assertEquals(List.of("Thing: " + thing1.getClass().getSimpleName(), "2"), inventory.get(1));
    }
}