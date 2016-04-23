import java.util.Comparator;


public class ComparyByCountry implements Comparator<EurovisionSong>{

	@Override
	public int compare(EurovisionSong arg0, EurovisionSong arg1) {
		return arg0.getCountry().compareTo(arg1.getCountry());
	}

}
