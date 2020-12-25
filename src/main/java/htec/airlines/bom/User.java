package htec.airlines.bom;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import htec.airlines.enums.UserType;

@Entity
@Table(name = "User")
public class User {
	@Id
	@Column(name = "IdUser")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private boolean isActive;
	
	@Column
	private LocalDateTime dateTimeCreatedOn;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String salt;
	
	@Column
	private UserType type;
	
	public User() {
		super();
	}

	public User(Long id, boolean isActive, LocalDateTime dateTimeCreatedOn, String firstName, String lastName, String password,
			String userName, String salt, UserType type) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.dateTimeCreatedOn = dateTimeCreatedOn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getDateTimeCreatedOn() {
		return dateTimeCreatedOn;
	}

	public void setDateTimeCreatedOn(LocalDateTime dateTimeCreatedOn) {
		this.dateTimeCreatedOn = dateTimeCreatedOn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
