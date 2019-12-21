package logic;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

    private static String path = System.getProperty("user.dir") + "/parameters.json";


    private static JSONObject getJsonObject() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);

            return (JSONObject) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<Double> readFileWorld() {
        if (getJsonObject() == null) {
            throw new NullPointerException();
        }
        return parseWorldObject(getJsonObject());
    }

    private static ArrayList<Double> parseWorldObject(JSONObject world) {
        ArrayList<Double> result = new ArrayList<Double>();

        Object width = world.get("Width");
        if (width instanceof Long) {
            result.add(Double.parseDouble(width.toString()));
        } else result.add((double) width);

        Object height = world.get("Height");
        if (height instanceof Long) {
            result.add(Double.parseDouble(height.toString()));
        } else result.add((double) height);

        Object startEnergy = world.get("Start energy");
        if (startEnergy instanceof Long) {
            result.add(Double.parseDouble(startEnergy.toString()));
        } else result.add((double) startEnergy);

        Object moveEnergy = world.get("Move energy");
        if (moveEnergy instanceof Long) {
            result.add(Double.parseDouble(moveEnergy.toString()));
        } else result.add((double) moveEnergy);

        Object plantEnergy = world.get("Plant energy");
        if (plantEnergy instanceof Long) {
            result.add(Double.parseDouble(plantEnergy.toString()));
        } else result.add((double) plantEnergy);

        Object jungleRatio = world.get("Jungle ratio");
        if (jungleRatio instanceof Long) {
            result.add(Double.parseDouble(jungleRatio.toString()));
        } else result.add((double) jungleRatio);


        Object numberOfGrass = world.get("Number of Grass");
        if (numberOfGrass instanceof Long) {
            result.add(Double.parseDouble(numberOfGrass.toString()));
        } else result.add((double) numberOfGrass);

        Object numberOfAnimals = world.get("Number of Animals");
        if (numberOfGrass instanceof Long) {
            result.add(Double.parseDouble(numberOfAnimals.toString()));
        } else result.add((double) numberOfAnimals);

        return result;
    }

    public static ArrayList<ArrayList<String>> readFileForm() {

        if (getJsonObject() == null) {
            throw new NullPointerException();
        }
        return parseDescriptionObject(getJsonObject());

    }

    private static ArrayList<ArrayList<String>> parseDescriptionObject(JSONObject world) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> helpArray = new ArrayList<String>();


        Object width = world.get("Width");
        helpArray.add("Width");
        helpArray.add(Long.toString(Math.round(Double.parseDouble(width.toString()))));
        result.add(helpArray);
        helpArray = new ArrayList<>();


        Object height = world.get("Height");
        helpArray.add("Height");
        helpArray.add(Long.toString(Math.round(Double.parseDouble(height.toString()))));
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object startEnergy = world.get("Start energy");
        helpArray.add("Start energy");
        helpArray.add(startEnergy.toString());
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object moveEnergy = world.get("Move energy");
        helpArray.add("Move energy");
        helpArray.add(moveEnergy.toString());
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object plantEnergy = world.get("Plant energy");
        helpArray.add("Plant energy");
        helpArray.add(plantEnergy.toString());
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object jungleRatio = world.get("Jungle ratio");
        helpArray.add("Jungle ratio");
        helpArray.add(jungleRatio.toString());
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object numberOfGrass = world.get("Number of Grass");
        helpArray.add("Number of Grass");
        helpArray.add(Long.toString(Math.round(Double.parseDouble(numberOfGrass.toString()))));
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object numberOfAnimals = world.get("Number of Animals");
        helpArray.add("Number of Animals");
        helpArray.add(Long.toString(Math.round(Double.parseDouble(numberOfAnimals.toString()))));
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object scale = world.get("Scale of Draw");
        helpArray.add("Scale of Draw");
        helpArray.add(scale.toString());
        result.add(helpArray);
        helpArray = new ArrayList<>();

        Object delay = world.get("Delay in ms");
        helpArray.add("Delay in ms");
        helpArray.add(Long.toString(Math.round(Double.parseDouble(delay.toString()))));
        result.add(helpArray);

        return result;
    }

    public static void saveChanges(ArrayList<String> keys, ArrayList<Double> values) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;

            for (int i = 0; i < keys.size(); i++) {
                jsonObject.remove(keys.get(i));
                jsonObject.put(keys.get(i), values.get(i));
            }

            FileWriter file = new FileWriter(path);
            file.write(jsonObject.toJSONString());
            file.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static int getScale() {
        if (getJsonObject() == null) throw new NullPointerException();
        return (int) Math.round(Double.parseDouble(getJsonObject().get("Scale of Draw").toString()));
    }

    public static double getJungleRatio() {
        if (getJsonObject() == null) throw new NullPointerException();
        return Double.parseDouble(getJsonObject().get("Jungle ratio").toString());
    }

    public static int getDelay() {
        if (getJsonObject() == null) throw new NullPointerException();
        return (int) Math.round(Double.parseDouble(getJsonObject().get("Delay in ms").toString()));
    }

}