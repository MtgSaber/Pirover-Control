import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Andrew Arnold (2/26/2017)
 */
class Settings {
    private static boolean inUse;
    private static String settingsFile = "./Data/Settings.config";
    private static String[][] commandBindings;
    private static int[] arduinoSignalPins;
    private static int[] defaultCameraPosition;

    Settings() throws Exception {
        if (Settings.inUse)
            throw new Exception("There should exist only ONE instance of Settings.");
        else
            inUse = true;

        ArrayList<ArrayList<String>> commandBindings = new ArrayList<>();
        commandBindings.add(new ArrayList<>());
        commandBindings.add(new ArrayList<>());

        ArrayList<Integer> arduinoSignalPins = new ArrayList<>(), defaultCameraPosition = new ArrayList<>();

        try {
            for (String str: new Scanner(new File(Settings.settingsFile)).useDelimiter("\\A").next().split("\n"))
                if (str.length()>1) {
                    if (str.charAt(1)==':')
                        switch (str.charAt(0)) {
                            case 'B':
                                commandBindings.get(0).add(str.substring(3, str.indexOf(':', 3)));
                                commandBindings.get(1).add(str.substring(
                                            str.indexOf(':', 3)+2, str.indexOf(';')
                                        ));
                                break;
                            case 'P':
                                arduinoSignalPins.add(Integer.parseInt(str.substring(3, str.indexOf(';'))));
                                break;
                            case 'C':
                                defaultCameraPosition.add(Integer.parseInt(str.substring(3, str.indexOf(';'))));
                                break;
                            default:
                                System.out.println("Err in Settings Loader: Unexpected Variable in:\n\t\""+str+"\"");
                        }
                }

            Settings.commandBindings = new String[2][];
            Settings.commandBindings[0] = commandBindings.get(0).toArray(new String[commandBindings.get(0).size()]);
            Settings.commandBindings[1] = commandBindings.get(1).toArray(new String[commandBindings.get(1).size()]);

            Settings.arduinoSignalPins = new int[arduinoSignalPins.size()];
            for (Integer i: arduinoSignalPins)
                Settings.arduinoSignalPins[arduinoSignalPins.indexOf(i)] = i;

            Settings.defaultCameraPosition = new int[defaultCameraPosition.size()];
            for (Integer i: defaultCameraPosition)
                Settings.arduinoSignalPins[defaultCameraPosition.indexOf(i)] = i;

        } catch(FileNotFoundException ex) {
            System.out.println("Settings File not found.");
        }
    }

    String[][] getBindings() { return Settings.commandBindings; }
    int[] getSignalPins() { return Settings.arduinoSignalPins; }
    int[] getDefaultCameraPosition() { return Settings.defaultCameraPosition; }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Settings:\n\tDefault Camera Position:\t( "
            +Settings.defaultCameraPosition[0]+" , "+Settings.defaultCameraPosition[1]+" )\n\tCommand Key Bindings:"
        );

        for (int i=0; i<Settings.commandBindings[0].length; i++)
            output.append("\n\t\t"+Settings.commandBindings[0][i]+":\t\""+Settings.commandBindings[1][i]+"\"");

        return output.toString();
    }
}