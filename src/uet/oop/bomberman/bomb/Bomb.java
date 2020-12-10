package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity {
    private final List<Flame> left = new ArrayList<>();
    private final List<Flame> right = new ArrayList<>();
    private final List<Flame> up = new ArrayList<>();
    private final List<Flame> down = new ArrayList<>();
    public boolean passThrough = true;
    public List<Flame> flames = new ArrayList<>();
    public int timerEx = 0;
    private boolean isExploded = false;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        animate += Sprite.DEFAULT_SIZE / 10;
        int flameLength = Management.bomberman.getFlameLength();
        if (this.isExploded()) {
            this.animate += Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1
                    , Sprite.bomb_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
            if (this.timerEx == 1) {
                this.timerEx++;

                Flame flame;
                for (int i = 0; i <= flameLength; ++i) {
                    flame = new FlameVertical(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE + i
                            , Sprite.explosion_vertical.getFxImage());
                    if (!flame.checkBrick() && !flame.checkWall()) {
                        down.add(flame);
                        this.flames.add(flame);
                    } else {
                        break;
                    }
                }

                for (int i = 0; i <= flameLength; ++i) {
                    flame = new FlameVertical(getX() / Sprite.SCALED_SIZE, getY() / Sprite.SCALED_SIZE - i
                            , Sprite.explosion_vertical.getFxImage());
                    if (!flame.checkBrick() && !flame.checkWall()) {
                        up.add(flame);
                        this.flames.add(flame);
                    } else {
                        break;
                    }
                }

                for (int i = 0; i <= flameLength; ++i) {
                    flame = new FlameHorizontal(getX() / Sprite.SCALED_SIZE + i, getY() / Sprite.SCALED_SIZE
                            , Sprite.explosion_horizontal.getFxImage());
                    if (!flame.checkBrick() && !flame.checkWall()) {
                        right.add(flame);
                        this.flames.add(flame);
                    } else {
                        break;
                    }
                }

                for (int i = 0; i <= flameLength; ++i) {
                    flame = new FlameHorizontal(getX() / Sprite.SCALED_SIZE - i, getY() / Sprite.SCALED_SIZE
                            , Sprite.explosion_horizontal.getFxImage());
                    if (!flame.checkBrick() && !flame.checkWall()) {
                        left.add(flame);
                        this.flames.add(flame);
                    } else {
                        break;
                    }
                }
            }
        } else {
            if (this.timerEx == 0) {
                this.timerEx++;
                TimerTask bombEx = new TimerTask() {
                    @Override
                    public void run() {
                        setExploded(true);
                    }
                };
                if (!this.isExploded()) {
                    Timer timerEx = new Timer();
                    timerEx.schedule(bombEx, 2000);
                    Sound.play("bom_no");
                }
                TimerTask removeFlame = new TimerTask() {
                    @Override
                    public void run() {
                        Sound.play("BOM_11_M");
                        Management.removeBrick();
                        Management.removeBomb();
                        Management.removeEnemy();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(removeFlame, 2500L);
            }
            this.animate += Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1
                    , Sprite.bomb_2, animate, Sprite.DEFAULT_SIZE).getFxImage());
        }
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public List<Flame> getDown() {
        return down;
    }

    public List<Flame> getLeft() {
        return left;
    }

    public List<Flame> getRight() {
        return right;
    }

    public List<Flame> getUp() {
        return up;
    }
}
