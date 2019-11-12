package view;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ta2.Darbuotojas;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class EditWindowController implements Serializable {

    @FXML
    Label darbId;
    @FXML
    TextField vardas, pavarde;
    @FXML
    CheckBox pirm, antr, trec, ketv, penk, sest, sekm;

    public void uzpildytiDuomenis(Darbuotojas d) {
        darbId.setText(d.getId() + "");
        vardas.setText(d.getVardas());
        vardas.setEditable(false);
        pavarde.setText(d.getPavarde());
        pirm.setSelected(d.getDarboDienos()[0]);
        antr.setSelected(d.getDarboDienos()[1]);
        trec.setSelected(d.getDarboDienos()[2]);
        ketv.setSelected(d.getDarboDienos()[3]);
        penk.setSelected(d.getDarboDienos()[4]);
        sest.setSelected(d.getDarboDienos()[5]);
        sekm.setSelected(d.getDarboDienos()[6]);
    }

    public void uzdaryti(Event e) {
        Stage stage = (Stage) darbId.getScene().getWindow();
        stage.close();
    }

    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
}
