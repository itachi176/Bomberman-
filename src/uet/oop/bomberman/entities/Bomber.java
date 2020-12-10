package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {
    public List<Bomb> bombs = new ArrayList<>();
    private int speed = Sprite.SCALED_SIZE / 6;
    private int flameLength = 1;
    private boolean isAlive = true;
    private int numBombs = 1;
    private int time = 0;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.animate += Sprite.DEFAULT_SIZE / 10;
        if (!isAlive()) {
            this.setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2
                    , Sprite.player_dead3, animate, Sprite.DEFAULT_SIZE).getFxImage());
           // Sound.play("AA126_11");
            EntityArr.clearArr();
            CreateMap.createMapByLevel(BombermanGame.level);
        }
        if (checkPortal()) {
            if (this.time == 0) {
                this.time++;
                BombermanGame.level++;
                //System.out.println(BombermanGame.level);
                CreateMap.createMapByLevel(2);
            }
        }
    }

    public boolean checkPortal() {
        for (Entity portal : EntityArr.portals) {
            if (EntityArr.ballooms.size() != 0 || EntityArr.oneals.size() != 0) break;
            if (this.intersects(portal)) {
                return true;
            }
        }
        return false;
    }

    public void goLeft() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x -= 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.x += 1;
                if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                Sprite.player_left_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goRight() {
        for (int i = 1; i <= this.speed; ++i) {
            this.x += 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.x -= 1;
                if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                Sprite.player_right_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goUp() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y -= 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.y += 1;
                if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                Sprite.player_up_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
    }

    public void goDown() {
        for (int i = 1; i <= this.speed; ++i) {
            this.y += 1;
            if (checkBrick() || checkWall() || checkBomb()) {
                this.y -= 1;
                if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                    this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
                }
                break;
            }
        }
        setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                Sprite.player_down_2, this.animate, Sprite.DEFAULT_SIZE).getFxImage());
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


    private boolean duplicateBomb(Bomb bomb) {
        for (Bomb b : this.bombs) {
            if (b.getX() == bomb.getX() && b.getY() == bomb.getY()) {
                return true;
            }
        }
        for (Brick b : EntityArr.bricks) {
            if (b.getX() == bomb.getX() && b.getY() == bomb.getY()) {
                return true;
            }
        }

        return false;
    }

    public void putBomb() {
        int xBomb, yBomb;
        if (getX() % Sprite.SCALED_SIZE > Sprite.SCALED_SIZE / 3) {
            xBomb = (getX() / Sprite.SCALED_SIZE) + 1;
        } else {
            xBomb = (getX() / Sprite.SCALED_SIZE);
        }
        if (getY() % Sprite.SCALED_SIZE > Sprite.SCALED_SIZE / 3) {
            yBomb = (getY() / Sprite.SCALED_SIZE) + 1;
        } else {
            yBomb = (getY() / Sprite.SCALED_SIZE);
        }
        Bomb bomb = new Bomb(xBomb, yBomb, Sprite.bomb.getFxImage());

        if (!this.duplicateBomb(bomb)
                && getNumBombs() >= this.bombs.size() + 1) {
            this.bombs.add(bomb);
        }
    }

    @Override
    public boolean checkBomb() {
        for (Bomb e : EntityArr.bomberman.bombs) {
            double diffX = this.getX() - e.getX();
            double diffY = this.getY() - e.getY();
            if (!(diffX > -32 && diffX < 32 && diffY > -32 && diffY < 32)) {
                e.passThrough = false;
            }
            if (e.passThrough) return false;
            if (this.intersects(e)) return true;
        }
        return false;
    }

}
