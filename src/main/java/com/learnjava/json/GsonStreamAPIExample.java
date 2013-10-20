package com.learnjava.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonStreamAPIExample {

    private static final String jsonFilePath = "jsonFile.json";

    public static void main(String[] args) {
          GsonStreamAPIExample instance = new GsonStreamAPIExample();
          instance.writeJSON();
          instance.readJSON();
    }

    private void writeJSON() {
        try {
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            try (JsonWriter jsonWriter = new JsonWriter(fileWriter)) {
                jsonWriter.beginObject();
                jsonWriter.name("domain").value("www.javacodegeeks.com");
                jsonWriter.name("members").value(200);
                jsonWriter.name("names");
                jsonWriter.beginArray();
                jsonWriter.value("Jack");
                jsonWriter.value("James");
                jsonWriter.value(3);
                jsonWriter.endArray();
                jsonWriter.endObject();
            }
            System.out.println("The file was created successfully!");
        } catch (IOException e) {
        }
    }
    
    private void readJSON(){
        try {
            FileReader fileReader = new FileReader(new File(jsonFilePath));
            try (JsonReader jsonReader = new JsonReader(fileReader)) {
                jsonReader.beginObject();

                while (jsonReader.hasNext()) {

                    String name = jsonReader.nextName();
                    switch (name) {
                        case "domain":
                            System.out.println("domain: " + jsonReader.nextString());
                            break;
                        case "members":
                            System.out.println("members: " + jsonReader.nextInt());
                            break;
                        case "names":
                            System.out.println("names: ");
                            jsonReader.beginArray();
                            while (jsonReader.hasNext()) {
                                System.out.println(" " + jsonReader.nextString());
                            }
                            jsonReader.endArray();
                            break;
                        default:
                            // use this when you are not sure about all the contents in th JSON file
                            jsonReader.skipValue();
                            break;
                    }
                }

                jsonReader.endObject();
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

}
