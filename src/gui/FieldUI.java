package gui;

import hero.Fire;
import hero.Love;
import hero.Plant;
import hero.Summoner;
import hero.Water;
import hero.base.Hero;
import hero.hybrid.FirePlant;
import hero.hybrid.PlantWater;
import hero.hybrid.WaterFire;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Field;

public class FieldUI extends Canvas {
	
	GraphicsContext gc;
	Field field;
	
	public FieldUI(Field field) {	
		gc = this.getGraphicsContext2D();
		this.field = field;
		this.setHeight(900);
		this.setWidth(900);
		render();
	}
	
	public void render() {
		gc.clearRect(0, 0, 900, 900);
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(i<=3&&Math.abs(j-4)>i)continue;
				else if(i>3&&Math.abs(j-4)>(8-i))continue;
				gc.setFill(field.getCellAt(i, j).getColor());
				gc.fillRect(j * 100, i * 100, 100, 100);
				Hero hero = field.getCellAt(i, j).getHero(); 
				if(hero != null) {
					if(hero instanceof Fire) {
						gc.setFill(Color.RED);
					}
					else if(hero instanceof Water) {
						gc.setFill(Color.BLUE);
					}
					else if(hero instanceof Plant) {
						gc.setFill(Color.GREEN);
					}
					else if(hero instanceof Love) {
						gc.setFill(Color.PINK);
					}
					else if(hero instanceof Summoner) {
						gc.setFill(Color.YELLOW);
					}
					gc.fillOval(j*100+5, i*100+5, 90, 90);
					gc.setFill(hero.getColor());
					gc.fillOval(j*100+10, i*100+10, 80, 80);
					if(hero instanceof FirePlant) {
						gc.setFill(Color.GREEN);
						gc.fillOval(j*100+25, i*100+25, 50, 50);
					}
					else if(hero instanceof WaterFire) {
						gc.setFill(Color.RED);
						gc.fillOval(j*100+25, i*100+25, 50, 50);
					}
					else if(hero instanceof PlantWater) {
						gc.setFill(Color.BLUE);
						gc.fillOval(j*100+25, i*100+25, 50, 50);
					}
					else if(hero instanceof Summoner) {
						gc.setFill(Color.PINK);
						gc.fillOval(j*100+25, i*100+25, 50, 50);
					}
					
				}
			}
		}
	}
	
}
