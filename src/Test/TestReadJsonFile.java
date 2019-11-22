package Test;

import Utils.ReadUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;

public class TestReadJsonFile {

    public static void main(String[] args) throws Exception {

        File directory = new File("");
        String dir = directory.getCanonicalPath();
        String temp = "/src/Resources/initialize.json";
        String finalPath = dir + temp;

        String getContents = ReadUtils.readJsonFile(finalPath);

        JSONObject jsonObject = JSON.parseObject(getContents);
        System.out.println("name: " + jsonObject.get("name"));

        JSONArray rooms = jsonObject.getJSONArray("room");
        JSONObject initialRoom = (JSONObject) rooms.get(0);
        JSONArray walls = initialRoom.getJSONArray("wall");
        JSONObject northWall = (JSONObject) walls.get(0);
        System.out.println(northWall.getString("north-wall"));
    }
}
