package gui;

import java.util.ArrayList;
import java.util.List;

import hero.base.HeroType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SummonBox extends VBox {
	
	private List<HeroButton> heroButtonList;
	
	public SummonBox(String head, HeroType first, HeroType second, HeroType third) {
		heroButtonList = new ArrayList<HeroButton>();
		Label headLabel = new Label(head);
		headLabel.setFont(Font.font("Italic", FontWeight.BOLD, 15));
		HBox box = new HBox(60);
		box.setAlignment(Pos.CENTER);
		HeroButton one = new HeroButton(first);
		HeroButton two = new HeroButton(second);
		HeroButton three = new HeroButton(third);
		box.getChildren().addAll(one, two, three);
		heroButtonList.add(one);
		heroButtonList.add(two);
		heroButtonList.add(three);
		this.setPrefSize(600, 200);
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(headLabel, box);
	}
	
	public SummonBox(String head, HeroType first) {
		heroButtonList = new ArrayList<HeroButton>();;
		Label headLabel = new Label(head);
		headLabel.setFont(Font.font("Italic", FontWeight.BOLD, 15));
		HeroButton one = new HeroButton(first);
		this.setPrefSize(600, 200);
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(headLabel, one);
		heroButtonList.add(one);
	}
	
	public List<HeroButton> getHeroButtonList(){
		return heroButtonList;
	}
}
