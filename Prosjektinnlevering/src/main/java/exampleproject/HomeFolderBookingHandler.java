package exampleproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class HomeFolderBookingHandler implements IBookingHandler {


    @Override
    public TableTennis readBookings(String filename, TableTennis tableTennis) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(getFile(filename))) {
            if (tableTennis.getName() != null) {
                tableTennis.getPerson().setName(tableTennis.getName());
            }

            if(tableTennis.getBookedTimeList().size() == 1) {
                String[] properties = scanner.nextLine().split("\n|-");
                tableTennis.addBooking(new Time(properties[0], properties[1]).toString());

                if(tableTennis.getBookedTimeList().size() == 2) {
                    String[] properties2 = scanner.nextLine().split("\n|-");
                    tableTennis.addBooking(new Time(properties2[2], properties2[3]).toString());

                    if(tableTennis.getBookedTimeList().size() == 3) {
                        String[] properties3 = scanner.nextLine().split("\n|-");
                        tableTennis.addBooking(new Time(properties3[4], properties3[5]).toString());
                    }
                }
            }
            return tableTennis;
            }
        }
    

    @Override
    public void writeBookings(String filename, TableTennis tableTennis) throws IOException {
        Files.createDirectories(getBookingFolderPath());
        try(PrintWriter writer = new PrintWriter(getFile(filename))){
            for (String time : tableTennis.getBookedTimeList()) {
                writer.println(time.toString());
            }
        }
    }

    private static File getFile(String filename){
        return new File(HomeFolderBookingHandler.class.getResource("bookinger/").getFile() + filename + ".txt");
    }

    @Override
    public Path getBookingPath(String filename) {
        return getBookingFolderPath().resolve(filename + ".txt");
    }

    private static Path getBookingFolderPath() {
        return Path.of(System.getProperty("user.home"));
    }

    public static void main(String[] args) throws IOException {
        HomeFolderBookingHandler homeFolderBookingHandler = new HomeFolderBookingHandler();
        TableTennis tableTennis = new TableTennis(new Person("trump"));
        tableTennis.addBooking(new Time("09:00", "09:30").toString());
        System.out.println( tableTennis.getBookedTimeList().size());
        // homeFolderBookingHandler.writeBookings(tableTennis.getName(), tableTennis);

        homeFolderBookingHandler.readBookings(tableTennis.getName(), tableTennis);
    }
}
