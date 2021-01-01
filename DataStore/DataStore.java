import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.System.*;

public class DataStore {
	private String key;
	private JSONObject employeeObject;
	private String filePath;
	private File file;
	private long size = 1000000000;
	private LinkedHashMap<String, JSONObject> map;

	DataStore(String filePath) {
		this.filePath = filePath;
		this.file = new File(filePath);
		map = new LinkedHashMap<String,JSONObject>();
		dataLoading(this.map,this.file);
	}

	DataStore() {
		this.filePath = "data.json";
		this.file = new File(filePath);
		map = new LinkedHashMap<>();
		dataLoading(this.map,this.file);
	}
	
	@SuppressWarnings("unchecked")
	public void create(String key, JSONObject jsonObj) {
		this.key = key;
		this.employeeObject = jsonObj;
		JSONObject employeeDetails = new JSONObject(); 
        employeeDetails.put(key, employeeObject);
 		
        if(!capacityCheck(this.file,key,employeeObject)) {
			System.out.println("try Again");
		}
		else if(!map.containsKey(key)) {
			map.put(key,employeeObject);
			BufferedWriter bf = null;

        	try{
            
            	//create new BufferedWriter for the output file
            	bf = new BufferedWriter( new FileWriter(this.file,true) );
            	bf.write("{\""+this.key+"\":"+this.employeeObject+"}");
            	bf.newLine();
            	bf.flush();
            	System.out.println("\ncreated: "+this.key+":"+ this.employeeObject);    
 
        	}catch(IOException e){
            	e.printStackTrace();
        	}finally{
            	
            	try{
                	//always close the writer
                	bf.close();
            	}catch(Exception e){}
        	} 
		}
		else {
			System.out.println("\n"+key + " is already present");
		}
    }    

	public void read(String key) {
		JSONObject json = null;
		String keyy = key;
		if(!map.containsKey(keyy)) {
			displayJson(json, keyy);
		}
		else {
			for(Map.Entry<String, JSONObject> entry : map.entrySet())
				if(entry.getKey().equals(keyy)) {
					json = entry.getValue();
				}
            displayJson(json, keyy);
		}
	}

	public void delete(String key) {
		String key2 = key;
		int flag = 1;
		if(!map.containsKey(key2)) {
			flag = 0;

		} 
		else if(isAlive(map.get(key2))) {
			map.remove(key2);
			BufferedWriter bf = null;;
        	try{
           
            	//create new BufferedWriter for the output file
            	bf = new BufferedWriter( new FileWriter(this.file) );
 
            	//iterate map entries
            	for(Map.Entry<String, JSONObject> entry : map.entrySet()){
                
                	//put key and value separated by a colon
                	bf.write("{\""+entry.getKey()+"\":"+entry.getValue()+"}");
                	//new line
                	bf.newLine();
            	}
            
            	bf.flush();
 
        	}catch(IOException e){
            	e.printStackTrace();
        	}finally{
            
            	try{
                	//always close the writer
                	bf.close();
            	}catch(Exception e){}
        	}
		}
		else flag = 2;
		displayDeleteStatus(flag,key);			
	}

	private boolean capacityCheck(File file, String key, JSONObject jsonObj) {
		Boolean flag = true;

		if(this.file.length() >= size || this.file.length() + jsonObj.size() > size){
			System.out.println("\nFile size is exceeding 1GB, can't perform operations");
			flag = false;
		}

		if(key.length() > 32) {
			System.out.println("\nKey size must be below 33");
			flag = false;
		}

		if(jsonObj.size() > 10000) {
			System.out.println("\nJSONObject must be less than or equal to 16kb");
			flag = false;
		}
		return flag;
	}

	void displayJson(JSONObject json, String key) {
		
		JSONObject returnedJson = json;
		if(returnedJson != null) {
			if(isAlive(returnedJson)) {
     			System.out.println("\n"+returnedJson);	
			}
			else {
				System.out.println("\n"+key+" Session expired");
			}
     	}
        else {
        	if(isStoreEmpty()) {
        		System.out.println("\nStore is empty, read operaion can't be performed");
        	}
        	else {
        		System.out.println("\n"+key+" is not present in store");
        	}
   		}
	}
	
	void displayDeleteStatus(int flag, String key) {
		String keyy = key;
		int returnedFlag = flag;
		if(returnedFlag == 0 && isStoreEmpty()) {
			System.out.println("\nStore is empty, delete operaion can't be performed");
      	}
       	else if(returnedFlag == 0) {
       		System.out.println("\n"+keyy+" is not present in store");
       	}
        else if(returnedFlag == 1){
        	System.out.println("\n"+keyy+" is deleted");	
       	} 
       	else if(flag == 2) {
       		System.out.println("\n"+keyy+" Session expired");
       	}
	}

	@SuppressWarnings("unchecked")
	void dataLoading(LinkedHashMap<String,JSONObject> map, File file)  {
		File file1 = file;
		LinkedHashMap<String,JSONObject> hashMap = map;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file1));
			String line = reader.readLine();
			while (line != null) {
        		line = line.replaceAll("\\s", "");
        		line = line.replaceAll("\"", "");
        		line = line.replaceAll("[{}]+", "");
        		String str[] = line.split("[:,]+");
          		String key = str[0];
          		long createdTime = Long.parseLong(str[2]);
        		String firstName = str[4];
        		String lastName = str[6];
        		int span = Integer.parseInt(str[8]);
        		int age = Integer.parseInt(str[10]);
        		JSONObject temp = new JSONObject();
        		temp.put("FirstName",firstName);
        		temp.put("LastName",lastName);
        		temp.put("Age",age);
        		temp.put("CreatedTime",createdTime);
        		temp.put("Time-To-Live",span);
        		hashMap.put(key, temp);
        		line = reader.readLine();
        	}
        	reader.close();
    	}
    	catch(Exception ex){
    	ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked") 
	boolean isAlive(JSONObject json) {
		JSONObject jsonObj = json;
		long createdTime = (long)jsonObj.get("CreatedTime");
		int span = (int)jsonObj.get("Time-To-Live");
		long currentTime = System.currentTimeMillis();
		if(currentTime <= (createdTime + (span *1000))) return true;
		else return false;
	}

	boolean isStoreEmpty() {
		return map.isEmpty();
	}	
}

