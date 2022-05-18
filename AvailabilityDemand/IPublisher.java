package AvailabilityDemand;

import java.util.Date;

public interface IPublisher {

	public boolean publish(String providerName, String Description, Date availableFrom, Date availableDate);

}
