package Patterns;

import java.util.ArrayList;
import Business.Workout;
import Business.WorkoutComponent;
import Objects.Client;
import Patterns.Mappers.ClientMapper;
import Patterns.Mappers.WorkoutMapper;

public class UnitOfWork {
	private static ThreadLocal current = new ThreadLocal(); 
	
	//TODO: Change this back to private, after debugging/testing finished
	public static ArrayList<Object> cleanObjects = new ArrayList<Object>(); 
	private static ArrayList<Object> dirtyObjects = new ArrayList<Object>();
	private static ArrayList<Object> deletedObjects = new ArrayList<Object>();  
	//TODO: same with below, change to private
	public static ArrayList<Object> newObjects = new ArrayList<Object>();
	
	private static IdentityMap identMap = new IdentityMap(); 
	
	public static void registerDelete(Object o) {
		if (cleanObjects.contains(o)) {
			// contains true
			cleanObjects.remove(o);
		}
		if (dirtyObjects.contains(o)) {
			dirtyObjects.remove(o);
		}
		deletedObjects.add(o);
		
	}
	
	public static void registerNew(Object o) {
		if(dirtyObjects.contains(o) || deletedObjects.contains(o)) {
			System.out.println("Trying to register as clean an object that already exists within dirty or deleted");
			return;
		}
		if (!newObjects.contains(o)) {
			newObjects.add(o);
		} else {
			System.out.println("newObjects contains what you're trying to chuck in");
		}
	}
	
	public static void newCurrent() {
		setCurrent(new UnitOfWork()); 
	
	}
	
	public static void setCurrent(UnitOfWork uow) {
		current.set(uow);
	}
	
	public static UnitOfWork getCurrent() {
		return (UnitOfWork) current.get();
	}
	
	public static void registerAsClean(Object obj) {  
		//check if obj is in the identity map 
		//obj = findSingleton(obj, getIdentityMap() );
		//TODO: Re-implement identity map feature^
		if(!cleanObjects.contains(obj) && !dirtyObjects.contains(obj) && !deletedObjects.contains(obj)) {
		cleanObjects.add(obj); 
		} else {
			System.out.println("****ERROR***** what you're trying to add as clean exists in another UOW list ");
		}
	}
	
	
	
	public static void registerAsDirty(Object obj) { 
		//check if obj is in identity map 
		//obj = findSingleton(obj, getIdentityMap() );
		if(deletedObjects.contains(obj)) {
			System.out.print("Cannot modify deleted object");
		} else if (dirtyObjects.contains(obj)) {
			System.out.print("Already in modified list");
		} else {
			dirtyObjects.add(obj);
			cleanObjects.remove(obj);
		}
	}
	/*
	public static void registerAsDeleted(Object obj) {
		//check if obj is in identity map 
		obj = findSingleton(obj, getIdentityMap() );
		if(deletedObjects.contains(obj)) {
			System.out.print("Already in deleted list");
		} else {
			deletedObjects.add(obj); 
		}
	}  */
	
	
	
	//identity map pattern 
	public static Object findSingleton(Object obj, IdentityMap identMap) {
		Class c = obj.getClass(); 
		
		
		if(obj.getClass().getName().equalsIgnoreCase("Workout")) { 
			Workout wo = new Workout();
			wo = (Workout) obj;    
			int id = wo.getId(); 
			identMap = IdentityMap.getInstance(wo); 
			wo = (Workout) identMap.get(id);  
			
			if(wo == null) {
				Workout woNew = new Workout();
				//get from database , with the result, save each parameter 
				/*woNew.setId(record.getId()); 
				woNew.setName(record.getName());
				woNew.setTargetMuscleGroup(record.getTargetMuscleGroup());
				woNew.setExercises(record.getExercises()); */
				//delete above
				identMap.put(id, woNew); 
				return woNew;
			} else {
				return wo; 
			}
		
		} else if(obj.getClass().getName().equalsIgnoreCase("Client"))  {
			Client cl = new Client();
			cl = (Client) obj;    
			int id = cl.getClientId(); 
			identMap = IdentityMap.getInstance(cl); 
			cl = (Client) identMap.get(id);  
			
			if(c == null) {
				Client cNew = new Client();
				//get from database/
	/*
				rs = RESULT
				cNew.setClientId(id);
				cNew.setTrainerId();
				cNew.setFirstName();
				cNew.setLastName();
				cNew.setContactNumber();
				cNew.setGoal(goal); */
				//delete above
				identMap.put(id, cNew); 
				return cNew; 
			} else {
				return c; 
			}
		} 
		return new Object();
		
		
	}
	
	public static IdentityMap getIdentityMap() {
		return identMap; 
	}
	
	public static void commitChanges() { 
		for (Object o : deletedObjects) {
			if (o instanceof Client) {
				Client client = (Client)o;
				int id = client.getClientId();
				ClientMapper.removeClientById(id);
			}
			if (o instanceof Workout) {
				Workout workout = (Workout)o;
				int workoutDeletionId = workout.getId();
				WorkoutMapper.removeWorkoutByWorkoutId(workoutDeletionId);
			}
		}
		for (Object o : newObjects) {
			if (o instanceof Client) {
				Client client = (Client)o;
				ClientMapper.addNewClient(client);
			}
			if (o instanceof Workout) {
				System.out.println("UOW removal method called");
				Workout workout = (Workout)o;
				WorkoutMapper.addNewWorkoutToClient(workout);
				int nextWorkoutComponentId = WorkoutMapper.getNextWorkoutComponentIdFromDatabase();
				for (WorkoutComponent wc : workout.getWorkoutComponents()) {
					WorkoutMapper.addWorkoutComponentToDatabase(nextWorkoutComponentId, workout.getId(), wc);
					nextWorkoutComponentId++;
				}
			}
		}
		newObjects.clear();
		deletedObjects.clear();
		// ADD DATABASE insert, modify, delete 
	}
}
