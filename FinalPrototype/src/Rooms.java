import java.io.Serializable;

public class Rooms implements Serializable {

	private int roomId;
	private String roomName;
	private boolean roomState;
	private double locationX;
	private double locationY;
	private String roomInfo;
	
	public Rooms(int roomId,String roomName,boolean roomState,double locationX,double locationY,String roomInfo )
	{
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomState = roomState;
		this.locationX = locationX;
		this.locationY = locationY;
		this.roomInfo = roomInfo;
	}
	
	// Setters
	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}
	public void setRoomState(boolean roomState)
	{
		this.roomState = roomState;
	}
	public void setRoomLocationX(double locationX)
	{
		this.locationX = locationX;
	}
	public void setRoomLocationY(double locationY)
	{
		this.locationY = locationY;
	}
	public void setRoomInfo(String roomInfo)
	{
		this.roomInfo = roomInfo;
	}
	
	// Getters
	public int getRoomId()
	{
		return this.roomId;
	}
	public String getRoomName()
	{
		return this.roomName ;
	}
	public boolean getRoomState()
	{
		return this.roomState ;
	}
	public double getRoomLocationX()
	{
		return this.locationX ;
	}
	public double getRoomLocationY()
	{
		return this.locationY;
	}
	public String getRoomInfo()
	{
		return this.roomInfo;
	}
}
