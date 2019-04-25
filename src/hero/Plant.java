package hero;

import hero.base.FireBase;
import hero.base.Hero;
import hero.base.HeroType;
import hero.base.PlantBase;
import hero.highlevel.SuperPlant;
import hero.hybrid.PlantWater;
import hero.property.Sacrifice;
import hero.property.SpreadMoveable;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;

public class Plant extends PlantBase implements SpreadMoveable, Sacrifice {
	
	public Plant(int x, int y, Color color) {
		super(x, y, color);
		this.type = HeroType.PLANT;
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
		if(hero instanceof FireBase || hero instanceof SuperPlant || hero instanceof PlantWater) {
			return false;
		}
		return canKillSpread(x, y);
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

	@Override
	public boolean canBeSacrifice(HeroType heroType) {
		return heroType == HeroType.SUPERPLANT || heroType == HeroType.PLANTWATER || heroType == HeroType.FIREPLANT;
	}

	@Override
	public boolean canBeSacrifice(HeroType heroType1, HeroType heroType2) {
		if(heroType2 == HeroType.FIRE && heroType1 == HeroType.FIREPLANT) {
			return true;
		}
		else if(heroType2 == HeroType.WATER && heroType1 == HeroType.PLANTWATER) {
			return true;
		}
		else if(heroType2 == HeroType.PLANT && heroType1 == HeroType.SUPERPLANT) {
			return true;
		}
		return false;
	}

}
