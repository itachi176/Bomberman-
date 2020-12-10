package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        if (isAlive()) {
            int diffX = getX() - Management.bomberman.getX();
            int diffY = getY() - Management.bomberman.getY();
            if (diffX > 0) {
                this.x -= this.getSpeedX();
            } else {
                this.x += this.getSpeedX();
            }

            if (diffY > 0) {
                this.y -= this.getSpeed();
            } else {
                this.y += this.getSpeed();
            }


            if (this.getSpeedX() > 0) {
                this.img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2
                        , Sprite.kondoria_right3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2
                        , Sprite.kondoria_left3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            }
        } else {
            this.img = Sprite.kondoria_dead.getFxImage();
        }

    }

}
