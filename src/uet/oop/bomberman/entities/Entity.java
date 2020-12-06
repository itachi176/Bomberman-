package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
//import uet.oop.bomberman.enemy.Ballom;
//import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected int animate;

    protected  boolean isVisible = true;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
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

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersects(Entity s)
    {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public boolean checkBounds_bom() {
        for (Entity e : EntityArr.walls) {
            if (this.intersects(e)) return true;
        }

        for (Entity e : EntityArr.bricks) {
            if (this.intersects(e)) return true;
        }

        for (Entity e: EntityArr.bomberman.bombs) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public boolean checkBounds() {
        for (Entity e : EntityArr.walls) {
            if (this.intersects(e)) return true;
        }

        for (Entity e : EntityArr.bricks) {
            if (this.intersects(e)) return true;
        }
        return false;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
