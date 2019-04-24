package gui;

import hero.base.HeroType;
import javafx.scene.control.Button;

public class HeroButton extends Button {
	
	public HeroButton(HeroType heroType) {
		this.setPrefSize(120, 120);
		switch(heroType) {
			case Water : this.setText("Water"); break;
			case Fire : this.setText("Fire"); break;
			case Plant : this.setText("Plant"); break;
			case SuperWater : this.setText("SuperWater"); break;
			case SuperFire : this.setText("SuperFire"); break;
			case SuperPlant : this.setText("SuperPlant"); break;
			case WaterFire : this.setText("WaterFire"); break;
			case FirePlant : this.setText("FirePlant"); break;
			case PlantWater : this.setText("PlantWater"); break;
			case Love : this.setText("Love"); break;
			default :
		}
	}
	
}
