package gui.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;

public class GameScene extends Scene {
	
	private Parent currentScene; 
	
	public GameScene(Parent root) {
		super(root);
		this.currentScene = root;
	}
	
	public void switchScene(Parent scene) {
		Main.gameScene.setRoot(scene);
	}
	
	public Parent getScene() {
		return currentScene;
	}
	
}
