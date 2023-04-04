package View;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SlideApp2 implements Initializable {

    /*
    Buttons Panel 1
     */
    @FXML
    private Button Back_Panel1;
    @FXML
    private Button Next1_Panel1;

    /*
    Buttons Panel 2
     */
    @FXML
    private Button Back_Panel2;
    @FXML
    private Button Next_Panel2;

    /*
    Buttons >Panel 3
     */
    @FXML
    private Button Back_Panel3;
    @FXML
    private Button Next_Panel3;

    /*
    Label Panel 1
     */
    @FXML
    private Label Numero;
    /*
    Label Panel 2
     */
    @FXML
    private Label Numero2;
    /*
    Label Panel 3
     */
    @FXML
    private Label Numero3;

    /*
    other Label
     */
    @FXML
    private Label ActionMovies;
    @FXML
    private Label SciFiMovies;
    @FXML
    private Label HistoryMovies;

    /* CORRESPOND AUX ELEMENTS DU PREMIER PANEL */
    @FXML
    private AnchorPane Panel_Carousel4;
    @FXML
    private AnchorPane Panel1_Carousel4_1;
    @FXML
    private AnchorPane Panel1_Carousel4_2;
    @FXML
    private AnchorPane Panel1_Carousel4_3;
    /*FIN PREMIER PANEL*/

    /* CORRESPOND AUX ELEMENTS DU DEUXIEME PANEL */
    @FXML
    private AnchorPane Panel2_Carousel4_1;

    @FXML
    private AnchorPane Panel2_Carousel4_2;

    @FXML
    private AnchorPane Panel2_Carousel4_3;
    /*FIN DEUXIEME PANEL*/

    /*CORRESPOND AUX ELEMENTS DU TROISIEME PANEL */
    @FXML
    private AnchorPane Panel3_Carousel4_1;

    @FXML
    private AnchorPane Panel3_Carousel4_2;

    @FXML
    private AnchorPane Panel3_Carousel4_3;
    /*FIN TROISIEME PANEL*/


/*
Exit button
 */
    @FXML
    private Button exitButton;

    //test image quitter
    @FXML
    private ImageView Image_Panel1;

    int show = 0;
    int show2 = 0;
    int show3=0;

    /*
    Action on the first Panel by Back1 and Next1
     */
    @FXML
    void Back_Panel1OnAction(ActionEvent event) {
        if(show ==1){
            translateAnimation(0.5, Panel1_Carousel4_2, 1275);
            show--;
            Numero.setText("1/3");
        }
        else if(show ==2){
            translateAnimation(0.5, Panel1_Carousel4_3, 1275);
            show--;
            Numero.setText("2/3");
        }
    }
    @FXML
    void Next1_Panel1OnAction(ActionEvent event) {
        if(show ==0){
            translateAnimation(0.5, Panel1_Carousel4_2, -1275);
            show++;
            Numero.setText("2/3");
        }
        else if(show ==1){
            translateAnimation(0.5, Panel1_Carousel4_3, -1275);
            show++;
            Numero.setText("3/3");
        }
    }

    /*
    Action on the Second Panel by Back2 and Next2
     */
    @FXML
    void Back_Panel2OnAction(ActionEvent event) {
        if(show2 ==1){
            translateAnimation(0.5, Panel2_Carousel4_2, 1275);
            show2--;
            Numero2.setText("1/3");
        }
        else if(show2 ==2){
            translateAnimation(0.5, Panel2_Carousel4_3, 1275);
            show2--;
            Numero2.setText("2/3");
        }

    }

    @FXML
    void Next_Panel2OnAction(ActionEvent event) {
        if(show2 ==0){
            translateAnimation(0.5, Panel2_Carousel4_2, -1275);
            show2++;
            Numero2.setText("2/3");
        }
        else if(show2 ==1){
            translateAnimation(0.5, Panel2_Carousel4_3, -1275);
            show2++;
            Numero2.setText("3/3");
        }

    }

     /*
    Action on the Third Panel by Back3 and Next3
     */
     @FXML
     void Back_Panel3OnAction(ActionEvent event) {
         if(show3 ==1){
             translateAnimation(0.5, Panel3_Carousel4_2, 1275);
             show3--;
             Numero3.setText("1/3");
         }
         else if(show3 ==2){
             translateAnimation(0.5, Panel3_Carousel4_3, 1275);
             show3--;
             Numero3.setText("2/3");
         }

     }

    @FXML
    void Next_Panel3OnAction(ActionEvent event) {
        if(show3 ==0){
            translateAnimation(0.5, Panel3_Carousel4_2, -1275);
            show3++;
            Numero3.setText("2/3");
        }
        else if(show3 ==1){
            translateAnimation(0.5, Panel3_Carousel4_3, -1275);
            show3++;
            Numero3.setText("3/3");
        }
    }



    //exit button
    @FXML
    void exitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    //Transistion when click on the button
    public void translateAnimation(double duration, Node node, double width){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }


    //Initialization of the page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

         //First Panel
        translateAnimation(0.5, Panel1_Carousel4_2, 1275);
        translateAnimation(0.5, Panel1_Carousel4_3, 1275);
        //translateAnimation(0.5, Panel1_Carousel4_1, 1275);

        //Second Panel
        translateAnimation(0.5, Panel2_Carousel4_2, 1275);
        translateAnimation(0.5, Panel2_Carousel4_3, 1275);

        //Third Panel
        translateAnimation(0.5, Panel3_Carousel4_2, 1275);
        translateAnimation(0.5, Panel3_Carousel4_3, 1275);

     }





}
