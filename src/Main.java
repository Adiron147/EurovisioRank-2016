import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;



public class Main {
//This is the main for running the GUI
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				EurovisionRankMainFrame frame = new EurovisionRankMainFrame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}
