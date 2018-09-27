package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Tab4 {
	private StackPane layout;
	private Label lblWinners;
	private Button btnListByName;
	private Button btnListByPrize;
	private Button btnAddName;
	private Button btnRemoveName;
	private Button btnSaveChanges;
	private TextField txtFirstName;
	private Label lblAddName;
	private TextField txtLastName;
	private Label lblRemoveName;
	private Color color;
	private BackgroundFill fill;
	private Background background;
	private ListView<String> listView;
	private Winner winr;
	private TheWinners theWinners;
	private Serialize ser;
	private PrizeComparator myPrizeComparator;
	
	public void start(Tab3 tab, TheWinners tw) {
		ser = new Serialize();
		theWinners = tw;
		
		lblWinners = new Label("Winners");
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		lblWinners.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: #151382;");
		//lblWinners.setBackground(background);
		lblWinners.setTextFill(Color.web("#f4e913"));
		lblWinners.setScaleX(2);
		lblWinners.setScaleY(2);
		lblWinners.setPadding(new Insets(10));
		
		listView = new ListView<String>();
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.setMaxWidth(200);
		listView.setPadding(new Insets(10));
		fillListView();
		
		btnListByName = new Button("List by name");
		btnListByName.setOnAction(e -> listByName());

		
		btnListByPrize = new Button("List by Prize");
		btnListByPrize.setOnAction(e -> listByPrize());		
		
		lblAddName = new Label("First name:");
		lblRemoveName = new Label("Lastname:");
		txtFirstName = new TextField();
		txtFirstName.setMaxSize(100, 10);
		txtLastName = new TextField();
		txtLastName.setMaxSize(100, 10);
		
		HBox hbxAddWinner = new HBox(33);
		// colour the background of hbxOListAndButton
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		hbxAddWinner.setBackground(background);
		hbxAddWinner.setMaxSize(400, 110);
		hbxAddWinner
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		hbxAddWinner.getChildren().addAll(lblAddName, txtFirstName, lblRemoveName, txtLastName);
		hbxAddWinner.setAlignment(Pos.CENTER);
		hbxAddWinner.setPadding(new Insets(10));
		
		btnAddName = new Button("Add Name");
		btnAddName.setOnAction(e -> addName(tab));
		btnRemoveName = new Button("Remove Name");
		btnRemoveName.setOnAction(e -> removeName(tab));
		btnSaveChanges = new Button("Save Changes");
		btnSaveChanges.setOnAction(e -> saveChanges());
		
		
		HBox hbxRemoveWinner = new HBox(33);
		// colour the background of hbxOListAndButton
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		hbxRemoveWinner.setBackground(background);
		hbxRemoveWinner.setMaxSize(400, 110);
		hbxRemoveWinner
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		hbxRemoveWinner.getChildren().addAll(btnAddName, btnRemoveName, btnSaveChanges);
		hbxRemoveWinner.setAlignment(Pos.CENTER);
		hbxRemoveWinner.setPadding(new Insets(10));
		
		HBox hbxListButtons = new HBox(33);
		// colour the background of hbxOListAndButton
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		hbxListButtons.setBackground(background);
		hbxListButtons.setMaxSize(400, 110);
		hbxListButtons
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		hbxListButtons.getChildren().addAll(btnListByName, btnListByPrize);
		hbxListButtons.setAlignment(Pos.CENTER);
		hbxListButtons.setPadding(new Insets(10));

		
		VBox vbxAll = new VBox(15);
		// colour the background of hbxOListAndButton
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		vbxAll.setBackground(background);
		vbxAll.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxAll.setMaxWidth(400);
		vbxAll.setMaxHeight(500);
		vbxAll.setAlignment(Pos.CENTER);
		vbxAll.setPadding(new Insets(10));
		vbxAll.getChildren().addAll(lblWinners, listView, hbxAddWinner, hbxRemoveWinner, hbxListButtons );
		
		
		layout = new StackPane();
		// colour the background of the layout
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		layout.setBackground(background);
		layout.setPadding(new Insets(10));
		layout.getChildren().add(vbxAll);
	}

	public StackPane getLayout() {
		return layout;
	}

	private void addName(Tab3 tab) {
		winr = new Winner(txtFirstName.getText(), txtLastName.getText(), tab.getPrize());
		theWinners.add(winr);
		listView.getItems().addAll(winr.getfName() + " " + winr.getlName(), winr.getPrize(), "");
	}

	private void removeName(Tab3 tab){
		theWinners.remove(txtFirstName.getText(), txtLastName.getText());
		listView.getItems().remove(txtFirstName.getText() + " " + txtLastName.getText());
	}
	
	private Object saveChanges() {
		ser.write(theWinners);
		return null;
	}
	public void fillListView(){
		try{
			ArrayList<Winner> aw = theWinners.getWinnerList();
		for(int i =0; i < aw.size();i++){
			listView.getItems().addAll(aw.get(i).getfName() + " " + aw.get(i).getlName(), aw.get(i).getPrize(), "");
			System.out.println("name " + aw.get(i).getfName() + " " + aw.get(i).getlName());
		}
		}catch(NullPointerException e){
			System.out.println("nasddgasdg");
		}
	}
	
	private void listByName(){
		ArrayList<Winner> aw = theWinners.getWinnerList();
		Collections.sort(aw);
		listView.getItems().clear();
		for(Winner w: aw)
			listView.getItems().addAll(w.toString(), w.getPrize(), "");
	}
	
	private void listByPrize() {
		myPrizeComparator = new PrizeComparator();
		ArrayList<Winner> e = theWinners.getWinnerList();
		Collections.sort(e, myPrizeComparator);
		listView.getItems().clear();
		for(Winner w: e)
			listView.getItems().addAll(w.toString(), w.getPrize(), "");
	}
	
}
