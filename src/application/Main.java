package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.main.LoginPageController;
import java.lang.UnsupportedOperationException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            LoginPageController loginPageController = new LoginPageController(primaryStage);
            Scene scene = new Scene(loginPageController);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
