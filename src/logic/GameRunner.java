package logic;


import gui.ActionPart;
import gui.FieldUI;
import gui.GamePart;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Main;

public class GameRunner {
	private Field field;
	private FieldUI fieldUI;
	private ActionPart actionPart;
	private int currentRow, currentCol, turn;
	private PlayerControl playerOne;
	private PlayerControl playerTwo;
	public GameRunner() {
		playerOne = new PlayerControl(4, 1, Color.BLACK);
		playerTwo = new PlayerControl(4, 7, Color.WHITE);
		field = Main.gameScreen.getGamePart().getLogicPane();
		fieldUI = Main.gameScreen.getGamePart().getPaintPane();
		actionPart = Main.gameScreen.getActionPart();
		field.getCellAt(4, 1).setHero(playerOne.getSummoner());
		field.getCellAt(4, 7).setHero(playerTwo.getSummoner());
		for(int i = 0; i < 9; i++) {
			currentRow = i;
			for(int j = 0; j < 9; j++) {
				currentCol = j;
				if(i<=3&&Math.abs(j-4)>i)continue;
				else if(i>3&&Math.abs(j-4)>(8-i))continue;
				field.getCellAt(i, j).setOnMouseClicked(e->{
					this.Click(this.field.getCellAt(this.currentRow, this.currentCol));
				});
			}
		}
		fieldUI.render();
	}
	
	public void start(){
		turn = 1;
		while(true) {
			
		}
	}
	
	public Field getField() {
		return field;
	}

	public int getCurrentRow() {
		return currentRow;
	}
	
	public int getCurrentCol() {
		return currentCol;
	}
	
	public void Click(Cell cell) {
		PlayerControl p;
		if(turn%2 == 1) {
			p = this.playerOne;
		}
		else {
			p = this.playerTwo;
		}
		if(p.getStatus() == PlayerControl.Status.NONE || p.getStatus() == PlayerControl.Status.SELECTHEROTOSUMMON) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Please Select Action First");
		}
		else if(p.getStatus() == PlayerControl.Status.SUMMON) {
			
		}
		else if(p.getStatus() == PlayerControl.Status.MOVE) {
			if(cell.getHero() == null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Please Select Hero to Move");
			}
			else {
				cell.getHero().showMove();
				p.setStatus(PlayerControl.Status.SELECTHEROTOMOVE);
			}
		}
		else if(p.getStatus() == PlayerControl.Status.SELECTHEROTOMOVE) {
			
		}
		else if(p.getStatus() == PlayerControl.Status.SELECTSACRIFICE) {
			
		}
	}
	
}
