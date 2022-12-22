package pl.put.poznan.JSON_tools.logic;

/**
 * Extends {@see JsonDecorator}. It removes white chars and make one-line format
 */
public class JsonMinificator extends JsonDecorator{

    /**
     * class constructor
     * @param jsonObject JsonObject class object
     */
    public JsonMinificator(JsonObject jsonObject) {
        super( jsonObject );
    }

    /**
     * this method reformat JSON and
     * @return string without white signs inside it
     */
    @Override
    public String getJSON() {
        return jsonObject.toString();
    }
}
