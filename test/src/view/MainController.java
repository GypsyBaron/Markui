package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ta2.Darbuotojas;
import ta2.TA2;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    TableView lentele;
    @FXML
    TextField vardas, pavarde;
    @FXML
    CheckBox pirm, antr, trec, ketv, penk, sest, sekm;

    public void saugotiDarbuotoja(Event e) {
        Darbuotojas naujas = new Darbuotojas();
        naujas.setVardas(vardas.getText());
        naujas.setPavarde(vardas.getText());
        boolean[] sav = new boolean[7];
        sav[0] = pirm.isSelected();
        sav[1] = antr.isSelected();
        sav[2] = trec.isSelected();
        sav[3] = ketv.isSelected();
        sav[4] = penk.isSelected();
        sav[5] = sest.isSelected();
        sav[6] = sekm.isSelected();
        naujas.setDarboDienos(sav);

        TA2 DB = new TA2();
        try {
            DB.connect();
            DB.addEmployee(naujas);
            DB.disconnect();

        } catch (Exception excep) {
            excep.printStackTrace();
        }

        refreshTable();
    }

    public void refreshTable() {
        TA2 DB = new TA2();
        lentele.getItems().clear();
        try {
            DB.connect();
            ArrayList<Darbuotojas> d = DB.getAllEmployees();
            lentele.getItems().addAll(d);
            DB.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openRecord(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            Darbuotojas pasirinktas = (Darbuotojas)lentele.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));
                Parent root = loader.load();
                EditWindowController ec = loader.getController();
                ec.uzpildytiDuomenis(pasirinktas);
                Stage primaryStage = new Stage();
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("vardas"));

        TableColumn surnameColumn = new TableColumn("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("pavarde"));

        TableColumn pirmadienisColumn = new TableColumn("Pr");
        pirmadienisColumn.setCellValueFactory(new PropertyValueFactory<>("pirm"));

        lentele.getColumns().clear();
        lentele.getColumns().addAll(idColumn, nameColumn, surnameColumn, pirmadienisColumn);

        refreshTable();
    }
}
