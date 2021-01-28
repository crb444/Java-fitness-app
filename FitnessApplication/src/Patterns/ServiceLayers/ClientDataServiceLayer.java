package Patterns.ServiceLayers;
import java.util.ArrayList;

import Business.Exercise;
import Business.FitnessGoal;
import LogicServlets.ClientCreationServlet;
import LogicServlets.ClientManagementServlet;
import Objects.Client;
import Patterns.IdentityMap;
import Patterns.UnitOfWork;
import Patterns.Mappers.ClientMapper;

public class ClientDataServiceLayer {
	
	ArrayList<Client> clients;
	IdentityMap identityMap;
	
	public ClientDataServiceLayer() {
		ClientManagementServlet.clientDataServiceLayer = this;
		ClientCreationServlet.clientDataServiceLayer = this;
		clients = ClientMapper.getAllClients();
		identityMap = UnitOfWork.getIdentityMap();
		
		for (Client c : clients) {
			UnitOfWork.registerAsClean(c);
			identityMap.put(c.getClientId(),c);
			ClientManagementServlet.clientIds.add(c.getClientId());
		}
		
	}
	
	
	public String displayAllClientsAsList() {
		String result = "<ul>";
		for (Client c : clients) {
			result = result + c.getFirstName() + " " + c.getLastName() + " <input type=\"submit\" name=\"" + c.getClientId() + "\" value=\"Remove\" />"
					+ " <input type=\"submit\" name=\"" + c.getClientId() + "WORKOUT" + "\" value=\"Add Workout\" />"
					+ " <input type=\"submit\" name=\"" + c.getClientId() + "PROFILE" + "\" value=\"View Profile\" />" + "<br>";
		}
		return result + "</ul>";
	}
	
	public void conductClientRemoval(int id) {
		System.out.println("BEFORE: im keyset");
		System.out.println(identityMap.getKeySet());
		System.out.println("BEFORE: uow all clean");
		for (Object o : UnitOfWork.cleanObjects) {
			if (o instanceof Client) {
				Client client = (Client)o;
				System.out.println(client.getClientId());
				System.out.println(client.getFirstName());
				
			}
		}
		Client client_iter = new Client();
		for (Client c : clients) {
			if (c.getClientId() == id) {
				client_iter = c;
				break;
			}
		}
		UnitOfWork.registerDelete(client_iter);
		identityMap.remove(id);
		UnitOfWork.commitChanges();
		System.out.println("AFTER: im keyset");
		System.out.println(identityMap.getKeySet());
		System.out.println("AFTER: uow all clean");
		for (Object o : UnitOfWork.cleanObjects) {
			if (o instanceof Client) {
				Client client_ = (Client)o;
				System.out.println(client_.getClientId());
				System.out.println(client_.getFirstName());
				
			}
		}
		System.out.println("CDSL conduct client removal method has executed");
	}
	
	public String populateDropDownWithAllGoals() {
		String result = "";
		for (FitnessGoal fitnessGoal : FitnessGoal.values()) {
			result = result + "<option>" + fitnessGoal.name() + "</option>";
		}
		
		return result;
	}
	
	public void conductClientAddition(String firstName, String lastName, FitnessGoal goal,
			String contactNumber) {
		
		// Find out which client id to assign to the new client
		int maxId = 0;
		/*
		for (Client client : allClients) { 
			if (client.getClientId() > maxId) {
				maxId = client.getClientId();
			}
		}
		*/
		maxId = clients.get(clients.size()-1).getClientId();
		System.out.println("MAX ID IS " + Integer.toString(maxId));
		Client client = ClientMapper.createNewClientInstance(maxId + 1, firstName, lastName, goal, contactNumber);
		UnitOfWork.registerNew(client);
		identityMap.put(maxId + 1, client);
		UnitOfWork.commitChanges();
		System.out.println("AFTERCONDUCTADD: im keyset");
		System.out.println(identityMap.getKeySet());
		System.out.println("AFTERCONDUCTADD: uow all new");
		for (Object o : UnitOfWork.newObjects) {
			if (o instanceof Client) {
				Client client_ = (Client)o;
				System.out.println(client_.getClientId());
				System.out.println(client_.getFirstName());
				
			}
		}
		
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
}
