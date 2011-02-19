
public class Tester {

   public static void main(String[] args) {
      int worldSize = 50;
      GenerateWorld world = new GenerateWorld(worldSize);
      Block b = new Block();

      b.fillMatrix();
      b.populate();
      b.printWorld();
      //System.out.println(b.count);
   }
}
