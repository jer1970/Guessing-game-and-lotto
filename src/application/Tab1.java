
/**
 * @author jeremy staunton
 * studentId r00158317
 * 
 */
package application;

import java.io.IOException;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Tab1 extends Tab {
	private Button btnSubmit;
	private Button btnReset;
	private Button btnQuit;
	private Label lblTitle;
	private Label lblPickANum;
	private Label lblSixAttempts;
	private Label lblGuessAndWin;
	private Label lblEnterANum;
	private Label lblTooLow;
	private Label lblTooHigh;
	private Label lblCorrect;
	private TextField txtNumber;
	private int starPrize;
	private int count = 1;
	private int randomNumber;
	private AlertBox altbx;
	private StackPane layout;

	public void start(Stage w, Tab tab, Tab3 tb3, ReadFile2 rf) throws Exception {
		altbx = new AlertBox();
		starPrize = 4;
		tab.setDisable(true);
		randomNumber = randomNum();

		Color color;
		BackgroundFill fill;
		Background background;

		lblTitle = new Label("Guessing Game");
		lblTitle.setTextFill(Color.web("#cc1818"));
		lblTitle.setScaleX(2);
		lblTitle.setScaleY(2);
		lblPickANum = new Label("Pick a Nunber from 1 to 100");
		lblPickANum.setTextFill(Color.web("#f4e913"));
		lblSixAttempts = new Label("You have 6 attempts");
		lblSixAttempts.setTextFill(Color.web("#f4e913"));
		lblGuessAndWin = new Label("Guess correctly and win a 4 * prize");
		lblGuessAndWin.setTextFill(Color.web("#f4e913"));

		VBox vbxTopLabels = new VBox(10);
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		vbxTopLabels.setBackground(background);
		vbxTopLabels.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
		vbxTopLabels.setMaxWidth(300);
		vbxTopLabels.setMaxHeight(150);
		vbxTopLabels.setAlignment(Pos.CENTER);
		vbxTopLabels.setPadding(new Insets(10));
		vbxTopLabels.getChildren().addAll(lblTitle, lblPickANum, lblSixAttempts, lblGuessAndWin);

		HBox hbxVBoxLabels = new HBox();
		hbxVBoxLabels.setMaxSize(300, 150);
		hbxVBoxLabels.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		hbxVBoxLabels.getChildren().add(vbxTopLabels);
		hbxVBoxLabels.setAlignment(Pos.CENTER);
		hbxVBoxLabels.setPadding(new Insets(10));

		lblTooLow = new Label("Too Low");
		lblTooLow.setTextFill(Color.web("f4e913"));
		lblTooLow.setVisible(false);
		lblTooHigh = new Label("Too High");
		lblTooHigh.setTextFill(Color.web("#f4e913"));
		lblTooHigh.setVisible(false);
		lblCorrect = new Label("Correct");
		lblCorrect.setTextFill(Color.web("#f4e913"));
		lblCorrect.setVisible(false);

		HBox hbxResultLabels = new HBox(30);
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		hbxResultLabels.setBackground(background);
		hbxResultLabels.setMaxSize(300, 30);
		hbxResultLabels
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
		hbxResultLabels.setAlignment(Pos.CENTER);
		hbxResultLabels.setPadding(new Insets(10));
		hbxResultLabels.getChildren().addAll(lblTooLow, lblCorrect, lblTooHigh);

		lblEnterANum = new Label("Please enter a number");
		lblEnterANum.setTextFill(Color.web("#f4e913"));
		txtNumber = new TextField();
		txtNumber.setMaxSize(100, 10);
		// txtNumber.set

		HBox hbxEnterANum = new HBox(30);
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		hbxEnterANum.setBackground(background);
		hbxEnterANum.setMaxSize(300, 30);
		hbxEnterANum.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
		hbxEnterANum.setAlignment(Pos.CENTER);
		hbxEnterANum.setPadding(new Insets(10));
		hbxEnterANum.getChildren().addAll(lblEnterANum, txtNumber);

		btnSubmit = new Button("Submit");
		btnReset = new Button("Reset");
		btnQuit = new Button("Quit");

		btnSubmit.setOnAction(e -> {
			boolean isInt = isInt(txtNumber.getText());
			if (isInt == true)
				try {
					submit(tab, tb3, rf);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			else
				txtNumber.clear();
		});

		btnReset.setOnAction(e -> reset(tab, tb3));
		try {
			btnQuit.setOnAction(e -> w.close());
		} catch (NullPointerException e) {

		}

		HBox hbxButtons = new HBox(30);
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		hbxButtons.setBackground(background);
		hbxButtons.setMaxSize(300, 30);
		hbxButtons.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: black;");
		hbxButtons.setAlignment(Pos.CENTER);
		hbxButtons.setPadding(new Insets(10));
		hbxButtons.getChildren().addAll(btnQuit, btnReset, btnSubmit);

		VBox vbxResults = new VBox(10);
		vbxResults.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxResults.setMaxWidth(300);
		vbxResults.setMaxHeight(150);
		vbxResults.setAlignment(Pos.CENTER);
		vbxResults.setPadding(new Insets(10));
		// vbxTopLabels.setSpacing(10);
		vbxResults.getChildren().addAll(hbxResultLabels, hbxEnterANum, hbxButtons);

		VBox vbxAll = new VBox(10);
		vbxAll.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxAll.setMaxWidth(400);
		vbxAll.setMaxHeight(150);
		vbxAll.setAlignment(Pos.CENTER);
		vbxAll.setPadding(new Insets(10));
		// colour the background of vbxAll
		color = Color.web("#ccc108");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		vbxAll.setBackground(background);
		vbxAll.getChildren().addAll(hbxVBoxLabels, vbxResults);

		layout = new StackPane();
		StackPane.setAlignment(hbxVBoxLabels, Pos.TOP_CENTER);
		StackPane.setAlignment(vbxResults, Pos.BOTTOM_CENTER);
		// colour the background of the layout
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		layout.setBackground(background);
		layout.setPadding(new Insets(10));
		layout.getChildren().addAll(vbxAll);
	}

	public StackPane getLayout() {
		return layout;
	}

	public int randomNum() {
		Random dice = new Random();
		int number;
		number = 1 + dice.nextInt(100);
		System.out.println("Num " + number);
		return number;
	}

	public void submit(Tab t, Tab3 tb3, ReadFile2 rf) throws IOException {
		tb3.getListView().getItems().clear();
		lblTooLow.setVisible(false);
		lblTooHigh.setVisible(false);
		lblCorrect.setVisible(false);
		if (count <= 6) {
			count++;
			System.out.println("txtNum " + txtNumber.getText());
			System.out.println("randNum " + randomNumber);
			int aNum = Integer.parseInt(txtNumber.getText());
			if (aNum == randomNumber) {
				lblCorrect.setVisible(true);
				altbx.display("Congratulations", "Well done, you have won a four * prize");
				btnSubmit.setDisable(true);
				t.setDisable(false);
				tb3.setTab3Content(starPrize, rf);

			} else if (aNum < randomNumber) {
				lblTooLow.setVisible(true);
				lblTooLow.setTextFill(Color.web("#f4e913"));
			} else {
				lblTooHigh.setVisible(true);
				lblTooHigh.setTextFill(Color.web("#f4e913"));
			}
		} else {
			Alert noMore = new Alert(AlertType.INFORMATION);
			noMore.setTitle("No More goes");
			noMore.setHeaderText("Information Alert");
			String s = "You have failed; click reset or quit";
			noMore.setContentText(s);
			noMore.show();
		}
		txtNumber.clear();
		txtNumber.requestFocus();
	}

	public void reset(Tab t, Tab3 tb) {
		lblTooLow.setVisible(false);
		lblTooHigh.setVisible(false);
		lblCorrect.setVisible(false);
		txtNumber.clear();
		randomNumber = randomNum();
		btnSubmit.setDisable(false);
		tb.getLblPrize().setText("Prize");
		t.setDisable(true);
		count = 1;
	}

	public boolean isInt(String number) {
		try {
			int num = Integer.parseInt(number);
			if(num < 0 || num > 100) {
				System.out.println("Num is outside range");
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
