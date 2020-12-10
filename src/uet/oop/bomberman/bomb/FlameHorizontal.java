package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class FlameHorizontal extends Flame {

    public FlameHorizontal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        this.animate += Sprite.DEFAULT_SIZE / 10;
        this.setImg(Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                , Sprite.explosion_horizontal2, animate, Sprite.DEFAULT_SIZE).getFxImage());
    }
}
