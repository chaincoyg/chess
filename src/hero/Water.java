package hero;

import hero.base.Hero;
import hero.base.HeroType;
import hero.base.PlantBase;
import hero.base.WaterBase;
import hero.highlevel.SuperWater;
import hero.hybrid.WaterFire;
import hero.property.Sacrifice;
import hero.property.StraightMoveable;
import javafx.scene.paint.Color;
import logic.Cell;
import main.Main;

public class Water extends WaterBase implements StraightMoveable, Sacrifice {
	
	public Water(int x, int y, Color color) {
		super(x, y, color);
		this.type = HeroType.WATER;
	}
	
	@Override
	public boolean canMove(int x, int y) {
		return canMoveStraight(x, y);
	}
	
	@Override
	public boolean canKill(int x, int y) {
		Hero hero = Main.gameScreen.getGamePart().getLogicPane().getCellAt(x, y).getHero();
		if(hero == null) {
			return false;
		}
		if(hero instanceof PlantBase || hero instanceof SuperWater || hero instanceof WaterFire) {
			return false;
		}
		return canKillStraight(x, y);
	}

	@Override
	public boolean canMoveStraight(int x, int y) {
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

	@Override
	public boolean canBeSacrifice(HeroType heroType) {
		return heroType == HeroType.SUPERWATER || heroType == HeroType.WATERFIRE || heroType == HeroType.PLANTWATER;
	}

	@Override
	public boolean canBeSacrifice(HeroType heroType1, HeroType heroType2) {
		if(heroType2 == HeroType.FIRE && heroType1 == HeroType.WATERFIRE) {
			return true;
		}
		else if(heroType2 == HeroType.WATER && heroType1 == HeroType.SUPERWATER) {
			return true;
		}
		else if(heroType2 == HeroType.PLANT && heroType1 == HeroType.PLANTWATER) {
			return true;
		}
		return false;
	}

}
