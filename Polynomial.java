public class Polynomial {
	double[] poly;

	public Polynomial() {
		poly = new double[1];
		poly[0] = 0;
	}

	public Polynomial(double[] array) {

		poly = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			poly[i] = array[i];
		}
	}

	public Polynomial add(Polynomial addedPoly) {
       
		double[] newPoly = new double[addedPoly.poly.length];
		for (int i = 0; i < addedPoly.poly.length; i++){
			if(i < poly.length){
				newPoly[i] = poly[i] + addedPoly.poly[i];
			}
			else{
				newPoly[i] = addedPoly.poly[i];
			}
		}
		return new Polynomial(newPoly);
	}

	public double evaluate(double x) {
		
		double result = 0.0;
		
		for (int i = 0; i < poly.length; i++) {
			result += (poly[i] * Math.pow(x, i));
		}
		return result;				
	}

	public boolean hasRoot(double x) {
		
		if (evaluate(x) == 0){
			return true;
		}
		return false;		
	}

}