package pl.put.poznan.JSON_tools.logic;

/**
 * Extends {@see JsonDecorator}. It makes JSON format standaraized
 */
public class JsonStandard extends JsonDecorator{

    /**
     * class constructor
     * @param jsonObject JsonObject class object
     */
    public JsonStandard(JsonObject jsonObject) {
        super( jsonObject );
    }

    /**
     * this method takes JSON and
     * @return String as formatted JSON in more human-readable form
     */
    @Override
    public String getJSON() {
        return jsonObject.toString(2);
    }
}
