package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Management;

public class BombItem extends Item {
    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        super.update();
        if (this.used == 1) {
            Management.bombers.forEach(g -> g.setNumBombs(g.getNumBombs() + 1));
        }
    }
}

