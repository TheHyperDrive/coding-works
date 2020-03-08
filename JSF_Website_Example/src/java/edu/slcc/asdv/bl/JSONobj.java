package edu.slcc.asdv.bl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

public class JSONobj
{

    static final String STATUS_KEY = "status";

    public static void addAttributeToObject()
    {
        JsonObject dev = Json.createObjectBuilder().
                add("developer", "duke").
                build();
        String expected = "master";

        JsonObject devWithStatus = enrich(dev, STATUS_KEY, expected);
        //assertThat(devWithStatus.getString(STATUS_KEY), is(expected));
        System.out.println(devWithStatus);
    }

    public static JsonObject enrich(JsonObject source, String key, String value)
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(key, value);
        source.entrySet().
                forEach(e -> builder.add(e.getKey(), e.getValue()));
        return builder.build();

    }

    public static JsonObject Test(JsonObject obj,
            String key, String value)
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(key, value);
        obj.entrySet().
                forEach(e -> builder.add(e.getKey(), e.getValue()));
        return builder.build();

    }

    public static void navigateTree(JsonValue tree, String key)
    {
        if (key != null)
          {
            System.out.print("Key " + key + ": ");
          }
        switch (tree.getValueType())
          {
            case OBJECT:
                System.out.println("OBJECT");
                JsonObject object = (JsonObject) tree;
                for (String name : object.keySet())
                  {
                    navigateTree(object.get(name), name);
                  }
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array)
                  {
                    navigateTree(val, null);
                  }
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                System.out.println("STRING " + st.getString());
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                System.out.println("NUMBER " + num.toString());
                break;
            case TRUE:
            case FALSE:
            case NULL:
                System.out.println(tree.getValueType().toString());
                break;
          }
    }

    static void writeObjectModelToStream2(JsonObject model)
    {

        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter))
          {
            jsonWriter.writeObject(model);
            String jsonData = stWriter.toString();
            System.out.println(jsonData);
          }
    }

    public static void writeObjectModelToStream(JsonObject model)
    {
        StringWriter stWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stWriter);
        jsonWriter.writeObject(model);
        jsonWriter.close();
        //form the stream we can get the string of data
        String jsonData = stWriter.toString();
        System.out.println(jsonData);
    }
  
    public static void readJASONdataUsingParser(String jsonData)
    {
        JsonParser parser = Json.createParser(new StringReader(jsonData));
        while (parser.hasNext())
          {
            JsonParser.Event event = parser.next();
            switch (event)
              {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                    System.out.println(event.toString());
                    break;
                case KEY_NAME:
                    System.out.print(event.toString() + " "
                            + parser.getString() + " - ");
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                    System.out.println(event.toString() + " "
                            + parser.getString());
                    break;
              }
          }
    }

    public static String readJasonValue(JsonObject jo, String keyArrayName, String keyObjectWithingArra)
    {
        JsonArray arJo = jo.getJsonArray(keyArrayName);
        if (arJo == null)
          {
            return null;
          }
        for (int i = 0; i < arJo.size(); ++i)
          {
            JsonObject o = arJo.getJsonObject(i);
            Set<Entry<String, JsonValue>> set = o.entrySet();
            Iterator<Entry<String, JsonValue>> it = set.iterator();
            while (it.hasNext())
              {
                Entry<String, JsonValue> entry = it.next();
                if (entry.getKey().equals(keyObjectWithingArra))
                  {
                    return entry.getValue().toString();
                  }
              }
          }
        return null;
    }

    public static void writingJSONDataUsingGenerator()
            throws IOException
    {
        FileWriter writer = new FileWriter("testJason.txt");
        JsonGenerator gen = Json.createGenerator(writer);
        gen.writeStartObject()
                .write("firstName", "Duke")
                .write("lastName", "Java")
                .write("age", 18)
                .write("streetAddress", "100 Internet Dr")
                .write("city", "JavaTown")
                .write("state", "JA")
                .write("postalCode", "12345")
                .writeStartArray("phoneNumbers")
                .writeStartObject()
                .write("type", "mobile")
                .write("number", "111-111-1111")
                .writeEnd()
                .writeStartObject()
                .write("type", "home")
                .write("number", "222-222-2222")
                .writeEnd()
                .writeEnd()
                .writeEnd();
        gen.close();
    }

    public static JsonObject createObject()
    {
        JsonObject model = Json.createObjectBuilder()
                .add("firstName", "Duke")
                .add("lastName", "Java")
                .add("age", 18)
                .add("streetAddress", "100 Internet Dr")
                .add("city", "JavaTown")
                .add("state", "JA")
                .add("postalCode", "12345")
                .add("phoneNumbers", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("type", "mobile")
                                .add("number", "111-111-1111"))
                        .add(Json.createObjectBuilder()
                                .add("type", "home")
                                .add("number", "222-222-2222")))
                .build();
        
        return model;
    }

    public static void main(String[] args)
            throws IOException
    {

        JsonObject model = createObject();
        //navigateTree(model, null);

        //System.out.println("------------------------------------");
        //writeObjectModelToStream(model);

        System.out.println("------------------------------------");
        System.out.println("------------------------------------");
        readJASONdataUsingParser(model.toString());
        //System.out.println("------------------------------------");
       // System.out.println("------------------------------------");

        //writingJSONDataUsingGenerator();
        /*
        System.out.println("------------------------------------");
        writeObjectModelToStream2(model);
       
         */
    }
}
