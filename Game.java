import java.util.Random;
import java.util.Scanner;

public class Game {

    // constants
    public final static int WIDTH = 10;
    private final static String WALL_CHARACTER = "M";
    private final static String EMPTY_CHARACTER = " ";

    private final Scanner console = new Scanner(System.in);
    private Hero hero;
    private Treasure treasure;
    private boolean isOver;

    public void run(){
        setUp();
        while(!isOver) {
           printWorld();
           move();
        }

        printWorld();
    }

    private void setUp(){
        System.out.print("Enter your hero's name: ");
        String name = console.nextLine();


        Random rand = new Random();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(WIDTH);

        hero = new Hero(name, x, y);

        do{
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(WIDTH);
        } while (x == hero.getX() && y == hero.getY());
        
        treasure = new Treasure(x, y);
    }

    private void printWorld(){
        //Top Wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));

        for(int row = 0; row < WIDTH; row++){
            //Left Wall border
            System.out.print(WALL_CHARACTER);
            for(int col = 0; col < WIDTH; col++){
                if(hero.getX() == col && hero.getY() == row) {
                    System.out.print(hero.getSymbol());
                } else if (row == treasure.getY() && col == treasure.getX()) {
                    System.out.print("T");
                } else {
                    System.out.print(EMPTY_CHARACTER);
                }
            }

            // right wall border
            System.out.println(WALL_CHARACTER);
        }
        //Bottom Wall border
        System.out.println(WALL_CHARACTER.repeat(WIDTH + 2));
    }

    
private void move() {

    System.out.print(hero.getName() + ", move [WASD]: ");
    String move = console.nextLine().trim().toUpperCase();

    if (move.length() != 1) {
        return;
    }

    switch (move.charAt(0)) {
        case 'W':
            hero.moveUp();
            break;
        case 'A':
            hero.moveLeft();
            break;
        case 'S':
            hero.moveDown();
            break;
        case 'D':
            hero.moveRight();
            break;
    }

    if (hero.getX() < 0 || hero.getX() >= WIDTH
            || hero.getY() < 0 || hero.getY() >= WIDTH) {
        System.out.println(hero.getName() + " touched lava! You lose.");
        isOver = true;
    } else if (hero.getX() == treasure.getX() && hero.getY() == treasure.getY()) {
        System.out.println(hero.getName() + " found the treasure! You win.");
        isOver = true;
    }
}
}
