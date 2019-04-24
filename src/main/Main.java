package main;



import gui.ActionPart;
import gui.GamePart;
import gui.scene.GameScene;
import gui.scene.GameScreen;
import gui.scene.HomeScreen;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.GameRunner;

public class Main extends Application {
	
	public static GameScene gameScene;
	public static GameScreen gameScreen;
	public static HomeScreen homeScreen;
	public static GameRunner gameRunner;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		homeScreen = new HomeScreen();
		gameScene = new GameScene(homeScreen);
		stage.setScene(gameScene);
		//stage.setResizable(false);
		stage.show();
	}
	
}
