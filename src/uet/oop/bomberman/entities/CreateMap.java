package uet.oop.bomberman.entities;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.enemy.Balloom;
import uet.oop.bomberman.enemy.Doll;
import uet.oop.bomberman.enemy.Kondoria;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class CreateMap {
    public static void createMapByLevel(int level) {
         Management.clearArr();
         Management.bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
         Management.bombers.add(Management.bomberman);
        try {
            String path = "res/levels/Level" + level + ".txt";
            File file = new File(path);
            Scanner sc = new Scanner(file);
//            sc.nextInt();
            level = sc.nextInt();
            Game.HEIGHT = sc.nextInt();
            Game.WIDTH = sc.nextInt();


            char[][] maps = new char[Game.HEIGHT][Game.WIDTH];
            sc.nextLine();
            for (int i = 0; i < Game.HEIGHT; ++i) {
                String line = sc.nextLine();
                for (int j = 0; j < Game.WIDTH; ++j) {
                    maps[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < Game.WIDTH; ++i) {
                for (int j = 0; j < Game.HEIGHT; ++j) {
                    if (j == 0 || j == Game.HEIGHT - 1 || i == 0 || i == Game.WIDTH - 1 || maps[j][i] == '#') {
                        Management.walls.add(new Wall(i, j, Sprite.wall.getFxImage()));
                    } else {
                        Management.grasses.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    }
                    if (maps[j][i] == 'x') {
                        Management.portals.add(new Portal(i, j, Sprite.portal.getFxImage()));
                    }
                    if (maps[j][i] == 'x' || maps[j][i] == '*') {
                        Management.bricks.add(new Brick(i, j, Sprite.brick.getFxImage()));
                    } else if (maps[j][i] == '1') {
                        Management.ballooms.add(new Balloom(i, j, Sprite.balloom_left1.getFxImage()));
                    } else if (maps[j][i] == '2') {
                        Management.oneals.add(new Oneal(i, j, Sprite.oneal_right1.getFxImage()));
                    } else if (maps[j][i] == '3') {
                        Management.dolls.add(new Doll(i, j, Sprite.doll_right1.getFxImage()));
                    } else if (maps[j][i] == '4') {
                        Management.kondorias.add(new Kondoria(i, j, Sprite.kondoria_right1.getFxImage()));
                    }
                }
            }
            sc.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}
