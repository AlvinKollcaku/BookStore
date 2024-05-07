package application.bookstore.views;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface View {
    Scene showView(Stage stage) throws Exception;

}
