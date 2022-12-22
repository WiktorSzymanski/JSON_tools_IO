package pl.put.poznan.JSON_tools.logic;

/**
 * This class is an abstract class.  Introduces a decorator class template
 */
abstract public class JsonDecorator extends JsonObject
{
    protected JsonObject jsonObject;
    /**
     * class constructor
     * @param jsonObject JsonObject class object
     */
    public JsonDecorator(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * basic getter
     */
    public abstract String getJSON();
}