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
	
	public static void continuousWrite(ArrayList<Point> ratesList, boolean clear){
		String fileName = "continuousStoreage.csv";		
		boolean checkIfExists = new File(fileName).exists();
		try {
			FileWriter csvWrite = new FileWriter(fileName, true);
							
			if(ratesList!=null){
				for(int i=0; i<ratesList.size(); i++) {
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
	public static void writeFixed(ArrayList<Point> ratesList, boolean clear){
		String fileName = "fixedStoreage.csv";		
		File file = new File(fileName);
		
		try {
			Date d = new Date();
			if (clear) {
				FileWriter csvWrite = new FileWriter(fileName, false);
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy h:mm:ss a");
				String formattedDate = dataFormat.format(d);
				csvWrite.write(formattedDate);
				csvWrite.append(',');				
				csvWrite.flush();				
				csvWrite.close();
			}

			if (file.length() < 2048) {
				if(ratesList!=null){
					FileWriter csvWrite = new FileWriter(fileName, true);
					for(int i=0; i<ratesList.size(); i++){
						String f = ratesList.get(i).getY()+"";
						csvWrite.append(f);
						csvWrite.append(',');
		        	
					}				
					csvWrite.flush();
					csvWrite.close();
				}	
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
