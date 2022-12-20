package pl.put.poznan.JSON_tools.logic;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public abstract class JSONDecorator extends JSONObject{
    private JSONObject jsonObject;

    public JSONDecorator( JSONObject jsonObject ) {
        this.jsonObject = jsonObject;
    }
}
