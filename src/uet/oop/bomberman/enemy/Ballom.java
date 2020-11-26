package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Ballom extends enemy {
    public Ballom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (checkBounds()) {
                this.setSpeed(getSpeed() * -1);
            }
            if (this.getSpeed() > 0) {
                this.x += this.getSpeed();
                this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2
                        , Sprite.balloom_right3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.x += this.getSpeed();
                this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2
                        , Sprite.balloom_left3, this.x, Sprite.DEFAULT_SIZE).getFxImage();
            }
        }
        else {
            this.img = Sprite.balloom_dead.getFxImage();
        }
    }
}
