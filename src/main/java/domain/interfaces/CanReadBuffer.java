package domain.interfaces;

import java.io.IOException;
import java.io.PushbackReader;

/**
 * Интерфейс, упрощающий взаимодействие с символьным буффером
 */
public interface CanReadBuffer {

    /**
     * Получить следующий символ в файле (за исключением специальных символов)
     * @param reader Объект класса BufferedReader, с помощью которого происходит посимвольное чтение
     * @return Следующий символ в файле, не являеющийся специальным символом
     */
    default int getc(PushbackReader reader) {
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
