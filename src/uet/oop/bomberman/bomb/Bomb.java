package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    private int fLength;
    private boolean isExploded = false;
    private List<flame> fLeft = new ArrayList<>();
    private List<flame> fRight = new ArrayList<>();
    private List<flame> fUp = new ArrayList<>();
    private List<flame> fDown = new ArrayList<>();
    private List<flame> flames = new ArrayList<>();
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        fLength = BombermanGame.bomberman.getFlength();
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
        return fLength;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public List<flame> getfDown() {
        return fDown;
    }

    public List<flame> getFlames() {
        return flames;
    }

    public List<flame> getfLeft() {
        return fLeft;
    }

    public List<flame> getfRight() {
        return fRight;
    }

    public List<flame> getfUp() {
        return fUp;
    }

    public void add_flame() {
        flame flame;
        for (int i = 0; i < fLength; i++) {
            flame = new flame_v(getX() / Sprite.DEFAULT_SIZE, getY() / Sprite.DEFAULT_SIZE + 1 + i
                    , Sprite.explosion_vertical.getFxImage());
        }
    }
}
