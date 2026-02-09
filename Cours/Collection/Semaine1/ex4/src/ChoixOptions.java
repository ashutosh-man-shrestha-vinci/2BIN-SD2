import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ChoixOptions {
	
	// associe le nom d'une option avec son objet Option correspondant
	private Map<String, Option> options;
	private HashMap<Etudiant,ArrayList<String>> choix;
	private PriorityQueue<Etudiant> queue;
	//constructeur prenant un entier et une suite de string en param�tres
	//ces string repr�sentent les noms des diff�rentes options possibles
	public ChoixOptions(int nbEtudiantsParOption, String... nomsOption) {
		this.options = new HashMap<String, Option>();
		if (nomsOption.length < 3)
			throw new IllegalArgumentException();
		for (int i = 0; i < nomsOption.length; i++) {
			String nomOption = nomsOption[i];
			options.put(nomOption, new Option(nomOption, nbEtudiantsParOption));
		}
		choix = new HashMap<>();
		queue = new PriorityQueue<>((e1,e2) -> Integer.compare(e2.getMoyenne(), e1.getMoyenne()));
	}

	// cette m�thode encode les pr�f�rences des �tudiants
	// il ne faut pas v�rifier que ces choix soient valides
	public void ajouterPreferences(Etudiant etu, String choix1, String choix2,
			String choix3) {
		if(!choix.containsKey(etu)){
			choix.put(etu,new ArrayList<>());
			queue.add(etu);
		}
    choix.get(etu).add(choix1);
		choix.get(etu).add(choix2);
		choix.get(etu).add(choix3);
	}

	// cette m�thode est appel�e apr�s que les �tudiants aient donn� leurs pr�f�rences
	// cette m�thode attribue les options aux �tudiants en favorisant les �tudiants 
	// ayant les meilleures moyennes si il n'y a plus de place disponible dans certaines 
	// options. Pour les �tudiants faibles, si les deux premi�res options sont pleines, 
	// il faut recourir au troisi�me choix.
	// Cette m�thode doit faire appel é la m�thode inscrireEtudiant de la classe Option.
	public void attribuerOptions() {
		while(!queue.isEmpty()) {
			Etudiant etu = queue.poll();
			for (String s : choix.get(etu)) {
				  Option option = options.get(s);
					if(option.inscrireEtudiant(etu)){
						break;
					}
			}
		}
	}
	
	public String toString(){
		String s="";
		for (Option o:options.values()){
			s=s+o+"\n"+"-----------------"+"\n";
		}
		return s;
	}
}
