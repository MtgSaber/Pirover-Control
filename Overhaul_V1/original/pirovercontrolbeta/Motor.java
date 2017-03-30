/**
 * Created by MtgSaber on 1/14/2017.
 *
 * Represents a physical bi-directional motor whose driver circuit
 * is controlled by the RasPi GPIO.
 */

final class Motor {
	private static int count=0;
	private String name;
	private StringBuilder state = new StringBuilder("coast");
	private final int pinEnableNum;
	private final int pinCWNum;
	private final int pinCCWNum;
	private PiroverPins piroverPins;

	Motor(String name, int[] pins, PiroverPins piroverPins) {
		this.name = name;
		this.pinEnableNum = pins[0];
		this.pinCWNum = pins[1];
		this.pinCCWNum = pins[2];
		this.piroverPins = piroverPins;

		piroverPins.pinInitialize(pinEnableNum, this.name+" Enable", false);
		piroverPins.pinInitialize(pinCWNum, this.name+" Clockwise", false);
		piroverPins.pinInitialize(pinCCWNum, this.name+" Counter-Clockwise", false);

		count++;
	}

	String getName() { return this.name; }
	String getState() { return this.state.toString(); }
	int getCount() { return count; }
	int getPinEnableNum() { return this.pinEnableNum; }
	int getPinCWNum() { return this.pinCWNum; }
	int getPinCCWNum() { return this.pinCCWNum; }

	void cW() {
		piroverPins.pinHigh(new int[] {pinEnableNum, pinCWNum});
		piroverPins.pinLow(new int[] {pinCCWNum});
		state = state.replace(0, state.length(), "clockwise");
	}

	void cCW() {
		piroverPins.pinHigh(new int[] {pinEnableNum, pinCCWNum});
		piroverPins.pinLow(new int[] {pinCWNum});
		state = state.replace(0, state.length(), "counter-clockwise");
	}

	void brake() {
		piroverPins.pinHigh(new int[] {pinEnableNum, pinCWNum, pinCCWNum});
		piroverPins.pinLow(new int[] {});
		state = state.replace(0, state.length(), "brake");
	}

	void coast() {
		piroverPins.pinHigh(new int[] {});
		piroverPins.pinLow(new int[] {pinEnableNum, pinCWNum, pinCCWNum});
		state = state.replace(0, state.length(), "coast");
	}
}
