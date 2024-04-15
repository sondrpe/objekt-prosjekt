package exampleproject;



import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class TableTennisController {

    TableTennis tableTennis = new TableTennis();


    @FXML
    private TextField skrivInnNavn;

    @FXML
    private Text tekstFelt, visTid, velgTidTekst, showBookedTime, showCost;

    @FXML
    private Button bekreftNavnKnapp, bekreftTidKnapp, slettTidKnapp, payButton;

    @FXML
    private ListView<String> listViewTider;


    @FXML
    private ListView<String> listOfBooked;

    @FXML
    private Pane topLeft, bottomLeft, topRight, bottomRight, heltNederstHøyre;

    @FXML
    private void initialize(){
        updateTimeList();
    }

    @FXML
    private void confirmName(){
        try{
            String navn = this.skrivInnNavn.getText();
            tableTennis.getPerson().setName(navn);
            tekstFelt.setText(tableTennis.toString());
            topRight.setVisible(true);
            bottomLeft.setVisible(true);
            bottomRight.setVisible(true);
            bekreftNavnKnapp.setVisible(false);
        }
        catch (IllegalArgumentException e) {
        showErrorMessage(e.toString());
    }
    }

    @FXML
    private void nyttNavn(){
        bekreftNavnKnapp.setVisible(true);
        tableTennis.getBookedTimeList().clear();
        listOfBooked.getItems().clear();
        heltNederstHøyre.setVisible(false);
        topRight.setVisible(false);
        bottomLeft.setVisible(false);
        bottomRight.setVisible(false);

    }

    @FXML
    private void confirmTime(){
        try {
            String valgtTid = listViewTider.getSelectionModel().getSelectedItem();
            tableTennis.addBooking(valgtTid);
            listOfBooked.getItems().add(valgtTid);
            listViewTider.getItems().remove(valgtTid);
            heltNederstHøyre.setVisible(true);
            showCost.setVisible(true);
            payButton.setVisible(true);
            showCost.setText("For å booke timen(e) dine må du betale: " + tableTennis.getCost(tableTennis.getPerson().getTimeList().size()));

            tekstFelt.setText(tableTennis.toString());
        }
        catch (IllegalArgumentException e) {
            showErrorMessage(e.toString());
        }
    }

    @FXML
    private void deleteTime(){
        try {
            String timeToRemove = listOfBooked.getSelectionModel().getSelectedItem();
            tableTennis.removeBooking(timeToRemove);
            listOfBooked.getItems().remove(timeToRemove);
            listViewTider.getItems().add(timeToRemove); // skulle egentlig brukt de to linjene under, ref dokumentasjonsbeskrivelse
            // updateTimeList();
            // tableTennis.getBookedTimeList().stream()
            //                                 .forEach(time -> listViewTider.getItems().remove(time));

            tekstFelt.setText(tableTennis.getPerson().getName() + " har slettet bookingen sin: " + timeToRemove + "\n\n" + tableTennis.toStringDelete());

            if (tableTennis.getBookedTimeList().size() == 0) {
                showCost.setVisible(false);
                payButton.setVisible(false);
            } else {
                showCost.setText("For å booke timen(e) dine må du betale: " + tableTennis.getCost(tableTennis.getBookedTimeList().size()) + ",-");
            }
        }
        catch (IllegalArgumentException e) {
            showErrorMessage(e.toString());
        }}

    @FXML
    private void displaySelectedList(MouseEvent event){
        String time = listViewTider.getSelectionModel().getSelectedItem();
        visTid.setText("Valgt tid fra lista: " + time.toString());
    }

    @FXML
    private void displaySelectedBooked(MouseEvent event){
        String bookedTime = listOfBooked.getSelectionModel().getSelectedItem();
        showBookedTime.setText("Valgt tid av bookede tider: " + bookedTime.toString());
    }


    private void updateTimeList(){
        listViewTider.getItems().setAll(tableTennis.getListOfTimes());
    }

    @FXML
    private void vippsToPay() throws IOException{
        vipps();
        HomeFolderBookingHandler booking = new HomeFolderBookingHandler();
        booking.writeBookings(tableTennis.getName(), tableTennis);
    }

    private void showErrorMessage(String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Det har oppstått en feil");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void vipps(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Betaling");
        alert.setContentText("Du kan vippse beløpet til #nummer sensurert med hensyn på anonymitet#");
        if (tableTennis.getBookedTimeList().size() == 1) {
            alert.setHeaderText("For å sikre bookingen din må du betale");
        }
        else {
            alert.setHeaderText("For å sikre bookingene dine må du betale");
        }
        alert.showAndWait();
    }


}
