package flight.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class LoginDialog extends Dialog<String> {
    @FXML
    private TextField fxUserId;
    @FXML
    private PasswordField fxPassword;
    @FXML
    private ButtonType fxOkay;

    /**
     * Runs Diolog and returns whatever was inputted
     */
    public LoginDialog() {
        setDialogPane(readLoginDialog());
        okayRule();
        setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return fxUserId.getText();
            } else {
                return null;
            }
        });
    }

    /**
    /**
     * Reads in fxml file and tries to set LoginDiolog
     * as controller and then loads it
     * @return DialogPane
     */
    private DialogPane readLoginDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Makes okay button inactive if name og password have not been entered
     */
    private void okayRule() {

        Node okay = getDialogPane().lookupButton(fxOkay);
        okay.disableProperty()
                .bind(fxUserId.textProperty().isEmpty()
                        .or(fxPassword.textProperty().isEmpty()));
    }

 
    /**
     * Runs Diolog and waits for a result when it is called
     * @return Result of Diolog
     */
    public String getUser() {
        Optional<String> result = showAndWait();
        return result.orElse(null);
    }

}
