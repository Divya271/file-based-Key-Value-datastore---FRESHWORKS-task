import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.lang.System.*;

class Client {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
  
    //initializing datastore object without file path  
    DataStore dataStoreObj = new DataStore();
    long createdTime = 0;

    //calling delete operation  
    dataStoreObj.delete("emp11");

    //calling read operation  
    dataStoreObj.read("emp1");

    //calling create operation
      JSONObject json1 = new JSONObject();
        json1.put("FirstName","Divya1");
        json1.put("LastName","ganta1");
        json1.put("Age",21);
        createdTime = System.currentTimeMillis();
        json1.put("CreatedTime",createdTime);
        json1.put("Time-To-Live",0);
        dataStoreObj.create("emp1", json1);
     
      //calling create operaion
        JSONObject  json2 = new JSONObject();
        json2.put("FirstName","Divya2");
        json2.put("LastName","ganta2");
        json2.put("Age",22);
        createdTime = System.currentTimeMillis();
        json2.put("CreatedTime",createdTime);
        json2.put("Time-To-Live",40);
        dataStoreObj.create("emp2", json2);

        //calling create operaion
        JSONObject  json0 = new JSONObject();
        json0.put("FirstName","Divya2");
        json0.put("LastName","ganta2");
        json0.put("Age",22);
        createdTime = System.currentTimeMillis();
        json0.put("CreatedTime",createdTime);
        json0.put("Time-To-Live",40);
        dataStoreObj.create("emp2jhgkfhghfuighfughfuhguhguifhghriuhruighiuhiuhfiu", json2);

        //calling read operation
        dataStoreObj.read("emp2");

        dataStoreObj.delete("emp2"); 

        //calling delete operation
        dataStoreObj.delete("emp6");
        
        //calling create operation
        JSONObject  json11 = new JSONObject();
        json11.put("FirstName","Divya11");
        json11.put("LastName","ganta11");
        json11.put("Age",11);
        createdTime = System.currentTimeMillis();
        json11.put("CreatedTime",createdTime);
        json11.put("Time-To-Live",100);
        dataStoreObj.create("emp11", json11);

        //calling delete operation
        dataStoreObj.delete("emp11");

        //calling create operation
        JSONObject  json3 = new JSONObject();
        json3.put("FirstName","Divya3");
        json3.put("LastName","ganta3");
        json3.put("Age",23);
        createdTime = System.currentTimeMillis();
        json3.put("CreatedTime",createdTime);
        json3.put("Time-To-Live",100);
        dataStoreObj.create("emp3", json3);

        //calling read operation  
        dataStoreObj.read("emp3"); 

        //calling read operation
        dataStoreObj.read("emp11"); 
        
        //calling delete operation
        dataStoreObj.delete("emp4");

        //calling create operation
        JSONObject json6 = new JSONObject();
        json6.put("FirstName","Divya6");
        json6.put("LastName","ganta6");
        json6.put("Age",26);
        createdTime = System.currentTimeMillis();
        json6.put("CreatedTime",createdTime);
        json6.put("Time-To-Live",120);
        dataStoreObj.create("emp6", json6);
     
        //calling create operation
        JSONObject  json7 = new JSONObject();
        json7.put("FirstName","Divya2");
        json7.put("LastName","ganta2");
        json7.put("Age",22);
        createdTime = System.currentTimeMillis();
        json7.put("CreatedTime",createdTime);
        json7.put("Time-To-Live",20);
        dataStoreObj.create("emp7", json7);

        //calling read operation
        dataStoreObj.read("emp6"); 

        //calling delete operation
        dataStoreObj.delete("emp1");

        //calling create operaion
        JSONObject  json9 = new JSONObject();
        json9.put("FirstName","Divya22222");
        json9.put("LastName","ganta2");
        json9.put("Age",22);
        createdTime = System.currentTimeMillis();
        json9.put("CreatedTime",createdTime);
        json9.put("Time-To-Live",40);
        dataStoreObj.create("emp2", json9);

    }
}
