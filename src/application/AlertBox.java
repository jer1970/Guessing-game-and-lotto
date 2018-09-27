/**
 * @author jeremy staunton
 * studentId r00158317
 * 
 */
package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
	private Color color;
	private BackgroundFill fill;
	private Background background;

	public void display(String title, String message) {
		Stage window = new Stage();
		// Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(375);
		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		// colour the background of layout
		color = Color.web("#f9e904");
		fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		background = new Background(fill);
		layout.setBackground(background);
		layout.setMinHeight(70);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		// Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}