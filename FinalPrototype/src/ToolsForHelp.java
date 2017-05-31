import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ToolsForHelp {
	
    public int FilesManagerGetVlaue (String Counter)
    {
    	int temp = 0;
        try
        {
        File file1 = new File("Storage/Reference File.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        String Currentline = "";
        String originalText = "";
         while((Currentline = fileReader.readLine()) != null)
            {
     	   if(Currentline.startsWith(Counter))
     	   {
         	   originalText += Currentline + "\r\n";
         	   Currentline = fileReader.readLine();
         	   temp = Integer.parseInt(Currentline);
     	   }
     	   originalText += Currentline + "\r\n";
        }
        fileReader.close();
       
    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    } return temp;
        
  }
    
    public  void FilesManagerIncrease (String Counter)
    {
        try
        {
        File file1 = new File("Storage/Reference File.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        String Currentline = "";
        String originalText = "";
        int temp;
        while((Currentline = fileReader.readLine()) != null)
            {
     	   if(Currentline.startsWith(Counter))
     	   {
         	   originalText += Currentline + "\r\n";
         	   Currentline = fileReader.readLine();
         	   temp = Integer.parseInt(Currentline);
         	   ++temp;
         	   Currentline = temp + "";
     	   }
     	   originalText += Currentline + "\r\n";
        }
        fileReader.close();
        
        FileWriter writer = new FileWriter("Storage/Reference File.txt");
        writer.write(originalText);
        writer.close();

    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
    }   

    public  void FilesManagerDecrease (String Counter)
    {
        try
        {
        File file1 = new File("Storage/Reference File.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        String Currentline = "";
        String originalText = "";
        int temp;
        while((Currentline = fileReader.readLine()) != null)
            {
     	   if(Currentline.startsWith(Counter))
     	   {
         	   originalText += Currentline + "\r\n";
         	   Currentline = fileReader.readLine();
         	   temp = Integer.parseInt(Currentline);
         	   --temp;
         	   Currentline = temp + "";
     	   }
     	   originalText += Currentline + "\r\n";
        }
        fileReader.close();
        
        FileWriter writer = new FileWriter("Storage/Reference File.txt");
        writer.write(originalText);
        writer.close();

    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
    }  
    
    public  void FilesManagerISetValue(String Counter, int count)
    {
        try
        {
        File file1 = new File("Storage/Reference File.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        String Currentline = "";
        String originalText = "";
        int temp;
        while((Currentline = fileReader.readLine()) != null)
            {
     	   if(Currentline.startsWith(Counter))
     	   {
         	   originalText += Currentline + "\r\n";
         	   Currentline = fileReader.readLine();
         	   temp = Integer.parseInt(Currentline);
         	   temp = temp + count;
         	   Currentline = temp + "";
     	   }
     	   originalText += Currentline + "\r\n";
        }
        fileReader.close();
        
        FileWriter writer = new FileWriter("Storage/Reference File.txt");
        writer.write(originalText);
        writer.close();
    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
    }   
    
    
    public  void FilesManagerSetNewValue(String Counter, int count)
    {
        try
        {
        File file1 = new File("Storage/Reference File.txt");
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        String Currentline = "";
        String originalText = "";
        int temp;
        while((Currentline = fileReader.readLine()) != null)
            {
     	   if(Currentline.startsWith(Counter))
     	   {
         	   originalText += Currentline + "\r\n";
         	   Currentline = fileReader.readLine();
         	   temp = Integer.parseInt(Currentline);
         	   temp = count;
         	   Currentline = temp + "";
     	   }
     	   originalText += Currentline + "\r\n";
        }
        fileReader.close();
        
        FileWriter writer = new FileWriter("Storage/Reference File.txt");
        writer.write(originalText);
        writer.close();
    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
    }   
    
}
