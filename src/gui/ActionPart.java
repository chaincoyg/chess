package gui;

import java.util.ArrayList;
import java.util.List;

import hero.base.HeroType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ActionPart extends VBox {
	
	private int selectedAction;
	private HeroType selectedHero;
	private ButtonBase summonbtn, movebtn;
	private List<HeroButton> heroButtonList;
	
	public ActionPart() {
		heroButtonList = new ArrayList<HeroButton>();
		SummonBox heroBox = new SummonBox("HERO", HeroType.FIRE, HeroType.WATER, HeroType.PLANT);
		SummonBox superHeroBox = new SummonBox("SUPER HERO", HeroType.SUPERFIRE, HeroType.SUPERWATER, HeroType.SUPERPLANT);
		SummonBox hybridBox = new SummonBox("HYBRID HERO",HeroType.FIREPLANT, HeroType.WATERFIRE, HeroType.PLANTWATER);
		SummonBox loveBox = new SummonBox("LOVE", HeroType.LOVE);
		for(int i = 0; i < heroBox.getHeroButtonList().size(); i++) {
			heroButtonList.add(heroBox.getHeroButtonList().get(i));
		}
		for(int i = 0; i < superHeroBox.getHeroButtonList().size(); i++) {
			heroButtonList.add(superHeroBox.getHeroButtonList().get(i));
		}
		for(int i = 0; i < hybridBox.getHeroButtonList().size(); i++) {
			heroButtonList.add(hybridBox.getHeroButtonList().get(i));
		}
		for(int i = 0; i < loveBox.getHeroButtonList().size(); i++) {
			heroButtonList.add(loveBox.getHeroButtonList().get(i));
		}
		HBox button = new HBox(50);
		button.setAlignment(Pos.CENTER);
		button.setPrefSize(600, 200);
		summonbtn = new ButtonBase("Summon");
		summonbtn.setPrefSize(150, 150);
		movebtn = new ButtonBase("Move");
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

	public ButtonBase getSummonbtn() {
		return summonbtn;
	}

	public ButtonBase getMovebtn() {
		return movebtn;
	}
	
	public List<HeroButton> getHeroButtonList(){
		return this.heroButtonList;
	}
	
}
