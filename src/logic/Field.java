package logic;


import java.util.ArrayList;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class Field extends GridPane {
	
	private static List<List<Cell> > field;
	private static Cell selectedCell;
	
	public Field() {
		super();
		this.setMinSize(900, 900);
		this.setMaxSize(900, 900);
		field = new ArrayList<List<Cell> >();
		for(int i=0; i<9; i++) {
			List<Cell> currentRow = new ArrayList<Cell>();
			for(int j=0; j<9; j++) {
				Cell cell;
				if((i+j)%2 == 1)cell = new Cell(i, j, Color.WHITE, Cell.Type.NORMAL);
				else cell = new Cell(i, j, Color.BLACK, Cell.Type.NORMAL);
				setConstraints(cell, j, i, 1, 1, HPos.CENTER, VPos.CENTER);
				if(i<=3&&Math.abs(j-4)>i)cell.setType(Cell.Type.OUTFIELD);
				else if(i>3&&Math.abs(j-4)>(8-i))cell.setType(Cell.Type.OUTFIELD);
				this.getChildren().add(cell);
				currentRow.add(cell);
			}
			field.add(currentRow);
		}
		//this.setStyle("-fx-border-width: 1px;");
		//this.setStyle("-fx-border-color: blue;");
		//this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		//this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setInitial() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(i<=3&&Math.abs(j-4)>i)this.getCellAt(i, j).setType(Cell.Type.OUTFIELD);
				else if(i>3&&Math.abs(j-4)>(8-i))this.getCellAt(i, j).setType(Cell.Type.OUTFIELD);
				else this.getCellAt(i, j).setType(Cell.Type.NORMAL);
			}
		}
	}
	
	public Cell getCellAt(int x, int y) {
		return field.get(x).get(y); 
	}
}
