package game.hangman.records;

/**
 * Запись {@code Word} представляет собой слово игры с текстом подсказки.
 *
 * @param word слово
 * @param hint подсказка
 */
public record Word(String word, String hint) {
}
