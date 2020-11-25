package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.enemy.Ballom;
import uet.oop.bomberman.enemy.Oneal;
//import uet.oop.bomberman.entities.item.Item;
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
//    public static List<Item> items = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//    public static List<Flame> flames = new ArrayList<>();

    public static void removeEnemy() {
        Iterator<Ballom> balloomIterator = balloms.listIterator();
        Iterator<Oneal> onealIterator = oneals.listIterator();
        while (balloomIterator.hasNext()) {
            Ballom balloom = balloomIterator.next();
            if (!balloom.isAlive()) balloomIterator.remove();
        }
        while (onealIterator.hasNext()) {
            Oneal oneal = onealIterator.next();
            if (!oneal.isAlive()) balloomIterator.remove();
        }
    }
}
