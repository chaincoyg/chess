package logic;


import gui.ActionPart;
import gui.FieldUI;
import gui.HeroButton;
import hero.base.Hero;
import hero.base.HeroType;
import hero.property.Sacrifice;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import main.Main;

public class GameRunner {
	private Field field;
	private FieldUI fieldUI;
	private ActionPart actionPart;
	private int currentRow, currentCol, turn;
	private PlayerControl playerOne, playerTwo, currentPlayer, anotherPlayer;
	public GameRunner() {
		playerOne = new PlayerControl(4, 1, Color.BLACK, PlayerControl.Status.NONE);
		playerTwo = new PlayerControl(4, 7, Color.WHITE, PlayerControl.Status.NONE);
		turn = 1;
		currentPlayer = playerOne;
		anotherPlayer = playerTwo;
		field = Main.gameScreen.getGamePart().getLogicPane();
		fieldUI = Main.gameScreen.getGamePart().getPaintPane();
		actionPart = Main.gameScreen.getActionPart();
		field.getCellAt(4, 1).setHero(playerOne.getSummoner());
		field.getCellAt(4, 7).setHero(playerTwo.getSummoner());
		actionPart.getSummonbtn().setOnAction(e->{
			clickSummonbtn();
		});
		actionPart.getMovebtn().setOnAction(e->{
			clickMovebtn();
		});
		fieldUI.render();
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
	
	public void clickCell(Cell cell) {
		field.setInitial();
		fieldUI.render();
		Hero hero = cell.getHero();
		if(currentPlayer.getStatus() == PlayerControl.Status.NONE || currentPlayer.getStatus() == PlayerControl.Status.SELECTHEROTOSUMMON) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Please Select Action First");
			alert.showAndWait();
		}
		else if(currentPlayer.getStatus() == PlayerControl.Status.SUMMON) {
			HeroType heroType = currentPlayer.getSelectedHeroToSummon();
			if(heroType == HeroType.FIRE || heroType == HeroType.WATER || heroType == HeroType.PLANT ) {
				if(hero != null) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("Please Select Cell to Summon");
					alert.showAndWait();
				}
				else if(((cell.getX() == currentPlayer.getSummoner().getxPosition()) && (cell.getY() == currentPlayer.getSummoner().getyPosition() + 1 || cell.getY() == currentPlayer.getSummoner().getyPosition() - 1))||
				   ((cell.getY() == currentPlayer.getSummoner().getyPosition()) && (cell.getX() == currentPlayer.getSummoner().getxPosition() + 1 || cell.getX() == currentPlayer.getSummoner().getxPosition() - 1))) {
					currentPlayer.summonHero(cell.getX(), cell.getY());
					endTurn();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("Please Select CorrectCell");
					alert.showAndWait();
				}
						
			}
			else if(heroType == HeroType.LOVE){
				if(hero == null) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("Please Select Hero to Sacrifice");
					alert.showAndWait();
				}
				else if(hero.getColor() == currentPlayer.getColor() && hero instanceof Sacrifice) {
					currentPlayer.getHeroes().remove(hero);
					hero.die();
					currentPlayer.summonHero(cell.getX(), cell.getY());
					endTurn();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("This Hero cannot be Sacrifice");
					alert.showAndWait();
				}
			}
			else {
				if(hero == null) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("Please Select Hero to Sacrifice");
					alert.showAndWait();
				}
				else if(hero.getColor() == currentPlayer.getColor() && hero instanceof Sacrifice) {
					if(((Sacrifice)hero).canBeSacrifice(heroType)) {
						currentPlayer.setSelectedSacrifice(hero);
						currentPlayer.setStatus(PlayerControl.Status.SELECTSACRIFICE);
					}
					else {
						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setHeaderText("This Hero cannot be Sacrifice");
						alert.showAndWait();
					}
				}
				else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("This Hero cannot be Sacrifice");
					alert.showAndWait();
				}
			}
		}
		else if(currentPlayer.getStatus() == PlayerControl.Status.MOVE) {
			if(hero == null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Please Select Hero to Move");
				alert.showAndWait();
			}
			else {
				if(hero.getColor() != currentPlayer.getColor()) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("Please Select Correct Hero");
					alert.showAndWait();
				}
				else {
					hero.showMove();
					currentPlayer.setStatus(PlayerControl.Status.SELECTHEROTOMOVE);
					currentPlayer.setSelectedHeroToMove(hero);
				}
				
			}
		}
		else if(currentPlayer.getStatus() == PlayerControl.Status.SELECTHEROTOMOVE) {
			if(currentPlayer.getSelectedHeroToMove().canMove(cell.getX(), cell.getY())) {
				currentPlayer.getSelectedHeroToMove().move(cell.getX(), cell.getY());
				endTurn();
			}
			else if(currentPlayer.getSelectedHeroToMove().canKill(cell.getX(), cell.getY())) {
				anotherPlayer.getHeroes().remove(hero);
				currentPlayer.getSelectedHeroToMove().kill(cell.getX(), cell.getY());
				endTurn();
			}
			else {
				if(hero == null || hero.getColor() != currentPlayer.getColor()) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("Please Select Correct Cell");
					alert.showAndWait();
				}
				else {
					cell.getHero().showMove();
					currentPlayer.setStatus(PlayerControl.Status.SELECTHEROTOMOVE);
					currentPlayer.setSelectedHeroToMove(cell.getHero());
				}
			}
		}
		else if(currentPlayer.getStatus() == PlayerControl.Status.SELECTSACRIFICE) {
			HeroType heroType1 = currentPlayer.getSelectedHeroToSummon();
			Hero hero2 = currentPlayer.getSelectedSacrifice();
			if(hero == null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Please Select Hero to Sacrifice");
				alert.showAndWait();
			}
			else if(hero.getColor() != currentPlayer.getColor()){
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText("Please Select Correct Hero");
				alert.showAndWait();
			}
			else {
				if(currentPlayer.getSelectedSacrifice().equals(hero)) {
					currentPlayer.setStatus(PlayerControl.Status.SUMMON);
				}
				else if(hero instanceof Sacrifice) {
					if(((Sacrifice)hero).canBeSacrifice(heroType1, hero2.getHeroType())) {
						currentPlayer.getHeroes().remove(hero);
						currentPlayer.getHeroes().remove(hero2);
						hero.die();
						hero2.die();
						currentPlayer.summonHero(cell.getX(), cell.getY());
						endTurn();
					}
					else {
						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setHeaderText("Please Select Correct Hero");
						alert.showAndWait();
					}
				}
				else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText("This Hero cannot be Sacrifice");
					alert.showAndWait();
				}
			}
		}
	}
	
	public void clickHeroButton(HeroButton heroButton) {
		for(int i = 0; i < Main.gameScreen.getActionPart().getHeroButtonList().size(); i++) {
			Main.gameScreen.getActionPart().getHeroButtonList().get(i).unHilight();
		}
		Main.gameScreen.getActionPart().getMovebtn().unHilight();
		Main.gameScreen.getActionPart().getSummonbtn().unHilight();
		HeroType heroType = heroButton.getHeroType();
		field.setInitial();
		fieldUI.render();
		currentPlayer.setStatus(PlayerControl.Status.NONE);
		boolean check = false;
		if(heroType == HeroType.FIRE || heroType == HeroType.WATER || heroType == HeroType.PLANT ) {
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(i != 0 && j != 0)continue;
					if(i == 0 && j == 0)continue;
					if(field.getCellAt(currentPlayer.getSummoner().getxPosition() + i, currentPlayer.getSummoner().getyPosition() + j).getHero() == null) {
						check = true;
					}
				}
			}
		}
		for(int i = 0; i < currentPlayer.getHeroes().size(); i++) {
			if(check)break;
			for(int j = 0; j < i; j++) {
				if(currentPlayer.getHeroes().get(i) instanceof Sacrifice) {
					if(heroType == HeroType.LOVE) {
						check = true;
						break;
					}
					if(((Sacrifice)(currentPlayer.getHeroes().get(i))).canBeSacrifice(heroType, currentPlayer.getHeroes().get(j).getHeroType())){
						check = true;
						break;
					}
				}
			}
		}
		if(check) {
			heroButton.hilight();
			currentPlayer.setStatus(PlayerControl.Status.SELECTHEROTOSUMMON);
			currentPlayer.setSelectedHeroToSummon(heroType);
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Hero Cannot Summon");
			alert.showAndWait();
		}
	}
	
	public void clickMovebtn() {
		Main.gameScreen.getActionPart().getMovebtn().hilight();
		Main.gameScreen.getActionPart().getSummonbtn().unHilight();
		for(int i = 0; i < Main.gameScreen.getActionPart().getHeroButtonList().size(); i++) {
			Main.gameScreen.getActionPart().getHeroButtonList().get(i).unHilight();
		}
		field.setInitial();
		fieldUI.render();
		currentPlayer.setStatus(PlayerControl.Status.MOVE);
	}
	
	public void clickSummonbtn() {
		Main.gameScreen.getActionPart().getMovebtn().unHilight();
		field.setInitial();
		fieldUI.render();
		if(currentPlayer.getStatus() != PlayerControl.Status.SELECTHEROTOSUMMON) {
			currentPlayer.setStatus(PlayerControl.Status.NONE);
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Please Select Hero To Summon");
			alert.showAndWait();
		}
		else {
			currentPlayer.setStatus(PlayerControl.Status.SUMMON);
			Main.gameScreen.getActionPart().getSummonbtn().hilight();
		}
	}
	
	public void endTurn() {
		for(int i = 0; i < Main.gameScreen.getActionPart().getHeroButtonList().size(); i++) {
			Main.gameScreen.getActionPart().getHeroButtonList().get(i).unHilight();
		}
		Main.gameScreen.getActionPart().getMovebtn().unHilight();
		Main.gameScreen.getActionPart().getSummonbtn().unHilight();
		this.field.setInitial();
		this.fieldUI.render();
		if(anotherPlayer.getSummoner().isDie()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			if(turn % 2 == 1)alert.setHeaderText("Player one Win");
			else alert.setHeaderText("Player two Win");
			alert.showAndWait();
			Main.gameScene.switchScene(Main.homeScreen);
			Main.gameScreen = null;
			Main.gameRunner = null;
		}
		turn++;
		if(turn%2 == 1) {
			currentPlayer = playerOne;
			anotherPlayer = playerTwo;
		}
		else {
			currentPlayer = playerTwo;
			anotherPlayer = playerOne;
		}
		playerOne.setStatus(PlayerControl.Status.NONE);
		playerTwo.setStatus(PlayerControl.Status.NONE);
	}
	
}
