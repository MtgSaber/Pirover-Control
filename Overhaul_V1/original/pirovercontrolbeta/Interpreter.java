import java.util.Scanner;

/**
 * Created by MtgSaber on 1/14/2017.
 *
 * Takes user input and translates it into a command
 * which will be executed by the Pirover object in Main.
 */
class Interpreter {
    // key mappings:
    private static final int forward = 5;
    private static final int reverse = 2;
    private static final int coast = 4;
    private static final int brake = 6;
    private static final int left = 1;
    private static final int right = 3;
    private static final int shutdown = 7;
    private static final int info = 9;
    private static Scanner userIn = new Scanner(System.in);

    private static int keyboardIn() {
        while (true) {
            if (userIn.hasNextInt())
                return userIn.nextInt();
            else
                userIn.next();
        }
    }

    static String getCommand() {
        switch (keyboardIn()) {
            case forward: return "forward";
            case reverse: return "reverse";
            case coast: return "coast";
            case brake: return "brake";
            case left : return "left";
            case right: return "right";
            case shutdown: return "shutdown";
            case info: return "info";
            default: return "invalid";
        }
    }

    static String getInstructions() {
        return "Controls:\n\tforward:\t"+forward+
                "\n\treverse:\t"+reverse+"\n\tleft:\t"+left+
                "\n\tright:\t"+right+"\n\tcoast:\t"+coast+
                "\n\tbrake:\t"+brake+"\n\tshutdown:\t"+shutdown+
                "\n\tget current Pirover data:\t"+info;
    }
}
