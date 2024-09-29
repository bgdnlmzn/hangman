package game.hangman.storage;

import game.hangman.enums.Category;
import game.hangman.enums.Difficulty;
import game.hangman.records.Word;

/**
 * Интерфейс {@code Storage} определяет контракт для работы с хранилищем слов.
 * Хранилище предназначено для хранения и извлечения списка слов в зависимости от выбранной
 * категории и уровня сложности.
 */
public interface Storage {
    /**
     * Возвращает массив слов для указанной категории и уровня сложности.
     *
     * @param category   категория слов
     * @param difficulty уровень сложности игры
     * @return массив объектов {@link Word}, соответствующих указанной категории и уровню сложности
     */
    Word[] getWords(Category category, Difficulty difficulty);

    /**
     * Добавляет массив слов в хранилище для указанной категории и уровня сложности.
     *
     * @param category   категория слов
     * @param difficulty уровень сложности игры
     * @param words      массив объектов {@link Word}, которые нужно добавить в хранилище
     */
    void addWords(Category category, Difficulty difficulty, Word[] words);
}
