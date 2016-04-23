import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jaco.mp3.player.MP3Player;
import jaco.mp3.player.N;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


public class SimplePlayer extends JFrame {

	JButton pause = new JButton();
	JButton play = new JButton();
	
	HashMap<String, String> songs = new HashMap<String,String>();

	InputStream in = getClass().getResourceAsStream("/Links.txt"); 
	InputStreamReader iSR = new InputStreamReader(in,"UTF-8");
	
	
	
	public SimplePlayer(String country, String artist, String songName, ImageIcon flag) throws MalformedURLException, UnsupportedEncodingException, FileNotFoundException{
		
		Scanner input = new Scanner(iSR);

		ImageIcon play_ = new ImageIcon(getClass().getResource("/product_text_play.gif"));
		play_.setDescription("Play");
		ImageIcon stop_ = new ImageIcon(getClass().getResource("/pause-icon.jpg"));
		stop_.setDescription("Stop");
		
		while(input.hasNextLine()){
			String temp = input.nextLine();
			songs.put(temp.split(" - ")[0],temp.split(" - ")[1]);
		}
		
		
		URL songToPlay = null;
		if (songName.equals("Fairytale Love")){
			songToPlay = new URL((String)songs.get("Fairytale Love"));	
		}
		else
			songToPlay = new URL((String)songs.get(country));	
		
		MP3Player m = new MP3Player(songToPlay);
		m.play();
		play.setEnabled(false);
		
		pause.setIcon(stop_);
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.pause();
				play.setEnabled(true);
			}
		});
		
		play.setIcon(play_);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.play();
				
			}
		});
		

		
		add(play);
		add(pause);
		setLayout(new GridLayout(1, 2));
		
/*		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Play")){
					m.play();
					play.setText("Pause");
				}
				else if (e.getActionCommand().equals("Pause")){
					m.pause();
					play.setText("Play");
				}
				
				
			}
		});*/
		
		

			

		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				if (m.isStopped())
					dispose();
				
			}
					
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				//m.stop();
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				m.stop();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				m.stop();
				
			}
			
		});
		
		setTitle(artist +" - " + songName);
		setIconImage(flag.getImage());
		setSize(400, 100);
		setVisible(true);
		centreWindow(this);
		

	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}
