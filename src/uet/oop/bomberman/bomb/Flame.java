package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.enemy.Ballom;
import uet.oop.bomberman.enemy.Enemy;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityArr;

public abstract class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        checkEnemy();
        checkBomb();
        checkBomber();
    }

    @Override
    public boolean checkWall() {
        for (Entity w : EntityArr.walls) {
            if (this.getX() == w.getX() && this.getY() == w.getY()) {
                this.setVisible(false);
                return true;
            }
        }
        return false;
    }

    public boolean checkBrick() {
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
        for (Enemy e : EntityArr.enemies) {
            if (this.intersects(e)) {
                e.setAlive(false);
            }
        }
    }

    @Override
    public boolean checkBomb() {
        for (Bomb bomb : EntityArr.bomberman.bombs) {
            if (this.intersects(bomb)) {
                bomb.setExploded(true);
                return true;
            }
        }
        return false;
    }

    protected void checkBomber() {
        if (this.intersects(EntityArr.bomberman))
            EntityArr.bomberman.setAlive(false);
    }
}
