package AvailabilityDemand;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AvailabilityDemand {

	//private String command;
	//private List<BnBProvider> providers = new ArrayList<BnBProvider>();
	//private List<Customer> customers;
	//private String name;
	//private String location;
	//private Date from;
	//private Date to;
	//private Customer[] customer;
	//private BnBProvider[] bnBProvider;
	
	private List<String> output;
	
	private static Broker broker = new Broker();
	
	private static Formatter format = new Formatter();
	
	public void processInput(String command) {
		String [] commandArr = command.split(", ");
				
		String name;
		String location;
		Date from;
		Date to;
		
		try {
			name = commandArr[1];
			location = commandArr[2];
			// format the date from string to date
			
			DateFormat form = new SimpleDateFormat("MM/dd/yyyy");
			
			form.setLenient(false);
	
			String fromStr = commandArr[3];
			from = (Date)form.parse(fromStr);
		
			String toStr = commandArr[4];
			to = (Date)form.parse(toStr);
			
			String defaultStr = "11/27/2021";
			Date defaultDate = 	(Date)form.parse(defaultStr);
					
			if(from.compareTo(to) < 0 && from.compareTo(defaultDate) > 0 && to.compareTo(defaultDate) > 0  ) {
				if(commandArr.length == 5) {
					if(commandArr[0].equals("subscribe")) {
						Customer cust = new Customer();
						cust.setName(name);
						cust.subscribe(location, from, to);
					}
					else if (commandArr[0].equals("unsubscribe")) {
	
						Customer cust = new Customer();
						cust.setName(name);
						cust.unSubsribe(location, from, to);
		
					}
					else if (commandArr[0].equals("publish")) {
						BnBProvider pro = new BnBProvider();
						pro.publish(name, location, from, to);
					}			
				}
			}	
		}catch(java.text.ParseException e) {}
	}

	public List<String> getAggregatedOutput() {
		output = format.getFormatOutput();
		return output;
	}

	public void reset() {
		broker.brokerReset();
		format.formatReset();
	}
	
}