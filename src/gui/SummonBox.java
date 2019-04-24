package gui;

import hero.base.HeroType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SummonBox extends VBox {
	
	public SummonBox(String head, HeroType first, HeroType second, HeroType third) {
		Label headLabel = new Label(head);
		headLabel.setFont(Font.font("Italic", FontWeight.BOLD, 15));
		HBox box = new HBox(60);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(new HeroButton(first), new HeroButton(second), new HeroButton(third));
		this.setPrefSize(600, 200);
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(headLabel, box);
	}
	
	public SummonBox(String head, HeroType first) {
		Label headLabel = new Label(head);
		headLabel.setFont(Font.font("Italic", FontWeight.BOLD, 15));
		this.setPrefSize(600, 200);
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(headLabel, new HeroButton(first));
	}
}
