package domain.classes.json.parsers;

import domain.abstraction.JsonPrimitiveParser;
import domain.classes.exceptions.JsonNumberParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonNumber;

import java.io.IOException;
import java.io.PushbackReader;

public class JsonNumberParser extends JsonPrimitiveParser<JsonNumber> {
    private final int startSymbol;

    public JsonNumberParser(int startSymbol) {
        this.startSymbol = startSymbol;
    }

    @Override
    public JsonNumber parse(final PushbackReader reader) throws JsonParserException {
        StringBuilder builder = new StringBuilder(String.valueOf((char)startSymbol));

        int c;
        try {
            while ((c = getc(reader)) != -1) {
                System.out.println("NUMBER: " + (char)c);
                if (c == ',' || c == '}' || c == ']') {
                    reader.unread(c);
                    break;
                }
                builder.append((char)c);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }


        try {
            String number = builder.toString();
            if (number.contains(".") || number.contains("e") || number.contains("E")) {
                return new JsonNumber(Double.parseDouble(number));
            }
            return new JsonNumber(Long.parseLong(number));
        } catch (NumberFormatException ex) {
            throw new JsonNumberParserException("Error while reading number: " + ex.getMessage());
        }
    }
}
