package domain;

import json.JsonObject;
import json.JsonPair;
import json.JsonNumber;
import json.JsonString;
import json.JsonBoolean;
import json.JsonArray;
import json.Tuple;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */

public class Student extends BasicStudent {
    private String name;
    private String surname;
    private Integer year;
    private Tuple<String, Integer>[] exams;

    public Student(String name,
                   String surname,
                   Integer year,
                   Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject(
                new JsonPair("name", new JsonString(name)),
                new JsonPair("surname", new JsonString(surname)),
                new JsonPair("year", new JsonNumber(year))
        );

        int mark, i = 0;
        String course;
        JsonObject[] data = new JsonObject[exams.length];

        for (Tuple<String, Integer> exam : exams) {
            mark = exam.getValue();
            course = exam.getKey();
            JsonObject jsonExam = new JsonObject();

            jsonExam.add(new JsonPair("course", new JsonString(course)));
            jsonExam.add(new JsonPair("mark", new JsonNumber(mark)));

            if (mark > 2) {
                jsonExam.add(new JsonPair("passed", new JsonBoolean(true)));
            } else {
                jsonExam.add(new JsonPair("passed", new JsonBoolean(false)));
            }

            data[i] = jsonExam;
            i++;
        }

        jsonObject.add(new JsonPair("exams", new JsonArray(data)));


        return jsonObject;
    }
}
