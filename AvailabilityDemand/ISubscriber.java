package AvailabilityDemand;

import java.util.Date;

public interface ISubscriber {

	public boolean subscribe(String location, Date from, Date to);

	public boolean unSubsribe(String location, Date from, Date to);

}
