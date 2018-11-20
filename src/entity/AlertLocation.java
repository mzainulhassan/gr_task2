package entity;

public class AlertLocation {
	private int alertLocationID;
	private String city;
	private String country;
	private AlertProfile alertProfileID;
	
	public AlertLocation() {}
	
	public AlertLocation(String city, String country, AlertProfile alertProfileID) {
		this.city = city;
		this.country = country;
		this.alertProfileID = alertProfileID;
	}

	public int getAlertLocationID() {
		return alertLocationID;
	}

	public void setAlertLocationID(int alertLocationID) {
		this.alertLocationID = alertLocationID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AlertProfile getAlertProfileID() {
		return alertProfileID;
	}

	public void setAlertProfileID(AlertProfile alertProfileID) {
		this.alertProfileID = alertProfileID;
	} 
}