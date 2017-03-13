package mx.hoola.hoola.Tools;

/**
 * Created by Isaac on 01/03/2017.
 */

public class StringTools {

    public String deleteAll(String strValue, Character charToRemove) {
        return strValue.replaceAll(String.valueOf(charToRemove), "");

    }

    public String upperCaseFirst(String value) {
        // Convert String to char array.
        char[] array = value.toCharArray();
        // Modify first element in array.
        array[0] = Character.toUpperCase(array[0]);
        // Return string.
        return new String(array);
    }
}
