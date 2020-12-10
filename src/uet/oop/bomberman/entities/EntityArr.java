package uet.oop.bomberman.entities;

import uet.oop.bomberman.bomb.Bomb;
import uet.oop.bomberman.enemy.*;
//import uet.oop.bomberman.entities.item.Item;
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
    public static List<Ballom> balloms = new ArrayList<>();
    public static List<Oneal> oneals = new ArrayList<>();
    public static List<Doll> dolls = new ArrayList<>();
    public static List<Kondoria> kondorias = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//    public static List<Flame> flames = new ArrayList<>();

    public static void removeEnemy() {
        Iterator<Ballom> ballomIterator = balloms.listIterator();
        while (ballomIterator.hasNext()) {
            Ballom ballom = ballomIterator.next();
            if (!ballom.isAlive()) ballomIterator.remove();
        }

        Iterator<Oneal> onealIterator = oneals.listIterator();
        while (onealIterator.hasNext()) {
            Oneal oneal = onealIterator.next();
            if (!oneal.isAlive()) onealIterator.remove();
        }

        Iterator<Doll> dollIterator = dolls.listIterator();
        while (dollIterator.hasNext()) {
            Doll doll = dollIterator.next();
            if (!doll.isAlive()) dollIterator.remove();
        }

        Iterator<Kondoria> kondoriaIterator = kondorias.listIterator();
        while (kondoriaIterator.hasNext()) {
            Kondoria kondoria = kondoriaIterator.next();
            if (!kondoria.isAlive()) kondoriaIterator.remove();
        }

    }

    public static void removeBrick() {
        Iterator<Brick> brickIterator = bricks.listIterator();
        while (brickIterator.hasNext()) {
            Brick brick = brickIterator.next();
            if (brick.isBroken()) {
                brickIterator.remove();
            }
        }
    }

    public static void removeBomb() {
        Iterator<Bomb> bombIterator = bomberman.bombs.listIterator();
        while (bombIterator.hasNext()) {
            Bomb bomb = bombIterator.next();
            if (bomb.isExploded()) {
                bombIterator.remove();
            }
        }
    }

    public static void clearArr() {
        bombers.clear();
        grasses.clear();
        bricks.clear();
        walls.clear();
        items.clear();
        portals.clear();
        oneals.clear();
        balloms.clear();
    }
}
