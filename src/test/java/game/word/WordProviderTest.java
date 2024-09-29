package game.word;

import game.hangman.enums.Category;
import game.hangman.enums.Difficulty;
import game.hangman.storage.impl.WordStorage;
import game.hangman.records.Word;
import game.hangman.word.WordProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WordProviderTest {
    private WordStorage wordStorage;
    private WordProvider wordProvider;

    @BeforeEach
    void setUp() {
        wordStorage = mock(WordStorage.class);
    }
    @DisplayName("Слово должно быть корректно предоставлено для категории 'Animals' с уровнем сложности 'Easy'.")
    @Test
    public void wordProvidedCorrectlyAnimalsEasy() {
        Category category = Category.ANIMALS;
        Difficulty difficulty = Difficulty.EASY;

        Word[] mockWords = {
            new Word("волк", "Хищник, обитающий в лесах и степях, родственный собаке."),
            new Word("рысь", "Хищная кошка с кисточками на ушах."),
            new Word("лось", " Крупное травоядное животное с большими рогами, обитающее в лесах.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Слово должно быть корректно предоставлено для категории 'Animals' с уровнем сложности 'Medium'.")
    @Test
    public void wordProvidedCorrectlyAnimalsMedium() {
        Category category = Category.ANIMALS;
        Difficulty difficulty = Difficulty.MEDIUM;

        Word[] mockWords = {
            new Word("верблюд", "Животное пустыни с одним или двумя горбами."),
            new Word("медведь", "Большой лесной зверь, очень любит мёд."),
            new Word("носорог", "Крупное травоядное животное с рогом.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Слово должно быть корректно предоставлено для категории 'Animals' с уровнем сложности 'Hard'.")
    @Test
    public void wordProvidedCorrectlyAnimalsHard() {
        Category category = Category.ANIMALS;
        Difficulty difficulty = Difficulty.HARD;

        Word[] mockWords = {
            new Word("бородавочник", "Дикий кабан с бородавками на морде."),
            new Word("выхухоль", "Водное млекопитающее с длинным гибким носом, напоминающее крота."),
            new Word("гиббон", "Примат, известный своим умением быстро передвигаться по деревьям.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Слово должно быть корректно предоставлено для категории 'Cities' с уровнем сложности 'Easy'.")
    @Test
    public void wordProvidedCorrectlyCitiesEasy() {
        Category category = Category.CITIES;
        Difficulty difficulty = Difficulty.EASY;

        Word[] mockWords = {
            new Word("париж", "Город любви."),
            new Word("москва", "Популярное место в этом городе - Красная площадь."),
            new Word("лондон", "В этом городе ездят большие красные автобусы.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Слово должно быть корректно предоставлено для категории 'Cities' с уровнем сложности 'Medium'.")
    @Test
    public void wordProvidedCorrectlyCitiesMedium() {
        Category category = Category.CITIES;
        Difficulty difficulty = Difficulty.MEDIUM;

        Word[] mockWords = {
            new Word("токио", "Страна суши, самураев и аниме."),
            new Word("берлин", "Когда-то там пала большая стена."),
            new Word("сидней", "Многие люди считают этот город столицей Австралии.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);;

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }
    @DisplayName("Слово должно быть корректно предоставлено для категории 'Cities' с уровнем сложности 'Hard'.")
    @Test
    public void wordProvidedCorrectlyCitiesHard() {
        Category category = Category.CITIES;
        Difficulty difficulty = Difficulty.HARD;

        Word[] mockWords = {
            new Word("новосибирск", "Город в Сибири, на реке Обь"),
            new Word("буэнос-айрес", "Столица страны, за которую играет Месси."),
            new Word("александровск-сахалинский", "Город с самым длинным названием в России.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Слово должно быть корректно предоставлено для категории 'Food' с уровнем сложности 'Easy'.")
    @Test
    public void wordProvidedCorrectlyFoodEasy() {
        Category category = Category.FOOD;
        Difficulty difficulty = Difficulty.EASY;

        Word[] mockWords = {
            new Word("яблоко", "Логотип одной известной компании бытовой электроники."),
            new Word("хлеб", "Основной продукт питания, выпекается из теста."),
            new Word("сыр", "Молочный продукт, бывает твердым или мягким.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Слово должно быть корректно предоставлено для категории 'Food' с уровнем сложности 'Medium'.")
    @Test
    public void wordProvidedCorrectlyFoodMedium() {
        Category category = Category.FOOD;
        Difficulty difficulty = Difficulty.MEDIUM;

        Word[] mockWords = {
            new Word("спагетти", "Длинные итальянские макароны."),
            new Word("котлеты", "Обычно это блюдо подают с пюре."),
            new Word("лазанья", "Это блюдо любил кот Гарфилд.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Слово должно быть корректно предоставлено для категории 'Food' с уровнем сложности 'Hard'.")
    @Test
    public void wordProvidedCorrectlyFoodHard() {
        Category category = Category.FOOD;
        Difficulty difficulty = Difficulty.HARD;

        Word[] mockWords = {
            new Word("шакшука", "Блюдо из яиц, входит в кухню арабских стран."),
            new Word("онигири", "Японское блюдо, быстрый перекус между парами."),
            new Word("пахлава", "Известная восточная сладость.")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        assertThat(word).isNotNull();
        assertThat(word).isIn(mockWords);
        verify(wordStorage, times(1)).getWords(category, difficulty);
    }

    @DisplayName("Должно выбрасываться исключение для слова некорректной длины.")
    @Test
    public void shouldThrowExceptionForShortWord() {
        Category category = Category.ANIMALS;
        Difficulty difficulty = Difficulty.EASY;

        Word[] mockWords = {
            new Word("a", "ab")
        };

        when(wordStorage.getWords(category, difficulty)).thenReturn(mockWords);
        wordProvider = new WordProvider(wordStorage);

        assertThatThrownBy(() -> wordProvider.getWord(category, difficulty))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Слово имеет некорректную длину");
    }
}
