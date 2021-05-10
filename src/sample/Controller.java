package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Controller implements Initializable
{
    String MasterData="";
    List<String> RecordList=new ArrayList<String>();

    List<String> Sdate=new ArrayList<String>();
    List<String> name=new ArrayList<String>();
    List<String> namekey=new ArrayList<String>();

    List<String> country=new ArrayList<String>();
    List<String> Svalue=new ArrayList<String>();
    List<String> category=new ArrayList<String>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
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

        /*FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);*/
        // TODO : üst satırı aç altı kapat
        String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\country_populations.xml";
        //String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\DATA\\DATA.xml";

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

    private void F_xml (String gelen){
        //System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
        DosyaOku(gelen);

        while (true){
            if (MasterData.indexOf("<record>") > 0){
                MasterData = MasterData.substring(MasterData.indexOf("<record>"));
                int r = MasterData.indexOf("</record>");
                RecordList.add(MasterData.substring(0,r));

                MasterData = MasterData.substring(r);

                for (String record:RecordList){
                    //System.out.println(record);
                    String a1 = record.substring(record.indexOf("\"Name\" key=\""));
                    namekey.add (RecordList.get(0).substring (0, RecordList.get(0).indexOf("\">")));

                    //System.out.println(a1);
                }
                System.out.println(namekey);
            }
            else break;
        }
    }
    private void DosyaOku(String gelen){
        try {
            File myObj = new File(gelen);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                MasterData += data;
            }
            myReader.close();
            return;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        MasterData = "Hata";
    }










    //--- eski fonksiyonlar

    private void F_xml_eski(String gelen){
        System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
        DosyaOku(gelen);
       // System.out.println(MasterData);

        //System.out.println(MasterData.indexOf("<record>"));
        //System.out.println(MasterData.substring(MasterData.indexOf("<record>")));

        //while (MasterData != "</record></DATA>"){  -- yine aynı java saçmalığı
        while (!(new String("</data>").equals(MasterData))){
            //System.out.println(MasterData.substring(MasterData.indexOf("<record>")));
            MasterData = MasterData.substring(MasterData.indexOf("<record>"));
            int r = MasterData.indexOf("</record>");
            RecordList.add(MasterData.substring(0,r));
            MasterData = MasterData.substring(r);
            //System.out.println(RecordList);
            //System.out.println(MasterData);
        }
        for (String record:RecordList){
            System.out.println(record);
            //date.add(new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ));
            //TODO : yarın burdan devam et listlere verileri bas
            String a1 = record.substring(record.indexOf("<date>"));
            System.out.println(a1);
        }
        System.out.println(Sdate);
        //System.out.println(RecordList.get(0));
        //System.out.println(MasterData.indexOf("</record>"));
    }

    @FXML
    private void test(ActionEvent event)
    {
        System.out.println("lollolol");
    }


    @FXML
    private void BCTextOku(ActionEvent event){
        System.out.println("TextOku butonu tetik");
    }

}