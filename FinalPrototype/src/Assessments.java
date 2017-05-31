import java.io.Serializable;

public class Assessments implements Serializable{

	private String proposalSV;
	private String propsalDM;
	private String proposalFG;
	
	private String interimSV;
	private String interimDM;
	private String interimFG;
	
	private String finalSV;
	private String finalDM;
	private String finalFG;
	
	public Assessments()
	{
		proposalSV = "";
		propsalDM = "";
		proposalFG = "";
		interimSV = "";
		interimDM = "";
		interimFG = "";
		finalSV = "";
		finalDM = "";
        finalFG = "";
	}
	// Setters
	public void setproposalSV(String proposalSV)
	{
		this.proposalSV = proposalSV;
	}
	public void setpropsalDM(String propsalDM)
	{
		this.propsalDM =propsalDM;
	}
	public void setproposalFG(String proposalFG)
	{
		this.proposalFG=proposalFG;
	}
	public void setinterimSV(String interimSV)
	{
		this.interimSV=interimSV;
	}
	public void setinterimDM(String interimDM)
	{
		this.interimDM=interimDM;
	}
	public void setinterimFG(String interimFG)
	{
		this.interimFG=interimFG;
	}
	public void setfinalSV(String finalSV)
	{
		this.finalSV=finalSV;
	}
	public void setfinalDM(String finalDM)
	{
	   this.finalDM=finalDM;	
	}
	public void setfinalFG(String finalFG)
	{
		this.finalFG=finalFG;
	}
	// Getters
	
	public String getproposalSV()
	{
		return this.proposalSV;
	}
	public String getpropsalDM()
	{
		return this.propsalDM;
	}
	public String getproposalFG()
	{
		return this.proposalFG;
	}
	public String getinterimSV()
	{
		return this.interimSV;
	}
	public String getinterimDM()
	{
		return this.interimDM;
	}
	public String getinterimFG()
	{
		return this.interimFG;
	}
	public String getfinalSV()
	{
		return this.finalSV;
	}
	public String getfinalDM()
	{
		return this.finalDM;
	}
	public String getfinalFG()
	{
		return this.finalFG;
	}
}
