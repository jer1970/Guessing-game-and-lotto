/**
 * @author jeremy staunton
 * studentId r00158317
 * 
 */
package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class Main extends Application {
	
	private Tab tab1 = new Tab();
	private Tab tab2 = new Tab();
	private Tab tab3 = new Tab();
	private Tab tab4 = new Tab();

	private ReadFile2 rf2 = new ReadFile2();
	private Serialize ser = new Serialize();
	private TheWinners winnerList;
	
	
	public Stage window;
	
	public Tab1 tb1;
	public Tab2 tb2;
	public Tab3 tb3 = new Tab3();
	public Tab4 tb4;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		rf2.read();
		winnerList = (TheWinners)ser.read();
		if(winnerList == null)
			winnerList = new TheWinners();
		//System.out.println("winnerlistObj "+winnerList.getWinnerList().get(0).getfName());
		window = primaryStage;
		window.setTitle("Assignment");
		
		TabPane tbPane = new TabPane();
		tab1.setText("Guessing Game");
		tb1 = new Tab1(); 
		tb1.start(this.window, tab3, tb3, rf2);//pass window to be able to use the quit btn in tab 1, tab3 to disable/enable tab3
        tab1.setContent(tb1.getLayout());
        tbPane.getTabs().add(tab1);
        
        tab2.setText("Lotto Cure");
        tb2 = new Tab2();
        tb2.start(tab3, tb3, rf2);
        tab2.setContent(tb2.getLayout());
        tbPane.getTabs().add(tab2);
        
        tab3.setText("Prizes");
        tab3.disableProperty();
        tb3.start(rf2);
        tab3.setContent(tb3.getLayout());
        tbPane.getTabs().add(tab3);
        
        
        tab4.setText("Winners");
        tb4 = new Tab4();
        tb4.start(tb3, winnerList);
        tab4.setContent(tb4.getLayout());
        tbPane.getTabs().add(tab4);        
        
		Scene scene = new Scene(tbPane, 400, 600);
		window.setScene(scene);
		window.show();
	}
	public void quit(){
		this.window.close();
	}
}