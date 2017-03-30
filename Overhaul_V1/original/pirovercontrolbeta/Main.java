/**
 * Created by MtgSaber on 1/14/2017.
 *
 * Executable of the project.
 *
 * Uses Interpreter to receive and translate user input into commands,
 * then calls Pirover to execute those commands.
 */
public class Main {
    public static void main(String[] args) {
        boolean on = true;
	Pirover pirover = new Pirover();
	pirover.brake();
        System.out.println(Interpreter.getInstructions());
        do {
            switch (Interpreter.getCommand()) {
                case "forward": pirover.forward(); break;
                case "reverse": pirover.reverse(); break;
                case "coast": pirover.coast(); break;
                case "brake": pirover.brake(); break;
                case "left": pirover.left(); break;
                case "right": pirover.right(); break;
                case "shutdown": pirover.shutdown(); on = false; break;
                case "info": System.out.println("Current Pirover data:\n"+pirover.getInfo()); break;
                default: System.out.println("invalid input."); break;
            }
        } while(on);
    }
}
