import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Staff extends Person implements Serializable{

  	private String specialization;
  	private String jobType;	
  	private char specialFinder;
 
	
	public Staff (String id, String firstName, String lastName, LocalDate birthday, String address, String gender,String contactNumber,
				  String contactEmail, String specialization, String jobType, char specialFinder)
	{
		super (id,  firstName,  lastName,  birthday,  address,  gender, contactNumber,  contactEmail );
		this.specialization = specialization;
		this.jobType = jobType;
		this.specialFinder = specialFinder;
 	}

	// Setters
	public void setSpecialization(String specialization)   
	{
		this.specialization = specialization;
	}
	public void setJobType(String jobType)   
	{
		this.jobType = jobType;
	}
	
	// Getters
	public String getSpecialization()
	{
		return specialization;
	}
	public String getJobType()
	{
		return jobType;
	}
	public char getSpecialFinder()
	{
		return specialFinder;
	}
}
