package gui.scene;

import gui.ActionPart;
import gui.GamePart;
import javafx.scene.layout.HBox;
import logic.GameRunner;
import main.Main;

public class GameScreen extends HBox {
	
	ActionPart actionPart;
	GamePart gamePart;
	
	public GameScreen() {
		super();
		actionPart = new ActionPart();
		gamePart = new GamePart();
		this.setPrefSize(1600, 1000);
		this.getChildren().addAll(actionPart, gamePart);
	}

	public ActionPart getActionPart() {
		return actionPart;
	}

	public GamePart getGamePart() {
		return gamePart;
	}
	
	
}
