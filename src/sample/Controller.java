package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
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

    public String LorB;
    public String XorT;
    @FXML Button XmlOku;
    @FXML Button Bar;
    @FXML Button Line;


    public String ustBilgi2 ;
    public String ustBilgi1 ;
    public Set<String> linkedHashSet = new LinkedHashSet<>();
    public Set<String> linkedHashSetUlke = new LinkedHashSet<>();

    public NumberAxis yAxis = new NumberAxis();
    public CategoryAxis xAxis = new CategoryAxis();
    public BarChart<Number, String> barChart = new BarChart<Number, String>(yAxis, xAxis);
    public LineChart<Number, String> lineChart = new LineChart<Number, String>(yAxis, xAxis);


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

    public String gelenSabit ;
    public String Dosyayolu;


    @FXML
    private void BCXmlOku(ActionEvent event) {

        System.out.println("XmlOku butonu tetik");

        XmlOku.setVisible(false);
        Line.setVisible(true);
        Bar.setVisible(true);


       FileChooser fileChooser = new FileChooser();
       File selectedFile = fileChooser.showOpenDialog(null);
        // TODO : üst satırı aç altı kapat
        //String selectedFile = "C:\\Users\\Mustafa\\IdeaProjects\\JavaOdev\\Data Source -20210510\\country_populations.xml";
        //String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\country_populations.xml";
       // String selectedFile = "C:\\Users\\musta\\IdeaProjects\\untitled\\Data Source -20210510\\city_populations.txt";
        gelenSabit = selectedFile.toString();
        if (selectedFile == null) return;
        System.out.println(selectedFile);

        //  kütüphane kullanamadığımdan dosyanın uzantısını alıcak bir şey yazmak zorundayım
        String extens2 = selectedFile.toString().substring(selectedFile.toString().lastIndexOf('.')+1);
        System.out.println(extens2);
        String sXML = "xml";

        if (new String("xml").equals(extens2))  F_xml(selectedFile.toString());
        if (new String("txt").equals(extens2))  F_txt(selectedFile.toString());

    }

    private void F_xml (String gelen){
        XorT = "X";
        Dosyayolu=gelen;
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
        XorT = "T";
        Dosyayolu=gelen;
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
                    ListxYear.add(data2[0].substring(0,4));
                    ListxYearORJ.add(data2[0].substring(0,4));
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
            System.out.println("asdnasdkjfbasdf");
            //return;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            MasterData = "Hata";
            e.printStackTrace();
        }

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
        LorB = "B";
        //BooleanProperty isGameRunning = new SimpleBooleanProperty(false);
//        Window v = null;
//        v.getScene();
//        v.hide();




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
        XYChart.Series<Number, String> dataSeries9 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries10 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries11 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries12 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries13 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries14 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries15 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries16 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries17 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries18 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries19 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries20 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries21 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries22 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries23 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries24 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries25 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries26 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries27 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries28 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries29 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries30 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries31 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries32 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries33 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries34 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries35 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries36 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries37 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries38 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries39 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries40 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries41 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries42 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries43 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries44 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries45 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries46 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries47 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries48 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries49 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries50 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries51 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries52 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries53 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries54 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries55 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries56 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries57 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries58 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries59 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries60 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries61 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries62 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries63 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries64 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries65 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries66 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries67 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries68 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries69 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries70 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries71 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries72 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries73 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries74 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries75 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries76 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries77 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries78 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries79 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries80 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries81 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries82 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries83 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries84 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries85 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries86 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries87 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries88 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries89 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries90 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries91 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries92 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries93 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries94 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries95 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries96 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries97 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries98 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries99 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries100 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries101 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries102 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries103 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries104 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries105 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries106 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries107 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries108 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries109 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries110 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries111 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries112 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries113 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries114 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries115 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries116 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries117 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries118 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries119 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries120 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries121 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries122 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries123 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries124 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries125 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries126 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries127 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries128 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries129 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries130 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries131 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries132 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries133 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries134 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries135 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries136 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries137 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries138 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries139 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries140 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries141 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries142 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries143 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries144 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries145 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries146 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries147 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries148 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries149 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries150 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries151 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries152 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries153 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries154 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries155 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries156 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries157 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries158 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries159 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries160 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries161 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries162 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries163 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries164 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries165 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries166 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries167 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries168 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries169 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries170 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries171 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries172 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries173 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries174 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries175 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries176 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries177 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries178 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries179 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries180 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries181 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries182 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries183 = new XYChart.Series<Number, String>();


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
            if (elementsCountryi == 9)           { dataSeries9.setName(x); barChart.getData().add(dataSeries9); }
            if (elementsCountryi == 10)           { dataSeries10.setName(x); barChart.getData().add(dataSeries10); }
            if (elementsCountryi == 11)           { dataSeries11.setName(x); barChart.getData().add(dataSeries11); }
            if (elementsCountryi == 12)           { dataSeries12.setName(x); barChart.getData().add(dataSeries12); }
            if (elementsCountryi == 13)           { dataSeries13.setName(x); barChart.getData().add(dataSeries13); }
            if (elementsCountryi == 14)           { dataSeries14.setName(x); barChart.getData().add(dataSeries14); }
            if (elementsCountryi == 15)           { dataSeries15.setName(x); barChart.getData().add(dataSeries15); }
            if (elementsCountryi == 16)           { dataSeries16.setName(x); barChart.getData().add(dataSeries16); }
            if (elementsCountryi == 17)           { dataSeries17.setName(x); barChart.getData().add(dataSeries17); }
            if (elementsCountryi == 18)           { dataSeries18.setName(x); barChart.getData().add(dataSeries18); }
            if (elementsCountryi == 19)           { dataSeries19.setName(x); barChart.getData().add(dataSeries19); }
            if (elementsCountryi == 20)           { dataSeries20.setName(x); barChart.getData().add(dataSeries20); }
            if (elementsCountryi == 21)           { dataSeries21.setName(x); barChart.getData().add(dataSeries21); }
            if (elementsCountryi == 22)           { dataSeries22.setName(x); barChart.getData().add(dataSeries22); }
            if (elementsCountryi == 23)           { dataSeries23.setName(x); barChart.getData().add(dataSeries23); }
            if (elementsCountryi == 24)           { dataSeries24.setName(x); barChart.getData().add(dataSeries24); }
            if (elementsCountryi == 25)           { dataSeries25.setName(x); barChart.getData().add(dataSeries25); }
            if (elementsCountryi == 26)           { dataSeries26.setName(x); barChart.getData().add(dataSeries26); }
            if (elementsCountryi == 27)           { dataSeries27.setName(x); barChart.getData().add(dataSeries27); }
            if (elementsCountryi == 28)           { dataSeries28.setName(x); barChart.getData().add(dataSeries28); }
            if (elementsCountryi == 29)           { dataSeries29.setName(x); barChart.getData().add(dataSeries29); }
            if (elementsCountryi == 30)           { dataSeries30.setName(x); barChart.getData().add(dataSeries30); }
            if (elementsCountryi == 31)           { dataSeries31.setName(x); barChart.getData().add(dataSeries31); }
            if (elementsCountryi == 32)           { dataSeries32.setName(x); barChart.getData().add(dataSeries32); }
            if (elementsCountryi == 33)           { dataSeries33.setName(x); barChart.getData().add(dataSeries33); }
            if (elementsCountryi == 34)           { dataSeries34.setName(x); barChart.getData().add(dataSeries34); }
            if (elementsCountryi == 35)           { dataSeries35.setName(x); barChart.getData().add(dataSeries35); }
            if (elementsCountryi == 36)           { dataSeries36.setName(x); barChart.getData().add(dataSeries36); }
            if (elementsCountryi == 37)           { dataSeries37.setName(x); barChart.getData().add(dataSeries37); }
            if (elementsCountryi == 38)           { dataSeries38.setName(x); barChart.getData().add(dataSeries38); }
            if (elementsCountryi == 39)           { dataSeries39.setName(x); barChart.getData().add(dataSeries39); }
            if (elementsCountryi == 40)           { dataSeries40.setName(x); barChart.getData().add(dataSeries40); }
            if (elementsCountryi == 41)           { dataSeries41.setName(x); barChart.getData().add(dataSeries41); }
            if (elementsCountryi == 42)           { dataSeries42.setName(x); barChart.getData().add(dataSeries42); }
            if (elementsCountryi == 43)           { dataSeries43.setName(x); barChart.getData().add(dataSeries43); }
            if (elementsCountryi == 44)           { dataSeries44.setName(x); barChart.getData().add(dataSeries44); }
            if (elementsCountryi == 45)           { dataSeries45.setName(x); barChart.getData().add(dataSeries45); }
            if (elementsCountryi == 46)           { dataSeries46.setName(x); barChart.getData().add(dataSeries46); }
            if (elementsCountryi == 47)           { dataSeries47.setName(x); barChart.getData().add(dataSeries47); }
            if (elementsCountryi == 48)           { dataSeries48.setName(x); barChart.getData().add(dataSeries48); }
            if (elementsCountryi == 49)           { dataSeries49.setName(x); barChart.getData().add(dataSeries49); }
            if (elementsCountryi == 50)           { dataSeries50.setName(x); barChart.getData().add(dataSeries50); }
