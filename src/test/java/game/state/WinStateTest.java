package game.state;

import game.hangman.context.GameContext;
import game.hangman.records.Word;
import game.hangman.state.StateManager;
import game.hangman.state.impl.WinState;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class WinStateTest {
    private GameContext gameContext;
    private WinState winState;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        Word word = new Word("example", "exampleHint");
        ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        gameContext = new GameContext(word, inputStream, printStream, 6);
        winState = new WinState();
    }

    @Test
    @DisplayName("Проверка корректности отображения состояния победы.")
    public void testWinStateDisplayed() {
        StateManager stateManager = new StateManager(winState);

        winState.initializeStage(gameContext, stateManager);

        String consoleOutput = outputStream.toString();
        assertThat(consoleOutput).contains("Ура!!! Вы победили! Загаданное слово: EXAMPLE");
        assertThat(stateManager).extracting("currentState").isInstanceOf(WinState.class);
    }

}

