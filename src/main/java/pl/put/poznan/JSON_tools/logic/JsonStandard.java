package pl.put.poznan.JSON_tools.logic;

public class JsonStandard extends JsonDecorator{


    public JsonStandard(JsonObject jsonObject) {
        super( jsonObject );
    }

    @Override
    public String getJSON() {
        return jsonObject.toString(2);
    }
}
