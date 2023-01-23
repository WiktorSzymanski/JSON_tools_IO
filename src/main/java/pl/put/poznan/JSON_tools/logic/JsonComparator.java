package pl.put.poznan.JSON_tools.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as line brief description).
 */
public class JsonComparator extends JsonDecorator {
    /**
     * class constructor
     *
     * @param jsonObject JsonObject class object
     */
    public JsonComparator(JsonObject jsonObject) {
        super(jsonObject);
    }

    public static void getDifferences(String aObj1, String aObj2, List<Integer> aDifferences) throws IOException {
        BufferedReader bufReader1 = new BufferedReader(new StringReader(aObj1));
        BufferedReader bufReader2 = new BufferedReader(new StringReader(aObj2));
        ArrayList<String> buffor1 = new ArrayList<>(100);
        ArrayList<String> buffor2 = new ArrayList<>(100);
        String line;
        while ((line = bufReader1.readLine()) != null) {
            buffor1.add(line);
        }
        while ((line = bufReader2.readLine()) != null) {
            buffor2.add(line);
        }
        ArrayList<String> iterable;
        if (buffor1.size() > buffor2.size()) {
            iterable = buffor1;
        } else {
            iterable = buffor2;
        }
        for (int i = 0; i < iterable.size(); i++) {
            try {
                if (!buffor1.get(i).equals(buffor2.get(i))) {
                    aDifferences.add(i);
                }
            } catch (Exception e) {
                aDifferences.add(i);
            }
        }
    }

    @Override
    public String getJSON() {
        return null;
    }
}