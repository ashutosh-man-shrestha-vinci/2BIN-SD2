import java.time.LocalTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	private Map<Integer,Deque<Integer>> mapPointureCasiersDisponibles;
	private LocalTime[] debutLocations;
	private int[] casiers;

	public LocationPatins(int[] casiers) {
		this.casiers = new int[casiers.length];
		this.debutLocations = new LocalTime[casiers.length];
		this.mapPointureCasiersDisponibles = new HashMap<Integer,Deque<Integer>>();
		for(int i = 0; i < casiers.length; i++) {
			this.casiers[i] = casiers[i];
			Deque<Integer> file = mapPointureCasiersDisponibles.get(casiers[i]);
			if(file == null) {
				file = new ArrayDeque<Integer>();
				this.mapPointureCasiersDisponibles.put(casiers[i],file);
			}
			file.add(i);
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

		Deque<Integer> file = this.mapPointureCasiersDisponibles.get(pointure);
		if(file == null||file.isEmpty()) {
			return -1;
		}
		int numCasier = file.poll();
		this.debutLocations[numCasier] = l;
		return numCasier;
	}

	public double libererCasier(int numeroCasier) {
		if (numeroCasier < 0|| numeroCasier >= this.casiers.length){
			throw new IllegalArgumentException();
		}
		LocalTime finLocation = LocalTime.now();
		LocalTime debutLocation = this.debutLocations[numeroCasier];
		this.mapPointureCasiersDisponibles.get(this.casiers[numeroCasier]).add(numeroCasier);
		this.debutLocations[numeroCasier] = null;
		return this.prix(debutLocation,finLocation);
	}

}