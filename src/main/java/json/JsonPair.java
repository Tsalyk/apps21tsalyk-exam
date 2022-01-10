package json;


/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */

public class JsonPair extends Tuple<String, Json> {

    public JsonPair(String name, Json value) {
        super(name, value);
    }

    @Override
    public String toString() {
        return "'" + this.getKey() + "': " + this.getValue().toJson();
    }

    public JsonPair factory(String name, Json value) {
        return new JsonPair(name, value);
    }
}
