package jaxb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class GsonTest {

	@Test
	public void test() throws IOException {
		
		InputStream inputStream = GsonTest.class.getClassLoader().getResourceAsStream("jsonObj.txt");
		System.out.println(inputStream);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		StringBuilder propPath = new StringBuilder();
		while((line = br.readLine()) != null){
			propPath.append(line);
		}
		String propStr = propPath.toString();
		JsonObject rootObj = new JsonObject();
		JsonObject cursor = rootObj;
		if(propStr.indexOf(".")>0){
			String[] split = propStr.split("\\.");
			String pPath = null;
			for (String path : split) {
				if(pPath==null){
					pPath =path;
					cursor.add(path, null);					
				}else{
					
					JsonObject curObj = cursor.getAsJsonObject(path);//look for obj for the current path
					if(curObj==null){
						curObj = new JsonObject();
					}
					curObj.add(path, null);
					cursor.add(pPath, curObj);
					
					cursor = curObj;
				}
				pPath =path;
			}
		}
		System.out.println(rootObj.toString());
	}

}
