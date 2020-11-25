package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private boolean isBroken = false;
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (isBroken) {
//            if (item != null) {
//                item.setVisible(true);
//            }
            this.animate += Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                    , Sprite.brick_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
    }
    public void setBroken(boolean broken) {
        isBroken = broken;
    }
    public boolean isBroken() {
        return isBroken;
    }

}
