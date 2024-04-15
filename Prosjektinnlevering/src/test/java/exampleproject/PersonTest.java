package exampleproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonTest {

    private Person person;
    private String time;
    private String testTime = new Time("08:00", "08:30").toString();


    @BeforeEach
    public void setup() {
        person = new Person();
    }

    @Test
    public void testConstructor() {
        Person mac = new Person("Mac");
        assertEquals("Mac", mac.getName());

        Time constructorTime = new Time("10:00", "10:30");
        Person miller = new Person("Miller", constructorTime);
        assertEquals("Miller", miller.getName());
        assertEquals("10:00 - 10:30", miller.getTime().toString());
    }

    @Test
    @DisplayName("Test å slette en tid fra lista som ikke finnes")
    public void testRemoveTimeNonExistent() {
        assertThrows(IllegalArgumentException.class, () -> {
            person.removeTime(testTime);
        });
    }

    @Test
    @DisplayName("Test å slette en tid fra lista")
    public void testRemoveTime() {
        person.addTime(testTime);
        person.removeTime(testTime);
        assertEquals(0, person.getTimeList().size());
    }

    @Test
    @DisplayName("Legge til tid null")
    public void testAddNullTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            person.addTime(time);
        });
    }

    @Test
    @DisplayName("Test å legge til en tid som allerede finnes i lista")
    public void testAddTimeDuplicate() {
        person.addTime(testTime);
        assertThrows(IllegalArgumentException.class, () -> {
            person.addTime(testTime);
        });
    }

    @Test
    @DisplayName("Test å legge til en tid i lista")
    public void testAddTime() {
        person.addTime(testTime);
        assertEquals("08:00 - 08:30", person.getTimeAtIndex(0));
    }

    @Test
    @DisplayName("Test å legge til 4 tider i lista")
    public void testAddTooMAnyTime() {
        person.addTime(testTime);
        person.addTime(new Time("08:30", "09:00").toString());
        person.addTime(new Time("09:00", "09:30").toString());
        assertThrows(IllegalArgumentException.class, () -> {
            person.addTime(new Time("09:30", "10:00").toString());
        });
    }

    @Test
    @DisplayName("Teste validering av for kort navn")
    public void testNameTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            person.setName("a");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            person.setName("");
        });
    }

    @Test
    @DisplayName("Teste validering av for langt navn")
    public void testNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            person.setName("abcdefghijklmnopqrstuvwxyz");
        });
    }

    @Test
    @DisplayName("Teste validering av navn med spesielle karakterer")
    public void testNameIrregularChar() {
        assertThrows(IllegalArgumentException.class, () -> {
            person.setName("Trump!");
        });
    }

    @Test
    @DisplayName("Teste validering av navn med norske karakterer")
    public void testNorwegianName() {
        person.setName("Fjørtoft");
        assertEquals("Fjørtoft", person.getName());
    }

    @Test
    @DisplayName("Teste validering av navn med mellomrom")
    public void testNameSpaceChar() {
        assertThrows(IllegalArgumentException.class, () -> {
            person.setName("Mac Miller");
        });
    }
}
