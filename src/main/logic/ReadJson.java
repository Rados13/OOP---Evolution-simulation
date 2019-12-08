
package logic;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson
{
    @SuppressWarnings("unchecked")
    public static ArrayList<Double> readFile()
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        URL url = ReadJson.class.getResource("parameters.json");

        try (FileReader reader = new FileReader(url.getPath()))
        {
            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);
            return parseWorldObject(jsonObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Double> parseWorldObject(JSONObject world)
    {
        ArrayList<Double> result = new ArrayList<Double>();
//        JSONObject world = (JSONObject) world.get("world");

        long width = (long) world.get("width");
        result.add((double) width);

        long height = (long) world.get("height");
        result.add((double)height);

        long startEnergy = (long) world.get("startEnergy");
        result.add((double)startEnergy);

        long moveEnergy = (long) world.get("moveEnergy");
        result.add((double)moveEnergy);

        long plantEnergy = (long) world.get("plantEnergy");
        result.add((double) plantEnergy);

        double jungleRatio = (double) world.get("jungleRatio");
        result.add(jungleRatio);

        long numberOfGrass = (long) world.get("numberOfGrass");
        result.add((double) numberOfGrass);


        return result;

    }
}