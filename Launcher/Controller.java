package Launcher;

import Tools.Recorder;
import Tools.Soundclip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Tools.FileHandler;

import java.io.File;
import java.io.IOException;

public class Controller {

    private final boolean DEBUG = true;

    ObservableList<String> directyloclist = FXCollections.observableArrayList("Desktop", "MidiPy");

    // Buttons for the program
    @FXML
    public Button launcher;

    //Text field
    @FXML
    public TextField delay;


    @FXML
    public ChoiceBox directloc ;


    public static void tester(){
        Recorder rec = new Recorder();
        rec.takeScreenshot(500);
//        rec.Record(150, 3000);
//        Soundclip sc = new Soundclip("C:\\Users\\Steven\\Desktop\\MidiPy\\foropinion.wav");

    }

    @FXML
    private void initialize(){
        directloc.setValue("Desktop");
        directloc.setItems(directyloclist);
    }

    @FXML
    public void Takescreenshot(){
        System.out.println("Inside function takescreenshot");
    }

    @FXML
    public void Launcher(){
//        int intdelay = Integer.parseInt(delay.getText());
//        System.out.println(String.format("The current delay is %d", intdelay));

        FileHandler fh = new FileHandler();
        try {
//            File newfile = fh.createFolder("C:\\Users\\Steven\\Desktop\\MidiPy\\booty")
            fh.deleteFolder("C:\\Users\\Steven\\Desktop\\MidiPy\\booty");
        }
        catch(IOException io) {
            System.out.println("ERROR IO: " + io);
        }
    }




}
