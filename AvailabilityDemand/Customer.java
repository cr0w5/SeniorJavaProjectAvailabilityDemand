package AvailabilityDemand;


import java.util.Date;
import java.util.List;

public class Customer implements ISubscriber {

	//private List<Room> cusRooms;
	//private Room[] room;

	private Room room;
	
	private String customerName;

	private static Broker broke = new Broker();

	public boolean subscribe(String location, Date from, Date to) {
	
		Room room = new Room();
		room.setlocation(location);
		room.setStayPeriod(from, to);
		this.room = room;
		
		return broke.addSub(room, customerName);		
	};

	public boolean unSubsribe(String location, Date from, Date to) {
		
		Room room = new Room();
		room.setlocation(location);
		room.setStayPeriod(from, to);
		this.room = room;
		
		return broke.unSub(room, customerName);
	}
	
	public String getName() {return customerName;}
	
	public Room getRoom() {return room;}
	
	public void setName(String name) {this.customerName = name;}

	public void setRoom(Room room) {this.room = room;};

}
