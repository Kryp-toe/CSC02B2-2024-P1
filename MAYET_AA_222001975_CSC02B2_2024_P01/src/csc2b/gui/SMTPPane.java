package csc2b.gui;

import csc2b.module.SMTP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SMTPPane extends StackPane{
	private VBox root;
	private GridPane gridPane;

	public SMTPPane()
	{
		//create a root node
		root = new VBox();
		
		//create a gridpane node
		gridPane = new GridPane();
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(5,5,5,5));
		
		//create labels for fields
		Label hostNameLabel = new Label("Host-Name: ");
		Label portNoLabel = new Label("Port Number: ");
		Label fromLabel = new Label("From: ");
		Label toLabel = new Label("To: ");
		Label subjectLabel = new Label("Subject: ");
		
		//add labels to grid
		gridPane.add(hostNameLabel, 0, 0);
		gridPane.add(portNoLabel, 0, 1);
		gridPane.add(fromLabel, 0, 2);
		gridPane.add(toLabel, 0, 3);
		gridPane.add(subjectLabel, 0, 4);
		
		//create fields with starting text
		TextField hostNameField = new TextField("mail.smtpbucket.com");
		TextField portNoField = new TextField("8025");
		TextField fromField = new TextField("sender@csc2b.uj.ac.za");
		TextField toField = new TextField("recipient@csc2b.uj.ac.za");
		TextField subjectField = new TextField("Subject");
		
		//set width of fields
		hostNameField.setPrefWidth(600);
		portNoField.setPrefWidth(600);
		fromField.setPrefWidth(600);
		toField.setPrefWidth(600);
		subjectField.setPrefWidth(600);
		
		//add fields to grid
		gridPane.add(hostNameField, 1, 0);
		gridPane.add(portNoField, 1, 1);
		gridPane.add(fromField, 1, 2);
		gridPane.add(toField, 1, 3);
		gridPane.add(subjectField, 1, 4);
		
		//add grid to root node
		root.getChildren().add(gridPane);
		
		//create message node
		VBox vBox = new VBox();
		TextArea messageField = new TextArea();
		messageField.setWrapText(true);
		messageField.setMaxSize(700, 800);
		vBox.getChildren().add(messageField);
		
		//add message node to root node
		root.getChildren().add(vBox);
		
		//create button node
		vBox = new VBox();
		Button sendButton = new Button("Send");
		vBox.setAlignment(Pos.BOTTOM_LEFT);
		vBox.getChildren().add(sendButton);
		
		//add button to root node
		root.getChildren().add(vBox);
		
		//when button is clicked
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//create mail instance
				SMTP mail = new SMTP();
				
				//get text from fields and send mail
				mail.setHostString(hostNameField.getText());
				mail.setPortNumber(Integer.parseInt(portNoField.getText()));
				mail.setSenderEmail(fromField.getText());
				mail.setRecipientEmail(toField.getText());
				mail.setSubjectString(subjectField.getText());
				mail.setMessagString(messageField.getText());
				
				mail.SendMail();
			}
		});
		
		//add root node to stackPane
		root.setAlignment(Pos.TOP_LEFT);
		this.setPadding(new Insets(10,10,10,10));
		this.getChildren().add(root);
	}
}
