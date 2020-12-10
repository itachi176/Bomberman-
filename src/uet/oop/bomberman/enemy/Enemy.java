package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int speedY = 0;
    private int speed = 1;
    protected int speedX = this.speed;
    private boolean isAlive = true;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        this.animate += Sprite.DEFAULT_SIZE / 10;
        checkBoundBomber();
    }

    protected void checkBoundBomber() {
        if (Management.bomberman.intersects(this)) {
            Management.bomberman.setAlive(false);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public int getSpeedY() {
        return speedY;
    }

    protected void rdMove() {
        Random random = new Random();
        int num = random.nextInt(4);
        if (num == 0) {
            this.speedX = this.getSpeed();
            this.speedY = 0;
        }
        else if (num == 1) {
            this.speedX = this.getSpeed() * -1;
            this.speedY = 0;
        }
        else if (num == 2) {
            this.speedY = this.getSpeed();
            this.speedX = 0;
        }
        else {
            this.speedY = this.getSpeed() * -1;
            this.speedX = 0;
        }
    }


}
