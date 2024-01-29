package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;

import java.io.IOException;

public class JsonReader {

    public static String getTestData(String key) throws IOException, ParseException {
        String testData;
        return testData = (String) getJsonData().get(key);
    }






    public static JSONObject getJsonData() throws IOException, ParseException {
        File file = new File("resources//TestData//testData.json");
        String json =FileUtils.readFileToString(file, "UTF-8");
        Object obj = new JSONParser().parse(json);
        JSONObject jobj = (JSONObject) obj;
        return jobj;
    }

    public static JSONArray getJsonArray(String key) throws IOException, ParseException {
        JSONObject jsonObject = getJsonData();
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return jsonArray;

    }


    public static Object getArrayData(String key, int index) throws IOException, ParseException {
        JSONArray languages = getJsonArray(key);
        return languages.get(index);


    }
}
