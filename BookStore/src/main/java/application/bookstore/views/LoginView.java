package application.bookstore.views;

//The admin log in info will be given at the beginning by the programmers
//Than the admin can change the username and password if they wish to

import application.bookstore.auxiliaries.DatabaseConnector;
import application.bookstore.controllers.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class LoginView implements View,DatabaseConnector {
    private GridPane grid;
    private Button loginBtn; 
    private TextField usernameTextField;
    private PasswordField passwordField;
    public Scene showView(Stage stage)
    {
        StackPane root = new StackPane();
        try {
            //Reading the images as binary files
            //FileInputStream -> for reading and writing bytes from/to files
            FileInputStream logoFile=new FileInputStream("C:\\Users\\alvin\\OneDrive\\Desktop\\BookStoreJavafx\\BookStore\\Images\\bookStoreLogo2.png");
            FileInputStream backgroundFile = new FileInputStream("C:\\Users\\alvin\\OneDrive\\Desktop\\BookStoreJavafx\\BookStore\\Images\\background-login.png");

            //Image is used to load an image from specified filename or URL. Cant be directly displayed in app-> need ImageView
            /*Image object can be shared by multiple nodes -> for example it can
            be represented by multiple ImageViews into a pane or scene, BUT an ImageView cant that is
            why I also created an Image object too
             */
            Image logoImage = new Image(logoFile);
            Image backgroundImage = new Image(backgroundFile);

            //ImageView -> Node that displays an image
            ImageView logo = new ImageView(logoImage);
            ImageView background = new ImageView(backgroundImage);

            StackPane.setAlignment(logo, Pos.TOP_CENTER);
            background.setFitWidth(1000);
            background.setFitHeight(700);

            root.getChildren().addAll(background, logo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text welcomeMessage = new Text("Welcome to the Bookstore");
        welcomeMessage.setFill(javafx.scene.paint.Color.WHITE);
        welcomeMessage.setTextAlignment(TextAlignment.CENTER);
        welcomeMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 50));
        grid.add(welcomeMessage,0,0,2,2);
        // (columnIndex, rowIndex, spannedColumns, spannedRows)

        Label userName = new Label("Username:");
        userName.setTextFill(javafx.scene.paint.Color.WHITE);
        userName.setFont(Font.font(20));
        grid.add(userName, 0,4 );

        usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 4);

        Label password_label = new Label("Password:");
        password_label.setTextFill(javafx.scene.paint.Color.WHITE);
        password_label.setFont(Font.font(20));
        grid.add(password_label, 0, 5);

        passwordField = new PasswordField();
        grid.add(passwordField, 1, 5);

        loginBtn = new Button("Log in");
        loginBtn.setStyle("-fx-background-color: #808080;");
        loginBtn.setTextFill(Color.WHITE);
        loginBtn.setFont(Font.font(20));
        HBox hbtn=new HBox(loginBtn);
        hbtn.setAlignment(Pos.CENTER);
        grid.add(hbtn, 0, 7,2,1);

        stage.setTitle("Log in");

        root.getChildren().add(grid);

        new LoginController(this, stage);
        //creating an instance of the controller to activate the event listeners
        /*The instance will not be garbage collected as long as the listeners defined
        * in it will be active (as long as they are bound to active UI components) */

        root.setStyle(
                "-fx-font-family: 'JetBrains Mono'; " +
                        "-fx-font-size: 20px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-repeat: no-repeat; " +
                        "-fx-background-size: cover;"
        );

        return new Scene(root, 1000, 700);
    }
    public  GridPane getMainPane()
    {
    return this.grid;
    }
    public Button getloginBtn()
    {
        return loginBtn;
    }
    public TextField getusernameTextField()
    {
        return usernameTextField;
    }
    public PasswordField getPasswordField()
    {
        return passwordField;
    }
}
