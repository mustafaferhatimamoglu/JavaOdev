package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML Button XmlOku;
    @FXML Button bar;
    @FXML Button line;

//    private void displayLabelForData(XYChart.Data<String, Number> data) {
//        final Node node = (Node) data.getNode();
//        final Text dataText = new Text(data.getYValue() + "");
//        ((javafx.scene.Node) node).parentProperty().addListener(new ChangeListener<Parent>() {
//            @Override public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
//                Group parentGroup = (Group) parent;
//                parentGroup.getChildren().add(dataText);
//            }
//        });
//
//        ((javafx.scene.Node) node).boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
//            @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
//                dataText.setLayoutX(
//                        Math.round(
//                                bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
//                        )
//                );
//                dataText.setLayoutY(
//                        Math.round(
//                                bounds.getMinY() - dataText.prefHeight(-1) * 0.5
//                        )
//                );
//            }
//        });
//    }
    @FXML
    private void BCXmlOku(ActionEvent event) {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Programming Language");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percent");

        // Create a BarChart
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        // Series 1 - Data of 2014
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<String, Number>("Java", 20.973));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("C#", 4.429));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("PHP", 2.792));

        // Series 2 - Data of 2015
        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
        dataSeries2.setName("2015");

        dataSeries2.getData().add(new XYChart.Data<String, Number>("Java", 26.983));
        dataSeries2.getData().add(new XYChart.Data<String, Number>("C#", 6.569));
        dataSeries2.getData().add(new XYChart.Data<String, Number>("PHP", 6.619));


        // Series 3 - Data of 2016
        XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<String, Number>();
        dataSeries2.setName("2016");

        dataSeries3.getData().add(new XYChart.Data<String, Number>("Java", 26.983));
        dataSeries3.getData().add(new XYChart.Data<String, Number>("C#", 6.569));
        dataSeries3.getData().add(new XYChart.Data<String, Number>("PHP", 6.619));
        // Add Series to BarChart.
        barChart.getData().add(dataSeries1);
        barChart.getData().add(dataSeries2);
        barChart.getData().add(dataSeries3);

        barChart.setTitle("Some Programming Languages");

        VBox vbox = new VBox(barChart);
