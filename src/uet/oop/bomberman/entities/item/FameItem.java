package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.EntityArr;

public class FameItem extends Item {
    public FameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            EntityArr.bomberman.setFlameLength(EntityArr.bomberman.getFlameLength() + 1);
        }
    }
}
