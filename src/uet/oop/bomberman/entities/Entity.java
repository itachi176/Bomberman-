package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    protected int x;

    protected int y;

    protected Image img;

    protected int animate;

    protected boolean isVisible = true;

    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.animate = this.x;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersects(Entity s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public boolean checkBounds() {
        for (Entity e : Management.walls) {
            if (this.intersects(e)) return true;
        }

        for (Entity e : Management.bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkBomb() {
        for (Entity e : Management.bomberman.bombs) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkWall() {
        for (Entity e : Management.walls) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkBrick() {
        for (Entity e : Management.bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }
}
