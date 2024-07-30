import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Основный класс парсера json-объектов
 */
public class JsonParser {
    private final String filename;

    public JsonParser(String filename) {
        this.filename = filename;
    }

    /**
     * Распарсить файл, путь к которому был передан в конструкторе данного объекта JsonParser
     */
    public void parse() {
        try (
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader)
        ) {
            int c;
            while ((c = getc(buffer)) != -1) {
                System.out.print((char)c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить следующий символ в файле (за исключением специальных символов)
     * @param reader Объект класса BufferedReader, с помощью которого происходит посимвольное чтение
     * @return Следующий символ в файле, не являеющийся специальным символом
     */
    private int getc(BufferedReader reader) {
        try {
            int c;
            while ((c = reader.read()) != -1) {
                if (!Character.isWhitespace(c)) {
                    return c;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }
}
