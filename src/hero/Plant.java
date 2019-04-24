package hero;

import hero.base.Hero;
import hero.highlevel.SuperPlant;
import hero.hybrid.PlantWater;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;
import movement.SpreadMoveable;

public class Plant extends Hero implements SpreadMoveable {
	
	public Plant(int x, int y, Color color) {
		super(x, y, color);
	}

	@Override
	public boolean canMove(int x, int y) {
		return canMoveSpread(x, y);
	}

	@Override
	public boolean canKill(int x, int y) {
		Hero hero = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).getHero();
		if(hero == null) {
			return false;
		}
		if(hero instanceof Fire || hero instanceof SuperPlant || hero instanceof PlantWater) {
			return false;
		}
		return canKillSpread(x, y);
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
