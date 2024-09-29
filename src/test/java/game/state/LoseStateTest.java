package game.state;

import game.hangman.context.GameContext;
import game.hangman.records.Word;
import game.hangman.state.StateManager;
import game.hangman.state.impl.LoseState;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LoseStateTest {
    private GameContext gameContext;
    private LoseState loseState;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        Word word = new Word("example", "exampleHint");
        ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        gameContext = new GameContext(word, inputStream, printStream, 6);
        loseState = new LoseState();
    }

    @Test
    @DisplayName("Проверка корректности отображения состояния поражения.")
    public void testLoseStateDisplayed() {
        StateManager stateManager = new StateManager(loseState);

        loseState.initializeStage(gameContext, stateManager);

        String consoleOutput = outputStream.toString();
        assertThat(consoleOutput).contains("К сожалению вы проиграли! А загаданное слово было: EXAMPLE");
        assertThat(stateManager).extracting("currentState").isInstanceOf(LoseState.class);
    }
}
