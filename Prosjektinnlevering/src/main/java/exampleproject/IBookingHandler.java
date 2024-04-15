package exampleproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface IBookingHandler {

    TableTennis readBookings(String filename, TableTennis tableTennis) throws FileNotFoundException;

    void writeBookings(String filename, TableTennis tableTennis) throws FileNotFoundException, IOException;

    Path getBookingPath(String filename);
    
}
