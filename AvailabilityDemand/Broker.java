package AvailabilityDemand;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Broker {

	private static List<BnBProvider> providers = new ArrayList<BnBProvider>();

	private static List<Customer> customers = new ArrayList<Customer>();

	private static Formatter format = new Formatter();

	public void pubMatch(BnBProvider provider) {
		for (int i = 0; i < customers.size(); i++){
			
			if(customers.get(i).getRoom().getLocation().equalsIgnoreCase(provider.getRoom().getLocation()) ) {
				
				Date proFrom = provider.getRoom().getStayPeriod().getStartDate();
				Date proTo = provider.getRoom().getStayPeriod().getEndDate();
				Date custFrom = customers.get(i).getRoom().getStayPeriod().getStartDate();
				Date custTo = customers.get(i).getRoom().getStayPeriod().getEndDate();
				
				if((custFrom.after(proFrom) || custFrom.equals(proFrom) ) && ( custTo.equals(proTo) || custTo.before(proTo)) ) {
					notification(customers.get(i), provider);
				}
			}
		}
	}
	
	public void subMatch(Customer cust1) {
		if(providers.size() != 0) {
			
			for (int i = 0; i < providers.size(); i++){
				
				if(cust1.getRoom().getLocation().equalsIgnoreCase(providers.get(i).getRoom().getLocation()) ) {
					
					Date proFrom = providers.get(i).getRoom().getStayPeriod().getStartDate();
					Date proTo = providers.get(i).getRoom().getStayPeriod().getEndDate();
					Date custFrom = cust1.getRoom().getStayPeriod().getStartDate();
					Date custTo = cust1.getRoom().getStayPeriod().getEndDate();
					
					if((custFrom.after(proFrom) || custFrom.equals(proFrom) ) && ( custTo.before(proTo) || custTo.equals(proTo))) {

						notification(cust1, providers.get(i));
					}
				}
			}
		}
	}

	public void notification(Customer cust, BnBProvider pro) {
		format.addOutput(cust, pro);
	}
	
	public boolean addPub(Room room, String providerName) {
		
		BnBProvider pro = new BnBProvider();
		pro.setName(providerName);
		pro.setRoom(room);
		
		if(providers.size() == 0) {
			
			providers.add(pro);
			pubMatch(pro);
			return true;
		
		}
		else {
			for(int i = 0; i < providers.size(); i++) {
				
				Date pro1From = providers.get(i).getRoom().getStayPeriod().getStartDate();
				Date pro1To = providers.get(i).getRoom().getStayPeriod().getEndDate();
				Date proFrom = pro.getRoom().getStayPeriod().getStartDate();
				Date proTo = pro.getRoom().getStayPeriod().getEndDate();
				
				if(!(providers.get(i).getName().equalsIgnoreCase(providerName) 
						&& providers.get(i).getRoom().getLocation().equalsIgnoreCase(pro.getRoom().getLocation())
						&& (( proFrom.compareTo(pro1From) <= 0 && proTo.compareTo(pro1From) >= 0 && proTo.compareTo(pro1To) <= 0) 
								|| ( proFrom.compareTo(pro1From) >= 0 && proFrom.compareTo(pro1To) <= 0 && proTo.compareTo(pro1To) <= 0) 
								|| ( proFrom.compareTo(pro1From) == 0) || (proTo.compareTo(pro1To) == 0) 
								|| ( proFrom.compareTo(pro1To) == 0) || (proTo.compareTo(pro1From) == 0) ))) {
					
					providers.add(pro);
					pubMatch(pro);
					return true;
				
				}
			}
		}
		return false;
	}
	
	public boolean addSub(Room room, String name) {

		Customer cust = new Customer();
		cust.setName(name);
		cust.setRoom(room);
				
		if(customers.size() == 0) {
			customers.add(cust);
			subMatch(cust);
			return true;
		}
		else {
			for(int i = 0; i < customers.size(); i++) {
				
				Date cust1From = customers.get(i).getRoom().getStayPeriod().getStartDate();
				Date cust1To = customers.get(i).getRoom().getStayPeriod().getEndDate();
				Date custFrom = cust.getRoom().getStayPeriod().getStartDate();
				Date custTo = cust.getRoom().getStayPeriod().getEndDate();
				
				if(!(customers.get(i).getName().equalsIgnoreCase(cust.getName()) 
						&& customers.get(i).getRoom().getLocation().equalsIgnoreCase(cust.getRoom().getLocation()) 
						&& custFrom.compareTo(cust1From) >= 0 && custTo.compareTo(cust1To) <= 0)) {
					
					customers.add(cust);
					subMatch(cust);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean unSub(Room room, String name) {
		for (int i = 0; i < customers.size(); i++) {
			
			Date cust1From = customers.get(i).getRoom().getStayPeriod().getStartDate();
			Date cust1To = customers.get(i).getRoom().getStayPeriod().getEndDate();
			Date custFrom = room.getStayPeriod().getStartDate();
			Date custTo = room.getStayPeriod().getEndDate();
			
			if (customers.get(i).getName().equalsIgnoreCase(name) && customers.get(i).getRoom().getLocation().equalsIgnoreCase(room.getLocation())
					&& (custFrom.compareTo(cust1From) == 0 && custTo.compareTo(cust1To) == 0) ) {
				
				customers.remove(customers.get(i));
				return true;
			}
		}
		return false;
	}
	
	public void brokerReset() {
		providers = new ArrayList<BnBProvider>();
		customers = new ArrayList<Customer>();
	}
	
}
