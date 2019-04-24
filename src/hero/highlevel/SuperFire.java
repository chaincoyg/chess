package hero.highlevel;

import hero.Fire;
import hero.Water;
import hero.base.Hero;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;

public class SuperFire extends Fire {

	public SuperFire(int x, int y, Color color) {
		super(x, y, color);
	}

	@Override
	public boolean canKill(int x, int y) {
		Hero hero = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).getHero();
		if(hero == null) {
			return false;
		}
		if(hero instanceof Water) {
			return false;
		}
		return canKillDiagonal(x, y);
	}

	@Override
	public boolean canMoveDiagonal(int x, int y) {
		// TODO Auto-generated method stub
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD) {
			if((this.getxPosition()-x==this.getyPosition()-y)||(x-this.getxPosition()==this.getyPosition()-y))
				if (consider.getHero() == null) return true;
		}
		return false;
	}

	@Override
	public boolean canKillDiagonal(int x, int y) {
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if((this.getxPosition()-x==this.getyPosition()-y)||(x-this.getxPosition()==this.getyPosition()-y))
			  if (consider.getType() != Cell.Type.OUTFIELD && consider.getHero()!= null) 
					if (consider.getHero().getColor() != this.getColor())return true;	
		return false;
	}
	
	
}
