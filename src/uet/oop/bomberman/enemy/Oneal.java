package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.enemy.enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends enemy {
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (isAlive()){
            if (checkBounds_bom()) {
                this.setSpeed(getSpeed() * -1);
            }
            if (this.getSpeed() > 0) {
                this.x += this.getSpeed();
                this.img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2
                        , Sprite.oneal_right3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.x += this.getSpeed();
                this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2
                        , Sprite.oneal_left3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            }
        }
        else {
            this.img = Sprite.oneal_dead.getFxImage();
        }

    }
}
