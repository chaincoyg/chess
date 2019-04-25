package hero.highlevel;

import hero.Fire;
import hero.Plant;
import hero.base.Hero;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;

public class SuperPlant extends Plant {

	public SuperPlant(int x, int y, Color color) {
		super(x, y, color);
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
		return canKillSpread(x, y);
	}

	@Override
	public boolean canMoveSpread(int x, int y) {
		// TODO Auto-generated method stub
		Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);

		if (consider.getType() != Cell.Type.OUTFIELD) {

			for (int i = -2; i <= 2; i++) {
				for (int j = -2; j <= 2; j++)
				{
					if ((x == this.getxPosition() + i) && (y == this.getyPosition() + j)) {
						if(1>=i&&i>=-1&&1>=j&&j>=-1) return false;
						else if (consider.getHero() == null)return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean canKillSpread(int x, int y) {
			// TODO Auto-generated method stub
			Cell consider = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y);
	
			if (consider.getType() != Cell.Type.OUTFIELD) {
	
				for (int i = -2; i <= 2; i++) {
					for (int j = -2; j <= 2; j++)
					{
						if ((x == this.getxPosition() + i) && (y == this.getyPosition() + j)) {
							if(1>=i&&i>=-1&&1>=j&&j>=-1) return false;
							else if (consider.getHero().getColor() != this.getColor())return true;
						}
					}
				}
			}
			return false;
		}
}
	
	
	

