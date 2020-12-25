package htec.airlines.enums;

public enum UserType {
	ADMINISTRATOR("ADMIN"),
	REGULAR("REGULAR");
	
	private String role;
	 
	UserType(String role) {
        this.role = role;
    }
 
    public String getType() {
        return role;
    }
}
