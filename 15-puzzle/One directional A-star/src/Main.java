import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int kraj=0,i,j,m[][]=new int[4][4];
		for (i=0;i<4;i++){
			for (j=0;j<4;j++){
				m[i][j]=s.nextInt();
			}
		}
		Tabla t=new Tabla(m);
		if (t.daLiJeParnaPermutacija()==0){
			System.out.println("Tabela koju ste upisali je neresiva!");
		}
		else{
			t.rastojanje=0;
			kraj=t.resenje;
			MojHeap svaStanja=new MojHeap();
			TreeSet<Tabla> posecenaStanja=new TreeSet<Tabla>();
			svaStanja.dodaj(t);
			while (kraj!=1){
				Tabla t1=svaStanja.extractMin();
				if (t1==null) System.out.println("t1 je null");
				t1.napraviNoveIDodajUNiz(svaStanja, posecenaStanja);
				posecenaStanja.add(t1);
				kraj=t.resenje;
				
			}
			Tabla setac=t.poslednjaTabla;
			ArrayList<Tabla> resenja=new ArrayList<Tabla>();
			resenja.add(t.poslednjaTabla);
			while (setac.predak!=null){
				resenja.add(setac.predak);
				setac=setac.predak;
			}
			System.out.println(resenja);
			System.out.println("resen u "+(resenja.size()-1));
		}
	}
}
