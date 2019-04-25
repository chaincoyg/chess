package gui;

import hero.base.HeroType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import main.Main;

public class HeroButton extends ButtonBase {
	
	private HeroType type;
	
	public HeroButton(HeroType heroType) {
		super();
		this.setPrefSize(120, 120);
		this.type = heroType;
		switch(heroType) {
			case WATER : this.setText("Water"); break;
			case FIRE : this.setText("Fire"); break;
			case PLANT : this.setText("Plant"); break;
			case SUPERWATER : this.setText("SuperWater"); break;
			case SUPERFIRE : this.setText("SuperFire"); break;
			case SUPERPLANT : this.setText("SuperPlant"); break;
			case WATERFIRE : this.setText("WaterFire"); break;
			case FIREPLANT : this.setText("FirePlant"); break;
			case PLANTWATER : this.setText("PlantWater"); break;
			case LOVE : this.setText("Love"); break;
			default :
		}
		this.setOnAction(e->{
			Main.gameRunner.clickHeroButton(this);
		});
	}
	
	public HeroType getHeroType() {
		return type;
	}
	
}
