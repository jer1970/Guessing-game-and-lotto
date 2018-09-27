/**
 * @author jeremy staunton
 * studentId r00158317
 * 
 */
package application;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Tab2 {
	private Label lblLottery;
	private Label lblSelect;
	private Label lblMatchSix;
	private Label lblMatchFive;
	private Label lblMatchFour;
	private Label lblLottoResults;
	private Button btnPlay;
	private int total = 0;
	private StackPane layout;
	private AlertBox altbx = new AlertBox();
	private Button[] button;
	private Label[] lblLotto;
	private int[] yourChoice = new int[6];
	private int matches;
	private Label lblCongrats;
	private Label lblInfo;
	// hash to store the choice of numbers
	private Hashtable<Button, Integer> hash = new Hashtable<Button, Integer>();

	public void start(Tab tab, Tab3 tb, ReadFile2 rf) {
		tab.setDisable(true);
		/*
		 * create instruction labels and vbox to display them
		 */
		lblLottery = new Label("Lottery Game");
		lblLottery.setTextFill(Color.web("#cc1818"));
		lblLottery.setScaleX(2);
		lblLottery.setScaleY(2);
		lblSelect = new Label("Select 6 numbers");
		lblSelect.setTextFill(Color.web("#f4e913"));
		lblMatchSix = new Label("Match all six and win a 6 * prize");
		lblMatchSix.setTextFill(Color.web("#f4e913"));
		lblMatchFive = new Label("Match five and win a 5 * prize");
		lblMatchFive.setTextFill(Color.web("#f4e913"));
		lblMatchFour = new Label("Match four and win a 4 * prize");
		lblMatchFour.setTextFill(Color.web("#f4e913"));

		VBox vbxTopLabels = new VBox(10);
		vbxTopLabels.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxTopLabels.setMaxWidth(300);
		vbxTopLabels.setMaxHeight(150);
		vbxTopLabels.setAlignment(Pos.CENTER);
		vbxTopLabels.setPadding(new Insets(10));
		vbxTopLabels.getChildren().addAll(lblLottery, lblSelect, lblMatchSix, lblMatchFive, lblMatchFour);

		/*
		 * Create an array of Button type Objects, and action for the buttons
		 */
		int button_count = 48;
		button = new Button[button_count];
		// Use for loop to instantiate every button object
		for (int i = 0; i < button_count; i++) {
			button[i] = new Button("" + (i + 1));
			final int f = i;
			button[i].setStyle("-fx-base:pink;");
			button[i].setOnAction(e -> colorNPick(f));
		}

		/*
		 * create a gridpane for the buttons
		 */

		GridPane gridPane = new GridPane();
		// gridPane.setGridLinesVisible(true);
		Color color = Color.web("#4a0966");
		BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(fill);
		gridPane.setBackground(background);
		// Setting size for the pane
		gridPane.setMinSize(200, 200);
		// Setting the padding
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		// Setting the vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);

		/*
		 * add buttons to the gridpane
		 */
		int j = 0;// counts the buttons 0 to 47
		int stop = 8;// limits each row to 8 columns
		// loop produces 6 rows
		for (int i = 0; i < 6; i++) {
			int column = 0;
			while (j < stop) {
				gridPane.add(button[j], column, i);
				j++;
				column++;
			}
			stop += 8;
		}
		/*
		 * btnPlay
		 */
		btnPlay = new Button("Play");
		btnPlay.setOnAction(e -> play(tab, tb, rf));

		/*
		 * two labels for congratulations and info on a playing again
		 */
		lblCongrats = new Label("Let's see how you do");
		lblCongrats.setStyle("-fx-text-fill: #b72c2c;");
		lblInfo = new Label();
		lblInfo.setStyle("-fx-text-fill: #d13232;");

		/*
		 * vbox to hold the congrats and info labels
		 */
		VBox vbxCongratsInfo = new VBox(10);
		vbxCongratsInfo
				.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxCongratsInfo.setMinWidth(250);
		vbxCongratsInfo.setMaxHeight(150);
		vbxCongratsInfo.setAlignment(Pos.CENTER);
		vbxCongratsInfo.setPadding(new Insets(10));
		vbxCongratsInfo.getChildren().addAll(lblCongrats, lblInfo);
		/*
		 * create hbox for user action buttons play
		 */
		HBox hbxPlayReset = new HBox(8);
		hbxPlayReset.setMaxSize(400, 15);
		hbxPlayReset.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		hbxPlayReset.getChildren().addAll(btnPlay, vbxCongratsInfo);
		hbxPlayReset.setAlignment(Pos.CENTER);
		hbxPlayReset.setPadding(new Insets(10));

		/*
		 * create labels for displaying lotto numbers
		 */
		lblLottoResults = new Label("Lotto Results");
		lblLottoResults.setStyle("-fx-text-fill: #d13232;");
		lblLotto = new Label[6];
		for (int i = 0; i < lblLotto.length; i++) {
			lblLotto[i] = new Label();
			lblLotto[i].setText("xx");
			lblLotto[i].setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1;" + "-fx-border-color: green;"
					+ "-fx-background-color: white;");
		}

		/*
		 * create hbox for lottery numbers' labels
		 */
		HBox hbxLottoNums = new HBox(8);
		hbxLottoNums.setMaxSize(200, 15);
		hbxLottoNums.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		for (int i = 0; i < lblLotto.length; i++)
			hbxLottoNums.getChildren().add(lblLotto[i]);
		hbxLottoNums.setAlignment(Pos.CENTER);
		hbxLottoNums.setPadding(new Insets(10));

		/*
		 * create hbox for label and lotto nums hbox
		 */
		HBox hbxResults = new HBox(30);
		hbxResults.setMaxSize(300, 15);
		hbxResults.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: red;");
		hbxResults.getChildren().addAll(lblLottoResults, hbxLottoNums);
		hbxResults.setAlignment(Pos.CENTER);
		hbxResults.setPadding(new Insets(10));

		/*
		 * vbox for all sections
		 */
		VBox vbxAll = new VBox(10);
		vbxAll.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: green;");
		vbxAll.setMaxWidth(400);
		vbxAll.setMaxHeight(150);
		vbxAll.setAlignment(Pos.CENTER);
		vbxAll.setPadding(new Insets(10));
		vbxAll.getChildren().addAll(vbxTopLabels, gridPane, hbxPlayReset, hbxResults);

		layout = new StackPane();
		// colour the background of the layout
		color = Color.web("#151382");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		layout.setBackground(background);
		layout.setPadding(new Insets(10));
		layout.getChildren().add(vbxAll);

	}

	public void colorNPick(int i) {
		// check is the button already a winning num from a previous game, if
		// yes, reset colour to pink, reduce matches by one and total (numbers
		// picked) by one
		// remove from the hashmap of selected numbers
		if (button[i].getStyle().contains("-fx-base:green;")) {
			button[i].setStyle("-fx-base:pink;");
			matches--;
			total--;
			hash.remove(button[i ]);
			// once winning nums have been deselected a player is allowed to
			// play again
			if (matches == 0)
				btnPlay.setDisable(false);
		} else
		// to deselect a number
		if (button[i].getStyle().contains("-fx-base:red;")) {
			button[i].setStyle("-fx-base:pink;");
			total--;
			hash.remove(button[i ]);
		} else {
			// as long as this button is not the 7th selection, change the
			// colour to red and increment total by 1, and store the choice in
			// the hashmap
			if (total <= 5) {
				button[i].setStyle("-fx-base:red;");
				total++;
				hash.put(button[i ], i + 1);
			} else
				altbx.display("enough's nuf", "only 6 allowed");
		}
	}

	public void play(Tab tab, Tab3 tb, ReadFile2 rf) {
		tab.setDisable(true);
		tb.getLblPrize().setText("Prize");
		int[] random = new int[6];
		// check that 6 numbers are picked
		if (total < 6)
			altbx.display("Try again", "You must select 6 numbers");
		else {
			// generate 6 random numbers
			random[0] = randomNum();
			for (int i = 1; i < random.length; i++) {
				int count = i - 1;
				random[i] = randomNum();
				while (count >= 0) {
					if (random[i] == random[count]) {
						System.out.println("match");
						random[i] = randomNum();
						count = i - 1;
					} else
						count--;
				}
			}

			/*
			 * sort the results in ascending order and fill results labels with
			 * random nums
			 */
			int[] sortedResult = insertionSort(random);
			for (int j = 0; j < sortedResult.length; j++) {
				lblLotto[j].setText("" + sortedResult[j]);
			}

			/*
			 * sort your choice in ascending order
			 * 
			 */
			// retrieve your selection from the hashmap and put in an array
			int choiceIndex = 0;
			for (Button btn : hash.keySet()) {
				yourChoice[choiceIndex] = hash.get(btn);
				choiceIndex++;
			}
			// sort your choice
			int[] sortedChoice = insertionSort(yourChoice);

			/*
			 * compare your choice with the lottery results
			 */
			int resultIndex = 0;
			matches = 0;
			choiceIndex = 0;
			while (resultIndex < 6 && choiceIndex < 6) {
				if (sortedChoice[choiceIndex] == sortedResult[resultIndex]) {
					button[sortedResult[resultIndex] - 1].setStyle("-fx-base:green;");
					matches++;
					resultIndex++;
					choiceIndex++;
				} else if (sortedChoice[choiceIndex] > sortedResult[resultIndex]) {
					resultIndex++;
				} else
					choiceIndex++;
			}
			System.out.println("matches = " + matches);

			// display message if matches >=1 for the purposes of this
			// assignment: otherwise you may have to wait too long for a match 4
			// or better
			if (matches >= 1) {
				btnPlay.setDisable(true);
				congratsLabel(matches, tab, tb, rf);
			}
		}
	}

	public StackPane getLayout() {
		return layout;
	}

	public int randomNum() {
		Random dice = new Random();
		int number;
		number = 1 + dice.nextInt(48);
		// System.out.println("Num " + number);
		return number;
	}

	public void congratsLabel(int stars, Tab t, Tab3 tb, ReadFile2 rf) {
		t.setDisable(false);
		lblCongrats.setText("Congratulations, you have matched " + (stars+3) +" numbers.");
		lblInfo.setText("Click on winning numbers to play again");
		switch (stars) {
		case 1:
		
			try {
				tb.setTab3Content(4, rf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
//			lblCongrats.setText("Congratulations, you have matched 5 numbers.");
//			lblInfo.setText("Click on winning numbers to play again");
			try {
				tb.setTab3Content(5, rf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
//			lblCongrats.setText("Congratulations, you have matched 6 numbers.");
//			lblInfo.setText("Click on winning numbers to play again");
			try {
				tb.setTab3Content(6, rf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	public int[] insertionSort(int[] m) {
		// -----------------------------
		// Output Variable --> InitialValue
		// -----------------------------
		int[] res = null;
		// -----------------------------
		// SET OF OPS
		// -----------------------------
		// -----------------------------
		// I. SCENARIO IDENTIFICATION
		// -----------------------------
		int scenario = 0;

		// Rule 1. MyList is empty
		if (m.length == 0)
			scenario = 1;
		else {
			// Rule 2. MyList is non-empty
			scenario = 2;
		}

		// -----------------------------
		// II. SCENARIO IMPLEMENTATION
		// -----------------------------
		switch (scenario) {

		// Rule 1. MyList is empty
		case 1:
			// 1. We create an empty list as a result
			res = new int[m.length];

			break;

		// Rule 2. MyList is non-empty
		case 2:
			// 1. We get the first element of MyList
			int e0 = m[0];

			// 2. We remove the first element from MyList we just checked
			int temp;
			int[] tempArray = new int[m.length - 1];
			for (int i = 0; i < m.length - 1; i++) {
				temp = m[i + 1];
				tempArray[i] = temp;
			}
			// 3. We recursively solve the smaller problem
			res = insertionSort(tempArray);
			// 4. We insert e0 into res in the proper order
			res = insertOrdered(e0, res);
			// 5. We also add the element back to m, so as to not to modify its
			// original state
			// m.addElement(0, e0);

			break;
		}

		// -----------------------------
		// Output Variable --> Return FinalValue
		// -----------------------------
		return res;
	}

	public int[] insertOrdered(int e, int[] m) {
		// -----------------------------
		// Output Variable --> InitialValue
		// -----------------------------
		int[] res = null;
		// -----------------------------
		// SET OF OPS
		// -----------------------------
		// -----------------------------
		// I. SCENARIO IDENTIFICATION
		// -----------------------------
		int scenario = 0;

		// Rule 1. MyList is empty
		if (m.length == 0)
			scenario = 1;
		else {
			// We get the first element of MyList
			int elem0 = m[0];

			// Rule 2. Elem is smaller or equal than first element of MyList
			if (e <= elem0)
				scenario = 2;
			// Rule 3. Elem is bigger than first element of MyList
			else
				scenario = 3;
		}

		// -----------------------------
		// II. SCENARIO IMPLEMENTATION
		// -----------------------------
		switch (scenario) {

		// Rule 1. MyList is empty
		case 1:
			// 1. We create the new list as a result
			res = new int[1];

			// 2. We add element to be the only element of MyList
			res[0] = e;
			break;

		// Rule 2. Elem is smaller or equal than first element of MyList
		case 2:
			// 1. We create the new list as a result
			res = new int[m.length + 1];
			// 2. We traverse all elements of m, adding them to the list
			int size = m.length - 1;
			while (size >= 0) {
				// 2.1. We access to the element in m2
				int auxE = m[size];

				// 2.2. We append the element to res
				res[size + 1] = auxE;
				// 2.3. We decrease the index of size
				size--;
			}

			// 3. We add the element e as the head of the list
			res[0] = e;
			break;

		// Rule 3. Elem is bigger than first element of MyList
		case 3:
			// 1. We get the first element of MyList
			int e0 = m[0];
			// 2. We remove the first element from MyList we just checked
			int[] tempArray = new int[m.length - 1];
			for (int i = 0; i < tempArray.length; i++) {
				tempArray[i] = m[i + 1];
			}
			// 3. We recursively solve the smaller problem
			res = insertOrdered(e, tempArray);
			int[] temp = new int[res.length + 1];
			// 4. We add e0 as the first element of the result
			for (int i = temp.length - 1; i > 0; i--)
				temp[i] = res[i - 1];
			temp[0] = e0;
			// 5. We also add the element back to m, so as to not to modify its
			// original state
			// m.addElement(0, e0);
			res = temp;
			break;
		}

		// -----------------------------
		// Output Variable --> Return FinalValue
		// -----------------------------
		return res;
	}
}