//            if (elementsCountryi == 51)           { dataSeries51.setName(x); barChart.getData().add(dataSeries51); }
//            if (elementsCountryi == 52)           { dataSeries52.setName(x); barChart.getData().add(dataSeries52); }
//            if (elementsCountryi == 53)           { dataSeries53.setName(x); barChart.getData().add(dataSeries53); }
//            if (elementsCountryi == 54)           { dataSeries54.setName(x); barChart.getData().add(dataSeries54); }
//            if (elementsCountryi == 55)           { dataSeries55.setName(x); barChart.getData().add(dataSeries55); }
//            if (elementsCountryi == 56)           { dataSeries56.setName(x); barChart.getData().add(dataSeries56); }
//            if (elementsCountryi == 57)           { dataSeries57.setName(x); barChart.getData().add(dataSeries57); }
//            if (elementsCountryi == 58)           { dataSeries58.setName(x); barChart.getData().add(dataSeries58); }
//            if (elementsCountryi == 59)           { dataSeries59.setName(x); barChart.getData().add(dataSeries59); }
//            if (elementsCountryi == 60)           { dataSeries60.setName(x); barChart.getData().add(dataSeries60); }
//            if (elementsCountryi == 61)           { dataSeries61.setName(x); barChart.getData().add(dataSeries61); }
//            if (elementsCountryi == 62)           { dataSeries62.setName(x); barChart.getData().add(dataSeries62); }
//            if (elementsCountryi == 63)           { dataSeries63.setName(x); barChart.getData().add(dataSeries63); }
//            if (elementsCountryi == 64)           { dataSeries64.setName(x); barChart.getData().add(dataSeries64); }
//            if (elementsCountryi == 65)           { dataSeries65.setName(x); barChart.getData().add(dataSeries65); }
//            if (elementsCountryi == 66)           { dataSeries66.setName(x); barChart.getData().add(dataSeries66); }
//            if (elementsCountryi == 67)           { dataSeries67.setName(x); barChart.getData().add(dataSeries67); }
//            if (elementsCountryi == 68)           { dataSeries68.setName(x); barChart.getData().add(dataSeries68); }
//            if (elementsCountryi == 69)           { dataSeries69.setName(x); barChart.getData().add(dataSeries69); }
//            if (elementsCountryi == 70)           { dataSeries70.setName(x); barChart.getData().add(dataSeries70); }
//            if (elementsCountryi == 71)           { dataSeries71.setName(x); barChart.getData().add(dataSeries71); }
//            if (elementsCountryi == 72)           { dataSeries72.setName(x); barChart.getData().add(dataSeries72); }
//            if (elementsCountryi == 73)           { dataSeries73.setName(x); barChart.getData().add(dataSeries73); }
//            if (elementsCountryi == 74)           { dataSeries74.setName(x); barChart.getData().add(dataSeries74); }
//            if (elementsCountryi == 75)           { dataSeries75.setName(x); barChart.getData().add(dataSeries75); }
//            if (elementsCountryi == 76)           { dataSeries76.setName(x); barChart.getData().add(dataSeries76); }
//            if (elementsCountryi == 77)           { dataSeries77.setName(x); barChart.getData().add(dataSeries77); }
//            if (elementsCountryi == 78)           { dataSeries78.setName(x); barChart.getData().add(dataSeries78); }
//            if (elementsCountryi == 79)           { dataSeries79.setName(x); barChart.getData().add(dataSeries79); }
//            if (elementsCountryi == 80)           { dataSeries80.setName(x); barChart.getData().add(dataSeries80); }
//            if (elementsCountryi == 81)           { dataSeries81.setName(x); barChart.getData().add(dataSeries81); }
//            if (elementsCountryi == 82)           { dataSeries82.setName(x); barChart.getData().add(dataSeries82); }
//            if (elementsCountryi == 83)           { dataSeries83.setName(x); barChart.getData().add(dataSeries83); }
//            if (elementsCountryi == 84)           { dataSeries84.setName(x); barChart.getData().add(dataSeries84); }
//            if (elementsCountryi == 85)           { dataSeries85.setName(x); barChart.getData().add(dataSeries85); }
//            if (elementsCountryi == 86)           { dataSeries86.setName(x); barChart.getData().add(dataSeries86); }
//            if (elementsCountryi == 87)           { dataSeries87.setName(x); barChart.getData().add(dataSeries87); }
//            if (elementsCountryi == 88)           { dataSeries88.setName(x); barChart.getData().add(dataSeries88); }
//            if (elementsCountryi == 89)           { dataSeries89.setName(x); barChart.getData().add(dataSeries89); }
//            if (elementsCountryi == 90)           { dataSeries90.setName(x); barChart.getData().add(dataSeries90); }
//            if (elementsCountryi == 91)           { dataSeries91.setName(x); barChart.getData().add(dataSeries91); }
//            if (elementsCountryi == 92)           { dataSeries92.setName(x); barChart.getData().add(dataSeries92); }
//            if (elementsCountryi == 93)           { dataSeries93.setName(x); barChart.getData().add(dataSeries93); }
//            if (elementsCountryi == 94)           { dataSeries94.setName(x); barChart.getData().add(dataSeries94); }
//            if (elementsCountryi == 95)           { dataSeries95.setName(x); barChart.getData().add(dataSeries95); }
//            if (elementsCountryi == 96)           { dataSeries96.setName(x); barChart.getData().add(dataSeries96); }
//            if (elementsCountryi == 97)           { dataSeries97.setName(x); barChart.getData().add(dataSeries97); }
//            if (elementsCountryi == 98)           { dataSeries98.setName(x); barChart.getData().add(dataSeries98); }
//            if (elementsCountryi == 99)           { dataSeries99.setName(x); barChart.getData().add(dataSeries99); }
//            if (elementsCountryi == 100)           { dataSeries100.setName(x); barChart.getData().add(dataSeries100); }
//            if (elementsCountryi == 101)           { dataSeries101.setName(x); barChart.getData().add(dataSeries101); }
//            if (elementsCountryi == 102)           { dataSeries102.setName(x); barChart.getData().add(dataSeries102); }
//            if (elementsCountryi == 103)           { dataSeries103.setName(x); barChart.getData().add(dataSeries103); }
//            if (elementsCountryi == 104)           { dataSeries104.setName(x); barChart.getData().add(dataSeries104); }
//            if (elementsCountryi == 105)           { dataSeries105.setName(x); barChart.getData().add(dataSeries105); }
//            if (elementsCountryi == 106)           { dataSeries106.setName(x); barChart.getData().add(dataSeries106); }
//            if (elementsCountryi == 107)           { dataSeries107.setName(x); barChart.getData().add(dataSeries107); }
//            if (elementsCountryi == 108)           { dataSeries108.setName(x); barChart.getData().add(dataSeries108); }
//            if (elementsCountryi == 109)           { dataSeries109.setName(x); barChart.getData().add(dataSeries109); }
//            if (elementsCountryi == 110)           { dataSeries110.setName(x); barChart.getData().add(dataSeries110); }
//            if (elementsCountryi == 111)           { dataSeries111.setName(x); barChart.getData().add(dataSeries111); }
//            if (elementsCountryi == 112)           { dataSeries112.setName(x); barChart.getData().add(dataSeries112); }
//            if (elementsCountryi == 113)           { dataSeries113.setName(x); barChart.getData().add(dataSeries113); }
//            if (elementsCountryi == 114)           { dataSeries114.setName(x); barChart.getData().add(dataSeries114); }
//            if (elementsCountryi == 115)           { dataSeries115.setName(x); barChart.getData().add(dataSeries115); }
//            if (elementsCountryi == 116)           { dataSeries116.setName(x); barChart.getData().add(dataSeries116); }
//            if (elementsCountryi == 117)           { dataSeries117.setName(x); barChart.getData().add(dataSeries117); }
//            if (elementsCountryi == 118)           { dataSeries118.setName(x); barChart.getData().add(dataSeries118); }
//            if (elementsCountryi == 119)           { dataSeries119.setName(x); barChart.getData().add(dataSeries119); }
//            if (elementsCountryi == 120)           { dataSeries120.setName(x); barChart.getData().add(dataSeries120); }
//            if (elementsCountryi == 121)           { dataSeries121.setName(x); barChart.getData().add(dataSeries121); }
//            if (elementsCountryi == 122)           { dataSeries122.setName(x); barChart.getData().add(dataSeries122); }
//            if (elementsCountryi == 123)           { dataSeries123.setName(x); barChart.getData().add(dataSeries123); }
//            if (elementsCountryi == 124)           { dataSeries124.setName(x); barChart.getData().add(dataSeries124); }
//            if (elementsCountryi == 125)           { dataSeries125.setName(x); barChart.getData().add(dataSeries125); }
//            if (elementsCountryi == 126)           { dataSeries126.setName(x); barChart.getData().add(dataSeries126); }
//            if (elementsCountryi == 127)           { dataSeries127.setName(x); barChart.getData().add(dataSeries127); }
//            if (elementsCountryi == 128)           { dataSeries128.setName(x); barChart.getData().add(dataSeries128); }
//            if (elementsCountryi == 129)           { dataSeries129.setName(x); barChart.getData().add(dataSeries129); }
//            if (elementsCountryi == 130)           { dataSeries130.setName(x); barChart.getData().add(dataSeries130); }
//            if (elementsCountryi == 131)           { dataSeries131.setName(x); barChart.getData().add(dataSeries131); }
//            if (elementsCountryi == 132)           { dataSeries132.setName(x); barChart.getData().add(dataSeries132); }
//            if (elementsCountryi == 133)           { dataSeries133.setName(x); barChart.getData().add(dataSeries133); }
//            if (elementsCountryi == 134)           { dataSeries134.setName(x); barChart.getData().add(dataSeries134); }
//            if (elementsCountryi == 135)           { dataSeries135.setName(x); barChart.getData().add(dataSeries135); }
//            if (elementsCountryi == 136)           { dataSeries136.setName(x); barChart.getData().add(dataSeries136); }
//            if (elementsCountryi == 137)           { dataSeries137.setName(x); barChart.getData().add(dataSeries137); }
//            if (elementsCountryi == 138)           { dataSeries138.setName(x); barChart.getData().add(dataSeries138); }
//            if (elementsCountryi == 139)           { dataSeries139.setName(x); barChart.getData().add(dataSeries139); }
//            if (elementsCountryi == 140)           { dataSeries140.setName(x); barChart.getData().add(dataSeries140); }
//            if (elementsCountryi == 141)           { dataSeries141.setName(x); barChart.getData().add(dataSeries141); }
//            if (elementsCountryi == 142)           { dataSeries142.setName(x); barChart.getData().add(dataSeries142); }
//            if (elementsCountryi == 143)           { dataSeries143.setName(x); barChart.getData().add(dataSeries143); }
//            if (elementsCountryi == 144)           { dataSeries144.setName(x); barChart.getData().add(dataSeries144); }
//            if (elementsCountryi == 145)           { dataSeries145.setName(x); barChart.getData().add(dataSeries145); }
//            if (elementsCountryi == 146)           { dataSeries146.setName(x); barChart.getData().add(dataSeries146); }
//            if (elementsCountryi == 147)           { dataSeries147.setName(x); barChart.getData().add(dataSeries147); }
//            if (elementsCountryi == 148)           { dataSeries148.setName(x); barChart.getData().add(dataSeries148); }
//            if (elementsCountryi == 149)           { dataSeries149.setName(x); barChart.getData().add(dataSeries149); }
//            if (elementsCountryi == 150)           { dataSeries150.setName(x); barChart.getData().add(dataSeries150); }
//            if (elementsCountryi == 151)           { dataSeries151.setName(x); barChart.getData().add(dataSeries151); }
//            if (elementsCountryi == 152)           { dataSeries152.setName(x); barChart.getData().add(dataSeries152); }
//            if (elementsCountryi == 153)           { dataSeries153.setName(x); barChart.getData().add(dataSeries153); }
//            if (elementsCountryi == 154)           { dataSeries154.setName(x); barChart.getData().add(dataSeries154); }
//            if (elementsCountryi == 155)           { dataSeries155.setName(x); barChart.getData().add(dataSeries155); }
//            if (elementsCountryi == 156)           { dataSeries156.setName(x); barChart.getData().add(dataSeries156); }
//            if (elementsCountryi == 157)           { dataSeries157.setName(x); barChart.getData().add(dataSeries157); }
//            if (elementsCountryi == 158)           { dataSeries158.setName(x); barChart.getData().add(dataSeries158); }
//            if (elementsCountryi == 159)           { dataSeries159.setName(x); barChart.getData().add(dataSeries159); }
//            if (elementsCountryi == 160)           { dataSeries160.setName(x); barChart.getData().add(dataSeries160); }
//            if (elementsCountryi == 161)           { dataSeries161.setName(x); barChart.getData().add(dataSeries161); }
//            if (elementsCountryi == 162)           { dataSeries162.setName(x); barChart.getData().add(dataSeries162); }
//            if (elementsCountryi == 163)           { dataSeries163.setName(x); barChart.getData().add(dataSeries163); }
//            if (elementsCountryi == 164)           { dataSeries164.setName(x); barChart.getData().add(dataSeries164); }
//            if (elementsCountryi == 165)           { dataSeries165.setName(x); barChart.getData().add(dataSeries165); }
//            if (elementsCountryi == 166)           { dataSeries166.setName(x); barChart.getData().add(dataSeries166); }
//            if (elementsCountryi == 167)           { dataSeries167.setName(x); barChart.getData().add(dataSeries167); }
//            if (elementsCountryi == 168)           { dataSeries168.setName(x); barChart.getData().add(dataSeries168); }
//            if (elementsCountryi == 169)           { dataSeries169.setName(x); barChart.getData().add(dataSeries169); }
//            if (elementsCountryi == 170)           { dataSeries170.setName(x); barChart.getData().add(dataSeries170); }
//            if (elementsCountryi == 171)           { dataSeries171.setName(x); barChart.getData().add(dataSeries171); }
//            if (elementsCountryi == 172)           { dataSeries172.setName(x); barChart.getData().add(dataSeries172); }
//            if (elementsCountryi == 173)           { dataSeries173.setName(x); barChart.getData().add(dataSeries173); }
//            if (elementsCountryi == 174)           { dataSeries174.setName(x); barChart.getData().add(dataSeries174); }
//            if (elementsCountryi == 175)           { dataSeries175.setName(x); barChart.getData().add(dataSeries175); }
//            if (elementsCountryi == 176)           { dataSeries176.setName(x); barChart.getData().add(dataSeries176); }
//            if (elementsCountryi == 177)           { dataSeries177.setName(x); barChart.getData().add(dataSeries177); }
//            if (elementsCountryi == 178)           { dataSeries178.setName(x); barChart.getData().add(dataSeries178); }
//            if (elementsCountryi == 179)           { dataSeries179.setName(x); barChart.getData().add(dataSeries179); }
//            if (elementsCountryi == 180)           { dataSeries180.setName(x); barChart.getData().add(dataSeries180); }
//            if (elementsCountryi == 181)           { dataSeries181.setName(x); barChart.getData().add(dataSeries181); }
//            if (elementsCountryi == 182)           { dataSeries182.setName(x); barChart.getData().add(dataSeries182); }
//            if (elementsCountryi == 183)           { dataSeries183.setName(x); barChart.getData().add(dataSeries183); }

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
        min++; //TODO:Burada min++ yapmasak??


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().stream().sorted();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {//milisaniyeye ayar lazım gibi
            @Override
            public void handle(ActionEvent actionEvent) {
                yearLabel.setText(Integer.toString(min)); //TODO ??
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
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(8))) dataSeries9.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(9))) dataSeries10.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(10))) dataSeries11.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(11))) dataSeries12.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(12))) dataSeries13.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(13))) dataSeries14.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(14))) dataSeries15.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(15))) dataSeries16.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(16))) dataSeries17.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(17))) dataSeries18.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(18))) dataSeries19.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(19))) dataSeries20.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(20))) dataSeries21.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(21))) dataSeries22.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(22))) dataSeries23.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(23))) dataSeries24.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(24))) dataSeries25.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(25))) dataSeries26.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(26))) dataSeries27.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(27))) dataSeries28.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(28))) dataSeries29.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(29))) dataSeries30.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(30))) dataSeries31.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(31))) dataSeries32.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(32))) dataSeries33.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(33))) dataSeries34.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(34))) dataSeries35.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(35))) dataSeries36.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(36))) dataSeries37.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(37))) dataSeries38.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(38))) dataSeries39.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(39))) dataSeries40.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(40))) dataSeries41.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(41))) dataSeries42.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(42))) dataSeries43.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(43))) dataSeries44.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(44))) dataSeries45.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(45))) dataSeries46.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(46))) dataSeries47.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(47))) dataSeries48.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(48))) dataSeries49.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(49))) dataSeries50.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(50))) dataSeries51.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(51))) dataSeries52.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(52))) dataSeries53.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(53))) dataSeries54.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(54))) dataSeries55.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(55))) dataSeries56.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(56))) dataSeries57.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(57))) dataSeries58.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(58))) dataSeries59.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(59))) dataSeries60.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(60))) dataSeries61.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(61))) dataSeries62.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(62))) dataSeries63.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(63))) dataSeries64.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(64))) dataSeries65.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(65))) dataSeries66.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(66))) dataSeries67.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(67))) dataSeries68.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(68))) dataSeries69.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(69))) dataSeries70.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(70))) dataSeries71.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(71))) dataSeries72.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(72))) dataSeries73.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(73))) dataSeries74.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(74))) dataSeries75.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(75))) dataSeries76.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(76))) dataSeries77.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(77))) dataSeries78.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(78))) dataSeries79.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(79))) dataSeries80.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(80))) dataSeries81.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(81))) dataSeries82.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(82))) dataSeries83.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(83))) dataSeries84.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(84))) dataSeries85.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(85))) dataSeries86.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(86))) dataSeries87.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(87))) dataSeries88.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(88))) dataSeries89.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(89))) dataSeries90.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(90))) dataSeries91.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(91))) dataSeries92.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(92))) dataSeries93.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(93))) dataSeries94.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(94))) dataSeries95.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(95))) dataSeries96.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(96))) dataSeries97.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(97))) dataSeries98.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(98))) dataSeries99.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(99))) dataSeries100.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(100))) dataSeries101.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(101))) dataSeries102.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(102))) dataSeries103.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(103))) dataSeries104.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(104))) dataSeries105.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(105))) dataSeries106.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(106))) dataSeries107.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(107))) dataSeries108.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(108))) dataSeries109.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(109))) dataSeries110.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(110))) dataSeries111.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(111))) dataSeries112.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(112))) dataSeries113.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(113))) dataSeries114.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(114))) dataSeries115.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(115))) dataSeries116.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(116))) dataSeries117.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(117))) dataSeries118.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(118))) dataSeries119.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(119))) dataSeries120.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(120))) dataSeries121.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(121))) dataSeries122.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(122))) dataSeries123.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(123))) dataSeries124.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(124))) dataSeries125.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(125))) dataSeries126.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(126))) dataSeries127.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(127))) dataSeries128.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(128))) dataSeries129.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(129))) dataSeries130.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(130))) dataSeries131.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(131))) dataSeries132.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(132))) dataSeries133.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(133))) dataSeries134.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(134))) dataSeries135.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(135))) dataSeries136.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(136))) dataSeries137.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(137))) dataSeries138.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(138))) dataSeries139.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(139))) dataSeries140.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(140))) dataSeries141.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(141))) dataSeries142.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(142))) dataSeries143.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(143))) dataSeries144.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(144))) dataSeries145.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(145))) dataSeries146.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(146))) dataSeries147.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(147))) dataSeries148.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(148))) dataSeries149.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(149))) dataSeries150.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(150))) dataSeries151.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(151))) dataSeries152.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(152))) dataSeries153.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(153))) dataSeries154.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(154))) dataSeries155.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(155))) dataSeries156.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(156))) dataSeries157.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(157))) dataSeries158.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(158))) dataSeries159.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(159))) dataSeries160.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(160))) dataSeries161.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(161))) dataSeries162.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(162))) dataSeries163.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(163))) dataSeries164.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(164))) dataSeries165.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(165))) dataSeries166.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(166))) dataSeries167.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(167))) dataSeries168.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(168))) dataSeries169.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(169))) dataSeries170.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(170))) dataSeries171.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(171))) dataSeries172.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(172))) dataSeries173.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(173))) dataSeries174.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(174))) dataSeries175.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(175))) dataSeries176.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(176))) dataSeries177.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(177))) dataSeries178.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(178))) dataSeries179.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(179))) dataSeries180.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(180))) dataSeries181.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(181))) dataSeries182.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(182))) dataSeries183.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}


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
//                timeline.playFromStart();
//                //TODO:Restart actionu tanımlanacak
                YedidenBasla123();
            }
        };
        butttonRestart.setOnAction(eventRestart);

        AnchorPane anchorPane = new AnchorPane(barChart,buttonPause,buttonPlay,butttonRestart,yearLabel);
        Scene scene = new Scene(anchorPane, 1000, 900);



        secondStage.setScene(scene);

        secondStage.setResizable(false);
        secondStage.setWidth(1000);
        secondStage.setHeight(900);

        secondStage.show();
        secondStage.setOnCloseRequest(e -> Platform.exit());

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
    Stage secondStage = new Stage();
    @FXML
    private void BCline (ActionEvent event) {
        System.out.println("line tetik");
        LorB ="L";

        //SET lineChart PROPERTY
        xAxis.setLabel(ustBilgi1);
        yAxis.setLabel("");
        lineChart.setTitle(ustBilgi2);
        lineChart.setPrefSize(900,800);
        //lineChart.autosize();
        lineChart.setHorizontalGridLinesVisible(true);
        lineChart.setLegendSide(Side.TOP);

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
        XYChart.Series<Number, String> dataSeries9 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries10 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries11 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries12 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries13 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries14 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries15 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries16 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries17 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries18 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries19 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries20 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries21 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries22 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries23 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries24 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries25 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries26 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries27 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries28 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries29 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries30 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries31 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries32 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries33 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries34 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries35 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries36 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries37 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries38 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries39 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries40 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries41 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries42 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries43 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries44 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries45 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries46 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries47 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries48 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries49 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries50 = new XYChart.Series<Number, String>();



//        elementsCountry =  new ArrayList<>(linkedHashSet);
//        ArrayList<String> elementsName = new ArrayList<>(linkedHashSetUlke);
//        int elementsCountryi = 0;
//        for (String x : elementsCountry){
        elementsCountry = new ArrayList<>(linkedHashSetUlke);
        ArrayList<String> elementsName = new ArrayList<>(linkedHashSet);
        int elementsCountryi = 0;
        for (String x : elementsCountry){
            System.out.println(x);

            elementsCountryi++;
            if (elementsCountryi == 1)           { dataSeries1.setName(x); lineChart.getData().add(dataSeries1); }
            if (elementsCountryi == 2)           { dataSeries2.setName(x); lineChart.getData().add(dataSeries2); }
            if (elementsCountryi == 3)           { dataSeries3.setName(x); lineChart.getData().add(dataSeries3); }
            if (elementsCountryi == 4)           { dataSeries4.setName(x); lineChart.getData().add(dataSeries4); }
            if (elementsCountryi == 5)           { dataSeries5.setName(x); lineChart.getData().add(dataSeries5); }
            if (elementsCountryi == 6)           { dataSeries6.setName(x); lineChart.getData().add(dataSeries6); }
            if (elementsCountryi == 7)           { dataSeries7.setName(x); lineChart.getData().add(dataSeries7); }
            if (elementsCountryi == 8)           { dataSeries8.setName(x); lineChart.getData().add(dataSeries8); }
            if (elementsCountryi == 9)           { dataSeries9.setName(x); lineChart.getData().add(dataSeries9); }
            if (elementsCountryi == 10)           { dataSeries10.setName(x); lineChart.getData().add(dataSeries10); }
            if (elementsCountryi == 11)           { dataSeries11.setName(x); lineChart.getData().add(dataSeries11); }
            if (elementsCountryi == 12)           { dataSeries12.setName(x); lineChart.getData().add(dataSeries12); }
            if (elementsCountryi == 13)           { dataSeries13.setName(x); lineChart.getData().add(dataSeries13); }
            if (elementsCountryi == 14)           { dataSeries14.setName(x); lineChart.getData().add(dataSeries14); }
            if (elementsCountryi == 15)           { dataSeries15.setName(x); lineChart.getData().add(dataSeries15); }
            if (elementsCountryi == 16)           { dataSeries16.setName(x); lineChart.getData().add(dataSeries16); }
            if (elementsCountryi == 17)           { dataSeries17.setName(x); lineChart.getData().add(dataSeries17); }
            if (elementsCountryi == 18)           { dataSeries18.setName(x); lineChart.getData().add(dataSeries18); }
            if (elementsCountryi == 19)           { dataSeries19.setName(x); lineChart.getData().add(dataSeries19); }
            if (elementsCountryi == 20)           { dataSeries20.setName(x); lineChart.getData().add(dataSeries20); }
            if (elementsCountryi == 21)           { dataSeries21.setName(x); lineChart.getData().add(dataSeries21); }
            if (elementsCountryi == 22)           { dataSeries22.setName(x); lineChart.getData().add(dataSeries22); }
            if (elementsCountryi == 23)           { dataSeries23.setName(x); lineChart.getData().add(dataSeries23); }
            if (elementsCountryi == 24)           { dataSeries24.setName(x); lineChart.getData().add(dataSeries24); }
            if (elementsCountryi == 25)           { dataSeries25.setName(x); lineChart.getData().add(dataSeries25); }
            if (elementsCountryi == 26)           { dataSeries26.setName(x); lineChart.getData().add(dataSeries26); }
            if (elementsCountryi == 27)           { dataSeries27.setName(x); lineChart.getData().add(dataSeries27); }
            if (elementsCountryi == 28)           { dataSeries28.setName(x); lineChart.getData().add(dataSeries28); }
            if (elementsCountryi == 29)           { dataSeries29.setName(x); lineChart.getData().add(dataSeries29); }
            if (elementsCountryi == 30)           { dataSeries30.setName(x); lineChart.getData().add(dataSeries30); }
            if (elementsCountryi == 31)           { dataSeries31.setName(x); lineChart.getData().add(dataSeries31); }
            if (elementsCountryi == 32)           { dataSeries32.setName(x); lineChart.getData().add(dataSeries32); }
            if (elementsCountryi == 33)           { dataSeries33.setName(x); lineChart.getData().add(dataSeries33); }
            if (elementsCountryi == 34)           { dataSeries34.setName(x); lineChart.getData().add(dataSeries34); }
            if (elementsCountryi == 35)           { dataSeries35.setName(x); lineChart.getData().add(dataSeries35); }
            if (elementsCountryi == 36)           { dataSeries36.setName(x); lineChart.getData().add(dataSeries36); }
            if (elementsCountryi == 37)           { dataSeries37.setName(x); lineChart.getData().add(dataSeries37); }
            if (elementsCountryi == 38)           { dataSeries38.setName(x); lineChart.getData().add(dataSeries38); }
            if (elementsCountryi == 39)           { dataSeries39.setName(x); lineChart.getData().add(dataSeries39); }
            if (elementsCountryi == 40)           { dataSeries40.setName(x); lineChart.getData().add(dataSeries40); }
            if (elementsCountryi == 41)           { dataSeries41.setName(x); lineChart.getData().add(dataSeries41); }
            if (elementsCountryi == 42)           { dataSeries42.setName(x); lineChart.getData().add(dataSeries42); }
            if (elementsCountryi == 43)           { dataSeries43.setName(x); lineChart.getData().add(dataSeries43); }
            if (elementsCountryi == 44)           { dataSeries44.setName(x); lineChart.getData().add(dataSeries44); }
            if (elementsCountryi == 45)           { dataSeries45.setName(x); lineChart.getData().add(dataSeries45); }
            if (elementsCountryi == 46)           { dataSeries46.setName(x); lineChart.getData().add(dataSeries46); }
            if (elementsCountryi == 47)           { dataSeries47.setName(x); lineChart.getData().add(dataSeries47); }
            if (elementsCountryi == 48)           { dataSeries48.setName(x); lineChart.getData().add(dataSeries48); }
            if (elementsCountryi == 49)           { dataSeries49.setName(x); lineChart.getData().add(dataSeries49); }
            if (elementsCountryi == 50)           { dataSeries50.setName(x); lineChart.getData().add(dataSeries50); }

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
        min++; //TODO:Burada min++ yapmasak??


        Timeline timeline = new Timeline();
        //timeline.getKeyFrames().stream().sorted();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {//milisaniyeye ayar lazım gibi
            @Override
            public void handle(ActionEvent actionEvent) {
                yearLabel.setText(Integer.toString(min)); //TODO ??
                //min = Integer.parseInt(ListxYear.get(0)); // TODO:Buna da ihtiyaç yok??

                for(int i = 0; i<kaculkevar;i++){

                    int abc = ListindexofYear.get(i);

                    try { if (ListxName.get(abc).equals(elementsCountry.get(0))) dataSeries1.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(1))) dataSeries2.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(2))) dataSeries3.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(3))) dataSeries4.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(4))) dataSeries5.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(5))) dataSeries6.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(6))) dataSeries7.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(7))) dataSeries8.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(8))) dataSeries9.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(9))) dataSeries10.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(10))) dataSeries11.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(11))) dataSeries12.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(12))) dataSeries13.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(13))) dataSeries14.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(14))) dataSeries15.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(15))) dataSeries16.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(16))) dataSeries17.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(17))) dataSeries18.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(18))) dataSeries19.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(19))) dataSeries20.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(20))) dataSeries21.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(21))) dataSeries22.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(22))) dataSeries23.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(23))) dataSeries24.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(24))) dataSeries25.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(25))) dataSeries26.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(26))) dataSeries27.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(27))) dataSeries28.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(28))) dataSeries29.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(29))) dataSeries30.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(30))) dataSeries31.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(31))) dataSeries32.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(32))) dataSeries33.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(33))) dataSeries34.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(34))) dataSeries35.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(35))) dataSeries36.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(36))) dataSeries37.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(37))) dataSeries38.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(38))) dataSeries39.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(39))) dataSeries40.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(40))) dataSeries41.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(41))) dataSeries42.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(42))) dataSeries43.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(43))) dataSeries44.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(44))) dataSeries45.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(45))) dataSeries46.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(46))) dataSeries47.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(47))) dataSeries48.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(48))) dataSeries49.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxName.get(abc).equals(elementsCountry.get(49))) dataSeries50.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}



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
        timeline.setAutoReverse(true);
        timeline.play();
        lineChart.setAnimated(true);

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
//                timeline.playFromStart();
//                //TODO:Restart actionu tanımlanacak
                YedidenBasla123();
            }
        };
        butttonRestart.setOnAction(eventRestart);

        AnchorPane anchorPane = new AnchorPane(lineChart,buttonPause,buttonPlay,butttonRestart,yearLabel);
        Scene scene = new Scene(anchorPane, 1000, 900);



        secondStage.setScene(scene);

        secondStage.setResizable(false);
        secondStage.setWidth(1000);
        secondStage.setHeight(900);

        secondStage.show();
        secondStage.setOnCloseRequest(e -> Platform.exit());


        //JOptionPane.showMessageDialog(null, "Hocam diğer taraf mükkemmel çalışsıon diye burayı yapmadık", "InfoBox: " + "malesef", JOptionPane.INFORMATION_MESSAGE);


    }
    private void DosyaOku(String gelen){
        try {
            File myObj = new File(gelen);
            gelenSabit = gelen;
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
    private void YedidenBasla123(){
        secondStage.close();

         linkedHashSet = new LinkedHashSet<>();
         linkedHashSetUlke = new LinkedHashSet<>();
        yAxis = new NumberAxis();
        xAxis = new CategoryAxis();
        barChart = new BarChart<Number, String>(yAxis, xAxis);
         lineChart = new LineChart<Number, String>(yAxis, xAxis);

         elementsCountry =  new ArrayList<String>();
         ListxName =new ArrayList<String>();
         ListxCountry =new ArrayList<String>();
         ListxYearORJ =new ArrayList<String>();
         ListxYear =new ArrayList<String>();
         ListxValue =new ArrayList<String>();
         ListxCategory =new ArrayList<String>();
         ListindexofYear =  new ArrayList();

        if (XorT.equals( "T")) F_txt(Dosyayolu);
        if (XorT.equals("X")) F_xml(Dosyayolu);
        if(LorB.equals("L") ) Line.fire();
        if(LorB.equals("B") ) Bar.fire();
        //(new String("</data>").equals(MasterData)))
    }
    private void YedidenBasla123_eski() {
        secondStage.close();


////////////

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(gelenSabit);
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

        //////////////////


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
        XYChart.Series<Number, String> dataSeries9 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries10 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries11 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries12 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries13 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries14 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries15 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries16 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries17 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries18 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries19 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries20 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries21 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries22 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries23 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries24 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries25 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries26 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries27 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries28 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries29 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries30 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries31 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries32 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries33 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries34 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries35 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries36 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries37 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries38 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries39 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries40 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries41 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries42 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries43 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries44 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries45 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries46 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries47 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries48 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries49 = new XYChart.Series<Number, String>();
        XYChart.Series<Number, String> dataSeries50 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries51 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries52 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries53 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries54 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries55 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries56 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries57 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries58 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries59 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries60 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries61 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries62 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries63 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries64 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries65 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries66 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries67 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries68 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries69 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries70 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries71 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries72 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries73 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries74 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries75 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries76 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries77 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries78 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries79 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries80 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries81 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries82 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries83 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries84 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries85 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries86 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries87 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries88 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries89 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries90 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries91 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries92 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries93 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries94 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries95 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries96 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries97 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries98 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries99 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries100 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries101 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries102 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries103 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries104 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries105 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries106 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries107 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries108 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries109 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries110 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries111 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries112 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries113 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries114 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries115 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries116 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries117 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries118 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries119 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries120 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries121 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries122 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries123 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries124 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries125 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries126 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries127 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries128 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries129 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries130 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries131 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries132 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries133 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries134 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries135 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries136 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries137 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries138 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries139 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries140 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries141 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries142 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries143 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries144 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries145 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries146 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries147 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries148 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries149 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries150 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries151 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries152 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries153 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries154 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries155 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries156 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries157 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries158 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries159 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries160 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries161 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries162 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries163 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries164 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries165 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries166 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries167 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries168 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries169 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries170 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries171 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries172 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries173 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries174 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries175 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries176 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries177 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries178 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries179 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries180 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries181 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries182 = new XYChart.Series<Number, String>();
//        XYChart.Series<Number, String> dataSeries183 = new XYChart.Series<Number, String>();


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
            if (elementsCountryi == 9)           { dataSeries9.setName(x); barChart.getData().add(dataSeries9); }
            if (elementsCountryi == 10)           { dataSeries10.setName(x); barChart.getData().add(dataSeries10); }
            if (elementsCountryi == 11)           { dataSeries11.setName(x); barChart.getData().add(dataSeries11); }
            if (elementsCountryi == 12)           { dataSeries12.setName(x); barChart.getData().add(dataSeries12); }
            if (elementsCountryi == 13)           { dataSeries13.setName(x); barChart.getData().add(dataSeries13); }
            if (elementsCountryi == 14)           { dataSeries14.setName(x); barChart.getData().add(dataSeries14); }
            if (elementsCountryi == 15)           { dataSeries15.setName(x); barChart.getData().add(dataSeries15); }
            if (elementsCountryi == 16)           { dataSeries16.setName(x); barChart.getData().add(dataSeries16); }
            if (elementsCountryi == 17)           { dataSeries17.setName(x); barChart.getData().add(dataSeries17); }
            if (elementsCountryi == 18)           { dataSeries18.setName(x); barChart.getData().add(dataSeries18); }
            if (elementsCountryi == 19)           { dataSeries19.setName(x); barChart.getData().add(dataSeries19); }
            if (elementsCountryi == 20)           { dataSeries20.setName(x); barChart.getData().add(dataSeries20); }
            if (elementsCountryi == 21)           { dataSeries21.setName(x); barChart.getData().add(dataSeries21); }
            if (elementsCountryi == 22)           { dataSeries22.setName(x); barChart.getData().add(dataSeries22); }
            if (elementsCountryi == 23)           { dataSeries23.setName(x); barChart.getData().add(dataSeries23); }
            if (elementsCountryi == 24)           { dataSeries24.setName(x); barChart.getData().add(dataSeries24); }
            if (elementsCountryi == 25)           { dataSeries25.setName(x); barChart.getData().add(dataSeries25); }
            if (elementsCountryi == 26)           { dataSeries26.setName(x); barChart.getData().add(dataSeries26); }
            if (elementsCountryi == 27)           { dataSeries27.setName(x); barChart.getData().add(dataSeries27); }
            if (elementsCountryi == 28)           { dataSeries28.setName(x); barChart.getData().add(dataSeries28); }
            if (elementsCountryi == 29)           { dataSeries29.setName(x); barChart.getData().add(dataSeries29); }
            if (elementsCountryi == 30)           { dataSeries30.setName(x); barChart.getData().add(dataSeries30); }
            if (elementsCountryi == 31)           { dataSeries31.setName(x); barChart.getData().add(dataSeries31); }
            if (elementsCountryi == 32)           { dataSeries32.setName(x); barChart.getData().add(dataSeries32); }
            if (elementsCountryi == 33)           { dataSeries33.setName(x); barChart.getData().add(dataSeries33); }
            if (elementsCountryi == 34)           { dataSeries34.setName(x); barChart.getData().add(dataSeries34); }
            if (elementsCountryi == 35)           { dataSeries35.setName(x); barChart.getData().add(dataSeries35); }
            if (elementsCountryi == 36)           { dataSeries36.setName(x); barChart.getData().add(dataSeries36); }
            if (elementsCountryi == 37)           { dataSeries37.setName(x); barChart.getData().add(dataSeries37); }
            if (elementsCountryi == 38)           { dataSeries38.setName(x); barChart.getData().add(dataSeries38); }
            if (elementsCountryi == 39)           { dataSeries39.setName(x); barChart.getData().add(dataSeries39); }
            if (elementsCountryi == 40)           { dataSeries40.setName(x); barChart.getData().add(dataSeries40); }
            if (elementsCountryi == 41)           { dataSeries41.setName(x); barChart.getData().add(dataSeries41); }
            if (elementsCountryi == 42)           { dataSeries42.setName(x); barChart.getData().add(dataSeries42); }
            if (elementsCountryi == 43)           { dataSeries43.setName(x); barChart.getData().add(dataSeries43); }
            if (elementsCountryi == 44)           { dataSeries44.setName(x); barChart.getData().add(dataSeries44); }
            if (elementsCountryi == 45)           { dataSeries45.setName(x); barChart.getData().add(dataSeries45); }
            if (elementsCountryi == 46)           { dataSeries46.setName(x); barChart.getData().add(dataSeries46); }
            if (elementsCountryi == 47)           { dataSeries47.setName(x); barChart.getData().add(dataSeries47); }
            if (elementsCountryi == 48)           { dataSeries48.setName(x); barChart.getData().add(dataSeries48); }
            if (elementsCountryi == 49)           { dataSeries49.setName(x); barChart.getData().add(dataSeries49); }
            if (elementsCountryi == 50)           { dataSeries50.setName(x); barChart.getData().add(dataSeries50); }
//            if (elementsCountryi == 51)           { dataSeries51.setName(x); barChart.getData().add(dataSeries51); }
//            if (elementsCountryi == 52)           { dataSeries52.setName(x); barChart.getData().add(dataSeries52); }
//            if (elementsCountryi == 53)           { dataSeries53.setName(x); barChart.getData().add(dataSeries53); }
//            if (elementsCountryi == 54)           { dataSeries54.setName(x); barChart.getData().add(dataSeries54); }
//            if (elementsCountryi == 55)           { dataSeries55.setName(x); barChart.getData().add(dataSeries55); }
//            if (elementsCountryi == 56)           { dataSeries56.setName(x); barChart.getData().add(dataSeries56); }
//            if (elementsCountryi == 57)           { dataSeries57.setName(x); barChart.getData().add(dataSeries57); }
//            if (elementsCountryi == 58)           { dataSeries58.setName(x); barChart.getData().add(dataSeries58); }
//            if (elementsCountryi == 59)           { dataSeries59.setName(x); barChart.getData().add(dataSeries59); }
//            if (elementsCountryi == 60)           { dataSeries60.setName(x); barChart.getData().add(dataSeries60); }
//            if (elementsCountryi == 61)           { dataSeries61.setName(x); barChart.getData().add(dataSeries61); }
//            if (elementsCountryi == 62)           { dataSeries62.setName(x); barChart.getData().add(dataSeries62); }
//            if (elementsCountryi == 63)           { dataSeries63.setName(x); barChart.getData().add(dataSeries63); }
//            if (elementsCountryi == 64)           { dataSeries64.setName(x); barChart.getData().add(dataSeries64); }
//            if (elementsCountryi == 65)           { dataSeries65.setName(x); barChart.getData().add(dataSeries65); }
//            if (elementsCountryi == 66)           { dataSeries66.setName(x); barChart.getData().add(dataSeries66); }
//            if (elementsCountryi == 67)           { dataSeries67.setName(x); barChart.getData().add(dataSeries67); }
//            if (elementsCountryi == 68)           { dataSeries68.setName(x); barChart.getData().add(dataSeries68); }
//            if (elementsCountryi == 69)           { dataSeries69.setName(x); barChart.getData().add(dataSeries69); }
//            if (elementsCountryi == 70)           { dataSeries70.setName(x); barChart.getData().add(dataSeries70); }
//            if (elementsCountryi == 71)           { dataSeries71.setName(x); barChart.getData().add(dataSeries71); }
//            if (elementsCountryi == 72)           { dataSeries72.setName(x); barChart.getData().add(dataSeries72); }
//            if (elementsCountryi == 73)           { dataSeries73.setName(x); barChart.getData().add(dataSeries73); }
//            if (elementsCountryi == 74)           { dataSeries74.setName(x); barChart.getData().add(dataSeries74); }
//            if (elementsCountryi == 75)           { dataSeries75.setName(x); barChart.getData().add(dataSeries75); }
//            if (elementsCountryi == 76)           { dataSeries76.setName(x); barChart.getData().add(dataSeries76); }
//            if (elementsCountryi == 77)           { dataSeries77.setName(x); barChart.getData().add(dataSeries77); }
//            if (elementsCountryi == 78)           { dataSeries78.setName(x); barChart.getData().add(dataSeries78); }
//            if (elementsCountryi == 79)           { dataSeries79.setName(x); barChart.getData().add(dataSeries79); }
//            if (elementsCountryi == 80)           { dataSeries80.setName(x); barChart.getData().add(dataSeries80); }
//            if (elementsCountryi == 81)           { dataSeries81.setName(x); barChart.getData().add(dataSeries81); }
//            if (elementsCountryi == 82)           { dataSeries82.setName(x); barChart.getData().add(dataSeries82); }
//            if (elementsCountryi == 83)           { dataSeries83.setName(x); barChart.getData().add(dataSeries83); }
//            if (elementsCountryi == 84)           { dataSeries84.setName(x); barChart.getData().add(dataSeries84); }
//            if (elementsCountryi == 85)           { dataSeries85.setName(x); barChart.getData().add(dataSeries85); }
//            if (elementsCountryi == 86)           { dataSeries86.setName(x); barChart.getData().add(dataSeries86); }
//            if (elementsCountryi == 87)           { dataSeries87.setName(x); barChart.getData().add(dataSeries87); }
//            if (elementsCountryi == 88)           { dataSeries88.setName(x); barChart.getData().add(dataSeries88); }
//            if (elementsCountryi == 89)           { dataSeries89.setName(x); barChart.getData().add(dataSeries89); }
//            if (elementsCountryi == 90)           { dataSeries90.setName(x); barChart.getData().add(dataSeries90); }
//            if (elementsCountryi == 91)           { dataSeries91.setName(x); barChart.getData().add(dataSeries91); }
//            if (elementsCountryi == 92)           { dataSeries92.setName(x); barChart.getData().add(dataSeries92); }
//            if (elementsCountryi == 93)           { dataSeries93.setName(x); barChart.getData().add(dataSeries93); }
//            if (elementsCountryi == 94)           { dataSeries94.setName(x); barChart.getData().add(dataSeries94); }
//            if (elementsCountryi == 95)           { dataSeries95.setName(x); barChart.getData().add(dataSeries95); }
//            if (elementsCountryi == 96)           { dataSeries96.setName(x); barChart.getData().add(dataSeries96); }
//            if (elementsCountryi == 97)           { dataSeries97.setName(x); barChart.getData().add(dataSeries97); }
//            if (elementsCountryi == 98)           { dataSeries98.setName(x); barChart.getData().add(dataSeries98); }
//            if (elementsCountryi == 99)           { dataSeries99.setName(x); barChart.getData().add(dataSeries99); }
//            if (elementsCountryi == 100)           { dataSeries100.setName(x); barChart.getData().add(dataSeries100); }
//            if (elementsCountryi == 101)           { dataSeries101.setName(x); barChart.getData().add(dataSeries101); }
//            if (elementsCountryi == 102)           { dataSeries102.setName(x); barChart.getData().add(dataSeries102); }
//            if (elementsCountryi == 103)           { dataSeries103.setName(x); barChart.getData().add(dataSeries103); }
//            if (elementsCountryi == 104)           { dataSeries104.setName(x); barChart.getData().add(dataSeries104); }
//            if (elementsCountryi == 105)           { dataSeries105.setName(x); barChart.getData().add(dataSeries105); }
//            if (elementsCountryi == 106)           { dataSeries106.setName(x); barChart.getData().add(dataSeries106); }
//            if (elementsCountryi == 107)           { dataSeries107.setName(x); barChart.getData().add(dataSeries107); }
//            if (elementsCountryi == 108)           { dataSeries108.setName(x); barChart.getData().add(dataSeries108); }
//            if (elementsCountryi == 109)           { dataSeries109.setName(x); barChart.getData().add(dataSeries109); }
//            if (elementsCountryi == 110)           { dataSeries110.setName(x); barChart.getData().add(dataSeries110); }
//            if (elementsCountryi == 111)           { dataSeries111.setName(x); barChart.getData().add(dataSeries111); }
//            if (elementsCountryi == 112)           { dataSeries112.setName(x); barChart.getData().add(dataSeries112); }
//            if (elementsCountryi == 113)           { dataSeries113.setName(x); barChart.getData().add(dataSeries113); }
//            if (elementsCountryi == 114)           { dataSeries114.setName(x); barChart.getData().add(dataSeries114); }
//            if (elementsCountryi == 115)           { dataSeries115.setName(x); barChart.getData().add(dataSeries115); }
//            if (elementsCountryi == 116)           { dataSeries116.setName(x); barChart.getData().add(dataSeries116); }
//            if (elementsCountryi == 117)           { dataSeries117.setName(x); barChart.getData().add(dataSeries117); }
//            if (elementsCountryi == 118)           { dataSeries118.setName(x); barChart.getData().add(dataSeries118); }
//            if (elementsCountryi == 119)           { dataSeries119.setName(x); barChart.getData().add(dataSeries119); }
//            if (elementsCountryi == 120)           { dataSeries120.setName(x); barChart.getData().add(dataSeries120); }
//            if (elementsCountryi == 121)           { dataSeries121.setName(x); barChart.getData().add(dataSeries121); }
//            if (elementsCountryi == 122)           { dataSeries122.setName(x); barChart.getData().add(dataSeries122); }
//            if (elementsCountryi == 123)           { dataSeries123.setName(x); barChart.getData().add(dataSeries123); }
//            if (elementsCountryi == 124)           { dataSeries124.setName(x); barChart.getData().add(dataSeries124); }
//            if (elementsCountryi == 125)           { dataSeries125.setName(x); barChart.getData().add(dataSeries125); }
//            if (elementsCountryi == 126)           { dataSeries126.setName(x); barChart.getData().add(dataSeries126); }
//            if (elementsCountryi == 127)           { dataSeries127.setName(x); barChart.getData().add(dataSeries127); }
//            if (elementsCountryi == 128)           { dataSeries128.setName(x); barChart.getData().add(dataSeries128); }
//            if (elementsCountryi == 129)           { dataSeries129.setName(x); barChart.getData().add(dataSeries129); }
//            if (elementsCountryi == 130)           { dataSeries130.setName(x); barChart.getData().add(dataSeries130); }
//            if (elementsCountryi == 131)           { dataSeries131.setName(x); barChart.getData().add(dataSeries131); }
//            if (elementsCountryi == 132)           { dataSeries132.setName(x); barChart.getData().add(dataSeries132); }
//            if (elementsCountryi == 133)           { dataSeries133.setName(x); barChart.getData().add(dataSeries133); }
//            if (elementsCountryi == 134)           { dataSeries134.setName(x); barChart.getData().add(dataSeries134); }
//            if (elementsCountryi == 135)           { dataSeries135.setName(x); barChart.getData().add(dataSeries135); }
//            if (elementsCountryi == 136)           { dataSeries136.setName(x); barChart.getData().add(dataSeries136); }
//            if (elementsCountryi == 137)           { dataSeries137.setName(x); barChart.getData().add(dataSeries137); }
//            if (elementsCountryi == 138)           { dataSeries138.setName(x); barChart.getData().add(dataSeries138); }
//            if (elementsCountryi == 139)           { dataSeries139.setName(x); barChart.getData().add(dataSeries139); }
//            if (elementsCountryi == 140)           { dataSeries140.setName(x); barChart.getData().add(dataSeries140); }
//            if (elementsCountryi == 141)           { dataSeries141.setName(x); barChart.getData().add(dataSeries141); }
//            if (elementsCountryi == 142)           { dataSeries142.setName(x); barChart.getData().add(dataSeries142); }
//            if (elementsCountryi == 143)           { dataSeries143.setName(x); barChart.getData().add(dataSeries143); }
//            if (elementsCountryi == 144)           { dataSeries144.setName(x); barChart.getData().add(dataSeries144); }
//            if (elementsCountryi == 145)           { dataSeries145.setName(x); barChart.getData().add(dataSeries145); }
//            if (elementsCountryi == 146)           { dataSeries146.setName(x); barChart.getData().add(dataSeries146); }
//            if (elementsCountryi == 147)           { dataSeries147.setName(x); barChart.getData().add(dataSeries147); }
//            if (elementsCountryi == 148)           { dataSeries148.setName(x); barChart.getData().add(dataSeries148); }
//            if (elementsCountryi == 149)           { dataSeries149.setName(x); barChart.getData().add(dataSeries149); }
//            if (elementsCountryi == 150)           { dataSeries150.setName(x); barChart.getData().add(dataSeries150); }
//            if (elementsCountryi == 151)           { dataSeries151.setName(x); barChart.getData().add(dataSeries151); }
//            if (elementsCountryi == 152)           { dataSeries152.setName(x); barChart.getData().add(dataSeries152); }
//            if (elementsCountryi == 153)           { dataSeries153.setName(x); barChart.getData().add(dataSeries153); }
//            if (elementsCountryi == 154)           { dataSeries154.setName(x); barChart.getData().add(dataSeries154); }
//            if (elementsCountryi == 155)           { dataSeries155.setName(x); barChart.getData().add(dataSeries155); }
//            if (elementsCountryi == 156)           { dataSeries156.setName(x); barChart.getData().add(dataSeries156); }
//            if (elementsCountryi == 157)           { dataSeries157.setName(x); barChart.getData().add(dataSeries157); }
//            if (elementsCountryi == 158)           { dataSeries158.setName(x); barChart.getData().add(dataSeries158); }
//            if (elementsCountryi == 159)           { dataSeries159.setName(x); barChart.getData().add(dataSeries159); }
//            if (elementsCountryi == 160)           { dataSeries160.setName(x); barChart.getData().add(dataSeries160); }
//            if (elementsCountryi == 161)           { dataSeries161.setName(x); barChart.getData().add(dataSeries161); }
//            if (elementsCountryi == 162)           { dataSeries162.setName(x); barChart.getData().add(dataSeries162); }
//            if (elementsCountryi == 163)           { dataSeries163.setName(x); barChart.getData().add(dataSeries163); }
//            if (elementsCountryi == 164)           { dataSeries164.setName(x); barChart.getData().add(dataSeries164); }
//            if (elementsCountryi == 165)           { dataSeries165.setName(x); barChart.getData().add(dataSeries165); }
//            if (elementsCountryi == 166)           { dataSeries166.setName(x); barChart.getData().add(dataSeries166); }
//            if (elementsCountryi == 167)           { dataSeries167.setName(x); barChart.getData().add(dataSeries167); }
//            if (elementsCountryi == 168)           { dataSeries168.setName(x); barChart.getData().add(dataSeries168); }
//            if (elementsCountryi == 169)           { dataSeries169.setName(x); barChart.getData().add(dataSeries169); }
//            if (elementsCountryi == 170)           { dataSeries170.setName(x); barChart.getData().add(dataSeries170); }
//            if (elementsCountryi == 171)           { dataSeries171.setName(x); barChart.getData().add(dataSeries171); }
//            if (elementsCountryi == 172)           { dataSeries172.setName(x); barChart.getData().add(dataSeries172); }
//            if (elementsCountryi == 173)           { dataSeries173.setName(x); barChart.getData().add(dataSeries173); }
//            if (elementsCountryi == 174)           { dataSeries174.setName(x); barChart.getData().add(dataSeries174); }
//            if (elementsCountryi == 175)           { dataSeries175.setName(x); barChart.getData().add(dataSeries175); }
//            if (elementsCountryi == 176)           { dataSeries176.setName(x); barChart.getData().add(dataSeries176); }
//            if (elementsCountryi == 177)           { dataSeries177.setName(x); barChart.getData().add(dataSeries177); }
//            if (elementsCountryi == 178)           { dataSeries178.setName(x); barChart.getData().add(dataSeries178); }
//            if (elementsCountryi == 179)           { dataSeries179.setName(x); barChart.getData().add(dataSeries179); }
//            if (elementsCountryi == 180)           { dataSeries180.setName(x); barChart.getData().add(dataSeries180); }
//            if (elementsCountryi == 181)           { dataSeries181.setName(x); barChart.getData().add(dataSeries181); }
//            if (elementsCountryi == 182)           { dataSeries182.setName(x); barChart.getData().add(dataSeries182); }
//            if (elementsCountryi == 183)           { dataSeries183.setName(x); barChart.getData().add(dataSeries183); }

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
        min++; //TODO:Burada min++ yapmasak??


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().stream().sorted();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {//milisaniyeye ayar lazım gibi
            @Override
            public void handle(ActionEvent actionEvent) {
                yearLabel.setText(Integer.toString(min)); //TODO ??
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
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(8))) dataSeries9.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(9))) dataSeries10.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(10))) dataSeries11.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(11))) dataSeries12.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(12))) dataSeries13.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(13))) dataSeries14.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(14))) dataSeries15.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(15))) dataSeries16.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(16))) dataSeries17.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(17))) dataSeries18.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(18))) dataSeries19.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(19))) dataSeries20.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(20))) dataSeries21.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(21))) dataSeries22.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(22))) dataSeries23.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(23))) dataSeries24.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(24))) dataSeries25.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(25))) dataSeries26.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(26))) dataSeries27.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(27))) dataSeries28.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(28))) dataSeries29.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(29))) dataSeries30.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(30))) dataSeries31.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(31))) dataSeries32.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(32))) dataSeries33.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(33))) dataSeries34.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(34))) dataSeries35.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(35))) dataSeries36.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(36))) dataSeries37.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(37))) dataSeries38.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(38))) dataSeries39.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(39))) dataSeries40.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(40))) dataSeries41.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(41))) dataSeries42.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(42))) dataSeries43.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(43))) dataSeries44.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(44))) dataSeries45.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(45))) dataSeries46.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(46))) dataSeries47.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(47))) dataSeries48.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(48))) dataSeries49.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(49))) dataSeries50.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(50))) dataSeries51.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(51))) dataSeries52.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(52))) dataSeries53.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(53))) dataSeries54.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(54))) dataSeries55.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(55))) dataSeries56.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(56))) dataSeries57.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(57))) dataSeries58.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(58))) dataSeries59.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(59))) dataSeries60.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(60))) dataSeries61.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(61))) dataSeries62.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(62))) dataSeries63.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(63))) dataSeries64.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(64))) dataSeries65.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(65))) dataSeries66.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(66))) dataSeries67.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(67))) dataSeries68.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(68))) dataSeries69.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(69))) dataSeries70.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(70))) dataSeries71.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(71))) dataSeries72.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(72))) dataSeries73.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(73))) dataSeries74.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(74))) dataSeries75.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(75))) dataSeries76.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(76))) dataSeries77.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(77))) dataSeries78.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(78))) dataSeries79.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(79))) dataSeries80.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(80))) dataSeries81.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(81))) dataSeries82.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(82))) dataSeries83.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(83))) dataSeries84.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(84))) dataSeries85.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(85))) dataSeries86.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(86))) dataSeries87.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(87))) dataSeries88.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(88))) dataSeries89.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(89))) dataSeries90.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(90))) dataSeries91.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(91))) dataSeries92.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(92))) dataSeries93.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(93))) dataSeries94.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(94))) dataSeries95.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(95))) dataSeries96.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(96))) dataSeries97.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(97))) dataSeries98.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(98))) dataSeries99.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(99))) dataSeries100.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(100))) dataSeries101.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(101))) dataSeries102.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(102))) dataSeries103.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(103))) dataSeries104.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(104))) dataSeries105.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(105))) dataSeries106.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(106))) dataSeries107.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(107))) dataSeries108.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(108))) dataSeries109.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(109))) dataSeries110.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(110))) dataSeries111.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(111))) dataSeries112.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(112))) dataSeries113.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(113))) dataSeries114.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(114))) dataSeries115.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(115))) dataSeries116.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(116))) dataSeries117.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(117))) dataSeries118.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(118))) dataSeries119.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(119))) dataSeries120.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(120))) dataSeries121.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(121))) dataSeries122.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(122))) dataSeries123.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(123))) dataSeries124.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(124))) dataSeries125.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(125))) dataSeries126.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(126))) dataSeries127.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(127))) dataSeries128.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(128))) dataSeries129.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(129))) dataSeries130.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(130))) dataSeries131.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(131))) dataSeries132.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(132))) dataSeries133.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(133))) dataSeries134.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(134))) dataSeries135.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(135))) dataSeries136.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(136))) dataSeries137.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(137))) dataSeries138.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(138))) dataSeries139.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(139))) dataSeries140.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(140))) dataSeries141.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(141))) dataSeries142.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(142))) dataSeries143.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(143))) dataSeries144.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(144))) dataSeries145.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(145))) dataSeries146.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(146))) dataSeries147.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(147))) dataSeries148.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(148))) dataSeries149.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(149))) dataSeries150.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(150))) dataSeries151.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(151))) dataSeries152.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(152))) dataSeries153.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(153))) dataSeries154.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(154))) dataSeries155.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(155))) dataSeries156.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(156))) dataSeries157.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(157))) dataSeries158.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(158))) dataSeries159.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(159))) dataSeries160.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(160))) dataSeries161.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(161))) dataSeries162.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(162))) dataSeries163.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(163))) dataSeries164.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(164))) dataSeries165.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(165))) dataSeries166.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(166))) dataSeries167.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(167))) dataSeries168.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(168))) dataSeries169.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(169))) dataSeries170.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(170))) dataSeries171.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(171))) dataSeries172.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(172))) dataSeries173.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(173))) dataSeries174.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(174))) dataSeries175.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(175))) dataSeries176.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(176))) dataSeries177.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(177))) dataSeries178.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(178))) dataSeries179.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(179))) dataSeries180.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(180))) dataSeries181.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(181))) dataSeries182.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}
//                    try { if (ListxCategory.get(abc).equals(elementsCountry.get(182))) dataSeries183.getData().add(new XYChart.Data<Number, String>(Integer.parseInt(ListxValue.get(abc)), ListxName.get(abc)));} catch(Exception e){}


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
//                timeline.playFromStart();
//                //TODO:Restart actionu tanımlanacak
                YedidenBasla123();
            }
        };
        butttonRestart.setOnAction(eventRestart);

        AnchorPane anchorPane = new AnchorPane(barChart,buttonPause,buttonPlay,butttonRestart,yearLabel);
        Scene scene = new Scene(anchorPane, 1000, 900);



        secondStage.setScene(scene);

        secondStage.setResizable(false);
        secondStage.setWidth(1000);
        secondStage.setHeight(900);

        secondStage.show();
        secondStage.setOnCloseRequest(e -> Platform.exit());
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
