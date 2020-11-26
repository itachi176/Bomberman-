package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.enemy.Ballom;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityArr;

public abstract class Flame extends Entity {
    private boolean isVisible = true;
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    protected boolean checkWall() {
        for (Entity w : EntityArr.walls) {
            if (this.getX() == w.getX() && this.getY() == w.getY()) {
                this.setVisible(false);
                return true;
            }
        }
        return false;
    }

    protected boolean checkBrick() {
        for (Brick b : EntityArr.bricks) {
            if (this.getX() == b.getX() && this.getY() == b.getY()) {
                this.setVisible(false);
                b.setBroken(true);
                return true;
            }
        }
        return false;
    }

    protected void checkEnemy() {
        for (Ballom b : EntityArr.balloms) {
            if (this.intersects(b)) {
                b.setAlive(false);
            }
        }
        for (Oneal b : EntityArr.oneals) {
            if (this.intersects(b)) {
                b.setAlive(false);
            }
        }
    }

    protected  void checkPlayer() {
        if (this.getX() == EntityArr.bomberman.getX() && this.getY() == EntityArr.bomberman.getY()){
            EntityArr.bomberman.setAlive(false);
        }
    }
}
