package game.context;

import game.hangman.context.GameContext;
import game.hangman.records.Word;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameContextTest {
    private GameContext gameContext;

    @BeforeEach
    public void setUp() {
        Word wordMock = mock(Word.class);
        InputStream inputStreamMock = mock(InputStream.class);
        PrintStream outputStreamMock = mock(PrintStream.class);

        when(wordMock.word()).thenReturn("example");
        gameContext = new GameContext(wordMock, inputStreamMock, outputStreamMock, 6);
    }

    @DisplayName("Проверка начального скрытого слова.")
    @Test
    public void testHiddenWord() {
        String hiddenWord = gameContext.getHiddenWord();

        assertThat(hiddenWord).isEqualTo("_______");
    }

    @DisplayName("Проверка скрытого слова после угадывания.")
    @Test
    public void testHiddenWordAfterGuess() {
        gameContext.addGuessLetter('e');
        String hiddenWord = gameContext.getHiddenWord();

        assertThat(hiddenWord).isEqualTo("e_____e");
    }

    @DisplayName("Проверка добавления букв в список с угаданными.")
    @Test
    public void testAddGuessLetter() {
        gameContext.addGuessLetter('e');

        assertThat(gameContext.isLetterGuessed('e')).isTrue();
    }

    @DisplayName("Проверка увеличения количества неверных попыток.")
    @Test
    public void testPlusWrongAttempts() {
        int initialAttempts = gameContext.wrongAttempts();
        gameContext.plusWrongAttempts();

        assertThat(gameContext.wrongAttempts()).isEqualTo(initialAttempts + 1);
    }

    @DisplayName("Проверка максимального количества попыток.")
    @Test
    public void testMaxAttempts() {
        assertThat(gameContext.maxAttempts()).isEqualTo(6);
    }

    @DisplayName("Проверка начального состояния списка угаданных букв.")
    @Test
    public void testGuessLettersIsEmpty() {
        assertThat(gameContext.guessLetters()).isEmpty();
    }

    @DisplayName("Проверка состояния списка угаданных букв после добавления неправильной буквы.")
    @Test
    public void testGuessLettersAfterAddingWrong() {
        gameContext.addGuessLetter('f');

        assertThat(gameContext.guessLetters()).containsExactly('f');
        assertThat(gameContext.isLetterGuessed('e')).isFalse();
    }
}
