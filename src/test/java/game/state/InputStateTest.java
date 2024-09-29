package game.state;

import game.hangman.context.GameContext;
import game.hangman.records.Word;
import game.hangman.state.StateManager;
import game.hangman.state.impl.InputState;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class InputStateTest {
    private GameContext gameContext;
    private InputState inputState;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        Word word = new Word("example", "exampleHint");
        ByteArrayInputStream inputStream = new ByteArrayInputStream("e\n".getBytes(StandardCharsets.UTF_8));
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        gameContext = new GameContext(word, inputStream, printStream, 6);
        inputState = new InputState();
    }

    @Test
    @DisplayName("Проверка корректности выводимых сообщений, при старте.")
    public void testOutputMessagesAreCorrect() {
        inputState.initializeStage(gameContext, new StateManager(inputState));

        String consoleOutput = outputStream.toString();
        assertThat(consoleOutput).contains("Текущее слово: _______");
        assertThat(consoleOutput).contains("Попыток осталось: 6");
        assertThat(consoleOutput).contains("Введите букву:");
    }

    @Test
    @DisplayName("Проверка отображения подсказки.")
    public void testHintIsDisplayed() {
        gameContext.plusWrongAttempts();
        gameContext.plusWrongAttempts();
        gameContext.plusWrongAttempts();
        gameContext.plusWrongAttempts();

        inputState.initializeStage(gameContext, new StateManager(inputState));

        String consoleOutput = outputStream.toString();
        assertThat(consoleOutput).contains("(!) Подсказка: " + gameContext.word().hint());
    }

    @Test
    @DisplayName("Проверка обработки правильного ввода буквы.")
    public void testHandleInputValidLetter() {
        String input = "e\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        outputStream = new ByteArrayOutputStream();
        gameContext = new GameContext(new Word("example", "exampleHint"), inputStream, new PrintStream(outputStream), 6);
        inputState.initializeStage(gameContext, new StateManager(inputState));

        assertThat(gameContext.isLetterGuessed('e')).isTrue();
        assertThat(gameContext.getHiddenWord()).isEqualTo("e_____e");
    }

    @Test
    @DisplayName("Проверка обработки неправильного ввода буквы")
    public void testHandleInputInvalid() {
        String input = "b\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        gameContext = new GameContext(new Word("example", "exampleHint"), inputStream, new PrintStream(outputStream), 6);


        inputState.initializeStage(gameContext, new StateManager(inputState));

        assertThat(gameContext.isLetterGuessed('b')).isTrue();
        assertThat(gameContext.getHiddenWord()).isEqualTo("_______");
        assertThat(gameContext.wrongAttempts()).isEqualTo(1);

    }

    @Test
    @DisplayName("Проверка обработки ввода верхнем регистре.")
    public void testHandleInputWithUpperCase() {
        String input = "X\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        gameContext = new GameContext(new Word("example", "exampleHint"), inputStream, new PrintStream(outputStream), 6);

        inputState.initializeStage(gameContext, new StateManager(inputState));

        assertThat(gameContext.isLetterGuessed('x')).isTrue();
        assertThat(gameContext.getHiddenWord()).isEqualTo("_x_____");
    }

    @Test
    @DisplayName("Проверка обработки ввода цифры.")
    public void testHandleInputWithNoChar() {
        String input = "1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        gameContext = new GameContext(new Word("example", "exampleHint"), inputStream, new PrintStream(outputStream), 6);

        inputState.initializeStage(gameContext, new StateManager(inputState));

        String consoleOutput = outputStream.toString();
        assertThat(consoleOutput).contains("(!) Можно вводить только буквы.");
        assertThat(gameContext.wrongAttempts()).isEqualTo(0);

    }

    @Test
    @DisplayName("Проверка обработки слова длиной больше 1(опечатка).")
    public void testHandleInputMultipleChar() {
        String input = "example\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        gameContext = new GameContext(new Word("example", "exampleHint"), inputStream, new PrintStream(outputStream), 6);
        StateManager stateManager = new StateManager(inputState);
        inputState.initializeStage(gameContext, stateManager);

        String consoleOutput = outputStream.toString();
        assertThat(consoleOutput).contains("(!) Буквы можно вводить только по одной.");
        assertThat(gameContext.wrongAttempts()).isEqualTo(0);
        assertThat(stateManager).extracting("currentState").isInstanceOf(InputState.class);
    }


    @Test
    @DisplayName("Проверка, что правильное угадывание не меняет состояние")
    public void testCorrectGuessShouldNotChangeState() {
        String input = "e\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        gameContext = new GameContext(new Word("example", "exampleHint"), inputStream, new PrintStream(outputStream), 6);
        StateManager stateManager = new StateManager(inputState);

        inputState.initializeStage(gameContext, stateManager);

        assertThat(stateManager).extracting("currentState").isInstanceOf(InputState.class);
    }
}

