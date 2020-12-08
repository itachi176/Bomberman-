package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityArr;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity {
    private int flameLength;

    private final List<Flame> fLeft = new ArrayList<>();
    private final List<Flame> fRight = new ArrayList<>();
    private final List<Flame> fUp = new ArrayList<>();
    private final List<Flame> fDown = new ArrayList<>();
    public boolean passThrough = true;


    public List<Flame> flames = new ArrayList<>();

    private boolean isExploded = false;

    public int timerEx = 0;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        animate += Sprite.DEFAULT_SIZE / 10;
        flameLength = EntityArr.bomberman.getFlameLength();
        if (this.isExploded()) {
            this.animate = this.animate + Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1
                    , Sprite.bomb_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
            if (this.timerEx == 1) {
                this.timerEx++;
                this.addFlame();
            }
        }
        else {
            if (this.timerEx == 0) {
                this.timerEx++;
                setTimeExploded();
            }
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
        return flames;
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
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameV(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE - 1 - i
                    , Sprite.explosion_vertical.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fUp.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE + i + 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fRight.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }

        for (int i = 0; i < flameLength; ++i) {
            flame = new FlameH(getX() / Sprite.SCALED_SIZE - i - 1, getY() / Sprite.SCALED_SIZE
                    , Sprite.explosion_horizontal.getFxImage());
            if (!flame.checkBrick() && !flame.checkWall()) {
                fLeft.add(flame);
                this.flames.add(flame);
            } else {
                break;
            }
        }
    }

    public void setTimeExploded() {
        Bomb bomb = this;
        TimerTask bombEx = new TimerTask() {
            @Override
            public void run() {
                bomb.setExploded(true);
            }
        };
        if (!this.isExploded()) {
            Timer timerEx = new Timer();
            timerEx.schedule(bombEx, 2000);
        }
        TimerTask removeFlame = new TimerTask() {
            @Override
            public void run() {
                EntityArr.removeBrick();
                EntityArr.removeBomb();
                EntityArr.removeEnemy();
            }
        };
        Timer timer = new Timer();
        timer.schedule(removeFlame, 2500L);
    }
}
