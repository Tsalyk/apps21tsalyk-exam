package json;

/**
 * Created by Andrii_Rodionov on 1/4/2017.
 */

public class JsonNull extends Json {
    private final Object nullObject = null;

    @Override
    public String toJson() {
        return "null";
    }
}
