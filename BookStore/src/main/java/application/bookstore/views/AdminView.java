package application.bookstore.views;
import java.io.FileInputStream;

import application.bookstore.charts.Chart1;
import application.bookstore.models.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminView implements View{
	private User user;
	private ImageView[] buttonImages = new ImageView[5];
	private BorderPane frame;
	private Button[] buttons;
	private FileInputStream image;
	private Image img;
	private Alert alert;
	private Label welcome_user;
	private HBox options_1;
	private HBox options_2;
	private VBox allOptions;

	public AdminView(User user){
		this.user = user;
	}
	public Scene showView(Stage stage) throws Exception {

		frame = new BorderPane(); // Main Frame that will hold every component and pane
		Image backgroundImage = new Image("C:\\Users\\alvin\\OneDrive\\Desktop\\BookStoreJavafx\\BookStore\\Images\\background.jpg");

		BackgroundSize backgroundSize = new BackgroundSize(
				1000,  // Width of the scene
				720,   // Height of the scene
				false, // Width is percentage
				false, // Height is percentage
				false, // Width contains aspect ratio
				false  // Height contains aspect ratio
		);

		BackgroundImage background = new BackgroundImage(
				backgroundImage,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				backgroundSize
		);

		frame.setBackground(new Background(background));

		getButtonImages();//calling function that will get the images required for the side options
		
		options_1 = new HBox();
		options_2 = new HBox();
		allOptions = new VBox();

		buttons=new Button[5];
		/*buttons[0] = profile_button
		* buttons[1] = other_users_buttons
		* buttons[2] = bookstore_button
		* buttons[3] = statistics_button
		* buttons[4] = log_out_button*/

		/*!Here the View will handle the event handlers too since
		* there are not many there is no need for a controller */
		
		for(int i=0;i<5;i++)
		{
			buttons[i] = new Button("",buttonImages[i]);
			buttons[i].setPrefHeight(120);
			buttons[i].setPrefWidth(150);
			buttons[i].setBackground(null);
		}
		
		buttons[0].setOnAction(e -> {
			Stage popup = new Stage();
			ProfileView profileView = new ProfileView(user);
			popup.setScene(profileView.showView(popup));
			popup.show();
		});

		buttons[1].setOnAction(e->{
			OtherUsersView otherUsersView=new OtherUsersView(user);
			stage.setScene(otherUsersView.showView(stage));
		});

		buttons[2].setOnAction( e -> {
			BookView bookview = new BookView(user.getRole() , user);
			stage.setScene(bookview.showView(stage));
		});

		buttons[3].setOnAction(e-> {
					Chart1 chart1=new Chart1(user);
					stage.setScene(chart1.showView(stage));
				}
		);

		buttons[4].setOnAction(event -> {
			LoginView loginView = new LoginView();
			stage.setScene(loginView.showView(stage));
		});

		options_1.getChildren().addAll(buttons[0], buttons[1] , buttons[2]);
		options_2.getChildren().addAll(buttons[3] , buttons[4]);
		options_2.setSpacing(20);
		allOptions.getChildren().addAll(options_1 , options_2);
		allOptions.setAlignment(Pos.CENTER);

		welcome_user = new Label("Welcome " + user.getFirstName() + " " + user.getLastName()); 												//!!!!!!!!!!!! Replace with the class.getUsername(); to make it Dynamic depending on which users logs in
		welcome_user.setStyle(
				"-fx-font-size: 80px;" + // Set font size to 24 pixels							//!!!!!!! Not Finished yet the style will be changed to match background and look better
						"-fx-font-family: 'Arial';" + // Set font family to Arial
						"-fx-text-fill: white;" // Set text color to white
		);

		frame.setAlignment(welcome_user, Pos.CENTER);
		frame.setAlignment(allOptions, Pos.CENTER);
		options_1.setAlignment(Pos.CENTER);
		options_2.setAlignment(Pos.CENTER);
		double buttonSize = 150.0;
		for (Button button : new Button[]{buttons[0], buttons[1] , buttons[2],buttons[3],buttons[4]}) {
			button.setPrefHeight(buttonSize);
			button.setPrefWidth(buttonSize);
			button.setBackground(null);
		}
		frame.setTop(welcome_user);
		frame.setCenter(allOptions);

		stage.setTitle("Admin Window");
		return new Scene(frame , 1000 , 720 );
	}

	protected void getButtonImages() {
		/*image is a fileinputstream which is used to get the png files into the code,
		 then we use that to create a imageview and store it into buttonImages array of imageviews which is later used to get the icons for buttons
		 */
		String[] images={"user-3.png","group-3.png","book-3.png","trend.png","power.png"};
		for(int i=0;i<5;i++)
		{
			try
			{
				image = new FileInputStream("C:\\Users\\alvin\\OneDrive\\Desktop\\BookStoreJavafx\\BookStore\\Images\\"+images[i]);
				img = new Image(image);
				buttonImages[i] = new ImageView(img);
				buttonImages[i].setFitHeight(100);
				buttonImages[i].setFitWidth(100);
			}catch (java.io.FileNotFoundException e) {
			//Alert notifying user if there is an error regarding loading picture
			alert = new Alert(AlertType.ERROR);
			alert.setContentText("Resources Missing!Contact your Admin!\nErrorCode:104");
			alert.show();
			e.printStackTrace();
		}
		}
	}
}
