package uet.oop.bomberman.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.enemy.Enemy;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Management;

public abstract class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        for (Enemy e : Management.ballooms) {
            if (this.intersects(e)) {
                e.setAlive(false);
            }
        }
        for (Enemy e : Management.oneals) {
            if (this.intersects(e)) {
                e.setAlive(false);
            }
        }
        for (Enemy e : Management.dolls) {
            if (this.intersects(e)) {
                e.setAlive(false);
            }
        }
        for (Enemy e : Management.kondorias) {
            if (this.intersects(e)) {
                e.setAlive(false);
            }
        }
        checkBomb();
        if (this.intersects(Management.bomberman))
            Management.bomberman.setAlive(false);
    }

    @Override
    public boolean checkWall() {
        for (Entity w : Management.walls) {
            if (this.getX() == w.getX() && this.getY() == w.getY()) {
                this.setVisible(false);
                return true;
            }
        }
        return false;
    }

    /**
     * xet khi bom no trung gach thi setbroken=true.
     * @return
     */
    public boolean checkBrick() {
        for (Brick b : Management.bricks) {
            if (this.getX() == b.getX() && this.getY() == b.getY()) {
                this.setVisible(false);
                b.setBroken(true);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean checkBomb() {
        for (Bomb bomb : Management.bomberman.bombs) {
            if (this.intersects(bomb)) {
                bomb.setExploded(true);
                return true;
            }
        }
        return false;
    }
}
