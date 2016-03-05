import java.util.ArrayList;

public class MojHeap extends ArrayList<Tabla>{
	
	public MojHeap(){
		super();	
	}
	
	public boolean dodaj(Tabla t){
		add(t);
		int poz=size()-1;
		while ((poz>1)&&(manji(poz,parent(poz)))){
			zameni(poz,parent(poz));
			poz=parent(poz);
		}
		return true;
	}
	
	public Tabla extractMin(){
		Tabla t=get(0);
		set(0, get(size()-1));
		remove(size()-1);
		maxHeapify(0);
		return t;
	}
	
	public void maxHeapify(int i){
		int min=i;
		if ((left(i)<size())&&(manji(left(i),i)))
			min=left(i);
		if ((right(i)<size())&&(manji(right(i),i)))
			min=right(i);
		if (min!=i){
			zameni(min,i);
			maxHeapify(min);
		}
	}
	
	public void zameni(int x, int y){
		Tabla t=get(x);
		set(x, get(y));
		set(y, t);
	}
	
	private boolean manji(int x, int y) {
		if (get(x).lose+get(x).rastojanje<get(y).lose+get(y).rastojanje)
			return true;
		return false;
	}

	private int left(int i){
		return 2*i;
	}
	
	private int right(int i){
		return 2*i+1;
	}
	
	private int parent(int i){
		return i/2;
	}
}