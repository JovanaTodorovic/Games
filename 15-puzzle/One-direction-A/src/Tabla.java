import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Tabla implements Comparable<Tabla>{
	int a[][]=new int[4][4];
	static Tabla poslednjaTabla=null;
	int rastojanje;
	int lose;
	static int resenje;
	Tabla predak=null;
	public Tabla(int m[][]){
		int i,j;
		a=m;
		lose=kolikoMinPotezaDoResenja();
		predak=null;
		if (resenje==0){
			resenje=1;
			for (i=0;i<4;i++){
				for (j=0;j<4;j++){
					if (a[i][j]!=4*i+j){
						resenje=0;
					}
				}
			}
			if (resenje==1) poslednjaTabla=this;
		}			
	}
	
	public void napraviNoveIDodajUNiz(MojHeap svaStanja,TreeSet<Tabla> posecenaStanja){		
		int i,j,t=0,iPoz=0,jPoz=0;
		for (i=0;(i<4) && (t==0);i++){		//trazi prazno polje u matrici
			for (j=0;j<4;j++){
				if (a[i][j]==15){
					t=1;
					iPoz=i;
					jPoz=j;
					break;
				}
			}
		}
		Tabla t1;
		int [][]m;
		if (zameni(iPoz,jPoz,iPoz-1,jPoz)==1){
			m=napraviMatricu(a);
			zameni(iPoz,jPoz,iPoz-1,jPoz);
			if (daLiListaSadrziMatricu(posecenaStanja, m)==0){
				t1=new Tabla(m);
				t1.predak=this;
				if ((poslednjaTabla!=null)&&(poslednjaTabla.compareTo(t1)==0)){
						poslednjaTabla.predak=this;
					}
				t1.rastojanje=rastojanje+1;
				svaStanja.dodaj(t1);
			}
		}
		if (zameni(iPoz,jPoz,iPoz+1,jPoz)==1){
			m=napraviMatricu(a);
			zameni(iPoz,jPoz,iPoz+1,jPoz);
			if (daLiListaSadrziMatricu(posecenaStanja, m)==0){
				t1=new Tabla(m);
				t1.predak=this;
				if ((poslednjaTabla!=null)&&(poslednjaTabla.compareTo(t1)==0)){
					poslednjaTabla.predak=this;
				}
				t1.rastojanje=rastojanje+1;
				svaStanja.dodaj(t1);
			}
		}
		if (zameni(iPoz,jPoz,iPoz,jPoz-1)==1){
			m=napraviMatricu(a);
			zameni(iPoz,jPoz,iPoz,jPoz-1);
			if (daLiListaSadrziMatricu(posecenaStanja, m)==0){
				t1=new Tabla(m);
				t1.predak=this;
				if ((poslednjaTabla!=null)&&(poslednjaTabla.compareTo(t1)==0)){
						poslednjaTabla.predak=this;
					}
				t1.rastojanje=rastojanje+1;
				svaStanja.dodaj(t1);
			}
		}
		if (zameni(iPoz,jPoz,iPoz,jPoz+1)==1){
			m=napraviMatricu(a);
			zameni(iPoz,jPoz,iPoz,jPoz+1);
			if (daLiListaSadrziMatricu(posecenaStanja, m)==0){
					t1=new Tabla(m);
					t1.predak=this;
					if ((poslednjaTabla!=null)&&(poslednjaTabla.compareTo(t1)==0)){
							poslednjaTabla.predak=this;
						}
					t1.rastojanje=rastojanje+1;
					svaStanja.dodaj(t1);
			}
			
		}	
	}
	
	private int[][] napraviMatricu(int[][] a2) {
		int i,j;
		int[][] m=new int[4][4];
		for (i=0;i<4;i++){
			for (j=0;j<4;j++){
				m[i][j]=a2[i][j];
			}
		}
		return m;
	}

	public int daLiJeParnaPermutacija(){
		int i,j,brojInverzija=0,dodatak=0;
		ArrayList<Integer> brojevi=new ArrayList<Integer>();
		for (i=0;i<4;i++){
			for (j=0;j<4;j++)
				brojevi.add(a[i][j]);
		}
		for (i=0;i<16;i++){
			if (brojevi.get(i)==(Integer)15){
				dodatak=i/4;
			}
			else{
				for (j=i+1;j<16;j++){
					if (brojevi.get(i)>brojevi.get(j)){
						brojInverzija++;
					}
				}
			}
		}
		return (dodatak+brojInverzija)%2;		
	}
	
	private int kolikoMinPotezaDoResenja(){
		int i,j,pozX,pozY,lose=0;
		for (i=0;i<4;i++){
			for (j=0;j<4;j++){
				if (a[i][j]!=15){	
					pozX=a[i][j]/4;
					pozY=a[i][j]%4;
					lose+=abs(pozX-i)+abs(pozY-j);
				}
			}
		}
		return lose;
	}

	private int abs(int i) {
		if (i>0) return i;
		return -i;
	}
	
	private int zameni(int x1,int y1,int x2,int y2){
		int pom;
		if ((x1>=0)&&(x1<=3)&&(y1>=0)&&(y1<=3)&&(x2>=0)&&(x2<=3)&&(y2>=0)&&(y2<=3)){
			pom=a[x1][y1];
			a[x1][y1]=a[x2][y2];
			a[x2][y2]=pom;		
			return 1;
		}
		return 0;
	}
	
	private int daLiListaSadrziMatricu(TreeSet<Tabla> svaStanja,int [][]m){
		Tabla t= new Tabla(m);
		if (svaStanja.contains(t)) return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "Tabla \n" + a[0][0]+" " + a[0][1]+" "+a[0][2]+" "+a[0][3]+"\n"+
				+ a[1][0]+" " + a[1][1]+" "+a[1][2]+" "+a[1][3]+"\n"+
				+ a[2][0]+" " + a[2][1]+" "+a[2][2]+" "+a[2][3]+"\n"+
				+ a[3][0]+" " + a[3][1]+" "+a[3][2]+" "+a[3][3]+"\n";
	}

	@Override
	public int compareTo(Tabla o) {
		int i,j;
		for (i=0;i<4;i++){
			for (j=0;j<4;j++){
				if (a[i][j]>o.a[i][j]) return 1;
				if (a[i][j]<o.a[i][j]) return -1;
			}
		}
		return 0;
	}
}
