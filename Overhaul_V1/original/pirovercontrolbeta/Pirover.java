/**
 * Created by MtgSaber on 1/14/2017.
 *
 * Controls all the circuits in Pirover by controlling their corresponding objects.
 */

final class Pirover {
	private static final int[][] motorPinNums = new int[][] {
		{0, 2, 3}, {12, 13, 14}, {1, 4, 5}, {25, 28, 29}
	};
	private static PiroverPins piroverPins = new PiroverPins();
	private static final Motor[] motors = new Motor[] {
		new Motor("Front Left", motorPinNums[0], piroverPins),
		new Motor("Back Left", motorPinNums[1], piroverPins),
		new Motor("Front Right", motorPinNums[2], piroverPins),
		new Motor("Back Right", motorPinNums[3], piroverPins)
	};
    private static StringBuilder state = new StringBuilder("coast");

	Pirover() {}

    void forward() {
        motors[0].cCW();
        motors[2].cW();
        motors[1].cCW();
        motors[3].cW();
        state = state.replace(0, state.length(), "forward");
    }

    void reverse() {
        motors[0].cW();
        motors[2].cCW();
        motors[1].cW();
        motors[3].cCW();
        state = state.replace(0, state.length(), "reverse");
    }

    void coast() {
        motors[0].coast();
        motors[2].coast();
        motors[1].coast();
        motors[3].coast();
        state = state.replace(0, state.length(), "coast");
    }

    void brake() {
        motors[0].brake();
        motors[2].brake();
        motors[1].brake();
        motors[3].brake();
        state = state.replace(0, state.length(), "brake");
    }

    void left() {
        motors[0].cW();
        motors[1].cW();
        motors[2].cW();
        motors[3].cW();
        state = state.replace(0, state.length(), "left");
    }

    void right() {
        motors[0].cCW();
        motors[1].cCW();
        motors[2].cCW();
        motors[3].cCW();
        state = state.replace(0, state.length(), "right");
    }

    void shutdown() {
        motors[0]=null;
        motors[1]=null;
        motors[2]=null;
        motors[3]=null;
		piroverPins.shutdown();
    }

    String getInfo() {
        return "\tCurrent state:\t\t\t"+state.toString()+
                "\n\t"+motors[0].getName()+" Motor's state:\t"+motors[0].getState()+
                "\n\t"+motors[1].getName()+" Motor's state:\t"+motors[1].getState()+
                "\n\t"+motors[2].getName()+" Motor's state:\t"+motors[2].getState()+
                "\n\t"+motors[3].getName()+" Motor's state:\t"+motors[3].getState();
    }
}
