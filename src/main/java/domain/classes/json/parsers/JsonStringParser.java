package domain.classes.json.parsers;

import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonParserException;
import domain.classes.exceptions.JsonStringParserException;
import domain.classes.json.JsonString;

import java.io.IOException;
import java.io.PushbackReader;

public class JsonStringParser extends JsonElementParser<JsonString> {
    @Override
    public JsonString parse(final PushbackReader reader) throws JsonParserException {
        StringBuilder builder = new StringBuilder();

        try {
            int c;
            while ((c = reader.read()) != -1) {
                if (c == '"') {
                    return new JsonString(builder.toString());
                }

                builder.append((char)c);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        throw new JsonStringParserException("Unexpected end of string");
    }
}
