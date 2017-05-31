import java.io.Serializable;
import java.time.LocalDate;

public class Students extends Person implements Serializable{

  	private String pathway;
  	private String course;
  	private Staff superVisor;
  	private Staff doubleMarker;
  	private String additionalNotes;
  	private String topic;
  	private Assessments assessments;
  	private String year;
  	private String level;
  	
  	public Students(String id, String firstName, String lastName, LocalDate birthday, String address,
  			 		String gender,String contactNumber, String contactEmail, String pathway,String course, 
  			 		String additionalNotes, String topic, String year, String level,  Assessments assessments )
  	{
  		super (id, firstName, lastName, birthday, address, gender,contactNumber, contactEmail );
  		this.pathway = pathway;
  		this.course=course;
  		this.additionalNotes = additionalNotes;
  		this.topic = topic;
  		this.year = year;
  		this.level = level;
  		this.assessments = assessments;
  	}
  	
  	// Setters
  	public void setPathway(String pathway)
  	{
  		this.pathway=pathway;
  	}
  	public void setCourse(String course)
  	{
  		this.course=course;
  	}
  	public void setSuperVisor(Staff superVisor)
  	{
  		this.superVisor=superVisor;
  	}
  	public void setDoubleMarker(Staff doubleMarker)
  	{
  		this.doubleMarker=doubleMarker;
  	}
  	public void setAdditionalNotes(String additionalNotes)
  	{
  		this.additionalNotes=additionalNotes;
  	}
  	public void setTopic(String topic)
  	{
  		this.topic=topic;
  	}
  	public void setAssessments(Assessments assessments)
  	{
  		this.assessments=assessments;
  	}
  	
  	// Getters
  	public String getPathway()
  	{
  		return this.pathway;
  	}
  	public String getCourse()
  	{
  		return this.course;
  	}
  	public String getSuperVisor()
  	{
  		return superVisor.getId();
  	}
  	public String getDoubleMarker()
  	{
  		return doubleMarker.getId();
  	}
  	public String getAdditionalNotes()
  	{
  		return this.additionalNotes;
  	}
 	public String getTopic()
  	{
  		return this.topic;
  	}
 	
 	public String getYear()
 	{
 		return this.year;
 	}
 	
 	public String getDegree()
 	{
 		return this.level;
 	}
 	
 	public Assessments getAssessments()
 	{
 		return assessments;
 	}
}