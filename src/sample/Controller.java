package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
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
    }
    @FXML
    private void BCTextOku(ActionEvent event){
        System.out.println("TextOku butonu tetik");
    }
}