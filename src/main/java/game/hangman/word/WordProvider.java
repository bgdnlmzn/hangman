package game.hangman.word;

import game.hangman.enums.Category;
import game.hangman.enums.Difficulty;
import game.hangman.records.Word;
import game.hangman.storage.Storage;
import java.security.SecureRandom;

/**
 * Класс {@code WordProvider} предоставляет случайные слова из {@link Storage} на основе заданной
 * {@link Category} и {@link Difficulty}.
 * Для выбора случайного слова из хранилища используется {@link SecureRandom}. Выбранное слово должно
 * иметь корректную длину, в противном случае выбрасывается {@link IllegalArgumentException}.
 */
public class WordProvider {
    private final Storage wordStorage;
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Конструктор {@code WordProvider}.
     *
     * @param wordStorage хранилище для получения слов
     */
    public WordProvider(Storage wordStorage) {
        this.wordStorage = wordStorage;
    }

    /**
     * Метод для получения случайного слова из хранилища.
     *
     * @param category   категория слова
     * @param difficulty уровень сложности слова
     * @return случайно выбранное {@link Word}
     * @throws IllegalArgumentException если выбранное слово имеет некорректную длину
     */
    public Word getWord(Category category, Difficulty difficulty) {
        Word[] words = wordStorage.getWords(category, difficulty);
        Word selectedWord = words[RANDOM.nextInt(words.length)];

        if (selectedWord.word().length() <= 2) {
            throw new IllegalArgumentException("Слово имеет некорректную длину");
        }

        return selectedWord;
    }
}
