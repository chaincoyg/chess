package hero.highlevel;

import hero.Fire;
import hero.Water;
import hero.base.FireBase;
import hero.base.Hero;
import hero.base.HeroType;
import hero.base.WaterBase;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;

public class SuperFire extends FireBase {

	public SuperFire(int x, int y, Color color) {
		super(x, y, color);
		this.type = HeroType.SUPERFIRE;
	}

	@Override
	public boolean canKill(int x, int y) {
		Hero hero = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).getHero();
		if(hero == null) {
			return false;
		}
		if(hero instanceof WaterBase) {
			return false;
		}
		return canKillDiagonal(x, y);
	}
	
	@Override
	public boolean canMove(int x, int y) {
		return canMoveDiagonal(x, y);
	}

	@Override
	public boolean canMoveDiagonal(int x, int y) {
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

		 if (consider.getType() != Cell.Type.OUTFIELD ) {
			if((this.getxPosition()-x==this.getyPosition()-y)||(x-this.getxPosition()==this.getyPosition()-y))
				if (consider.getHero()!= null && consider.getHero().getColor() != this.getColor())return true;	
		 }
		return false;
	}

}
