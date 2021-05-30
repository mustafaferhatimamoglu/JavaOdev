package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;


public class Controller implements Initializable
{
    String MasterData="";
    private int kacYilVar;
    /*
    List<String> RecordList=new ArrayList<String>();

    List<String> Sdate=new ArrayList<String>();
    List<String> name=new ArrayList<String>();
    List<String> namekey=new ArrayList<String>();

    List<String> country=new ArrayList<String>();
    List<String> Svalue=new ArrayList<String>();
    List<String> category=new ArrayList<String>();
    */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    @FXML Button XmlOku;
    @FXML Button Bar;
    @FXML Button Line;


    public String ustBilgi2 ;
    public String ustBilgi1 ;
    public Set<String> linkedHashSet = new LinkedHashSet<>();
    public Set<String> linkedHashSetUlke = new LinkedHashSet<>();

    public final NumberAxis yAxis = new NumberAxis();
    public final CategoryAxis xAxis = new CategoryAxis();
    public final BarChart<Number, String> barChart = new BarChart<Number, String>(yAxis, xAxis);


    ArrayList<String> elementsCountry =  new ArrayList<String>();

    ArrayList<String> ListxName =new ArrayList<String>();
    ArrayList<String> ListxCountry =new ArrayList<String>();
    ArrayList<String> ListxYearORJ =new ArrayList<String>();
    public ArrayList<String> ListxYear =new ArrayList<String>();
    ArrayList<String> ListxValue =new ArrayList<String>();
    ArrayList<String> ListxCategory =new ArrayList<String>();


    ArrayList<Integer> ListindexofYear =  new ArrayList();

    //public int i123 = 0;
    public int kaculkevar = 0;
    public int indexofYear=0;
    public int min = 0;
    public int max = 0;


    @FXML
    private void BCXmlOku(ActionEvent event) {

        System.out.println("XmlOku butonu tetik");

        XmlOku.setVisible(false);
        Line.setVisible(true);
        Bar.setVisible(true);


        /*FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);*/
        // TODO : üst satırı aç altı kapat
        String selectedFile = "C:\\Users\\Mustafa\\IdeaProjects\\JavaOdev\\Data Source -20210510\\country_populations.xml";
        //String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\country_populations.xml";

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
        if (new String("txt").equals(extens2))  F_txt(selectedFile.toString());

    }

