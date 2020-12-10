package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            Management.bomberman.setSpeed(Management.bomberman.getSpeed() + Sprite.DEFAULT_SIZE / 12);
        }
    }
}
