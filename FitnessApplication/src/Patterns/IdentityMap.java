package Patterns;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Business.Exercise;
import Business.Workout;
import Objects.Client;

public class IdentityMap {
	private Map<Integer, Object> objectMap = new HashMap<Integer, Object>();

	
	public Object get(int id) {
		return objectMap.get(id);
	}
	
	public void put(int id, Object object) {
		objectMap.put(id, object);
	}
	
	public void remove(int id) {
		objectMap.remove(id);
	}
		
	public static  IdentityMap getInstance(Object object) {
		return new IdentityMap();
	} 
	
	public Set<Integer> getKeySet() {
		return objectMap.keySet();
	}

}