    private void F_xml (String gelen){
        //System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
        //DosyaOku(gelen);

        /*while (true){
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
                System.out.println(RecordList);
            }
            else break;
        }
        */

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(gelen);
            NodeList titleList = doc.getElementsByTagName("title");
            for (int i=0;i<titleList.getLength();i++){
                Node t = titleList.item(i);
                if (t.getNodeType()== Node.ELEMENT_NODE){
                    Element tittle = (Element) t;
                    NodeList xlabelList = doc.getElementsByTagName("xlabel");
                    Node xlabel = xlabelList.item(0);
                    ustBilgi1 = xlabel.getTextContent();
                    NodeList recordList = doc.getElementsByTagName("record");
                    //xlabeli ustBilgi1 içine koymak için önceden kullanılan, üstteki okey? TODO
                    /*for (int k=0;k<xlabelList.getLength();k++){
                        Node xl = xlabelList.item(i);
                        if (xl.getNodeType() == Node.ELEMENT_NODE){
                            Element xlabel = (Element) xl;
                            //System.out.printf(""+xl.getTextContent()); //Xlabel burda
                            ustBilgi1 = xl.getTextContent();
                        }
                    }
                     */
                    for (int j=0;j<recordList.getLength();j++){
                        Node Name =recordList.item(j);
                        if (Name.getNodeType()== Node.ELEMENT_NODE){
                            Element name = (Element) Name;
                            //System.out.printf(""+t.getTextContent()); //Tittle burda
                            ustBilgi2 = t. getTextContent();
                            String xName = name.getElementsByTagName("field").item(0).getTextContent();
                            ListxName.add(xName);
                            String xCountry = name.getElementsByTagName("field").item(1).getTextContent();
                            ListxCountry.add(xCountry);
                            String xYear = name.getElementsByTagName("field").item(2).getTextContent();
                            ListxYear.add(xYear);
                            ListxYearORJ.add(xYear);
                            String xValue = name.getElementsByTagName("field").item(3).getTextContent();
                            ListxValue.add(xValue);
                            String xCategory = name.getElementsByTagName("field").item(4).getTextContent();
                            ListxCategory.add(xCategory);


                            linkedHashSet.add(xCategory);
                            linkedHashSetUlke.add(xName);



//                            for (int i=0 ; i<xCategory) {
//                                System.out.println(i);
//                            }

                            //System.out.printf(" "+xYear);
                        }
                    }

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private void F_txt (String gelen) {
        System.out.println(gelen + " >> dosya txt olarak saptandı devam ediliyor");
        try {
            File myObj = new File(gelen);
            int i = 0;
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                MasterData += data;
                if (i == 0) ustBilgi1 = data;
                if (i == 1) ustBilgi2 = data;
                if (data.contains(",")) {
                    String[] data2 = data.split(",");
                    //System.out.println(data2[0]);
                    //System.out.println(data2[0].toString());
                    ListxYear.add(data2[0]);
                    ListxName.add(data2[1]);
                    ListxCountry.add(data2[2]);
                    ListxValue.add(data2[3]);
                    ListxCategory.add(data2[4]);
                    linkedHashSet.add(data2[4]);
                    linkedHashSetUlke.add(data2[1]);
                }

                i++;
            }
            myReader.close();
            //return;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            MasterData = "Hata";
            e.printStackTrace();
        }
        //MasterData = "Hata";

        //System.out.println(MasterData);
        /*while (true){
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
                System.out.println(RecordList);
            }
            else break;
        }
        */
        //TODO : Sonradan eklenecek (txt)

    }

    private int kactaneyil (){
        kacYilVar = 0;
        Collections.sort(ListxYearORJ);
        int bas = Integer.parseInt(ListxYearORJ.get(0));
        for (int i= 1;i<ListxYearORJ.size();i++){
            if (Integer.parseInt(ListxYearORJ.get(i))!=bas){
                bas = Integer.parseInt(ListxYearORJ.get(i));
                kacYilVar++;
            }
        }
        System.out.printf(String.valueOf(kacYilVar));
        return kacYilVar;
    }   //TODO : İsimlendirme düzenlenecek
    @FXML
    private void BCbar (ActionEvent event) {
        System.out.println("bar tetik");

        //SET BARCHART PROPERTY
        xAxis.setLabel(ustBilgi1);
        yAxis.setLabel("");
        barChart.setTitle(ustBilgi2);
        barChart.setPrefSize(900,800);
        //barChart.autosize();
        barChart.setBarGap(-18);
        barChart.setCategoryGap(0);
        barChart.setHorizontalGridLinesVisible(false);
        barChart.setLegendSide(Side.TOP);

        //SET YEAR PROPERTY
        Label yearLabel = new Label();
        yearLabel.setOpacity(0.5);
        yearLabel.setLayoutX(725);
        yearLabel.setLayoutY(675);
        yearLabel.setFont(Font.font("Times New Roman",80));


        XYChart.Series<Number, String> dataSeries1 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries2 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries3 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries4 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries5 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries6 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries7 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries8 = new XYChart.Series<Number, String>();

        elementsCountry =  new ArrayList<>(linkedHashSet);
        ArrayList<String> elementsName = new ArrayList<>(linkedHashSetUlke);
        int elementsCountryi = 0;
        for (String x : elementsCountry){
            System.out.println(x);// Europe, North America
            //dataSeries1.getData().add(new XYChart.Data<Number, String>(0, x));
            elementsCountryi++;
            if (elementsCountryi == 1)           { dataSeries1.setName(x); barChart.getData().add(dataSeries1); }
            if (elementsCountryi == 2)           { dataSeries2.setName(x); barChart.getData().add(dataSeries2); }
            if (elementsCountryi == 3)           { dataSeries3.setName(x); barChart.getData().add(dataSeries3); }
            if (elementsCountryi == 4)           { dataSeries4.setName(x); barChart.getData().add(dataSeries4); }
            if (elementsCountryi == 5)           { dataSeries5.setName(x); barChart.getData().add(dataSeries5); }
            if (elementsCountryi == 6)           { dataSeries6.setName(x); barChart.getData().add(dataSeries6); }
            if (elementsCountryi == 7)           { dataSeries7.setName(x); barChart.getData().add(dataSeries7); }
            if (elementsCountryi == 8)           { dataSeries8.setName(x); barChart.getData().add(dataSeries8); }
        }


        min = Integer.parseInt(ListxYear.get(0));
        max = Integer.parseInt(ListxYear.get(0));
        for (int i = 1; i < ListxYear.size(); i++) {
            if (Integer.parseInt(ListxYear.get(i)) < min) {
                min = Integer.parseInt(ListxYear.get(i));
                //ListindexofYear.add(indexofYear);
            }
            if (Integer.parseInt(ListxYear.get(i)) > max){//gerek var mı bak bir daha
                max =Integer.parseInt(ListxYear.get(i));
            }
        }
        System.out.println(min);//1960
        System.out.println(max);//2019

        ListindexofYear.add(indexofYear);//burası çalışmıyor galiba ?

        kaculkevar = 0;

        indexofYear = ListxYear.indexOf(String.valueOf(min));
        while(indexofYear >= 0) {
            System.out.println(indexofYear);// 0 60 120 180 240
            ListxYear.set(indexofYear,"99999");
            indexofYear = ListxYear.indexOf(String.valueOf(min));
            ListindexofYear.add(indexofYear);
            kaculkevar++;
        }
        //min++; //TODO:Burada min++ yapmasak??


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().stream().sorted();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {//milisaniyeye ayar lazım gibi
            @Override
            public void handle(ActionEvent actionEvent) {
                yearLabel.setText(Integer.toString(min));
                //min = Integer.parseInt(ListxYear.get(0)); // TODO:Buna da ihtiyaç yok??

                for(int i = 0; i<kaculkevar;i++){

                    int abc = ListindexofYear.get(i);
//                    if (ListxCategory.get(abc).equals(elementsCountry.get(0)))
//                    {
//                        dataSeries1.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));
//                        System.out.println(ListxYearORJ.get(abc) + ListxName.get(abc)+ListxValue.get(abc));
//                    }
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(0))) dataSeries1.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(1))) dataSeries2.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(2))) dataSeries3.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(3))) dataSeries4.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(4))) dataSeries5.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(5))) dataSeries6.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(6))) dataSeries7.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(7))) dataSeries8.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}

                    /*Collections.sort(dataSeries1.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });
                    Collections.sort(dataSeries2.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });
                    Collections.sort(dataSeries3.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });
                    Collections.sort(dataSeries4.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });
                    Collections.sort(dataSeries5.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });
                    Collections.sort(dataSeries6.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });
                    Collections.sort(dataSeries7.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });
                    Collections.sort(dataSeries8.getData(), new Comparator<XYChart.Data>() {
                        @Override
                        public int compare(XYChart.Data o1, XYChart.Data o2) {                            Number xValue1 = (Number) o1.getXValue();                            Number xValue2 = (Number) o2.getXValue();                            return new BigDecimal(xValue1.toString()).compareTo(new BigDecimal(xValue2.toString()));                        }                    });


                     */
                    //barChart.
                    //if (ListxCategory.get(abc) == elementsCountry.get(1))                    dataSeries2.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));
                    //System.out.println(ListxYearORJ.get(abc) + ListxName.get(abc)+ListxValue.get(abc));
                }

                min = Integer.parseInt(ListxYear.get(0));
                for (int i = 1; i < ListxYear.size(); i++) {
                    if (Integer.parseInt(ListxYear.get(i)) < min) {
                        min = Integer.parseInt(ListxYear.get(i));
                    }
                }

                indexofYear = 0;


                ListindexofYear =  null;
                ListindexofYear =  new ArrayList<>();
                while(indexofYear >= 0) {
                    ListxYear.set(indexofYear,"99999");
                    indexofYear = ListxYear.indexOf(String.valueOf(min));
                    ListindexofYear.add(indexofYear);
                }
                //System.out.printf(String.valueOf(min)); //Bu arkadaş o anki yılı veriyor

            }
        }));

        timeline.setCycleCount(kactaneyil());
        timeline.setAutoReverse(false);
        timeline.play();
        barChart.setAnimated(false);

