import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class ControleDAcces {
    HashMap<Badge, Employe> acces;
    HashSet<Employe> employeHashMap;
    public ControleDAcces(){
        acces = new HashMap();
        employeHashMap = new HashSet();
	}
	// associe le badge � un employ�
	public void donnerBadge (Badge b, Employe e){
         acces.put(b, e);
	}
	
	// met � jour les employ�s pr�sents dans le batiment
	public void entrerBatiment (Badge b){
        Employe e = acces.get(b);
        employeHashMap.add(e);
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void sortirBatiment (Badge b){
        Employe e = acces.get(b);

        employeHashMap.remove(e);

	}
	
	// renvoie vrai si l'employ� est dans le batiment, faux sinon
	public boolean estDansBatiment (Employe e){

		return employeHashMap.contains(e);
	}

}
