import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.plaf.FileChooserUI;

import org.apache.commons.io.FileUtils;


public class EurovisionSong {
	private String country;
	private String artist;
	private String title;
	private Integer rank;
	private ImageIcon flag;
	private int change;
	private Date d;
	
	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}



	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public ImageIcon getFlag() {
		return flag;
	}

	public void setFlag(ImageIcon flag) {
		this.flag = flag;
		
	}


	
	public EurovisionSong(String country, String artist, String title, int rank, int change) throws IOException{
		this.country = country;
		this.artist = artist;
		this.title = title;
		this.rank = rank;
		java.net.URL url = getClass().getResource("/flags/24/"+country+".png");
		if (url == null)
		    System.out.println( "Could not find image!" );
		else
			this.flag = new ImageIcon(url);
		this.change = change;
	}
	
	
	public String toString(){
		return country + " " + artist + " " + title + " " + rank;
	}
}
