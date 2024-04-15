package exampleproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TableTennisTest {

    private TableTennis tableTennis = new TableTennis();
    private Person person = new Person("MacMiller");
    private String time = new Time("08:00", "08:30").toString();

    
    @BeforeEach
    public void setup() {
        tableTennis = new TableTennis(person, time);
    }

    @Test
    @DisplayName("Teste å endre avgift")
    public void testFeeOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            tableTennis.changeFee(-15);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            tableTennis.changeFee(20);
        });
    }

    @Test
    @DisplayName("Teste å endre gyldig avgift")
    public void testValidFee() {
        tableTennis.changeFee(8);
        assertEquals(8, tableTennis.getFee());
    }

    @Test
    @DisplayName("Teste å prisen for timen")
    public void testCost() {
        tableTennis.changeFee(8);
        assertEquals(16, tableTennis.getCost(2));
    }

    @Test
    @DisplayName("Tester om man kan fjerne et element som er booket")
    public void testRemoveFromBookedTimeList() {
        tableTennis.addBooking(time);
        tableTennis.removeBooking("08:00 - 08:30");
        assertEquals(0, tableTennis.getBookedTimeList().size());
    }

    @Test
    @DisplayName("Tester om man kan fjerne et element som man ikke har booket")
    public void testRemoveNonBookingFromBookedTimeList() {
        assertThrows(IllegalArgumentException.class, () -> {
            tableTennis.removeBooking(time);
        });
    }

    @Test
    @DisplayName("Tester konstruktører")
    public void testConstructors() {
        TableTennis mac = new TableTennis(person);
        assertEquals("MacMiller", mac.getName());

        TableTennis miller = new TableTennis(person, time);
        assertEquals("MacMiller", miller.getName());
        assertEquals("08:00 - 08:30", miller.getTime());
    }

    @Test
    @DisplayName("Tester å booke tid")
    public void testAddBooking() {
        tableTennis.addBooking(new Time("09:00", "09:30").toString());
        assertEquals(1, tableTennis.getBookedTimeList().size());
    }

    @Test
    @DisplayName("Tester å booke allerede booket tid")
    public void testAddAleadyBooked() {
        tableTennis.addBooking(new Time("09:00", "09:30").toString());
        assertThrows(IllegalArgumentException.class, () -> {
            tableTennis.addBooking(new Time("09:00", "09:30").toString());
        });
    }

    @Test
    @DisplayName("Test å booke 4 tider")
    public void testAddTooMAnyTime() {
        tableTennis.addBooking(time);
        tableTennis.addBooking(new Time("08:30", "09:00").toString());
        tableTennis.addBooking(new Time("09:00", "09:30").toString());
        assertThrows(IllegalArgumentException.class, () -> {
            person.addTime(new Time("09:30", "10:00").toString());
        });
    }
}
