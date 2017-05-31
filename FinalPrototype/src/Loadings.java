import java.io.Serializable;
import java.time.LocalDate;

public class Loadings implements Serializable {

	private String lodingDegree;
	private String loadingYear;
	
	public Loadings(String lodingDegree, String loadingYear)
	{
		this.lodingDegree = lodingDegree;
		this.loadingYear = loadingYear;
	}
	
	public String getLoadingDegree()
	{
		return this.lodingDegree;
	}
	
	public String getLoadingYear()
	{
		return this.loadingYear;
	}
}
