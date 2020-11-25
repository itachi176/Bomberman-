package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bomber extends Entity {
    private int speed = Sprite.SCALED_SIZE / 4;
    private int flameLength = 1;
    private boolean isAlive = true;
    private int numBombs = 1;
    public List<Bomb> bombs = new ArrayList<>();

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public void goLeft() {
        this.x -= this.speed;
        if (checkBounds()) {
            this.x += this.speed;
        }
        setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                Sprite.player_left_2, this.getX(), Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goRight() {
        this.x += this.speed;
        if (checkBounds()) {
            this.x -= this.speed;
        }
        setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                Sprite.player_right_2, this.getX(), Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goUp() {
        this.y -= this.speed;
        if (checkBounds()) {
            this.y += this.speed;
        }
        setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, this.getY(), Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goDown() {
        this.y += this.speed;
        if (checkBounds()) {
            this.y -= this.speed;
        }
        setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                Sprite.player_down_2, this.getY(), Sprite.DEFAULT_SIZE).getFxImage());
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * xu ly gioi han di chuyen khi gap vat can.
     */
    public boolean checkBounds() {
        for (Entity e : walls) {
            if (this.intersects(e)) return true;
        }

        for (Entity e : bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public void setNumBombs(int numBombs) {
        this.numBombs = numBombs;
    }

    public int getFlameLength() {
        return flameLength;
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }

    public void removeBomb(Bomb x) {
        Iterator<Bomb> bombIterator = this.bombs.iterator();
        while (bombIterator.hasNext()) {
            Bomb bomb = bombIterator.next();
            if (bomb.getX() == x.getX() && bomb.getY() == x.getY()) {
                bombIterator.remove();
            }
        }
    }
}
