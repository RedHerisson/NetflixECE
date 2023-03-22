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

    @FXML
    private Button Back_Panel1;
    @FXML
    private Button Next1_Panel1;
    @FXML
    private Label Numero;

    /* CORRESPOND AUX ELEMENTS DU PREMIER PANEL */
    @FXML
    private AnchorPane Panel_Carousel4;
    @FXML
    private AnchorPane Panel1_Carousel4_1;
    @FXML
    private AnchorPane Panel2_Carousel4_2;
    @FXML
    private AnchorPane Panel3_Carousel4_3;
    /*FIN PREMIER PANEL*/

    @FXML
    private Button exitButton;
    @FXML
    private ImageView Image_Panel1;

    int show = 0;

    @FXML
    void Back_Panel1OnAction(ActionEvent event) {
        if(show ==1){
            translateAnimation(0.5, Panel2_Carousel4_2, 1275);
            show--;
            Numero.setText("1/3");
        }
        else if(show ==2){
            translateAnimation(0.5, Panel3_Carousel4_3, 1275);
            show--;
            Numero.setText("2/3");
        }
    }

    @FXML
    void Next1_Panel1OnAction(ActionEvent event) {
        if(show ==0){
            translateAnimation(0.5, Panel2_Carousel4_2, -1275);
            show++;
            Numero.setText("2/3");
        }
        else if(show ==1){
            translateAnimation(0.5, Panel3_Carousel4_3, -1275);
            show++;
            Numero.setText("3/3");
        }
    }

    @FXML
    void exitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void translateAnimation(double duration, Node node, double width){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(width);
        translateTransition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        translateAnimation(0.5, Panel2_Carousel4_2, 1275);
        translateAnimation(0.5, Panel3_Carousel4_3, 1275);
        //translateAnimation(0.5, Panel1_Carousel4_1, 1275);
    }

    public void close(Stage stage){
        Image_Panel1.setOnMouseClicked(event->{
            System.exit(0);
        });
    }


}