Stage primaryStage = new Stage();
        primaryStage.setTitle("JavaFX BarChart (o7planning.org)");
        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);

        primaryStage.show();

        XmlOku.setVisible(false);
        line.setVisible(true);
        bar.setVisible(true);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\country_populations.xml");
            NodeList titleList = doc.getElementsByTagName("title");
            for (int i=0;i<titleList.getLength();i++){
                Node t = titleList.item(i);
                if (t.getNodeType()== Node.ELEMENT_NODE){
                    Element tittle = (Element) t;
                    NodeList xlabelList = doc.getElementsByTagName("xlabel");
                    NodeList recordList = doc.getElementsByTagName("record");
                    //NodeList recordList = doc.getElementsByTagName("field");
                    for (int j=0;j<recordList.getLength();j++){
                        Node Name =recordList.item(j);
                        if (Name.getNodeType()== Node.ELEMENT_NODE){
                            Element name = (Element) Name;
                           // System.out.printf("Name2 " + Name.getTextContent());
                            var a1 = name.getElementsByTagName("field").item(0).getTextContent();
                            var a2 = name.getElementsByTagName("field").item(1).getTextContent();
                            var a3 = name.getElementsByTagName("field").item(2).getTextContent();
                            var a4 = name.getElementsByTagName("field").item(3).getTextContent();
                            var a5 = name.getElementsByTagName("field").item(4).getTextContent();


                            //System.out.printf(""+a1+a2+a3+a4+a5);
                            //Element a = (Element) ((Element) Name).getElementsByTagName("field");

                            //System.out.printf("Name2 " + a.getTextContent());


                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
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



    ////
        private ScheduledExecutorService scheduledExecutorService;

        final static String austria = "Austria",  brazil = "Brazil",  france = "France";
        private IntegerProperty secondA,  secondB , secondC;
        private Text secondAText, secondBText , secondCText;

//        public static void main(String[] args) {
//            launch(args);
//        }


        public void start1(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Realtime Bar Chart Demo");

            //defining the axes
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            xAxis.setAnimated(false);
            yAxis.setAnimated(false);

            //creating the bar chart with two axis
            final BarChart<String,Number> bc =  new BarChart<>(xAxis,yAxis);
            bc.setAnimated(false);
            bc.setTitle("Country Summary");
            xAxis.setLabel("Country");
            yAxis.setLabel("Value");

            //defining a series to display data
            XYChart.Series<String, Number> seriesA = new XYChart.Series<>();
            Data<String, Number> dataA = new XYChart.Data<>(austria,0);
            seriesA.getData().add(dataA);
            seriesA.setName("Austra");

            secondA = new SimpleIntegerProperty(0);
            secondAText = new Text("");
            secondA.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
                dataA.setYValue(newValue);
                secondAText.setText(String.valueOf(newValue));
            });

            XYChart.Series<String, Number> seriesB = new XYChart.Series<>();
            Data<String, Number> dataB = new XYChart.Data<>(brazil,0);
            seriesB.getData().add(dataB);
            seriesB.setName("Brazil");
            secondB =  new SimpleIntegerProperty(0);
            secondB.bind(secondA.add(27));
            secondBText = new Text("");
            secondB.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
                dataB.setYValue(newValue);
                secondBText.setText(String.valueOf(newValue));
            });

            XYChart.Series<String, Number> seriesC = new XYChart.Series<>();
            Data<String, Number> dataC = new XYChart.Data<>(france,0);
            seriesC.getData().add(dataC);
            seriesC.setName("France");

            secondC =  new SimpleIntegerProperty(0);
            secondC.bind(secondA.add(14));
            secondCText = new Text("");
            secondC.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> {
                dataC.setYValue(newValue);
                secondCText.setText(String.valueOf(newValue));
            });

            // add series to chart
            bc.getData().add(seriesA);
            bc.getData().add(seriesB);
            bc.getData().add(seriesC);

            displayLabelForData(dataA, secondAText);
            displayLabelForData(dataB, secondBText);
            displayLabelForData(dataC, secondCText);

            // setup scene
            Scene scene = new Scene(bc, 800, 600);
            primaryStage.setScene(scene);

            // show the stage
            primaryStage.show();

            // setup a scheduled executor to periodically put data into the chart
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

            // input data onto graph per second
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

                // Update the chart
                Platform.runLater(() -> {
                    secondA.set( cal.get(Calendar.SECOND));
                });
            }, 0, 1, TimeUnit.SECONDS);
        }

//        @Override
//        public void stop() throws Exception {
//            super.stop();
//            scheduledExecutorService.shutdownNow();
//        }

        private void displayLabelForData(XYChart.Data<String, Number> data, Text text) {

            final Node node = (Node) data.getNode();
            ((Group) ((javafx.scene.Node) node).getParent()).getChildren().add(text);

            ((javafx.scene.Node) node).boundsInParentProperty().addListener((ChangeListener<Bounds>) (ov, oldBounds, bounds) -> {
                text.setLayoutX(
                        Math.round( bounds.getMinX() + bounds.getWidth() / 2 - text.prefWidth(-1) / 2));
                text.setLayoutY(Math.round( bounds.getMinY() - text.prefHeight(-1) * 0.5));
            });
        }
    }




