package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StatsWriter {
    public static void saveStats(Jungle map) {
        try {
            File file = new File("stats.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Number of ages : " + World.getNumberOfAges(map) + "\n");
            writer.write("Average number of animals in all ages : " + World.getAverageNumberOfAnimals(map) + "\n");
            writer.write("Average number of grassfields in all ages : " + World.getAverageNumberOfGrass(map) + "\n");
            Genotype dominant = World.getMostDominantGenotype(map);
            if (dominant == null) {
                writer.write("Average most dominant genotype in all ages : unknown\n");
            } else {
                writer.write("Average most dominant genotype in all ages : " + dominant + "\n");
            }
            writer.write("Average energy in all ages : " + World.getAverageEnergy(map) + "\n");
            writer.write("Average length of life for dead animals in all ages : " + World.getAverageLengthOfLife(map) + "\n");
            writer.write("Average numer of children for animal in all ages : " + World.getAverageNumberOfChildren(map) + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
