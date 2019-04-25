package gui.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameRunner;
import main.Main;

public class HomeScreen extends StackPane {
	
	public HomeScreen() {
		super();
		this.setPrefSize(1600, 1000);
		this.setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
		Button newGamebtn  = new Button("New Game");
		newGamebtn.setPrefSize(160, 60);
		newGamebtn.setOnAction(e->{
			Main.gameScreen = new GameScreen();
			Main.gameScene.switchScene(Main.gameScreen);
			Main.gameRunner = new GameRunner();
		});
		Button resumeGamebtn  = new Button("Resume");
		resumeGamebtn.setPrefSize(160, 60);
		resumeGamebtn.setOnAction(e->{
			if(Main.gameScreen != null)Main.gameScene.switchScene(Main.gameScreen);
		});
		VBox button = new VBox(20);
		button.setAlignment(Pos.CENTER);
		button.getChildren().addAll(resumeGamebtn, newGamebtn);
		this.getChildren().add(button);
	}
}
