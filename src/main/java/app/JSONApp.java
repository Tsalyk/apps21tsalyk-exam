package app;

import domain.BasicStudent;

import json.Json;
import json.JsonObject;
import json.JsonPair;
import json.JsonNumber;
import json.JsonString;
import json.JsonBoolean;
import json.JsonArray;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JSONApp {
    public static void main(String[] args) {
        Json jYear = new JsonNumber(2);
        print(jYear); // 2

        final int FIRST_MARK = 3;
        final int SECOND_MARK = 4;

        Json jMarks = new JsonArray(new JsonNumber(FIRST_MARK), new JsonNumber(SECOND_MARK));
        print(jMarks); // [3, 4]

        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));
        JsonPair marks = new JsonPair("marks", jMarks);
        JsonPair year = new JsonPair("year", jYear);
        JsonObject jsonObj = new JsonObject(name, surname, year, marks);
        print(jsonObj);
        // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        print(jsonObj.projection("surname", "age", "year", "marks"));
        // {'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        BasicStudent basicStudent = new BasicStudent("Andrii",
                                                   "Rodionov",
                                                               2);
        print(basicStudent.toJsonObject());
        // {'name': 'Andrii', 'surname': 'Rodionov', 'year': 2}

    }

    private static void print(Json json) {

        System.out.println(json.toJson());
    }

    public static JsonObject sessionResult() {
        final int FIRST_MARK = 3;
        final int SECOND_MARK = 5;

        JsonObject jsonObject = new JsonObject();

        JsonObject courseOOP = new JsonObject(new JsonPair(
                    "course", new JsonString("OOP")),
                        new JsonPair("mark",
                        new JsonNumber(FIRST_MARK)),
                        new JsonPair("passed", new JsonBoolean(true)));

        JsonObject courseEnglish = new JsonObject(new JsonPair(
                   "course", new JsonString("English")),
                                   new JsonPair("mark",
                                           new JsonNumber(SECOND_MARK)),
                                    new JsonPair("passed",
                                            new JsonBoolean(true)));

        JsonObject courseMath = new JsonObject(new JsonPair(
                "course", new JsonString("Math")),
                                new JsonPair("mark",
                                        new JsonNumber(2)),
                                new JsonPair("passed",
                                        new JsonBoolean(false)));

        JsonObject[] data = new JsonObject[]{courseOOP,
                                            courseEnglish,
                                            courseMath};

        jsonObject.add(new JsonPair("name", new JsonString("Andrii")));
        jsonObject.add(new JsonPair("surname", new JsonString("Rodionov")));
        jsonObject.add(new JsonPair("year", new JsonNumber(2)));
        jsonObject.add(new JsonPair("exams", new JsonArray(data)));

        return jsonObject;
    }
}

class Main {
    public static void main(String[] args) {
        Json jsonObject = JSONApp.sessionResult();

        System.out.println(jsonObject.toJson());
    }
}
