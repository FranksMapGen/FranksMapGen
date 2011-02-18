import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class Block {

   // x and y dimensions of map
   int size = GenerateWorld.arraySize;

   // variable for blocks x location
   int xLoc;

   // variable for blocks y location
   int yLoc;

   // one if block has been visited; initialized to zero
   int visited;

   // Frank?
   int selector;

   // ??
   int percentage = 10;

   // ??
   int x;

   // ??
   int y;

   // ??
   int count = 0;

   // Block object variables.
   Block source;
   Block left;
   Block right;
   Block up;
   Block down;
   Block temp;

   //What the generator will print out.
   String icon;

   Block[][] blocks = new Block[size][size];
   Random generator = new Random();
   Queue<Block> q = new LinkedList<Block>();
   Grass grass = new Grass();
   Water water = new Water();


   //Default constructor.
   public Block () {
   }

   //Constructor with embedded locations.
   public Block(int x, int y) {
      this.x = x;
      this.y = y;
   }

   //Calls X location.
   public int getX(Block block) {
      return block.x;
   }

   //Calls Y location.
   public int getY(Block block) {
      return block.y;
   }

   //Fills the matrix with blocks.
   public void fillMatrix() {
      for (int i = 0; i < size; i++) {
         for (int k = 0; k < size; k++) {
            blocks[i][k] = new Block(i, k);
            blocks[i][k].visited = 0;
            blocks[i][k].icon = "O";
         }
      }
   }

   //Prints world.
   public void printWorld() {
      for (int i = 0; i < size; i++) {
         for (int k = 0; k < size; k++) {
            System.out.print(blocks[i][k].icon);
            //System.out.print(blocks[k][i].visited);
         }
         System.out.print("\n");
      }
   }


   //Block Traversal methods:
   public Block getLeft() {
      if (xLoc != 0) {
         left = blocks[xLoc-1][yLoc];
         return left;
      }
      else {
         return left;
      }
   }

   public Block getRight() {
      if (xLoc < size - 1) {
         right = blocks[xLoc+1][yLoc];
         return right;
      }
      else {
         return right;
      }
   }

   public Block getUp() {
      if (yLoc != 0) {
         up = blocks[xLoc][yLoc-1];
         return up;
      }
      else {
         return up;
      }
   }

   public Block getDown() {
      if (yLoc < size - 1) {
         down = blocks[xLoc][yLoc+1];
         return down;
      }
      else {
         return down;
      }
   }

   //Step 1: Check to see if where you're placing the source block has
   //	has been visited.
   //Step 2: If it hasn't been visited, set it to visited.
   //Step 3: Push that location onto the queue.
   //Step 4: Pop the top of the queue, set temp to popped block.
   //Step 5: Check all 4 directions around temp, add all unvisited blocks
   //	to queue.
   //Step 6: Repeat 4-5 until the queue is empty.
   public void populate() {
      xLoc = generator.nextInt(size);
      yLoc = generator.nextInt(size);
      selector = generator.nextInt(percentage);

      // 60% chance to be a water tile
      if (selector > 6) {
         icon = water.getTile();
      }
      // 40% chane to be a grass tile
      else {
         icon = grass.getTile();
      }

      source = blocks[xLoc][yLoc];
      if (source.visited == 0) {
         source.icon = icon;
         source.visited = 1;
         q.add(source);
         //System.out.println("Adding " + source);
         while(!q.isEmpty()) {
            temp = q.remove();
            xLoc = temp.x;
            yLoc = temp.y;
            temp.icon = icon;
            count = count + 1;

            if (getLeft() != null) {
               if (blocks[getLeft().x][getLeft().y].visited == 0) {
                  q.add(getLeft());
                  blocks[getLeft().x][getLeft().y].visited = 1;
               }
            }
            if (getUp() != null) {
               if (blocks[getUp().x][getUp().y].visited == 0) {
                  q.add(getUp());
                  blocks[getUp().x][getUp().y].visited = 1;
               }
            }
            if (getRight() != null) {
               if (blocks[getRight().x][getRight().y].visited == 0) {
                  q.add(getRight());
                  blocks[getRight().x][getRight().y].visited = 1;
               }
            }
            if (getDown() != null) {
               if (blocks[getDown().x][getDown().y].visited == 0) {
                  q.add(getDown());
                  blocks[getDown().x][getDown().y].visited = 1;
               }
            }

         }
      }
   }
}
