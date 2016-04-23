import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JTable;

import org.apache.commons.io.FileUtils;


public class EurovisionSongsDB {
	
	
	public EurovisionSong[] createDB() throws NumberFormatException, IOException{
		
		EurovisionSong[] eurovisionEntries2016 = new EurovisionSong[43];
		if (!new File("Resources/entries.txt").exists()){
		InputStream in = getClass().getResourceAsStream("/entries.txt"); 
		InputStreamReader iSR = new InputStreamReader(in,"UTF-8");
		Scanner input = new Scanner(iSR);

		int i = 0;
		while (input.hasNext()){
		
		String entry = input.nextLine();
		String country = entry.split(" - ")[0].trim();
		byte[] theByteArray;
		theByteArray = country.getBytes("UTF-8");
		country = new String(theByteArray, Charset.forName("UTF-8"));
		String artist = entry.split(" - ")[1].trim();
		theByteArray = artist.getBytes("UTF-8");
		artist = new String(theByteArray, Charset.forName("UTF-8"));
		String title = entry.split(" - ")[2].trim().split("\"")[1];
		theByteArray = title.getBytes("UTF-8");
		title = new String(theByteArray, Charset.forName("UTF-8"));
		int rank = Integer.valueOf(entry.split(" - ")[3]);
		int change = 0;
		if (entry.split(" - ").length > 4)
			change = Integer.valueOf(entry.split(" - ")[4]);
		
		eurovisionEntries2016[i] = new EurovisionSong(country, artist, title, rank, change);
		i++;
		}
		input.close();
		boolean flag = false;
		for (int j = 0; j < eurovisionEntries2016.length; j++) {
			if (eurovisionEntries2016[j].getRank() == 0)
				flag = true;
		}
		if (flag == false)
			Arrays.sort(eurovisionEntries2016, new ComparyByRank());
		}
		else{
			File f = new File("Resources/entries.txt"); 
			Scanner input = new Scanner(f,"UTF-8");

			int i = 0;
			while (input.hasNext()){
			
			String entry = input.nextLine();
			String country = entry.split(" - ")[0].trim();
			byte[] theByteArray;
			theByteArray = country.getBytes("UTF-8");
			country = new String(theByteArray, Charset.forName("UTF-8"));
			String artist = entry.split(" - ")[1].trim();
			theByteArray = artist.getBytes("UTF-8");
			artist = new String(theByteArray, Charset.forName("UTF-8"));
			String title = entry.split(" - ")[2].trim().split("\"")[1];
			theByteArray = title.getBytes("UTF-8");
			title = new String(theByteArray, Charset.forName("UTF-8"));
			int rank = Integer.valueOf(entry.split(" - ")[3]);
			int change = 0;
			if (entry.split(" - ").length > 4)
				change = Integer.valueOf(entry.split(" - ")[4]);
			eurovisionEntries2016[i] = new EurovisionSong(country, artist, title, rank, change);
			i++;
			
		}
			input.close();	
}

			

		return eurovisionEntries2016;
	}
	
	public void saveDB(JTable table, EurovisionSong[] db) throws IOException{
		//Arrays.sort(db, new ComparyByRank());
		EurovisionSong[] temp = new EurovisionSong[db.length];
		ArrayList<Integer> indexes = new ArrayList<Integer>();
/*	1:	int j = 0;
		for (int i = 0; i < db.length; i++) {
			if (db[i].getRank() != 0){
				temp[j] = db[i];
				j++;
			}		
		}
		
		for (int i = 0; i < db.length; i++) {
			if (db[i].getRank() == 0){
				temp[j] = db[i];
				j++;
			}
			
		}*/
/* 2:		for (int i = 0; i < db.length; i++) {
			if (db[i].getRank() != 0){
				temp[db[i].getRank()-1] = db[i];
				indexes.add(i);
			}
		}
		
		for (int i = 0; i < indexes.size(); i++) {
			System.out.println(indexes.get(i));
		}
		
		for (int i = 0; i < db.length; i++) {
			if (!indexes.contains(i)){
				temp[i]= db[i];
			}
		}*/
		for (int i = 0; i < table.getRowCount(); i++) {
			temp[i] = new EurovisionSong((String)table.getValueAt(i, 1),
					(String)table.getValueAt(i, 2),
					(String)table.getValueAt(i, 3), 
					(Integer)table.getValueAt(i, 4),
					(Integer)table.getValueAt(i, 5));
		}
		File f = new File("Resources/entries.txt"); 
		f.createNewFile();
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream("Resources/entries.txt"), "UTF-8"));
				for (int i = 0; i < temp.length; i++) {
					out.write(temp[i].getCountry() + " - " + temp[i].getArtist() + " - " +"\"" + temp[i].getTitle() +"\"" + " - " + temp[i].getRank() + " - " + temp[i].getChange());
					out.write("\n");
				}
			    
			    out.close();
		}
	

}
