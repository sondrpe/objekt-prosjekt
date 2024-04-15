package exampleproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BoookingHandlerTest {
    
    private static final String test_booking_file_content = "08:00-08:30";
    
    
    private IBookingHandler getBookingHandler(){
        return new HomeFolderBookingHandler();
    }

    private TableTennis getPlainTableTennisObject(){
        return new TableTennis();
    }

    private TableTennis getFilledTableTennisObject(){
        TableTennis tableTennis = getPlainTableTennisObject();
        tableTennis.addBooking(new Time("08:00", "08:30").toString());
        return tableTennis;
    }

    @BeforeAll
    public void setup() throws IOException {
        Files.write(getBookingHandler().getBookingPath("test_booking"), test_booking_file_content.getBytes());
    }

    @Test
    public void testReadBooking() throws IOException {
        TableTennis expectedTableTennisObject = getFilledTableTennisObject();
        TableTennis actualTableTennisObject = getBookingHandler().readBookings("test_booking", getPlainTableTennisObject());
        
        Iterator<String> expectedIterator = expectedTableTennisObject.getBookedTimeList().iterator();
        Iterator<String> actualIterator = actualTableTennisObject.getBookedTimeList().iterator();

        while (expectedIterator.hasNext()){
            try {
                String expectedTime = expectedIterator.next();
                String actualTime = actualIterator.next();
                assertEquals(expectedTime.toString(), actualTime.toString());
            } catch (IndexOutOfBoundsException e) {
                fail("listene er ikke de samme");
            }
        }
    }

    @AfterAll
    public void teardown(){
        getBookingHandler().getBookingPath("test_booking").toFile().delete();
    }
}
