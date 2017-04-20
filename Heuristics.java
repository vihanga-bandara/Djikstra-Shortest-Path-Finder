
/**
 * @author H.B.R.A.K.R.V.K.Bandara || uow ID : w1582949 || IIT No : 2015072
 *
 */
public class Heuristics {

	private float algoman;
	private float algoeucl;
	private float algocheby;
	
public void setHeuristicMan(int x1,int x2, int y1, int y2){
	algoman=((Math.abs(x1-x2))+(Math.abs(y1-y2)));
}
public void setHeuristicEucl(int x1,int x2, int y1, int y2){
	algoeucl=(float) Math.pow((Math.pow(Math.abs(x1-x2),2)
			+(Math.pow(Math.abs(y1-y2),2))),1/2);
}	
public void setHeuristicCheby(int x1,int x2, int y1, int y2){
	algocheby=Math.max(Math.abs(x1-x2),Math.abs(y1-y2));
}	
public float getHeuristicMan(){
	return algoman;
	
}
public float getHeuristicEucl(){
	return algoeucl;
	
}
public float getHeuristicCheby(){
	return algocheby;
	
}

}
