package game.storage;

import game.hangman.enums.Category;
import game.hangman.enums.Difficulty;
import game.hangman.records.Word;
import game.hangman.storage.Storage;
import game.hangman.storage.impl.WordStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WordStorageTest {
    private Storage wordStorage;

    @BeforeEach
    public void setUp() {
        wordStorage = new WordStorage();
    }

    @DisplayName("Получение слов для категории ANIMALS и уровня сложности EASY.")
    @Test
    public void testWordRepositoryEasyAnimals() {
        Category category = Category.ANIMALS;
        Difficulty difficulty = Difficulty.EASY;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("волк", "рысь", "лось");
    }

    @DisplayName("Получение слов для категории ANIMALS и уровня сложности MEDIUM.")
    @Test
    public void testWordRepositoryMediumAnimals() {
        Category category = Category.ANIMALS;
        Difficulty difficulty = Difficulty.MEDIUM;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("верблюд", "медведь", "носорог");
    }

    @DisplayName("Получение слов для категории ANIMALS и уровня сложности HARD.")
    @Test
    public void testWordRepositoryHardAnimals() {
        Category category = Category.ANIMALS;
        Difficulty difficulty = Difficulty.HARD;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("бородавочник", "выхухоль", "гиббон");
    }

    @DisplayName("Получение слов для категории CITIES и уровня сложности EASY.")
    @Test
    public void testWordRepositoryEasyCities() {
        Category category = Category.CITIES;
        Difficulty difficulty = Difficulty.EASY;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("париж", "москва", "лондон");
    }

    @DisplayName("Получение слов для категории CITIES и уровня сложности MEDIUM.")
    @Test
    public void testWordRepositoryMediumCities() {
        Category category = Category.CITIES;
        Difficulty difficulty = Difficulty.MEDIUM;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("токио", "берлин", "сидней");
    }

    @DisplayName("Получение слов для категории CITIES и уровня сложности HARD.")
    @Test
    public void testWordRepositoryHardCities() {
        Category category = Category.CITIES;
        Difficulty difficulty = Difficulty.HARD;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("новосибирск", "буэнос-айрес", "александровск-сахалинский");
    }

    @DisplayName("Получение слов для категории FOOD и уровня сложности EASY.")
    @Test
    public void testWordRepositoryEasyFood() {
        Category category = Category.FOOD;
        Difficulty difficulty = Difficulty.EASY;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("яблоко", "хлеб", "сыр");
    }

    @DisplayName("Получение слов для категории FOOD и уровня сложности MEDIUM.")
    @Test
    public void testWordRepositoryMediumFood() {
        Category category = Category.FOOD;
        Difficulty difficulty = Difficulty.MEDIUM;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("спагетти", "котлеты", "лазанья");
    }

    @DisplayName("Получение слов для категории FOOD и уровня сложности HARD.")
    @Test
    public void testWordRepositoryHardFood() {
        Category category = Category.FOOD;
        Difficulty difficulty = Difficulty.HARD;

        Word[] words = wordStorage.getWords(category, difficulty);

        assertThat(words).hasSize(3);
        assertThat(words).isNotNull();
        assertThat(words).extracting(Word::word).containsExactly("шакшука", "онигири", "пахлава");
    }
}
