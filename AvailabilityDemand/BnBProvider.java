package AvailabilityDemand;

import java.util.Date;

public class BnBProvider implements IPublisher {

	private String providerName;

	private Room room;
		
	private static Broker broke = new Broker();

	public boolean publish(String providerName, String Description, Date availableFrom, Date availableDate) {
		this.providerName = providerName;	
		Room room = new Room();
		room.setlocation(Description);
		room.setStayPeriod(availableFrom, availableDate);
		this.room = room;
	
		return broke.addPub(room, providerName);
	};
	
	public String getName() {return providerName;};
	
	public Room getRoom() {return room;};

	public void setName(String providerName) {this.providerName = providerName;};
	
	public void setRoom(Room room) {this.room = room;};
}
