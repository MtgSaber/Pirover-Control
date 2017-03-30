/**
 * Author: Andrew Arnold (2/26/2017)
 */
class PiroverData {
    private static boolean inUse;
    private static boolean[] componentsUsed;
    private static Settings settings;

    private PinAccess gpio;

    private static StringBuilder chassisState;
    private static StringBuilder leftMotorState;
    private static StringBuilder rightMotorState;
    private static double chassisSpeed;
    private static int[] cameraPosition;
    private static int[] arduinoSignals;

    private static int[] arduinoSignalPinNums;

    private static String[] motorGroupNames;
    private static String[] servoNames;

    PiroverData(Settings settings) throws Exception {
        if (!inUse)
            inUse = true;
        else
            throw new Exception("There should exist only ONE instance of PiroverData.");

        PiroverData.settings = settings;
        this.gpio = new PinAccess();
        PiroverData.chassisState = new StringBuilder("coast");
        PiroverData.leftMotorState = new StringBuilder("coast");
        PiroverData.rightMotorState = new StringBuilder("coast");
        PiroverData.cameraPosition = PiroverData.settings.getDefaultCameraPosition();
        PiroverData.arduinoSignals = new int[6];
        PiroverData.arduinoSignalPinNums = PiroverData.settings.getSignalPins();
        PiroverData.motorGroupNames = new String[] {"left", "right"};
        PiroverData.servoNames = new String[] {"yaw", "pitch"};
    }

    String getChassisState() { return chassisState.toString(); }
    String getLeftMotorState() { return leftMotorState.toString(); }
    String getRightMotorState() { return rightMotorState.toString(); }
    double getChassisSpeed() { return chassisSpeed; }
    int[] getCameraPosition() { return cameraPosition; }
    String getCameraPositionString() { return ""+cameraPosition[0]+" , "+cameraPosition[1]; }
    int[] getArduinoSignals() { return arduinoSignals; }
    int[] getArduinoSignalPinNums() { return arduinoSignalPinNums; }

    void setChassisState(String state) { chassisState.replace(0, chassisState.length(), state); }
    void setLeftMotorState(String state) { leftMotorState.replace(0, leftMotorState.length(), state); }
    void setRightMotorState(String state) { rightMotorState.replace(0, rightMotorState.length(), state); }
    void setChassisSpeed(double speed) { chassisSpeed = speed; }
    void setCameraPosition(int[] position) { cameraPosition = position; }

    @Override
    public String toString() {
        return (
                "Data:\n\tChassis Info:\n\t\tChassis State:\t"+getChassisState()
                +"\n\t\tLeft Motor Group State:\t"+getLeftMotorState()
                +"\n\t\tRight Motor Group State:\t"+getRightMotorState()
                +"\n\t\tChassis Speed:\t"+getChassisSpeed()
                +"\n\tCamera Position:\t"+getCameraPositionString()
                +"\n\tArduino Info:\n\t\tArduino Signals:\n\t\t\t0:"+arduinoSignals[0]
                +"\n\t\t\t1:\t"+arduinoSignals[1]+"\n\t\t\t2:\t"+arduinoSignals[2]
                +"\n\t\t\t3:\t"+arduinoSignals[3]+"\n\t\t\t4:\t"+arduinoSignals[4]
                +"\n\t\t\t5:\t"+arduinoSignals[5]+"\n\t\tArduino Signal Pin Numbers:"
                +"\n\t\t\t0:\t"+arduinoSignalPinNums[0]+"\n\t\t\t1:\t"+arduinoSignalPinNums[1]
                +"\n\t\t\t2:\t"+arduinoSignalPinNums[2]+"\n\t\t\t3:\t"+arduinoSignalPinNums[3]
                +"\n\t\t\t4:\t"+arduinoSignalPinNums[4]+"\n\t\t\t5:\t"+arduinoSignalPinNums[5]
        );
    }
}
