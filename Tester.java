
public class Tester {

	public static void main(String[] args) {
		GenerateWorld world = new GenerateWorld(3);
		Block b = new Block();
		
		b.fillMatrix();
		b.populate();
		b.printWorld();
		//System.out.println(b.count);
	}
}
