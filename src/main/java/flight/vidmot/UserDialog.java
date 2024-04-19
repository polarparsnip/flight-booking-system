package flight.vidmot;

import flight.classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;

public class UserDialog extends Dialog<User> {
    @FXML
    private TextField fxName;
    @FXML
    private TextField fxUserId;
    @FXML
    private TextField fxPassword;
    @FXML
    private ButtonType fxOkay;

    /**
     * Runs Diolog and returns whatever was inputted
     */
    public UserDialog() {
        setDialogPane(readUserDialog());
        okayRule();
        setResultConverter(b -> {                                 // b is of type ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new User(fxUserId.getText(), fxName.getText());
            } else {
                return null;
            }
        });
    }

    /**
     * Reads in fxml file and tries to set UserDiolog
     * as controller and then loads it
     * @return DialogPane
     */
    private DialogPane readUserDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
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
        Node iLagi = getDialogPane().lookupButton(fxOkay);
        iLagi.disableProperty()
                .bind(fxName.textProperty().isEmpty()
                        .or(fxPassword.textProperty().isEmpty()));
    }

    /**
     * Runs Diolog and waits for a result when it is called
     * @return Result of Diolog
     */
    public User getUser() {
        Optional<User> result = showAndWait();
        return result.orElse(null);
    }

}
