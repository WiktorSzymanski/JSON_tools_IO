package pl.put.poznan.JSON_tools.logic;

public class JsonMinificator extends JsonDecorator{


    public JsonMinificator(JsonObject jsonObject) {
        super( jsonObject );
    }

    @Override
    public String getJSON() {
        return jsonObject.toString();
    }
}
