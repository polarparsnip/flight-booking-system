package flight.vidmot;

import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FlightApplication extends Application {
    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(FlightApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        // stage.setTitle("Hello!");
        // stage.setScene(scene);
        // stage.show();

        Scene scene = new Scene(new Pane(), 650, 500);
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MENU);
        Image image = new Image("file:src/main/resources/flight/vidmot/picture/smile.jpg");
        stage.getIcons().add(image);
        stage.setTitle("Flight Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}