//
//
//   /* String MasterData="";
//    List<String> RecordList=new ArrayList<String>();
//
//    List<String> Sdate=new ArrayList<String>();
//    List<String> name=new ArrayList<String>();
//    List<String> namekey=new ArrayList<String>();
//
//    List<String> country=new ArrayList<String>();
//    List<String> Svalue=new ArrayList<String>();
//    List<String> category=new ArrayList<String>();*/
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle)
//    {
//    }
//
//    @FXML
//    private void BCXmlOku(ActionEvent event){
//        System.out.println("XmlOku butonu tetik");
//
//      /*  FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.showOpenDialog(fileOpen.getParentPopup().getScene().getWindow());
//        //chooser.showOpenDialog(node.getScene().getWindow());
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
//                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
//                new FileChooser.ExtensionFilter("All Files", "*.*"));
//        File selectedFile = fileChooser.showOpenDialog(mainStage);
//        if (selectedFile != null) {
//            mainStage.display(selectedFile);
//        }*/
//
//        /*FileChooser fileChooser = new FileChooser();
//        File selectedFile = fileChooser.showOpenDialog(null);*/
//        // TODO : üst satırı aç altı kapat
//        String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\country_populations.xml";
//        //String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\DATA\\DATA.xml";
//
//        if (selectedFile == null) return;
//        System.out.println(selectedFile);
//
//        //  kütüphane kullanamadığımdan dosyanın uzantısını alıcak bir şey yazmak zorundayım
//        String extens2 = selectedFile.toString().substring(selectedFile.toString().lastIndexOf('.')+1);
//        System.out.println(extens2);
//        String sXML = "xml";
//        /*if (extens2 == sXML){
//            System.out.println("1");
//            F_xml(selectedFile.toString());
//            System.out.println("2");
//        }*/
//        if (new String("xml").equals(extens2))  F_xml(selectedFile.toString());
//
//
//
//
//    }
//
//    private void F_xml (String gelen){
//        //System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
//        DosyaOku(gelen);
//
//        while (true){
//            if (MasterData.indexOf("<record>") > 0){
//                MasterData = MasterData.substring(MasterData.indexOf("<record>"));
//                int r = MasterData.indexOf("</record>");
//                RecordList.add(MasterData.substring(0,r));
//
//                MasterData = MasterData.substring(r);
//
//                for (String record:RecordList){
//                    //System.out.println(record);
//                    String a1 = record.substring(record.indexOf("\"Name\" key=\""));
//                    namekey.add (RecordList.get(0).substring (0, RecordList.get(0).indexOf("\">")));
//
//                    //System.out.println(a1);
//                }
//                System.out.println(namekey);
//            }
//            else break;
//        }
//    }
//    private void DosyaOku(String gelen){
//        try {
//            File myObj = new File(gelen);
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                //System.out.println(data);
//                MasterData += data;
//            }
//            myReader.close();
//            return;
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        MasterData = "Hata";
//    }
//
//
//
//
//
//
//
//
//
//
//    //--- eski fonksiyonlar
//
//    private void F_xml_eski(String gelen){
//        System.out.println(gelen + " >> dosya xml olarak saptandı devam ediliyor");
//        DosyaOku(gelen);
//       // System.out.println(MasterData);
//
//        //System.out.println(MasterData.indexOf("<record>"));
//        //System.out.println(MasterData.substring(MasterData.indexOf("<record>")));
//
//        //while (MasterData != "</record></DATA>"){  -- yine aynı java saçmalığı
//        while (!(new String("</data>").equals(MasterData))){
//            //System.out.println(MasterData.substring(MasterData.indexOf("<record>")));
//            MasterData = MasterData.substring(MasterData.indexOf("<record>"));
//            int r = MasterData.indexOf("</record>");
//            RecordList.add(MasterData.substring(0,r));
//            MasterData = MasterData.substring(r);
//            //System.out.println(RecordList);
//            //System.out.println(MasterData);
//        }
//        for (String record:RecordList){
//            System.out.println(record);
//            //date.add(new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ));
//            //TODO : yarın burdan devam et listlere verileri bas
//            String a1 = record.substring(record.indexOf("<date>"));
//            System.out.println(a1);
//        }
//        System.out.println(Sdate);
//        //System.out.println(RecordList.get(0));
//        //System.out.println(MasterData.indexOf("</record>"));
//    }
//
//    @FXML
//    private void test(ActionEvent event)
//    {
//        System.out.println("lollolol");
//    }
//
//
//    @FXML
//    private void BCTextOku(ActionEvent event){
//        System.out.println("TextOku butonu tetik");
//    }

