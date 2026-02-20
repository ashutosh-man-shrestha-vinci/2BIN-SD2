import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatriceDAdjacence extends Graph{
	
	private Map<Integer, Airport>  correspondanceIndiceAirport;
	private Map<Airport, Integer>  correspondanceAirportIndice;
	private Flight[][] matrice= new Flight[0][0];
	private int nbAirport=0;

	public MatriceDAdjacence() {
		super();
		correspondanceAirportIndice= new HashMap<Airport,Integer>();
		correspondanceIndiceAirport= new HashMap<Integer,Airport>();
	}

	@Override
	// Complexit�: ?
	protected void ajouterSommet(Airport a) {	
   correspondanceAirportIndice.put(a,nbAirport);
	 correspondanceIndiceAirport.put(nbAirport,a);
	 Flight[][] newMatrice = new Flight[nbAirport+1][nbAirport+1];

		for (int i = 0; i < nbAirport; i++) {
			for (int j = 0; j <nbAirport ; j++) {
				  newMatrice[i][j]= matrice[i][j];
			}
		}
   matrice= newMatrice;
	 nbAirport++;

	}

	@Override
	// Complexit�: ?
	protected void ajouterArc(Flight f) {
     Integer source = correspondanceAirportIndice.get(f.getSource());
		 Integer destination = correspondanceAirportIndice.get(f.getDestination());
		 matrice[source][destination]= f;
	}

	@Override
	// Complexit�: ?
	public Set<Flight> arcsSortants(Airport a) {
		Set<Flight> result= new HashSet<Flight>();
   Integer indiceAirport= correspondanceAirportIndice.get(a);

		for (int i = 0; i < nbAirport; i++) {
				if(matrice[indiceAirport][i]!=null){
					result.add(matrice[indiceAirport][i]);
			}
		}

		//� compl�ter
		return result;
	}

	@Override
	// Complexit�: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		Integer indiceAirport= correspondanceAirportIndice.get(a1);
		Integer indiceAirport2= correspondanceAirportIndice.get(a2);
		if(matrice[indiceAirport][indiceAirport2]!=null || matrice[indiceAirport2][indiceAirport]!=null){
			return true;
		}
		// � compl�ter
		return false;
	}
	
	

}
