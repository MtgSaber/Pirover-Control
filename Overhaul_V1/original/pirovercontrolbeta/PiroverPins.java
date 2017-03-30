/**
 * Created by MtgSaber on 1/17/17
 *
 * Handles all interaction with the Pi4J project libraries.
 * Also holds data to represent the states of the GPIO pins.
 */

import com.pi4j.io.gpio.*;

final class PiroverPins {
	private static final GpioController gpioController = GpioFactory.getInstance();
	private static final Pin[] pinNumbers = new Pin[] {
		RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03,
		RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07,
		RaspiPin.GPIO_08, RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11,
		RaspiPin.GPIO_12, RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15,
		RaspiPin.GPIO_16, RaspiPin.GPIO_17, RaspiPin.GPIO_18, RaspiPin.GPIO_19,
		RaspiPin.GPIO_20, RaspiPin.GPIO_21, RaspiPin.GPIO_22, RaspiPin.GPIO_23,
		RaspiPin.GPIO_24, RaspiPin.GPIO_25, RaspiPin.GPIO_26, RaspiPin.GPIO_26,
		RaspiPin.GPIO_28, RaspiPin.GPIO_29, RaspiPin.GPIO_30, RaspiPin.GPIO_31
	};
	private static final GpioPinDigitalOutput[] pinsOutput = new GpioPinDigitalOutput[32];
	private static final boolean[] pinsUsed = new boolean[32];
	private static final boolean[] pinStates = new boolean[32];

	PiroverPins() {}

	void pinInitialize(int pinNumber, String name, boolean initialState) {
		if (initialState)
			pinsOutput[pinNumber] = gpioController.provisionDigitalOutputPin(pinNumbers[pinNumber], name, PinState.HIGH);
		else
			pinsOutput[pinNumber] = gpioController.provisionDigitalOutputPin(pinNumbers[pinNumber], name, PinState.LOW);

		pinsOutput[pinNumber].setShutdownOptions(true, PinState.LOW);

		pinsUsed[pinNumber] = true;
		pinStates[pinNumber] = initialState;
	}

	void pinHigh(int[] pinNums) {
		for (int a=0; a<pinNums.length; a++) {
			if (pinsUsed[pinNums[a]]) {
				pinsOutput[pinNums[a]].high();
				pinStates[pinNums[a]] = true;
			}
		}
	}

	void pinLow(int[] pinNums) {
		for (int a=0; a<pinNums.length; a++) {
			if (pinsUsed[pinNums[a]]) {
				pinsOutput[pinNums[a]].low();
				pinStates[pinNums[a]] = false;
			}
		}
	}

	void shutdown() { gpioController.shutdown(); }
}
