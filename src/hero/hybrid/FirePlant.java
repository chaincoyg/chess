package hero.hybrid;

import hero.Fire;
import hero.Water;
import hero.base.Hero;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;
import movement.SpreadMoveable;

public class FirePlant extends Fire implements SpreadMoveable {

	
	public FirePlant(int x, int y, Color color) {
		super(x, y, color);
	}

	@Override
	public boolean canMove(int x, int y) {
		return super.canMove(x, y) || canMoveSpread(x,y);
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
		return canKillDiagonal(x, y) || canKillSpread(x, y);
	}

	@Override
	public boolean canMoveSpread(int x, int y) {
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD) {

			for (int i = -1; i <= 1; i += 2)
				for (int j = -2; i <= 2 ; i += 4) {
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
