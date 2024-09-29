package game.state;

import game.hangman.context.GameContext;
import game.hangman.records.Word;
import game.hangman.state.StateManager;
import game.hangman.state.impl.InputState;
import game.hangman.state.impl.LoseState;
import game.hangman.state.impl.WinState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CorrectStateChangeTest {

    private GameContext context;
    private StateManager manager;
    private PipedOutputStream pipedOutputStream;

    @BeforeEach
    public void setup() throws IOException {
        Word mockWord = mock(Word.class);
        when(mockWord.word()).thenReturn("example");

        pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        context = new GameContext(mockWord, pipedInputStream, new PrintStream(outputStream), 4);
        manager = mock(StateManager.class);
    }

    @Test
    @DisplayName("Проверка перехода в состояние проигрыша после максимального количества попыток.")
    public void testGameOverAfterMaxAttempts() throws IOException {
        context.plusWrongAttempts();
        context.plusWrongAttempts();
        context.plusWrongAttempts();
        context.plusWrongAttempts();
        InputState state = new InputState();

        pipedOutputStream.write("t\n".getBytes());
        pipedOutputStream.flush();

        state.initializeStage(context, manager);

        verify(manager).setState(isA(LoseState.class));
    }

    @Test
    @DisplayName("Проверка перехода в состояние победы после угадываний.")
    public void testWinStateAfterCorrectGuesses() throws IOException {
        String[] guesses = {"e", "e", "1", "b", "example", "x", "a", "m", "p", "l"};

        InputState state = new InputState();

        for (String guess : guesses) {
            pipedOutputStream.write((guess + "\n").getBytes());
            pipedOutputStream.flush();

            state.initializeStage(context, manager);
            verify(manager,atLeastOnce()).setState(isA(InputState.class));
        }

        verify(manager,times(1)).setState(isA(WinState.class));
    }
}
