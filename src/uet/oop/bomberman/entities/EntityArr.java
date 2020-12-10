package uet.oop.bomberman.entities;

import uet.oop.bomberman.bomb.Bomb;
import uet.oop.bomberman.enemy.*;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityArr {
    public static List<Bomber> bombers = new ArrayList<>();
    public static List<Entity> grasses = new ArrayList<>();
    public static List<Entity> walls = new ArrayList<>();
    public static List<Brick> bricks = new ArrayList<>();
    public static List<Entity> portals = new ArrayList<>();
    public static List<Balloom> ballooms = new ArrayList<>();
    public static List<Oneal> oneals = new ArrayList<>();
    public static List<Doll> dolls = new ArrayList<>();
    public static List<Kondoria> kondorias = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    public static void removeEnemy() {
        ballooms.removeIf(balloom -> !balloom.isAlive());

        oneals.removeIf(oneal -> !oneal.isAlive());

        dolls.removeIf(doll -> !doll.isAlive());

        kondorias.removeIf(kondoria -> !kondoria.isAlive());

    }

    public static void removeBrick() {
        bricks.removeIf(Brick::isBroken);
    }

    public static void removeBomb() {
        bomberman.bombs.removeIf(Bomb::isExploded);
    }

    public static void clearArr() {
        items.clear();
        bombers.clear();
        grasses.clear();
        bricks.clear();
        walls.clear();

        portals.clear();
        oneals.clear();
        ballooms.clear();
        dolls.clear();
        kondorias.clear();
    }

}
