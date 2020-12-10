package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
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
         Management.clearArr();
         Management.bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
         Management.bombers.add(Management.bomberman);
        try {
            String path = "res/levels/Level" + level + ".txt";
            File file = new File(path);
            Scanner sc = new Scanner(file);
            level = sc.nextInt();
            BombermanGame.HEIGHT = sc.nextInt();
            BombermanGame.WIDTH = sc.nextInt();

            char[][] maps = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];

            sc.nextLine();

            for (int i = 0; i < BombermanGame.HEIGHT; ++i) {
                String line = sc.nextLine();
                for (int j = 0; j < BombermanGame.WIDTH; ++j) {
                    maps[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < BombermanGame.WIDTH; ++i) {
                for (int j = 0; j < BombermanGame.HEIGHT; ++j) {
                    Brick brick;
                    Entity object;
                    Balloom balloom;
                    Oneal oneal;
                    Doll doll;
                    Kondoria kondoria;
                    if (j == 0 || j == BombermanGame.HEIGHT - 1 || i == 0 || i == BombermanGame.WIDTH - 1 || maps[j][i] == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        Management.walls.add(object);
                    } else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        Management.grasses.add(object);
                    }
                    if (maps[j][i] == 'x') {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                        Management.portals.add(object);
                    }
                    if (maps[j][i] == 'x' || maps[j][i] == '*') {
                        brick = new Brick(i, j, Sprite.brick.getFxImage());
                        Management.bricks.add(brick);
                    } else if (maps[j][i] == '1') {
                        balloom = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                        Management.ballooms.add(balloom);
                    } else if (maps[j][i] == '2') {
                        oneal = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                        Management.oneals.add(oneal);
                    } else if (maps[j][i] == '3') {
                        doll = new Doll(i, j, Sprite.doll_right1.getFxImage());
                        Management.dolls.add(doll);
                    } else if (maps[j][i] == '4') {
                        kondoria = new Kondoria(i, j, Sprite.kondoria_right1.getFxImage());
                        Management.kondorias.add(kondoria);
                    }
                }
            }
            sc.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}
