package hero.base;

import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;

public abstract class Hero {
	
	protected int xPosition, yPosition;
	
	protected HeroType type; 
	
	protected Color color;
	
	private boolean alive;
	
	public void showMove(){
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(canMove(i, j))Main.gameScreen.getGamePart().getLogicPane().getCellAt(i, j).setType(Cell.Type.MOVEABLE);
				if(canKill(i, j))Main.gameScreen.getGamePart().getLogicPane().getCellAt(i, j).setType(Cell.Type.KILLABLE);
			}
		}
		Main.gameScreen.getGamePart().getPaintPane().render();
	}
	
	public abstract boolean canMove(int x,int y);
	
	public Hero(int x, int y, Color color) {
		alive = true;
		this.xPosition = x;
		this.yPosition = y;
		this.color = color;
	}
	
	public void move(int x, int y) {
		Main.gameScreen.getGamePart().getLogicPane().getCellAt(xPosition, yPosition).setHero(null);
		Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).setHero(this);
		this.xPosition = x;
		this.yPosition = y;
	};
	
	public abstract boolean canKill(int x, int y);
	
	public void kill(int x, int y) {
		Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).getHero().die();
		move(x, y);
	}
	
	public void die() {
		Main.gameScreen.getGamePart().getLogicPane().getCellAt(this.xPosition, this.yPosition).setHero(null);
		this.alive = false;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	
	public Color getColor() {
		return color;
	}
	
	public HeroType getHeroType() {
		return type;
	}
	
	public boolean isDie() {
		return !alive;
	}
	
}
