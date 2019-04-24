package hero.hybrid;

import hero.Plant;
import hero.Water;
import hero.base.Hero;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;
import movement.DiagonalMoveable;

public class WaterFire extends Water implements DiagonalMoveable{

	public WaterFire(int x, int y, Color color) {
		super(x, y, color);
	}

	@Override
	public boolean canMove(int x, int y) {
		return super.canMove(x, y) || canMoveDiagonal(x,y);
	}

	@Override
	public boolean canKill(int x, int y) {
		Hero hero = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).getHero();
		if(hero == null) {
			return false;
		}
		if(hero instanceof Plant) {
			return false;
		}
		return canKillStraight(x, y) || canKillDiagonal(x, y);
	}

	@Override
	public boolean canMoveDiagonal(int x, int y) {
		// TODO Auto-generated method stub
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD) {

			for (int i = -1; i <= 1; i += 2)
				for (int j = -1; j <= 1; j += 2)
					if ((x == this.getxPosition() + i) && (y == this.getyPosition() + j)) {
						if (consider.getHero() == null) return true;
					}

			for (int i = 2; i <= 2; i++) {
					if (((x == this.getxPosition() + i) && (y == this.getyPosition() + i) && canMoveDiagonal(x - 1, y - 1))||
						((x == this.getxPosition() + i) && (y == this.getyPosition() - i) && canMoveDiagonal(x - 1, y + 1))||
						((x == this.getxPosition() - i) && (y == this.getyPosition() + i) && canMoveDiagonal(x + 1, y - 1))||
						((x == this.getxPosition() - i) && (y == this.getyPosition() - i) && canMoveDiagonal(x + 1, y + 1))) {
						if (consider.getHero() == null) return true;
					}
			}
		}
		return false;
	}

	@Override
	public boolean canKillDiagonal(int x, int y) {
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD && consider.getHero()!= null) {

			for (int i = -1; i <= 1; i += 2)
				for (int j = -1; j <= 1; j += 2)
					if ((x == this.getxPosition() + i) && (y == this.getyPosition() + j) 
							&& (consider.getHero().getColor()!= this.getColor())) {
							return true;
					}

			for (int i = 2; i <= 2; i++) {
				if (((x == this.getxPosition() + i) && (y == this.getyPosition() + i) && canMoveDiagonal(x - 1, y - 1))||
					((x == this.getxPosition() + i) && (y == this.getyPosition() - i) && canMoveDiagonal(x - 1, y + 1))||
					((x == this.getxPosition() - i) && (y == this.getyPosition() + i) && canMoveDiagonal(x + 1, y - 1))||
					((x == this.getxPosition() - i) && (y == this.getyPosition() - i) && canMoveDiagonal(x + 1, y + 1))){
						if (consider.getHero().getColor() != this.getColor())return true;
					}
			}
		}
	return false;
	}
}
