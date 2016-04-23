import java.util.Comparator;


public class ComparyByRank implements Comparator<EurovisionSong> {

		@Override
		public int compare(EurovisionSong arg0, EurovisionSong arg1) {
			return Integer.compare(arg0.getRank(), arg1.getRank());

		}
	}
		
