package logic;

import hero.base.Hero;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.Main;

public class Cell extends StackPane{
	
	private Hero hero;
	private Color realColor, color;
	private int x, y;
	private Type type;
	
	public enum Type{
		NORMAL, MOVEABLE, KILLABLE, OUTFIELD;
	}
	
	public Cell(int x, int y, Color color, Type type) {
		super();
		this.setPrefSize(100, 100);
		this.realColor = color;
		this.color = color;
		this.x = x;
		this.y = y;
		this.type = type;
		this.hero = null;
		this.setOnMouseEntered(e->{
			this.color = Color.DIMGRAY;
			Main.gameScreen.getGamePart().getPaintPane().render();
		});
		this.setOnMouseExited(e->{
			this.color = this.realColor;
			Main.gameScreen.getGamePart().getPaintPane().render();
		});
		this.setOnMouseClicked(e->{
			if(this.type != Type.OUTFIELD) {
				Main.gameRunner.clickCell(this);
			}
		});
		if(!(x<=3&&Math.abs(y-4)>x)&&!(x>3&&Math.abs(y-4)>(8-x))) {
			this.setStyle("-fx-border-width: 1px;");
			this.setStyle("-fx-border-color: pink;");
		}
		
	}
	
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int  getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
