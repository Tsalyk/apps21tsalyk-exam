package json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final List<JsonPair> pairs = new ArrayList<JsonPair>();
    private final List<String> keys = new ArrayList<String>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair : jsonPairs) {
            if (pair instanceof JsonPair) {
                this.add(pair);
            }
        }
    }

    private int findIdx(String key) {
        int i = 0;

        for (JsonPair pair : pairs) {
            if (pair.getKey().equals(key)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private void update(JsonPair pair) {
        int i = this.findIdx(pair.getKey());
        pairs.set(i, pair.factory(pair.getKey(), pair.getValue()));
    }

    @Override
    public String toJson() {
        String repr = "{";
        int i = pairs.size();

        for (JsonPair pair : pairs) {
            repr += pair.toString();
            if (i > 1) {
                repr += ", ";
            }
            i--;
        }

        return repr + "}";
    }

    public void add(JsonPair jsonPair) {
        String key = jsonPair.getKey();

        if (keys.contains(key)) {
            this.update(jsonPair);
        } else {
            pairs.add(jsonPair);
            keys.add(key);
        }
    }

    public boolean contains(String name) {
        return keys.contains(name);
    }

    public Json find(String name) {
        for (JsonPair pair : pairs) {
            if (pair.getKey().equals(name)) {
                return pair.getValue();
            }
        }

        return null;
    }

    public JsonObject projection(String... names) {
        int i = 0;
        JsonPair[] jsonPairs = new JsonPair[names.length];

        for (String name : names) {
            if (this.contains(name)) {
                jsonPairs[i] = pairs.get(this.findIdx(name));
                i++;
            }
        }

        return new JsonObject(jsonPairs);
    }
}
