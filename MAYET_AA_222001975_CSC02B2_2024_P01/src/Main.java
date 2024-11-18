import csc2b.gui.SMTPPane;
import csc2b.module.SMTP;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create pane instance
		SMTPPane pane = new SMTPPane();
		
		// Create the Scene
		Scene scene = new Scene(pane);

		//set stage width and height
		primaryStage.setWidth(750);
		primaryStage.setHeight(850);

		// Set the Scene
		primaryStage.setScene(scene);
		primaryStage.setTitle("Email server Client");

		//show the stage
		primaryStage.show();
	}
}