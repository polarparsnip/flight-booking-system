package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    @FXML
    private TextField userName;
    @FXML
    private TextField bookingCode;
    @FXML
    private Button fxLoginButton;





    public void handleLogin(ActionEvent event) {
        System.out.println("clicked");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("flights" +
                    "-view" +
                    ".fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) fxLoginButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getuserName() {
    }
}




