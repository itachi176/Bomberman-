package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.enemy.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CreateMap {
     public static void createMapByLevel(int level) {
        EntityArr.clearArr();
        EntityArr.bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        EntityArr.bombers.add(EntityArr.bomberman);
        try {
            String path = "res/levels/Level" + level + ".txt";
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line = buffReader.readLine().trim();
            String[] str = line.split(" ");
            BombermanGame.HEIGHT = Integer.parseInt(str[1]);
            BombermanGame.WIDTH = Integer.parseInt(str[2]);
            char [][] maps = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];

            for (int i = 0; i < BombermanGame.HEIGHT; ++i) {
                line = buffReader.readLine();
                for (int j = 0; j < BombermanGame.WIDTH; ++j) {
                    maps[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < BombermanGame.WIDTH; ++i) {
                for (int j = 0 ; j < BombermanGame.HEIGHT; ++j) {
                    Brick brick;
                    Entity object;
                    Ballom ballom;
                    Oneal oneal;
                    Doll doll;
                    Kondoria kondoria;
                    // create wall and grass
                    if (j == 0 || j == BombermanGame.HEIGHT - 1 || i == 0 || i == BombermanGame.WIDTH - 1 || maps[j][i] == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        EntityArr.walls.add(object);
                    } else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        EntityArr.grasses.add(object);
                    }
                    // create portal
                    if (maps[j][i] == 'x') {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                        EntityArr.portals.add(object);
                    }
                    // create brick
                    if (maps[j][i] == 'x' || maps[j][i] == '*') {
                        brick = new Brick(i, j, Sprite.brick.getFxImage());
                        EntityArr.bricks.add(brick);
                    } else if (maps[j][i] == '1') {
                        ballom = new Ballom(i, j, Sprite.balloom_left1.getFxImage());
                        EntityArr.balloms.add(ballom);
                    } else if (maps[j][i] == '2') {
                        oneal = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                        EntityArr.oneals.add(oneal);
                    } else if (maps[j][i] == '3') {
                        doll = new Doll(i, j, Sprite.doll_right1.getFxImage());
                        EntityArr.dolls.add(doll);
                    } else if (maps[j][i] == '4') {
                        kondoria = new Kondoria(i, j, Sprite.kondoria_right1.getFxImage());
                        EntityArr.kondorias.add(kondoria);
                    }
                }
            }
            fileReader.close();
            buffReader.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}
