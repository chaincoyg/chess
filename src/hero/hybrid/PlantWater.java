package hero.hybrid;

import hero.Fire;
import hero.Plant;
import hero.base.Hero;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;
import movement.StraightMoveable;

public class PlantWater extends Plant implements StraightMoveable {
	
	public PlantWater(int x, int y, Color color) {
		super(x, y, color);
	}

	@Override
	public boolean canMove(int x, int y) {
		return super.canMove(x, y) || canMoveStraight(x, y);
	}

	@Override
	public boolean canKill(int x, int y) {
		Hero hero = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).getHero();
		if(hero == null) {
			return false;
		}
		if(hero instanceof Fire) {
			return false;
		}
		return canKillSpread(x, y) || canKillStraight(x, y);
	}

	@Override
	public boolean canMoveStraight(int x, int y) {
		// TODO Auto-generated method stub
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD) {

			for (int i = -1; i <= 1; i += 2) {
				if (((x == this.getxPosition() + i) && (y == this.getyPosition()))||
					((x == this.getxPosition()) && (y == this.getyPosition() + i))){
					if (consider.getHero() == null)return true;
				}
			}

			for (int i = 2; i <= 2; i++) {
				if (((x == this.getxPosition() + i) && (y == this.getyPosition()) && canMoveStraight(x-1, y))||
					((x == this.getxPosition() - i) && (y == this.getyPosition()) && canMoveStraight(x+1, y))||
					((x == this.getxPosition()) && (y == this.getyPosition() + i) && canMoveStraight(x, y-1))||
					((x == this.getxPosition()) && (y == this.getyPosition() - i) && canMoveStraight(x, y+1))) {
					if (consider.getHero() == null)return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canKillStraight(int x, int y) {
		// TODO Auto-generated method stub
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD && consider.getHero()!= null) {

			for (int i = -1; i <= 1; i += 2) {
				if (((x == this.getxPosition() + i) && (y == this.getyPosition()))||
					((x == this.getxPosition()) && (y == this.getyPosition() + i))){
					if (consider.getHero().getColor()!= this.getColor())return true;
				}
			}

			for (int i = 2; i <= 2; i++) {
				if (((x == this.getxPosition() + i) && (y == this.getyPosition()) && canMoveStraight(x-1, y))||
					((x == this.getxPosition() - i) && (y == this.getyPosition()) && canMoveStraight(x+1, y))||
					((x == this.getxPosition()) && (y == this.getyPosition() + i) && canMoveStraight(x, y-1))||
					((x == this.getxPosition()) && (y == this.getyPosition() - i) && canMoveStraight(x, y+1))) {
					if (consider.getHero().getColor()!= this.getColor())return true;
				}
			}
		}
		return false;
	}

}
