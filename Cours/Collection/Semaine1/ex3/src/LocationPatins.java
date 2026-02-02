import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	  HashMap<Integer, ArrayDeque<Integer>> patins;
		HashMap<Integer,LocalTime> locations;
		HashMap<Integer,Integer> pointureParCasier;
	public LocationPatins(int[] casiers) {
		patins = new HashMap<>();
		locations = new HashMap<>();
		pointureParCasier = new HashMap<>();
		for (int i = 0; i < casiers.length; i++) {
			if(!patins.containsKey(casiers[i])) {
				patins.put(casiers[i], new ArrayDeque<>());
			}
			pointureParCasier.put(i,casiers[i]);
			patins.get(casiers[i]).add(i);

		}

	}

	// date1 < date2
	private static double prix(LocalTime date1, LocalTime date2) {
		// 1 euro par milliseconde (c'est assez cher en effet)
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();
		LocalTime l = LocalTime.now();
		  if(patins.containsKey(pointure) && !patins.get(pointure).isEmpty()) {
				int casier = patins.get(pointure).removeFirst();
				 locations.put(casier, l);
				 return casier;
			}


			return -1;
		//a compl�ter

	}

	public double libererCasier(int numeroCasier) {

		  LocalTime debut = locations.remove(numeroCasier);
			int pointure = pointureParCasier.get(numeroCasier);
			patins.get(pointure).push(numeroCasier);
			return prix(debut, LocalTime.now());
	}



}
