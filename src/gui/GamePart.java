package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.Field;
import main.Main;

public class GamePart extends StackPane {
	
	Field logicPane;
	FieldUI paintPane;
	
	public GamePart() {
		this.setPadding(new Insets(50));
		this.setPrefSize(1000, 1000);
		this.setAlignment(Pos.BOTTOM_RIGHT);
		Button mainMenu = new Button("Main Menu");
		mainMenu.setOnAction(e->{
			Main.gameScene.switchScene(Main.homeScreen);
		});
		this.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		logicPane = new Field();
		paintPane = new FieldUI(logicPane);
		this.getChildren().addAll(paintPane, logicPane, mainMenu);
	}
	
	public Field getLogicPane() {
		return logicPane;
	}
	
	public FieldUI getPaintPane() {
		return paintPane;
	}
	
}
