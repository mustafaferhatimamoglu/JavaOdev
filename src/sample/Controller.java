package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller implements Initializable
{
    String MasterData="";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }
    @FXML
    private void test(ActionEvent event)
    {
        System.out.println("lollolol");
    }
    @FXML
    private void BCXmlOku(ActionEvent event){
        System.out.println("XmlOku butonu tetik");

      /*  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(fileOpen.getParentPopup().getScene().getWindow());
        //chooser.showOpenDialog(node.getScene().getWindow());

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (selectedFile != null) {
            mainStage.display(selectedFile);
        }*/
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == null) return;
        System.out.println(selectedFile);

        //  kütüphane kullanamadığımdan dosyanın uzantısını alıcak bir şey yazmak zorundayım
        String extens2 = selectedFile.toString().substring(selectedFile.toString().lastIndexOf('.')+1);
        System.out.println(extens2);
        String sXML = "xml";
        /*if (extens2 == sXML){
            System.out.println("1");
            F_xml(selectedFile.toString());
            System.out.println("2");
        }*/
        if (new String("xml").equals(extens2))  F_xml(selectedFile.toString());


    }
    @FXML
    private void BCTextOku(ActionEvent event){
        System.out.println("TextOku butonu tetik");
    }

    private void F_xml(String gelen){
        System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
        DosyaOku("a1");
    }

    private void DosyaOku(String gelen){
        String ForReturn = "";
        try {
            File myObj = new File("C:\\Users\\musta\\IdeaProjects\\untitled\\DATA\\DATA.xml");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                ForReturn += data;
            }
            myReader.close();
            MasterData = ForReturn;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        MasterData = "Hata";
    }
}