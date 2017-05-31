import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
	
	private String id;
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private String address;
	private String gender;
	private String contactNumber;
	private String contactEmail;
 
	public Person (String id, String firstName, String lastName, LocalDate birthday, String address, String gender,String contactNumber, String contactEmail )
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
 	}
	// Setters 
	public void setId(String id)
	{
		this.id = id;
	}
	public void setFirstname (String firstName)
	{
		this.firstName = firstName;
	}
	public void setLastname (String lastName)
	{
		this.lastName = lastName;
	}
	public void setbirthday (LocalDate birthday)
	{
		this.birthday = birthday;
	}
	public void setaddress (String address)
	{
		this.address = address;
	}
	public void setgender (String gender)
	{
		this.gender = gender;
	}
	public void setcontactNumber (String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	public void setcontactEmail (String contactEmail)
	{
		this.contactEmail = contactEmail;
	}
 
	
	// Getters
	public String getId()
	{
		return this.id;
	}
	public String getFirstName ()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}

	public String getAddress ()
	{
		return address;
	}
	
	public LocalDate getBirthday ()
	{
		return birthday;
	}
	public String getGender ()
	{
		return gender;
	}
	public String getContactNumber ()
	{
		return contactNumber;
	}
	public String getContactEmail()
	{
		return contactEmail;
	}
 
}
