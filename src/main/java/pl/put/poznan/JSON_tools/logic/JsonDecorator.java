package pl.put.poznan.JSON_tools.logic;


abstract public class JsonDecorator extends JsonObject
{
    protected JsonObject jsonObject;

    public JsonDecorator(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public abstract String getJSON();
}