package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        if (isAlive()) {
            if (this.getSpeedX() == 0) {
                this.y += this.getSpeedY();
                if (checkBounds() || checkBomb() || getY() % Sprite.SCALED_SIZE == 0) {
                    if (getY() % Sprite.SCALED_SIZE != 0) {
                        this.y -= this.getSpeedY();
                    }
                    this.randomVector();
                }
            } else {
                this.x += this.getSpeedX();
                if (checkBounds() || checkBomb() || getX() % Sprite.SCALED_SIZE == 0) {
                    if (getX() % Sprite.SCALED_SIZE != 0) {
                        this.x -= this.getSpeedX();
                    }
                    this.randomVector();
                }
            }

            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2
                        , Sprite.oneal_right3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2
                        , Sprite.oneal_left3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.img = Sprite.oneal_dead.getFxImage();
        }

    }

}
