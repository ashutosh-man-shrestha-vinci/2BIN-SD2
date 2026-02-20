import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListeDAdjacence extends Graph{
	
	private Map<Airport,Set<Flight>> outputFlights;

	public ListeDAdjacence(){
		super();
		outputFlights=new HashMap<Airport,Set<Flight>>();

	}

	@Override
	// Complexité: ?
	protected void ajouterSommet(Airport a) {	
		//à compléter
   outputFlights.put(a,new HashSet<>());
	}

	@Override
	// Complexité: ?
	protected void ajouterArc(Flight f) {
	   outputFlights.get(f.getSource()).add(f);
	}

	@Override
	// Complexité: ?
	public Set<Flight> arcsSortants(Airport a) {

		return outputFlights.get(a);
	}

	@Override
	// Complexité: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		for (Flight value : outputFlights.get(a1)) {
	   if(value.getDestination().equals(a2)) {
			 return true;
		 }
		}

		return false;
	}

}
