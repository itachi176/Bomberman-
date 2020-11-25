package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityArr;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    private int flameLength;
    private boolean isExploded = false;
    private List<Flame> fLeft = new ArrayList<>();
    private List<Flame> fRight = new ArrayList<>();
    private List<Flame> fUp = new ArrayList<>();
    private List<Flame> fDown = new ArrayList<>();
    private List<Flame> Flames = new ArrayList<>();
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        flameLength = EntityArr.bomberman.getFlameLength();
        if (this.isExploded()) {
            this.animate = this.animate + Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1
                    , Sprite.bomb_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
        else {
            this.animate = this.animate + Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1
                    , Sprite.bomb_2, animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
    }

    public boolean isExploded() {
        return isExploded;
    }

    public int getfLength() {
        return flameLength;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public List<Flame> getfDown() {
        return fDown;
    }

    public List<Flame> getFlames() {
        return Flames;
    }

    public List<Flame> getfLeft() {
        return fLeft;
    }

    public List<Flame> getfRight() {
        return fRight;
    }

    public List<Flame> getfUp() {
        return fUp;
    }

    public void addFlame() {
        Flame flame;
        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameV(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE + 1 + i
                    , Sprite.explosion_vertical.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fDown.add(flame);
                this.Flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameV(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE - 1 - i
                    , Sprite.explosion_vertical.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fUp.add(flame);
                this.Flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE + i + 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fRight.add(flame);
                this.Flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE - i - 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fLeft.add(flame);
                this.Flames.add(flame);
            } else {
                break;
            }
        }
    }
}
