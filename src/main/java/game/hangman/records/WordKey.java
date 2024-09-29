package game.hangman.records;

import game.hangman.enums.Category;
import game.hangman.enums.Difficulty;

/**
 * Запись {@code WordKey} представляет собой ключ для поиска слов в хранилище.
 *
 * @param category   категория слов, к которым относится слово
 * @param difficulty уровень сложности слов
 */
public record WordKey(Category category, Difficulty difficulty) {
}