//        // Series 2 - Data of 2015
//        XYChart.Series<Number, String> dataSeries2 = new XYChart.Series<Number, String>();
//        dataSeries2.setName("2015");
//
//        dataSeries2.getData().add(new XYChart.Data<Number, String>(26.983, "Java"));
//        dataSeries2.getData().add(new XYChart.Data<Number, String>(6.569, "C#"));
//        dataSeries2.getData().add(new XYChart.Data<Number, String>(6.619, "PHP"));

        // Add Series to BarChart.
        //barChart.getData().addAll(dataSeries1,dataSeries2,dataSeries3,dataSeries4,dataSeries5,dataSeries6,dataSeries7,dataSeries8);
        //barChart.getData().add(dataSeries2);

        //BUTTON PROPERTY
        Button buttonPause = new Button("Duraklat");
        buttonPause.setLayoutX(2);
        buttonPause.setLayoutY(800);
        Button buttonPlay = new Button("Devam Et");
        buttonPlay.setDisable(true);
        buttonPlay.setLayoutX(112);
        buttonPlay.setLayoutY(800);
        EventHandler<ActionEvent> eventPlay = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                timeline.play();
                buttonPause.setDisable(false);
                buttonPlay.setDisable(true);
            }
        };
        EventHandler<ActionEvent> eventPause = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                timeline.pause();
                buttonPause.setDisable(true);
                buttonPlay.setDisable(false);
            }
        };
        buttonPause.setOnAction(eventPause);
        buttonPlay.setOnAction(eventPlay);
        buttonPause.setPrefSize(100,50);
        buttonPlay.setPrefSize(100,50);

        Button butttonRestart = new Button( "Yeniden Başlat" );
        butttonRestart.setPrefSize(150,50);
        butttonRestart.setLayoutX(222);
        butttonRestart.setLayoutY(800);
        EventHandler<ActionEvent> eventRestart = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                timeline.playFromStart();
                //TODO:Restart actionu tanımlanacak
            }
        };
        butttonRestart.setOnAction(eventRestart);

        AnchorPane anchorPane = new AnchorPane(barChart,buttonPause,buttonPlay,butttonRestart,yearLabel);
        Scene scene = new Scene(anchorPane, 1000, 900);


        Stage secondStage = new Stage();
        secondStage.setScene(scene);

        secondStage.setResizable(false);
        secondStage.setWidth(1000);
        secondStage.setHeight(900);
        secondStage.show();
    }
