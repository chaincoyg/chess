package hero.base;

import hero.Fire;
import hero.Love;
import hero.Plant;
import hero.Water;
import hero.highlevel.SuperFire;
import hero.highlevel.SuperPlant;
import hero.highlevel.SuperWater;
import hero.hybrid.FirePlant;
import hero.hybrid.PlantWater;
import hero.hybrid.WaterFire;
import javafx.scene.paint.Color;

public enum HeroType {
	FIRE,
	WATER,
	PLANT,
	SUPERFIRE,
	SUPERWATER,
	SUPERPLANT,
	FIREPLANT,
	WATERFIRE,
	PLANTWATER,
	LOVE,
	SUMMONER;
	
	public Hero toHero(int x, int y, Color color) {
		switch(this) {
			case FIRE : return new Fire(x, y, color);
			case WATER : return new Water(x, y, color);
			case PLANT : return new Plant(x, y, color);
			case SUPERFIRE : return new SuperFire(x, y, color);
			case SUPERWATER : return new SuperWater(x, y, color);
			case SUPERPLANT : return new SuperPlant(x, y, color);
			case FIREPLANT : return new FirePlant(x, y, color);
			case WATERFIRE : return new WaterFire(x, y, color);
			case PLANTWATER : return new PlantWater(x, y, color);
			default : return new Love(x, y, color); 
		}
	}

}
