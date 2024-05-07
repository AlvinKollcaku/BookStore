package application.bookstore.views;

import application.bookstore.controllers.ProfileViewController;
import application.bookstore.controllers.ProfileViewEditController;
import application.bookstore.models.Role;
import application.bookstore.models.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileView {
    private User user;
    private Label first_name_label;
    private TextField surname;
    private TextField first_name;
    private Label surname_label;
    private Label email_label;
    private TextField email;
    private TextField username;
    private Label username_label;
    private Label gender_label;
    private TextField gender;
    private Label role_label;
    private TextField role;
    private GridPane pane;
    private Button edit_button;

    public ProfileView(User user) {
        this.user = user;
    }

    public Scene showView(Stage stage) {
        pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(30);
        pane.setVgap(15);

        first_name_label = new Label("First Name:");
        first_name_label.setFont(Font.font(20));

        first_name = new TextField();
        first_name.setEditable(false);
        first_name.setFont(Font.font(20));
        first_name.setText(user.getFirstName());

        surname_label = new Label("Last Name:");
        surname_label.setFont(Font.font(20));

        surname = new TextField();
        surname.setEditable(false);
        surname.setFont(Font.font(20));
        surname.setText(user.getLastName());

        email_label = new Label("Email:");
        email_label.setFont(Font.font(20));

        email = new TextField();
        email.setEditable(false);
        email.setFont(Font.font(20));
        email.setText(user.getEmail());

        username_label = new Label("Username:");
        username_label.setFont(Font.font(20));

        username = new TextField();
        username.setEditable(false);
        username.setFont(Font.font(20));
        username.setText(user.getUsername());

        gender_label = new Label("Gender:");
        gender_label.setFont(Font.font(20));

        gender = new TextField();
        gender.setEditable(false);
        gender.setText(user.getGender());
        gender.setFont(Font.font(20));

        role_label = new Label("Role:");
        role_label.setFont(Font.font(20));

        role = new TextField();
        role.setEditable(false);
        role_label.setFont(Font.font(20));
        role.setText(user.getRoleString());
        role.setFont(Font.font(20));

        edit_button = new Button("Edit");
        edit_button.setFont(Font.font(20));

        pane.add(first_name_label, 0, 0);
        pane.add(first_name, 1, 0);
        pane.add(surname_label, 0, 1);
        pane.add(surname, 1, 1);
        pane.add(email_label, 0, 2);
        pane.add(email, 1, 2);
        pane.add(username_label, 0, 3);
        pane.add(username, 1, 3);
        pane.add(gender_label, 0, 4);
        pane.add(gender, 1, 4);
        pane.add(role_label, 0, 5);
        pane.add(role, 1, 5);
        pane.add(edit_button, 1, 8);

        new ProfileViewController(this,stage,user);
        stage.setTitle("Profile");
        return new Scene(pane, 500, 510);
    }
    public User getUser() {
        return user;
    }
    public Label getFirst_name_label() {
        return first_name_label;
    }

    public TextField getSurname() {
        return surname;
    }

    public TextField getFirst_name() {
        return first_name;
    }

    public Label getSurname_label() {
        return surname_label;
    }

    public Label getEmail_label() {
        return email_label;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getUsername() {
        return username;
    }

    public Label getUsername_label() {
        return username_label;
    }

    public Label getGender_label() {
        return gender_label;
    }

    public TextField getGender() {
        return gender;
    }

    public GridPane getPane() {
        return pane;
    }
    public Button getEdit_button() {
        return edit_button;
    }
}
