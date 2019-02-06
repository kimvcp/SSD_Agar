package agarssd.client;

import agarssd.model.Player;

public interface Observable {
	
	//methods to register and unregister observers
	public void register(Observer obj);
	public void unregister(Observer obj);
	
	//method to notify observers of change
	public void notifyObservers();
	
	//method to get updates from Observable
	public Object getUpdate(Observer obj);
	
	

}
