package application.bookstore.controllers;

import application.bookstore.Exceptions.EmailAlreadyExistsException;
import application.bookstore.auxiliaries.Alerts;
import application.bookstore.models.User;
import application.bookstore.views.ProfileView;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileViewController {
    private final Stage stage;
    private User user;
    public ProfileViewController(ProfileView view, Stage stage,User user) {
        this.user=user;
        this.stage = stage;
        stage.centerOnScreen();
        stage.setResizable(false);
        addListener(view);
    }
    public void addListener(ProfileView view)
    {   //Updating the current window
        view.getEdit_button().setOnAction(e ->
        {
                Button button = new Button("Confirm Changes");
                button.setFont(Font.font(25));
                view.getPane().add(button, 1, 8);
                view.getFirst_name_label().setText("Change Name: ");
                view.getSurname_label().setText("Change Surname: ");
                view.getEmail_label().setText("Change Email: ");
                view.getUsername_label().setText("Change Username: ");
                view.getGender_label().setText("Change Gender: ");
                String old_first_name = view.getFirst_name().getText();
                String old_surname = view.getSurname().getText();
                String old_email = view.getEmail().getText();
                String old_username = view.getUsername().getText();
                String old_gender = view.getGender().getText();

                view.getFirst_name().setEditable(true);
                view.getSurname().setEditable(true);
                view.getEmail().setEditable(true);
                view.getUsername().setEditable(true);
                view.getGender().setEditable(true);
                TextField password = new TextField();
                Label password_label = new Label("New Password:");
                password.setText(user.getPassword());
                password_label.setFont(Font.font(20));
                password.setFont(Font.font(20));
                view.getPane().add(password_label, 0, 6);
                view.getPane().add(password, 1, 6);
                button.setOnAction(b -> {

                    if (!old_first_name.equals(view.getFirst_name().getText()))
                    {
                        if(view.getFirst_name().getText().matches("[a-zA-Z ]{1,24}")
                                && !view.getFirst_name().getText().isEmpty())
                        {
                            ProfileViewEditController.changeName(view.getFirst_name().getText(), view.getUser());
                        }else
                        {
                            Alerts.showAlert(Alert.AlertType.ERROR,"Error in name","The name and surname can only " +
                                    "include letters and spaces and cannot be longer than 25 characters.");
                            stage.close();
                        }
                    }

                    if (!old_surname.equals(view.getSurname().getText()))
                    {
                        if( view.getSurname().getText().matches("[a-zA-Z ]{1,24}")
                                && !view.getSurname().getText().isEmpty())
                        {
                            ProfileViewEditController.changeSurname(view.getSurname().getText(), view.getUser());
                        }else
                        {
                            Alerts.showAlert(Alert.AlertType.ERROR,"Error in surname","The name and surname can only " +
                                    "include letters and spaces and cannot be longer than 25 characters.");
                            stage.close();
                        }
                    }
                    if (!old_email.equals(view.getEmail().getText()))
                    {
                        if( view.getEmail().getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
                                && !view.getEmail().getText().isEmpty())
                        {
                            try {
                                ProfileViewEditController.changeEmail(view.getEmail().getText(), view.getUser());
                            } catch (EmailAlreadyExistsException l) {
                                Alerts.showAlert(Alert.AlertType.ERROR, "Invalid email!",
                                        "The entered email already exists");
                            }
                        }else
                        {
                            Alerts.showAlert(Alert.AlertType.ERROR,"Error in email","Invalid Email");
                            stage.close();
                        }
                    }
                    if (!old_username.equals(view.getUsername().getText()))
                    {
                        if(view.getUsername().getText().matches("^[a-zA-Z0-9_]{3,16}$") &&
                                !view.getUsername().getText().isEmpty()) {
                            try {
                                ProfileViewEditController.changeUsername(view.getUsername().getText(), view.getUser());
                            } catch (Exception l) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText(l.getMessage());
                                alert.show();
                            }
                        }else
                        {
                            Alerts.showAlert(Alert.AlertType.ERROR,"Invalid username","The username must be" +
                                    "between 3 and 16 charactes and can only include letter,numbers and underscore");
                            stage.close();
                        }
                    }
                    if (!old_gender.equals(view.getGender().getText())) {
                        String enteredGender = view.getGender().getText().trim();

                        if (enteredGender.equalsIgnoreCase("male") || enteredGender.equalsIgnoreCase("female") || enteredGender.equalsIgnoreCase("other")) {
                            ProfileViewEditController.changeGender(enteredGender, view.getUser());
                        } else {
                            Alerts.showAlert(Alert.AlertType.ERROR,"Invalid gender","Please enter male,female or other");
                            stage.close();
                        }
                    }
                    if (!(view.getUser().getPassword()).equals(password.getText()))
                    {
                        if(!password.getText().isEmpty() &&
                                password.getText().matches("^(?=.*[A-Za-z])(?=.*[\\d])[A-Za-z\\d]{4,}$")) {
                            try {
                                ProfileViewEditController.changePassword(password.getText(), view.getUser());
                            } catch (Exception l) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText(l.getMessage());
                                alert.show();
                            }
                        }else {
                            Alerts.showAlert(Alert.AlertType.ERROR,"Error in password","The password must contain " +
                                    "a minimum of four characters, at least one letter and one number and no special characters.");
                            stage.close();
                        }
                    }
                    stage.close();
                });
        });
    }

}
