import java.io.IOException;
public class Driver {
	public static void main(String[] args) {
		//Polynomial p = new Polynomial();
		//System.out.println(p.evaluate(3));
		double[] c1 = { 6, -2, 5 };
		int[] e1 = {0, 1, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = {-6, 2, -5};
		int[] e2 = {0, 1, 3};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		
		try {
			
            p1.saveToFile("test_output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		//System.out.println(s.poly);
		//System.out.println(s.exp);
		 
		
		/*
		 * if (s.hasRoot(1)) System.out.println("1 is a root of s"); else
		 * System.out.println("1 is not a root of s");
		 */
}