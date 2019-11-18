package Test;

import Resources.GetPath;
import Utils.ReadUtils;
import com.alibaba.fastjson.JSON;
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
    }
}
