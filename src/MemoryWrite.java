import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemoryWrite {	
	//static ArrayList<Point> ratesList =null;
	public MemoryWrite(HeartRate heartRate) {		
		
	}
	
	public static void continuousWrite(ArrayList<Point> ratesList){
		String fileName = "continuousStoreage.csv";		
		boolean checkIfExists = new File(fileName).exists();
		try {
			FileWriter csvWrite = new FileWriter(fileName,false);
			
								
				if(ratesList!=null){
				for(int i=0; i<1000; i++){
					String f = ratesList.get(i).getY()+"";
					csvWrite.write(f);
					csvWrite.append(',');
	        	
				}
				csvWrite.append('\n');
				Date d = new Date();
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
				String formattedDate = dataFormat.format(d);
				csvWrite.write(formattedDate);
			}
						
			csvWrite.flush();
			csvWrite.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	public static void writeFixed(ArrayList<Point> ratesList){
		String fileName = "fixedStoreage.csv";		
		boolean checkIfExists = new File(fileName).exists();
		try {
			FileWriter csvWrite = new FileWriter(fileName,true);
			
			if(!checkIfExists){
				Date d = new Date();
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
				String formattedDate = dataFormat.format(d);
				csvWrite.write(formattedDate);
				csvWrite.append('\n');
				System.out.println(formattedDate);
				if(ratesList!=null){
				for(int i=0; i<1000; i++){
					String f = ratesList.get(i).getY()+"";
					csvWrite.write(f);
					csvWrite.append(',');
	        	
				}
			}
		}					
			csvWrite.flush();
			csvWrite.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
}
