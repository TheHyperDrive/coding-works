package edu.slcc.asdv.bl;
import edu.slcc.asdv.pojos.Item;
import edu.slcc.asdv.pojos.Keyable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class JsonSupplier
{
/** Creates a JsonObject by 
 * traversing the arralyList of LinkedHashMap
 * 
     * @param suppliers
 * @return JsonObject
 */
    public static JsonObject createJsonObjectForSuppliers(ArrayList< LinkedHashMap<String, String>> suppliers)
    {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        int counter = 1;
        for (LinkedHashMap<String, String> supplier : suppliers)
          {
// create an array of key-value pairs
            JsonArrayBuilder arraySupplierBld = Json.createArrayBuilder();
            // create each key-value pair as seperate object and add it to the array

            Set<Map.Entry<String, String>> entrySet = supplier.entrySet();
            for (Map.Entry<String, String> entry : entrySet)
              {
                arraySupplierBld.add(Json.createObjectBuilder().add(entry.getKey(), entry.getValue()).build());
              }

            // add trip-array to object
            String objectID = "+" + (counter++);
            jsonBuilder.add(objectID, arraySupplierBld);
          }

        JsonObject allTripsJsonObject = jsonBuilder.build();
        return allTripsJsonObject;
    }

    public static JsonObject createJsonObjectForSupplier(String jsonData)
    {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonData));
        JsonObject o = jsonReader.readObject();
        return o;
    }
   

    public static ArrayList<LinkedHashMap<String, String>> createMapOfItems(ProductsForSale<String, Keyable> pfs)
    {
        Collection<Keyable> items = pfs.findAll();
        ArrayList<LinkedHashMap<String, String>> itemMapArray = new ArrayList<>();
        items.forEach(keyable -> {
            LinkedHashMap<String, String> mapItems = 
                new LinkedHashMap<>();
            Item item = (Item)keyable;
            mapItems.put("itemId", item.getId());
            mapItems.put("itemName", item.getName());
            mapItems.put("itemDesc", item.getDescription());
            mapItems.put("itemQty", Integer.toString(item.getQty()));
            mapItems.put("itemPrice", Double.toString(item.getPrice()));
            mapItems.put("itemImage", item.getImage());
            mapItems.put("itemType", item.getCategory());
            mapItems.put("itemSearch", item.getSearch());
            itemMapArray.add(mapItems);
        });
        
        return itemMapArray;
    }

    public static void main(String a[])
    {
        ArrayList< LinkedHashMap<String, String>> suppliers = new ArrayList();
        JsonObject j = createJsonObjectForSuppliers( suppliers);
        StringWriter strWtr = new StringWriter();
        JsonWriter jsonWtr = Json.createWriter(strWtr);
        jsonWtr.writeObject(j);
        jsonWtr.close();
        
        JSONobj.readJASONdataUsingParser(strWtr.toString());
        JsonObject o = createJsonObjectForSupplier(strWtr.toString());
        System.out.println("----------------");
        JSONobj.writeObjectModelToStream(o);
        

    }

    
}
