import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.Pin;

/**
 * Author: Andrew Arnold "MtgSaber" (2/26/2017)
 *
 * This acts as a neat little wrapper for a few basic pi4j uses.
 * All pin numbering is based on WiringPi. Information on that can be found on pi4j's project site.
 * BE SURE TO CHECK PIN-TO-MODE COMPATIBILITY ON PI4J'S SITE! NOT ALL PINS SUPPORT ALL MODES.
 * DON'T FORGET TO INVOKE "shutdown()" BEFORE YOUR PROGRAM EXITS!
 * Also, don't forget to handle those Exceptions :P
 */
class PinAccess {
    // Autological.
    private static final GpioController GPIO_CONTROLLER = GpioFactory.getInstance();

    /*
     * represents Digital Output pins. non-initialized/non-digital-output pins are null.
     * the array indices represent the pins' corresponding WiringPi numberings.
     * (ie. PINS_BOOL[12] represents WiringPi's Pin 12). There are a total of 32 pins in the WiringPi setup.
     */
    private static final GpioPinDigitalOutput[] PINS_BOOL = new GpioPinDigitalOutput[32];
    private static final GpioPinPwmOutput[] PINS_PWM = new GpioPinPwmOutput[32];

    // for syntactical purposes.
    private static final Pin[] PIN_NUMBERS = new Pin[] {
            RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03,
            RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07,
            RaspiPin.GPIO_08, RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11,
            RaspiPin.GPIO_12, RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15,
            RaspiPin.GPIO_16, RaspiPin.GPIO_17, RaspiPin.GPIO_18, RaspiPin.GPIO_19,
            RaspiPin.GPIO_20, RaspiPin.GPIO_21, RaspiPin.GPIO_22, RaspiPin.GPIO_23,
            RaspiPin.GPIO_24, RaspiPin.GPIO_25, RaspiPin.GPIO_26, RaspiPin.GPIO_26,
            RaspiPin.GPIO_28, RaspiPin.GPIO_29, RaspiPin.GPIO_30, RaspiPin.GPIO_31
    };

    PinAccess() {} // class must be kept in active memory in order for the gpio pins to maintain state.

    // initializes a pin for digital output.
    void initializeDigitalPin(int pinNum) throws Exception {
        if (PINS_BOOL[pinNum] != null && PINS_PWM[pinNum] != null)
            throw new Exception("Pin " + pinNum + " has already been initialized.");
        else {
            PINS_BOOL[pinNum] = GPIO_CONTROLLER.provisionDigitalOutputPin(PIN_NUMBERS[pinNum]);
        }
    }

    // initializes a pin for pwm output.
    void initializePwmPin(int pinNum) throws Exception {
        if (PINS_BOOL[pinNum] != null && PINS_PWM[pinNum] != null)
            throw new Exception("Pin " + pinNum + " has already been initialized.");
        else {
            PINS_PWM[pinNum] = GPIO_CONTROLLER.provisionSoftPwmOutputPin(PIN_NUMBERS[pinNum]);
        }
    }

    // sets a digital pin to HIGH
    void pinHigh(int pinNum) throws Exception {
        if (PINS_BOOL[pinNum] == null)
            throw new Exception("Pin " + pinNum + " has not been initialized as a Digital Output Pin.");
        else {
            PINS_BOOL[pinNum].high();
        }
    }

    // sets a digital pin to LOW
    void pinLow(int pinNum) throws Exception {
        if (PINS_BOOL[pinNum] == null)
            throw new Exception("Pin " + pinNum + " has not been initialized as a Digital Output Pin.");
        else {
            PINS_BOOL[pinNum].low();
        }
    }

    // for a PWM frequency F * Hz = 10000 / R, "range" sets the value for R. R is measured in milliseconds.
    void setPwmRange(int pinNum, int range) throws Exception {
        if (PINS_PWM[pinNum] == null)
            throw new Exception("Pin " + pinNum + " has not been initialized as a Pwm Output Pin.");
        else {
            PINS_PWM[pinNum].setRange(range);
        }
    }

    // "value" sets the "ON" time for the PWM pulse, and is measured in milliseconds.
    void setPwmValue(int pinNum, int value) throws Exception {
        if (PINS_PWM[pinNum] == null)
            throw new Exception("Pin " + pinNum + " has not been initialized as a Pwm Output Pin.");
        else {
            PINS_PWM[pinNum].setValue(value);
        }
    }

    // properly shuts down the gpio controller to prevent... issues.
    void shutdown() {
        GPIO_CONTROLLER.shutdown();
    }
}
