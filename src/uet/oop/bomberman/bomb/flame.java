package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class flame extends Entity {
    public flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public abstract void update();
}
