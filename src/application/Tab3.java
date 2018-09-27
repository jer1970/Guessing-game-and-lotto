/**
 * @author jeremy staunton
 * studentId r00158317
 * 
 */
package application;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import myTree.Tree;


public class Tab3 extends Tab {
	private StackPane layout;

	private Label lblInstructions;
	private Label lblPrizeLevel;
	private ListView<String> listView;
	private Button btnSubmit;
	private Label lblCongrats;
	private Label lblPrize;
	private ReadFile rf = new ReadFile();
	//private ReadFile2 rf2 = new ReadFile2();
	private Tree<Prize> prizeTree;
	private String thePrize;
	private Color color;
	private BackgroundFill fill;
	private Background background;

	public void start(ReadFile2 rf) {

		lblInstructions = new Label("Click on an option and press Submit to claim your prize");
		lblInstructions.setTextFill(Color.web("#f4e913"));
		lblPrizeLevel = new Label("xxxxxxxxxxxx");
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		lblPrizeLevel
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: #151382;");
		lblPrizeLevel.setBackground(background);
		lblPrizeLevel.setTextFill(Color.web("#f4e913"));
		lblPrizeLevel.setScaleX(2);
		lblPrizeLevel.setScaleY(2);
		/*
		 * vBox for top labels
		 */
		VBox vbxToplabels = new VBox(10);
		// colour the background of hbxOListAndButton
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		vbxToplabels.setBackground(background);
		vbxToplabels.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxToplabels.setMaxWidth(400);
		vbxToplabels.setMaxHeight(150);
		vbxToplabels.setAlignment(Pos.CENTER);
		vbxToplabels.setPadding(new Insets(10));
		vbxToplabels.getChildren().addAll(lblInstructions, lblPrizeLevel);
		/*
		 * OList for displaying fruit
		 */
		listView = new ListView<String>();
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setMaxWidth(200);
		listView.setPadding(new Insets(10));
		listView.setOnMouseClicked(e -> {
			if (listView.hasProperties())
				btnSubmit.setDisable(false);
		});

		btnSubmit = new Button("Submit");
		color = Color.web("#FFFFFF");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		btnSubmit.setBackground(background);
		btnSubmit.setDisable(true);
		btnSubmit.setOnAction(e -> {
			showPrize();
			btnSubmit.setDisable(true);
		});
		/*
		 * hbox for the olist and submit button
		 */
		HBox hbxOListAndButton = new HBox(8);
		// colour the background of hbxOListAndButton
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		hbxOListAndButton.setBackground(background);
		hbxOListAndButton.setMaxSize(400, 110);
		hbxOListAndButton
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		hbxOListAndButton.getChildren().addAll(listView, btnSubmit);
		hbxOListAndButton.setAlignment(Pos.CENTER);
		hbxOListAndButton.setPadding(new Insets(10));

		lblCongrats = new Label("Congratuations, you have won");
		lblCongrats.setTextFill(Color.web("#f4e913"));
		lblPrize = new Label("Prize");
		lblPrize.setTextFill(Color.web("#f4e913"));
		/*
		 * vbox for the congrats and prize display labels
		 */
		VBox vbxCongratsLabels = new VBox(10);
		// colour the background of hbxOListAndButton
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		vbxCongratsLabels.setBackground(background);
		vbxCongratsLabels
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxCongratsLabels.setMaxWidth(400);
		vbxCongratsLabels.setMaxHeight(150);
		vbxCongratsLabels.setAlignment(Pos.CENTER_LEFT);
		vbxCongratsLabels.setPadding(new Insets(10));
		vbxCongratsLabels.getChildren().addAll(lblCongrats, lblPrize);

		/*
		 * vbox for all the other containers
		 */
		VBox vbxAll = new VBox(10);
		// colour the background of vbxAll
		color = Color.web("#ccc108");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		vbxAll.setBackground(background);
		vbxAll.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxAll.setMaxWidth(400);
		vbxAll.setMaxHeight(400);
		vbxAll.setAlignment(Pos.CENTER);
		vbxAll.setPadding(new Insets(10));
		vbxAll.getChildren().addAll(vbxToplabels, hbxOListAndButton, vbxCongratsLabels);

		// put in setStar??????????/

		setLayout(new StackPane());
		// colour the background of the layout
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		getLayout().setBackground(background);
		getLayout().setPadding(new Insets(10));
		getLayout().getChildren().add(vbxAll);

	}

	public Label getLblPrize() {
		return lblPrize;
	}

	public ListView<String> getListView() {
		return listView;
	}

	public void setTab3Content(int s, ReadFile2 rf) throws IOException {
		listView.getItems().clear();
		prizeTree = rf.getTree();
		lblCongrats.setText("Congratulations, you have matched "  + s + " numbers");
		lblPrizeLevel.setText(s + " Star Prizes");
		switch (s) {
		case 4:
			listView.getItems().addAll("Apple", "Pear", "Plum");
			break;
		case 5:
			listView.getItems().addAll("Banana", "Grape", "Peach");
			break;
		case 6:
			listView.getItems().addAll("Blueberry", "Tangerine");
			break;
		}
	}

	public void showPrize() {
		for (Prize prize : prizeTree) {
			if (prize.getKey().equals(listView.getSelectionModel().getSelectedItem()))
				thePrize = prize.getPrize();
		}
		listView.getItems().clear();
		lblPrize.setText("Prize: " + thePrize);
	}

	public StackPane getLayout() {
		return layout;
	}

	public void setLayout(StackPane layout) {
		this.layout = layout;
	}
	public String getPrize(){
		return thePrize;
	}

}
