package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityArr;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends Entity {
    private int speed = 1;

    protected int speedX = this.speed;

    protected int speedY = 0;

    private boolean isAlive = true;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    protected void go() {
    }
    @Override
    public void update(){
        this.animate += Sprite.DEFAULT_SIZE / 10;
        checkBoundBomber();
    }
    protected void checkBoundBomber() {
        if (EntityArr.bomberman.intersects(this)) {
            EntityArr.bomberman.setAlive(false);
        }
    }
    public void enemyDead() {
        this.setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3
                , this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

}
