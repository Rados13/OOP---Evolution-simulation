package logic;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {


    private static JSONObject getJsonObject(){
        JSONParser jsonParser = new JSONParser();

        URL url = ReadJson.class.getResource("parameters.json");

        try (FileReader reader = new FileReader(url.getPath())) {
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


    public static ArrayList<Double> readFileWorld() {
        if(getJsonObject()==null){
            throw new NullPointerException();
        }
        return parseWorldObject(getJsonObject());
    }

    private static ArrayList<Double> parseWorldObject(JSONObject world) {
        ArrayList<Double> result = new ArrayList<Double>();
//        JSONObject world = (JSONObject) world.get("world");

        Object width = world.get("width");
        if (width instanceof Long) {
            result.add(Double.parseDouble(width.toString()));
        } else result.add((double) width);

        Object height = world.get("height");
        if (height instanceof Long) {
            result.add(Double.parseDouble(height.toString()));
        } else result.add((double) height);

        Object startEnergy = world.get("startEnergy");
        if (startEnergy instanceof Long) {
            result.add(Double.parseDouble(startEnergy.toString()));
        } else result.add((double) startEnergy);

        Object moveEnergy = world.get("moveEnergy");
        if (moveEnergy instanceof Long) {
            result.add(Double.parseDouble(moveEnergy.toString()));
        } else result.add((double) moveEnergy);

        Object plantEnergy = world.get("plantEnergy");
        if (plantEnergy instanceof Long) {
            result.add(Double.parseDouble(plantEnergy.toString()));
        } else result.add((double) plantEnergy);


        Object jungleRatio = world.get("jungleRatio");
        if (jungleRatio instanceof Long) {
            result.add(Double.parseDouble(jungleRatio.toString()));
        } else result.add((double) jungleRatio);


        Object numberOfGrass = world.get("numberOfGrass");
        if (numberOfGrass instanceof Long) {
            result.add(Double.parseDouble(numberOfGrass.toString()));
        } else result.add((double) numberOfGrass);

        return result;

    }

    public static ArrayList<ArrayList<String>> readFileForm() {

        if(getJsonObject()==null){
            throw new NullPointerException();
        }
        return parseDescriptionObject(getJsonObject());

    }

    private static ArrayList<ArrayList<String>> parseDescriptionObject(JSONObject world) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        for (Object key : world.keySet()) {
            ArrayList<String> helpArray = new ArrayList<String>();
            helpArray.add(key.toString());
            helpArray.add(world.get(key).toString());
            result.add(helpArray);
        }
        return result;
    }

    public static void saveChanges(ArrayList<String> keys, ArrayList<Double> values) {
        JSONParser jsonParser = new JSONParser();

        URL url = ReadJson.class.getResource("parameters.json");
        try (FileReader reader = new FileReader(url.getPath())) {
            Object obj = jsonParser.parse(reader);

            JSONObject jsonObject = (JSONObject) obj;

            for (int i = 0; i < keys.size(); i++) {
                jsonObject.remove(keys.get(i));
                jsonObject.put(keys.get(i), values.get(i));
            }

            FileWriter file = new FileWriter(url.getPath());
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
        if(getJsonObject()==null)throw new NullPointerException();
        return (int)Math.round(Double.parseDouble(getJsonObject().get("scaleOfDraw").toString()));
    }

    public static int getNumberOfAnimals(){
        if(getJsonObject()==null)throw new NullPointerException();
        return (int)Math.round(Double.parseDouble(getJsonObject().get("numberOfAnimals").toString()));

    }

}