//    public static int indexOf(LinkedHashSet<String> set,
//                              int element)
//    {
//
//        // If element not present in the LinkedHashSet it
//        // returns -1
//        int index = -1;
//
//        // get an iterator
//        Iterator<String> iterator = set.iterator();
//
//        int currentIndex = 0;
//        while (iterator.hasNext()) {
//
//            // If element present in the LinkedHashSet
//            if (iterator.next().equals(element)) {
//                index = currentIndex;
//                break;
//            }
//
//            currentIndex++;
//        }
//
//        // Return index of the element
//        return index;
//    }

    @FXML
    private void BCline (ActionEvent event) {
        System.out.println("line tetik");

        JOptionPane.showMessageDialog(null, "Hocam diğer taraf mükkemmel çalışsıon diye burayı yapmadık", "InfoBox: " + "malesef", JOptionPane.INFORMATION_MESSAGE);


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
}




//--- eski fonksiyonlar

  /*  private void F_xml_eski(String gelen){
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



}

//package sample;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Bounds;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.chart.BarChart;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.chart.XYChart.Data;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Calendar;
//import java.util.ResourceBundle;
//import java.util.TimeZone;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//
//public class Controller implements Initializable {
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
//    @FXML Button XmlOku;
//    @FXML Button bar;
//    @FXML Button line;
//
////    private void displayLabelForData(XYChart.Data<String, Number> data) {
////        final Node node = (Node) data.getNode();
////        final Text dataText = new Text(data.getYValue() + "");
////        ((javafx.scene.Node) node).parentProperty().addListener(new ChangeListener<Parent>() {
////            @Override public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
////                Group parentGroup = (Group) parent;
////                parentGroup.getChildren().add(dataText);
////            }
////        });
////
////        ((javafx.scene.Node) node).boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
////            @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
////                dataText.setLayoutX(
////                        Math.round(
////                                bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
////                        )
////                );
////                dataText.setLayoutY(
////                        Math.round(
////                                bounds.getMinY() - dataText.prefHeight(-1) * 0.5
////                        )
////                );
////            }
////        });
////    }
//    @FXML
//    private void BCXmlOku(ActionEvent event) {
//
//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setLabel("Programming Language");
//
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("Percent");
//
//        // Create a BarChart
//        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
//
//        // Series 1 - Data of 2014
//        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
//        dataSeries1.setName("2014");
//
//        dataSeries1.getData().add(new XYChart.Data<String, Number>("Java", 20.973));
//        dataSeries1.getData().add(new XYChart.Data<String, Number>("C#", 4.429));
//        dataSeries1.getData().add(new XYChart.Data<String, Number>("PHP", 2.792));
//
//        // Series 2 - Data of 2015
//        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
//        dataSeries2.setName("2015");
//
//        dataSeries2.getData().add(new XYChart.Data<String, Number>("Java", 26.983));
//        dataSeries2.getData().add(new XYChart.Data<String, Number>("C#", 6.569));
//        dataSeries2.getData().add(new XYChart.Data<String, Number>("PHP", 6.619));
//
//
//        // Series 3 - Data of 2016
//        XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<String, Number>();
//        dataSeries2.setName("2016");
//
//        dataSeries3.getData().add(new XYChart.Data<String, Number>("Java", 26.983));
//        dataSeries3.getData().add(new XYChart.Data<String, Number>("C#", 6.569));
//        dataSeries3.getData().add(new XYChart.Data<String, Number>("PHP", 6.619));
//        // Add Series to BarChart.
//        barChart.getData().add(dataSeries1);
//        barChart.getData().add(dataSeries2);
//        barChart.getData().add(dataSeries3);
//
//        barChart.setTitle("Some Programming Languages");
//
//        VBox vbox = new VBox(barChart);
//Stage primaryStage = new Stage();
//        primaryStage.setTitle("JavaFX BarChart (o7planning.org)");
//        Scene scene = new Scene(vbox, 400, 200);
//
//        primaryStage.setScene(scene);
//        primaryStage.setHeight(300);
//        primaryStage.setWidth(400);
//
//        primaryStage.show();
//
//        XmlOku.setVisible(false);
//        line.setVisible(true);
//        bar.setVisible(true);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse("C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\country_populations.xml");
//            NodeList titleList = doc.getElementsByTagName("title");
//            for (int i=0;i<titleList.getLength();i++){
//                Node t = titleList.item(i);
//                if (t.getNodeType()== Node.ELEMENT_NODE){
//                    Element tittle = (Element) t;
//                    NodeList xlabelList = doc.getElementsByTagName("xlabel");
//                    NodeList recordList = doc.getElementsByTagName("record");
//                    //NodeList recordList = doc.getElementsByTagName("field");
//                    for (int j=0;j<recordList.getLength();j++){
//                        Node Name =recordList.item(j);
//                        if (Name.getNodeType()== Node.ELEMENT_NODE){
//                            Element name = (Element) Name;
//                           // System.out.printf("Name2 " + Name.getTextContent());
//                            var a1 = name.getElementsByTagName("field").item(0).getTextContent();
//                            var a2 = name.getElementsByTagName("field").item(1).getTextContent();
//                            var a3 = name.getElementsByTagName("field").item(2).getTextContent();
//                            var a4 = name.getElementsByTagName("field").item(3).getTextContent();
//                            var a5 = name.getElementsByTagName("field").item(4).getTextContent();
//
//
//                            //System.out.printf(""+a1+a2+a3+a4+a5);
//                            //Element a = (Element) ((Element) Name).getElementsByTagName("field");
//
//                            //System.out.printf("Name2 " + a.getTextContent());
//
//
//                        }
//                    }
//                }
//            }
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//        @FXML
//    private void test(ActionEvent event)
//    {
//        System.out.println("lollolol");
//    }
//
//
//    @FXML
//    private void BCTextOku(ActionEvent event){
//        System.out.println("TextOku butonu tetik");
//   }
//
//
//
//    ////
//        private ScheduledExecutorService scheduledExecutorService;
//
//        final static String austria = "Austria",  brazil = "Brazil",  france = "France";
//        private IntegerProperty secondA,  secondB , secondC;
//        private Text secondAText, secondBText , secondCText;
//
////        public static void main(String[] args) {
////            launch(args);
////        }
//
//
//        public void start1(Stage primaryStage) throws Exception {
//            primaryStage.setTitle("Realtime Bar Chart Demo");
//
//            //defining the axes
//            final CategoryAxis xAxis = new CategoryAxis();
//            final NumberAxis yAxis = new NumberAxis();
//
//            xAxis.setAnimated(false);
//            yAxis.setAnimated(false);
//
//            //creating the bar chart with two axis
//            final BarChart<String,Number> bc =  new BarChart<>(xAxis,yAxis);
//            bc.setAnimated(false);
//            bc.setTitle("Country Summary");
//            xAxis.setLabel("Country");
//            yAxis.setLabel("Value");
//
//            //defining a series to display data
//            XYChart.Series<String, Number> seriesA = new XYChart.Series<>();
//            Data<String, Number> dataA = new XYChart.Data<>(austria,0);
//            seriesA.getData().add(dataA);
//            seriesA.setName("Austra");
//
//            secondA = new SimpleIntegerProperty(0);
//            secondAText = new Text("");
//            secondA.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
//                dataA.setYValue(newValue);
//                secondAText.setText(String.valueOf(newValue));
//            });
//
//            XYChart.Series<String, Number> seriesB = new XYChart.Series<>();
//            Data<String, Number> dataB = new XYChart.Data<>(brazil,0);
//            seriesB.getData().add(dataB);
//            seriesB.setName("Brazil");
//            secondB =  new SimpleIntegerProperty(0);
//            secondB.bind(secondA.add(27));
//            secondBText = new Text("");
//            secondB.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
//                dataB.setYValue(newValue);
//                secondBText.setText(String.valueOf(newValue));
//            });
//
//            XYChart.Series<String, Number> seriesC = new XYChart.Series<>();
//            Data<String, Number> dataC = new XYChart.Data<>(france,0);
//            seriesC.getData().add(dataC);
//            seriesC.setName("France");
//
//            secondC =  new SimpleIntegerProperty(0);
//            secondC.bind(secondA.add(14));
//            secondCText = new Text("");
//            secondC.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
//                dataC.setYValue(newValue);
//                secondCText.setText(String.valueOf(newValue));
//            });
//
//            // add series to chart
//            bc.getData().add(seriesA);
//            bc.getData().add(seriesB);
//            bc.getData().add(seriesC);
//
//            displayLabelForData(dataA, secondAText);
//            displayLabelForData(dataB, secondBText);
//            displayLabelForData(dataC, secondCText);
//
//            // setup scene
//            Scene scene = new Scene(bc, 800, 600);
//            primaryStage.setScene(scene);
//
//            // show the stage
//            primaryStage.show();
//
//            // setup a scheduled executor to periodically put data into the chart
//            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//
//            // input data onto graph per second
//            scheduledExecutorService.scheduleAtFixedRate(() -> {
//                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//
//                // Update the chart
//                Platform.runLater(() -> {
//                    secondA.set( cal.get(Calendar.SECOND));
//                });
//            }, 0, 1, TimeUnit.SECONDS);
//        }
//
////        @Override
////        public void stop() throws Exception {
////            super.stop();
////            scheduledExecutorService.shutdownNow();
////        }
//
//        private void displayLabelForData(XYChart.Data<String, Number> data, Text text) {
//
//            final Node node = (Node) data.getNode();
//            ((Group) ((javafx.scene.Node) node).getParent()).getChildren().add(text);
//
//            ((javafx.scene.Node) node).boundsInParentProperty().addListener((ChangeListener<Bounds>) (ov, oldBounds, bounds) -> {
//                text.setLayoutX(
//                        Math.round( bounds.getMinX() + bounds.getWidth() / 2 - text.prefWidth(-1) / 2));
//                text.setLayoutY(Math.round( bounds.getMinY() - text.prefHeight(-1) * 0.5));
//            });
//        }
//    }
//
//
//
//
////
////
////   /* String MasterData="";
////    List<String> RecordList=new ArrayList<String>();
////
////    List<String> Sdate=new ArrayList<String>();
////    List<String> name=new ArrayList<String>();
////    List<String> namekey=new ArrayList<String>();
////
////    List<String> country=new ArrayList<String>();
////    List<String> Svalue=new ArrayList<String>();
////    List<String> category=new ArrayList<String>();*/
////
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle)
////    {
////    }
////
////    @FXML
////    private void BCXmlOku(ActionEvent event){
////        System.out.println("XmlOku butonu tetik");
////
////      /*  FileChooser fileChooser = new FileChooser();
////        fileChooser.setTitle("Open Resource File");
////        fileChooser.showOpenDialog(fileOpen.getParentPopup().getScene().getWindow());
////        //chooser.showOpenDialog(node.getScene().getWindow());
////
////        FileChooser fileChooser = new FileChooser();
////        fileChooser.setTitle("Open Resource File");
////        fileChooser.getExtensionFilters().addAll(
////                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
////                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
////                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
////                new FileChooser.ExtensionFilter("All Files", "*.*"));
////        File selectedFile = fileChooser.showOpenDialog(mainStage);
////        if (selectedFile != null) {
////            mainStage.display(selectedFile);
////        }*/
////
////        /*FileChooser fileChooser = new FileChooser();
////        File selectedFile = fileChooser.showOpenDialog(null);*/
////        // TODO : üst satırı aç altı kapat
////        String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\country_populations.xml";
////        //String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\DATA\\DATA.xml";
////
////        if (selectedFile == null) return;
////        System.out.println(selectedFile);
////
////        //  kütüphane kullanamadığımdan dosyanın uzantısını alıcak bir şey yazmak zorundayım
////        String extens2 = selectedFile.toString().substring(selectedFile.toString().lastIndexOf('.')+1);
////        System.out.println(extens2);
////        String sXML = "xml";
////        /*if (extens2 == sXML){
////            System.out.println("1");
////            F_xml(selectedFile.toString());
////            System.out.println("2");
////        }*/
////        if (new String("xml").equals(extens2))  F_xml(selectedFile.toString());
////
////
////
////
////    }
////
////    private void F_xml (String gelen){
////        //System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
////        DosyaOku(gelen);
////
////        while (true){
////            if (MasterData.indexOf("<record>") > 0){
////                MasterData = MasterData.substring(MasterData.indexOf("<record>"));
////                int r = MasterData.indexOf("</record>");
////                RecordList.add(MasterData.substring(0,r));
////
////                MasterData = MasterData.substring(r);
////
////                for (String record:RecordList){
////                    //System.out.println(record);
////                    String a1 = record.substring(record.indexOf("\"Name\" key=\""));
////                    namekey.add (RecordList.get(0).substring (0, RecordList.get(0).indexOf("\">")));
////
////                    //System.out.println(a1);
////                }
////                System.out.println(namekey);
////            }
////            else break;
////        }
////    }
////    private void DosyaOku(String gelen){
////        try {
////            File myObj = new File(gelen);
////            Scanner myReader = new Scanner(myObj);
////            while (myReader.hasNextLine()) {
////                String data = myReader.nextLine();
////                //System.out.println(data);
////                MasterData += data;
////            }
////            myReader.close();
////            return;
////        } catch (FileNotFoundException e) {
////            System.out.println("An error occurred.");
////            e.printStackTrace();
////        }
////        MasterData = "Hata";
////    }
////
////
////
////
////
////
////
////
////
////
////    //--- eski fonksiyonlar
////
////    private void F_xml_eski(String gelen){
////        System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
////        DosyaOku(gelen);
////       // System.out.println(MasterData);
////
////        //System.out.println(MasterData.indexOf("<record>"));
////        //System.out.println(MasterData.substring(MasterData.indexOf("<record>")));
////
////        //while (MasterData != "</record></DATA>"){  -- yine aynı java saçmalığı
////        while (!(new String("</data>").equals(MasterData))){
////            //System.out.println(MasterData.substring(MasterData.indexOf("<record>")));
////            MasterData = MasterData.substring(MasterData.indexOf("<record>"));
////            int r = MasterData.indexOf("</record>");
////            RecordList.add(MasterData.substring(0,r));
////            MasterData = MasterData.substring(r);
////            //System.out.println(RecordList);
////            //System.out.println(MasterData);
////        }
////        for (String record:RecordList){
////            System.out.println(record);
////            //date.add(new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ));
////            //TODO : yarın burdan devam et listlere verileri bas
////            String a1 = record.substring(record.indexOf("<date>"));
////            System.out.println(a1);
////        }
////        System.out.println(Sdate);
////        //System.out.println(RecordList.get(0));
////        //System.out.println(MasterData.indexOf("</record>"));
////    }
////
////    @FXML
////    private void test(ActionEvent event)
////    {
////        System.out.println("lollolol");
////    }
////
////
////    @FXML
////    private void BCTextOku(ActionEvent event){
////        System.out.println("TextOku butonu tetik");
////    }
//
