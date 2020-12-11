package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        //xet di chuyen cua ballom.
        if (isAlive()) {
            if (this.getSpeed() > 0) {
                this.x += this.getSpeed();
                this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2
                        , Sprite.balloom_right3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            } else {
                this.x += this.getSpeed();
                this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2
                        , Sprite.balloom_left3, this.animate, Sprite.DEFAULT_SIZE).getFxImage();
            }
            if (checkBounds() || checkBomb()) {
                this.setSpeed(getSpeed() * -1);//quay lai
            }
        } else {
            this.img = Sprite.balloom_dead.getFxImage();//cap nhat hoat anh chet cua ballom
            Sound.play("enemydie");
        }
    }

}
