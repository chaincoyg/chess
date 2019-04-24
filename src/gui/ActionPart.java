package gui;

import hero.base.HeroType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Main;

public class ActionPart extends VBox {
	
	private int selectedAction;
	private HeroType selectedHero;
	private Button summonbtn, movebtn;
	
	public ActionPart() {
		SummonBox heroBox = new SummonBox("HERO", HeroType.Fire, HeroType.Water, HeroType.Plant);
		SummonBox superHeroBox = new SummonBox("SUPER HERO", HeroType.SuperFire, HeroType.SuperWater, HeroType.SuperPlant);
		SummonBox hybridBox = new SummonBox("HYBRID HERO",HeroType.FirePlant, HeroType.WaterFire, HeroType.PlantWater);
		SummonBox loveBox = new SummonBox("LOVE", HeroType.Love);
		HBox button = new HBox(50);
		button.setAlignment(Pos.CENTER);
		button.setPrefSize(600, 200);
		summonbtn = new Button("Summon");
		summonbtn.setPrefSize(150, 150);
		movebtn = new Button("Move");
		movebtn.setPrefSize(150, 150);
		button.getChildren().addAll(summonbtn,movebtn);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(heroBox, superHeroBox, hybridBox, loveBox, button);
		this.setPrefSize(600, 1000);
		this.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public int getSelectedAction() {
		return selectedAction;
	}

	public HeroType getSelectedHero() {
		return selectedHero;
	}

	public Button getSummonbtn() {
		return summonbtn;
	}

	public Button getMovebtn() {
		return movebtn;
	}
	
}
