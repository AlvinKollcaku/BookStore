package application.bookstore.controllers;

import application.bookstore.Exceptions.*;
import application.bookstore.auxiliaries.DatabaseConnector;
import application.bookstore.models.User;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.sql.*;

public class ProfileViewEditController implements DatabaseConnector {
    public static void changeName(String new_value , User user){
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE User SET firstName = ? WHERE email = ? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, new_value);
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("First name updated successfully");
                    user.setFirstName(new_value);
                } else {
                    System.out.println("Name: User not found or no updates performed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeSurname(String new_value , User user){
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE User SET lastName = ? WHERE email = ? and password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, new_value);
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Last name updated successfully");
                    user.setLastName(new_value);
                } else {
                    System.out.println("Last Name: User not found or no updates performed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void changeUsername(String new_value, User user) throws UsernameAlreadyExistsException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Checking if the new username already exists for any other user
            String checkQuery = "SELECT COUNT(*) FROM User WHERE userName = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, new_value);

                ResultSet resultSet = checkStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count > 0) {
                    throw new UsernameAlreadyExistsException();
                } else {
                    // Update username if it doesn't exist for any other user
                    String updateQuery = "UPDATE User SET userName = ? WHERE email = ? AND password = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setString(1, new_value);
                        preparedStatement.setString(2, user.getEmail());
                        preparedStatement.setString(3, user.getPassword());

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Username updated successfully");
                            user.setUsername(new_value);
                        } else {
                            System.out.println("Username: User not found or no updates performed");
                        }
                    }
                }
            }catch(Exception e)
            {
                System.out.println("Problem changing username for admin");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Problem connecting to DB");
            e.printStackTrace();
        }
    }


    public static void changeEmail(String new_value, User user) throws  EmailAlreadyExistsException{
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String checkQuery = "SELECT COUNT(*) FROM user WHERE email = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, new_value);

                ResultSet resultSet = checkStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count > 0) {
                    throw new EmailAlreadyExistsException();
                } else {
                    String updateQuery = "UPDATE User SET email = ? WHERE firstName = ? AND lastName = ? AND password = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setString(1, new_value);
                        preparedStatement.setString(2, user.getFirstName());
                        preparedStatement.setString(3, user.getLastName());
                        preparedStatement.setString(4, user.getPassword());

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Email updated successfully");
                            user.setEmail(new_value);
                        } else {
                            System.out.println("Email: User not found or no updates performed");
                        }
                    }
                }
            }catch (Exception e)
            {
                System.out.println("Problem updating email for admin");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Problem connecting to DB");
            e.printStackTrace();
        }
    }

    public static void changeGender(String new_value , User user){
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                String updateQuery = "UPDATE User SET gender = ? WHERE email = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, new_value);
                    preparedStatement.setString(2,user.getEmail());

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Gender updated successfully");
                        user.setGender(new_value);
                    } else {
                        System.out.println("Gender: User not found or no updates performed");
                    }
                }catch(Exception e)
                {
                    System.out.println("Problem updating gender");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Problem connecting to DB");
                e.printStackTrace();
            }
    }

    public static void changePassword(String new_value, User user) throws PasswordAlreadyExistsException{
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // checking if the new password already exists for any other user
            String checkQuery = "SELECT COUNT(*) FROM User WHERE password = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, new_value);

                ResultSet resultSet = checkStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count > 0) {
                    throw new PasswordAlreadyExistsException();
                } else {
                    String updateQuery = "UPDATE User SET password = ? WHERE firstName = ? AND lastName = ? AND email = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setString(1, new_value);
                        preparedStatement.setString(2, user.getFirstName());
                        preparedStatement.setString(3, user.getLastName());
                        preparedStatement.setString(4, user.getEmail());
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Password updated successfully");
                            user.setPassword(new_value);
                        } else {
                            System.out.println("Password: User not found or no updates performed");
                        }
                    }
                }
            }catch(Exception e)
            {
                System.out.println("Problem updating email for admin");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Problem connecting to DB");
            e.printStackTrace();
        }
    }
}
