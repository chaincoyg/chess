package hero.base;

import hero.property.StraightMoveable;
import javafx.scene.paint.Color;

public abstract class WaterBase extends Hero implements StraightMoveable {

	public WaterBase(int x, int y, Color color) {
		super(x, y, color);
	}

}
