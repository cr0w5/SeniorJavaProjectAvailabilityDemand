package AvailabilityDemand;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Formatter {
	
	//private AvailabilityDemand availabilityDemand;

	private static List<String> output = new ArrayList<String>();
	
	public List<String> getFormatOutput() {
		return output;
	}
	
	public void addOutput (Customer cust, BnBProvider pro){
		// add cust to the output in the right format
		String name = cust.getName();
		String location = cust.getRoom().getLocation();
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		Date from = pro.getRoom().getStayPeriod().getStartDate();
		String fromStr = format.format(from).toString();

		Date to = pro.getRoom().getStayPeriod().getEndDate();
		String toStr = format.format(to).toString();
		
		output.add(name + " notified of B&B availability in " + location +" from " + fromStr + " to " + toStr +" by "+ pro.getName() +" B&B");
			
	}
	
	public void formatReset() {output = new ArrayList<String>();}
		
}
