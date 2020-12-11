package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.item.BombItem;
import uet.oop.bomberman.entities.item.FameItem;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Brick extends Entity {
    private boolean isBroken = false;
    private int timeBroken = 0;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        //random item duoi cac bick.
        if (isBroken) {
            this.animate += Sprite.DEFAULT_SIZE / 10;
            this.setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                    , Sprite.brick_exploded2, animate, Sprite.DEFAULT_SIZE).getFxImage());
            if (timeBroken == 0) {
                this.timeBroken++;
                TimerTask createItem = new TimerTask() {
                    @Override
                    public void run() {
                        Item item;
                        Random random = new Random();
                        int num = random.nextInt(10);
                        if (num == 1) {
                            item = new BombItem(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                                    , Sprite.powerup_bombs.getFxImage());
                        } else if (num == 2) {
                            item = new FameItem(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                                    , Sprite.powerup_flames.getFxImage());
                        } else if (num == 3) {
                            item = new SpeedItem(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
                                    , Sprite.powerup_speed.getFxImage());
                        } else {
                            item = null;
                        }
                        if (item != null) {
                            Management.items.add(item);
                            item.setVisible(true);
                        }
                    }
                };
                Timer timer = new Timer();
                timer.schedule(createItem, 300L);
            }
        }
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
