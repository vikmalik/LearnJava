package com.learnjava.general;


import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class ValidateIPAddress {

	/**
	 * commons-validator-1.4.0.jar is required to compile this class
	 * @param args
	 */
	public static void main(String[] args) {
		String[] ipAddresses = {
			"10.0.0.1",
			"www.google.com",
			"192.168.1.1",
			"192.168.1.1 , 10.0.0.1",
			"ucbu-pgw-11.cisco.com",
			"ucbu-pgw-11.cisco.com, ucbu-pgw-13.cisco.com",
			"192.168.1.1 10.0.0",
			"352.0.0.1"
		};
		
		for (int i = 0; i < ipAddresses.length; i++) {
			boolean validFlag = isValidEntry(ipAddresses[i]);
			//Logger.getLogger(ValidateIPAddress.class).log(Level.INFO, "'" + ipAddresses[i] + "' is " + (validFlag? "valid": "invalid"));		
		}
	}
	
	protected static boolean isValidIPAddress(String ipAddress){
		InetAddressValidator validator = new InetAddressValidator();
		boolean returnValue = validator.isValid(ipAddress);
		//Logger.getLogger(ValidateIPAddress.class).log(Level.INFO, "'" + ipAddress + "' is " + (returnValue? "valid": "invalid"));
		return returnValue;
	}
	
	protected static boolean isValidURL(String url){
		UrlValidator validator = new UrlValidator();
		boolean returnValue = validator.isValid(url);
		//Logger.getLogger(ValidateIPAddress.class).log(Level.INFO, "'" + url + "' is " + (returnValue? "valid": "invalid"));
		return returnValue;
	}
	
	protected static boolean isValidDomain(String domain){
		DomainValidator validator = DomainValidator.getInstance(true);
		boolean returnValue = validator.isValid(domain);
		//Logger.getLogger(ValidateIPAddress.class).log(Level.INFO, "'" + domain + "' is " + (returnValue? "valid": "invalid"));
		return returnValue;
	}
	
	protected static boolean isValidEntry(String fieldEntry){
		boolean returnValue = false;
		if(isValidIPAddress(fieldEntry) || isValidDomain(fieldEntry)){
			returnValue = true;
		}
		return returnValue;
	}
}
