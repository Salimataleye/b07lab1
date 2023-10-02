import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class Polynomial {
	double[] poly;
	int[] exp;

	public Polynomial() {
		poly = new double[1];
		exp = new int[1];
		poly[0] = 0;
		exp[0] = 0;
	}

	public Polynomial(double[] array, int[] powers) {

		poly = new double[array.length];
		exp = new int[powers.length];
		for (int i = 0; i < array.length; i++) {
			poly[i] = array[i];
		}
		for (int j = 0; j < powers.length; j++) {
			exp[j] = powers[j];
		}
				
	}

	public Polynomial add(Polynomial addedPoly) {

		int maxLength = Math.max(this.poly.length, addedPoly.poly.length);
		
		ArrayList<Double> newPoly = new ArrayList<>();
		ArrayList<Integer> newExp = new ArrayList<>();
		int numzeros = 0;
		
		if(maxLength == addedPoly.poly.length) {
			for(int i = 0; i < maxLength; i++) {
				for(int j = 0; j < this.poly.length; j++) {
					//In case there is a match
					if(addedPoly.exp[i] == this.exp[j]) {
						newPoly.add(addedPoly.poly[i]+this.poly[j]);
						newExp.add(addedPoly.exp[i]);
						if(addedPoly.poly[i]+this.poly[j] == 0.00) {
							numzeros++;
						}
					}
				}
				if(!newExp.contains(addedPoly.exp[i])) {
					newPoly.add(addedPoly.poly[i]);
					newExp.add(addedPoly.exp[i]);
				}
								
			}
			
			//if any leftovers in the smaller array
			for(int i = 0; i < this.poly.length; i++) {
				if(!newExp.contains(this.exp[i])) {
					newPoly.add(this.poly[i]);
					newExp.add(this.exp[i]);
				}
			}
		}
		else {
			for(int i = 0; i < maxLength; i++) {
				for(int j = 0; j < addedPoly.poly.length; j++) {
					if(this.exp[i] == addedPoly.exp[j]) {
						newPoly.add(addedPoly.poly[j]+this.poly[i]);
						newExp.add(this.exp[i]);
						if(addedPoly.poly[j]+this.poly[i] == 0.00) {
							numzeros++;
						}
					}
				}
				if(!newExp.contains(this.exp[i])) {
					newPoly.add(this.poly[i]);
					newExp.add(this.exp[i]);
				}
			}
			for(int i = 0; i < addedPoly.poly.length; i++) {
				if(!newExp.contains(addedPoly.exp[i])) {
					newPoly.add(addedPoly.poly[i]);
					newExp.add(addedPoly.exp[i]);
				}
			}
		}
		double[] coefficients = new double[newPoly.size() - numzeros];
		int[] exponents = new int[newExp.size() - numzeros];
		
		int j =0;
		for(int i = 0; i < newExp.size(); i++) {
			if (newPoly.get(i) != 0) {
				coefficients[j] = newPoly.get(i);
				j++;
						
			}
		}
		j =0;
		for(int i = 0; i < newPoly.size(); i++) {
			if(newPoly.get(i) != 0) {
				exponents[j] = newExp.get(i);
				j++;
		    }
		}
		
		return new Polynomial(coefficients, exponents);	
			
	}
		
		
	public double evaluate(double x) {

		double result = 0.0;

		for (int i = 0; i < poly.length; i++) {
			result += (poly[i] * Math.pow(x, i));
		}
		return result;
	}

	public boolean hasRoot(double x) {

		if (evaluate(x) == 0) {
			return true;
		}
		return false;
	}
	
	public Polynomial multiply(Polynomial p2) {
		
		Polynomial finalPoly = new Polynomial();
		
		for (int i = 0; i < this.poly.length; i++ ) {
	
			for(int j = 0; j < p2.poly.length; j++){
				
				double coefficient = this.poly[i] * p2.poly[j];
				int exponent = this.exp[i] + p2.exp[j];
				finalPoly = finalPoly.add(new Polynomial(new double[] {coefficient}, new int[] {exponent}));
			}			
		}
		   
        return finalPoly;
	}
	
	public Polynomial(File file) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        if (line == null) {
            throw new IllegalArgumentException("Empty File");
        }

 
        String[] terms = line.split("[-+]");

        double[] coefficients = new double[terms.length];
        int[] exponents = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String term = terms[i];

            String[] parts = term.split("x");
            if (parts.length == 1) {
            	
                coefficients[i] = Double.parseDouble(parts[0]);
                exponents[i] = 0;
            } 
            
            else {
            	
                coefficients[i] = Double.parseDouble(parts[0]);
                exponents[i] = Integer.parseInt(parts[1]);
            }

        
            if (term.startsWith("-")) {
                coefficients[i] *= -1;
            }
        }

        poly = coefficients;
        exp = exponents;
        reader.close();
    }
	
	public void saveToFile(String fileName) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

		for (int i = 0; i < this.poly.length; i++) {
			
			double coefficient = this.poly[i];
			int exponent = this.exp[i];

			if (i != 0 && coefficient >= 0) {
				writer.write("+");
			}

			if (coefficient != 0) {
				writer.write(Double.toString(coefficient));

				if (exponent != 0) {
					
					writer.write("x");
					writer.write(Integer.toString(exponent));

				}
			}
		}

		writer.close();
	}	

}