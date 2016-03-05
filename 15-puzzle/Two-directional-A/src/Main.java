import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int kraj=0,i,j;
		byte m[][]=new byte[4][4];
		byte[][] matricaResenje=new byte[4][4];
		for (i=0;i<4;i++){
			for (j=0;j<4;j++){
				m[i][j]=(byte) s.nextInt();
				matricaResenje[i][j]=(byte) (4*i+j);
			}
		}
		Tabla t=new Tabla(m);
		Tabla tKraj=new Tabla(matricaResenje);
		if (t.daLiJeParnaPermutacija()==0){
			System.out.println("Tabela koju ste upisali je neresiva!");
		}
		else{
			t.rastojanje=0;
			tKraj.rastojanje=0;
			Tabla t1;
			byte [][]pomocniNiz=new byte[16][2];
			for (i=0;i<4;i++){
				for (j=0;j<4;j++){
					pomocniNiz[t.a[i][j]][0]=(byte) i;
					pomocniNiz[t.a[i][j]][1]=(byte) j;
				}
			}
			int minRastojanje=100;
			Tabla t2 = null;
			MojHeap svaStanjaNapred=new MojHeap();
			TreeSet<Tabla> posecenaStanjaNapred=new TreeSet<Tabla>();
			MojHeap svaStanjaNazad=new MojHeap();
			TreeSet<Tabla> posecenaStanjaNazad=new TreeSet<Tabla>();
			svaStanjaNapred.dodaj(t);
			svaStanjaNazad.dodaj(tKraj);
			while (true){
				t1=svaStanjaNapred.extractMin();
				if (posecenaStanjaNazad.contains(t1)){
					
					for (Tabla tp:posecenaStanjaNazad){
						if (tp.compareTo(t1)==0){
								t2=tp;
								break;
						}
					}
					break;
				}					
				t1.napraviNoveIDodajUNiz(svaStanjaNapred, posecenaStanjaNapred,(byte)1,pomocniNiz);
				posecenaStanjaNapred.add(t1);
				
				t1=svaStanjaNazad.extractMin();
				if (posecenaStanjaNapred.contains(t1)){
					for (Tabla tp:posecenaStanjaNapred){
						if (tp.compareTo(t1)==0){
								t2=tp;
								break;
						}
					}
					break;
				}
				t1.napraviNoveIDodajUNiz(svaStanjaNazad, posecenaStanjaNazad,(byte)0,pomocniNiz);
				posecenaStanjaNazad.add(t1);	
			}
			
			
			ArrayList<Tabla> nizZaIspis=new ArrayList<Tabla>();
			Tabla setac=t1;
			while (setac.predakNapred!=null){
				nizZaIspis.add(setac.predakNapred);
				setac=setac.predakNapred;
			}
			setac=t2;
			while (setac.predakNapred!=null){
				nizZaIspis.add(setac.predakNapred);
				setac=setac.predakNapred;
			}
			
			for (i=nizZaIspis.size()-1;i>=0;i--)
				System.out.println(nizZaIspis.get(i));
			System.out.println(t1);
			while (t1.predakNazad!=null){
				System.out.println(t1.predakNazad);
				t1=t1.predakNazad;
			}
			System.out.println("USO 4");
			while (t2.predakNazad!=null){
				System.out.println(t2.predakNazad);
				t2=t2.predakNazad;
			}
		}
	}
}
