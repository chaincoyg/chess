package hero.hybrid;

import hero.Fire;
import hero.Water;
import hero.base.FireBase;
import hero.base.Hero;
import hero.base.HeroType;
import hero.base.WaterBase;
import hero.property.Sacrifice;
import hero.property.SpreadMoveable;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;

public class FirePlant extends FireBase implements SpreadMoveable {

	
	public FirePlant(int x, int y, Color color) {
		super(x, y, color);
		this.type = HeroType.FIREPLANT;
	}

	@Override
	public boolean canMove(int x, int y) {
		return canMoveDiagonal(x, y) || canMoveSpread(x,y);
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
		return canKillDiagonal(x, y) || canKillSpread(x, y);
	}
	
	@Override
	public boolean canMoveDiagonal(int x, int y) {
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

	@Override
	public boolean canMoveSpread(int x, int y) {
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD) {

			for (int i = -1; i <= 1; i += 2)
				for (int j = -2; j <= 2 ; j += 4) {
						if (((x == this.getxPosition() + j) && (y == this.getyPosition() + i) )||
							((x == this.getxPosition() + i) && (y == this.getyPosition() + j) )) {
							if (consider.getHero() == null) return true;
						}
				}
		}
		return false;
	}

	@Override
	public boolean canKillSpread(int x, int y) {
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD) {

			for (int i = -1; i <= 1; i += 2)
				for (int j = -2; i <= 2 ; i += 4) {
						if (((x == this.getxPosition() + j) && (y == this.getyPosition() + i) )||
							((x == this.getxPosition() + i) && (y == this.getyPosition() + j) )) {
							if (consider.getHero().getColor()!= this.getColor()) return true;
						}
				}
		}
		return false;
	}

	

}
