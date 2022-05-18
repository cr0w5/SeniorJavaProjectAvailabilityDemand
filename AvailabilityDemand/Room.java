package AvailabilityDemand;

import java.util.Date;

public class Room {

	private String location;

	private StayPeriod stayPeriod = new StayPeriod();
		
	public void setlocation(String location) {this.location = location;};
	
	public String getLocation() {return this.location;};

	public void setStayPeriod(Date from, Date to) {
		this.stayPeriod.setStartDate(from);
		this.stayPeriod.setEndDate(to);
	};
	
	public StayPeriod getStayPeriod() {
		return stayPeriod;
	};
	
}
