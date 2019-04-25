package main;




import gui.scene.GameScene;
import gui.scene.GameScreen;
import gui.scene.HomeScreen;
import hero.Fire;
import hero.Plant;
import hero.Water;
import hero.base.FireBase;
import hero.base.Hero;
import hero.base.PlantBase;
import hero.base.WaterBase;
import hero.highlevel.SuperFire;
import hero.highlevel.SuperPlant;
import hero.highlevel.SuperWater;
import hero.hybrid.FirePlant;
import hero.hybrid.PlantWater;
import hero.hybrid.WaterFire;
import hero.property.DiagonalMoveable;
import hero.property.Sacrifice;
import hero.property.SpreadMoveable;
import hero.property.StraightMoveable;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
	}	
	
}
