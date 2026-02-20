import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class ListeDArc extends Graph{
	
	private ArrayList<Flight> flights;

	public ListeDArc() {
		super();
		flights=new ArrayList<Flight>();
	}

	@Override
	// Complexit�: ?
	protected void ajouterSommet(Airport a) {


	}

	@Override
	// Complexit�: ?
	protected void ajouterArc(Flight f) {
		flights.add(f);
	}

	@Override
	// Complexit�: ?
	public Set<Flight> arcsSortants(Airport a) {
		Set<Flight> flights1 = new HashSet<>();
		for (Flight flight : flights) {
			if(flight.getSource().equals(a)) {
				flights1.add(flight);
			}
		}
		return flights1;
	}

	@Override
	// Complexit�: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		for (Flight flight : flights) {
			if(flight.getSource().equals(a1) && flight.getDestination().equals(a2)) {
				return true;
			}
		}
		// � compl�ter
		return false;
	}

}
