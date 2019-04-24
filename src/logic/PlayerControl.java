package logic;

import java.util.ArrayList;
import java.util.List;

import hero.Love;
import hero.Summoner;
import hero.base.Hero;
import hero.base.HeroType;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class PlayerControl {
	private List<Hero> heroes;
	private Color color;
	private Summoner summoner;
	private int numFire, numWater, numPlant, numLove;
	private Status status;
	private Hero selectedHeroToMove, selectedHeroToSummon, selectedSacrifice;
	
	public enum Status{
		NONE, SELECTHEROTOSUMMON, SELECTSACRIFICE, SELECTHEROTOMOVE, SUMMON, MOVE;
	}
	
	public PlayerControl(int x, int y, Color color) {
		heroes = new ArrayList<Hero>();
		numFire = 0;
		numWater = 0;
		numPlant = 0;
		numLove = 1;
		this.color = color;
		summoner = new Summoner(x, y, color);
		heroes.add(summoner);
		
	}
	public void summonHero(HeroType heroType) {
		
	}
	public Summoner getSummoner() {
		return summoner;
	}
	public List<Hero> getHeroes() {
		return heroes;
	}
	public Color getColor() {
		return color;
	}
	public int getNumFire() {
		return numFire;
	}
	public int getNumWater() {
		return numWater;
	}
	public int getNumPlant() {
		return numPlant;
	}
	public int getNumLove() {
		return numLove;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Status getStatus() {
		return status;
	}
	public Hero getSelectedHeroToMove() {
		return selectedHeroToMove;
	}
	public Hero getSelectedHeroToSummon() {
		return selectedHeroToSummon;
	}
	public Hero getSelectedSacrifice() {
		return selectedSacrifice;
	}
	public void setNumFire(int numFire) {
		this.numFire = numFire;
	}
	public void setNumWater(int numWater) {
		this.numWater = numWater;
	}
	public void setNumPlant(int numPlant) {
		this.numPlant = numPlant;
	}
	public void setNumLove(int numLove) {
		this.numLove = numLove;
	}
	public void setSelectedHeroToMove(Hero selectedHeroToMove) {
		this.selectedHeroToMove = selectedHeroToMove;
	}
	public void setSelectedSacrifice(Hero selectedSacrifice) {
		this.selectedSacrifice = selectedSacrifice;
	}
	
	
}
