package dev.mirrex;

/**
 * Исключение для случаев переполнения резервуаров.
 */
public class OverflowException extends Exception {

    /**
     * Конструктор исключения.
     *
     * @param message Сообщение об ошибке
     */
    public OverflowException(String message) {
        super(message);
    }
}
