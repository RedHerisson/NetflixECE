package View;

//Fenetre semblable à HTML
//contrôle des objets


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TextField tfTitle;

    @FXML
    void btnOKClicked(ActionEvent event) {
        Stage mainWindow =(Stage) tfTitle.getScene().getWindow();
        String title = tfTitle.getText();
        mainWindow.setTitle(title);

    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {
        Stage mainWindow2 = (Stage) tfTitle.getScene().getWindow();
        String title2 = tfTitle.getText();
        mainWindow2.setTitle(title2);

    }

}
