package game.state;

import game.hangman.context.GameContext;
import game.hangman.records.Word;
import game.hangman.state.GameState;
import game.hangman.state.StateManager;
import game.hangman.state.impl.LoseState;
import game.hangman.state.impl.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StateManagerTest {
    private StateManager stateManager;
    private GameContext gameContext;
    private GameState winState;
    private GameState loseState;

    @BeforeEach
    public void setUp() {
        GameState initialState = mock(GameState.class);
        winState = mock(WinState.class);
        loseState = mock(LoseState.class);
        gameContext = mock(GameContext.class);
        stateManager = new StateManager(initialState);
    }

    @Test
    @DisplayName("Проверка установки начального состояния.")
    public void testSetState() {
        stateManager.setState(winState);
        stateManager.executeState(gameContext);
        verify(winState, times(1)).initializeStage(gameContext, stateManager);
    }

    @Test
    @DisplayName("Проверка выполнения состояния после победы.")
    public void testExecuteStateAfterWin() {
        when(gameContext.word()).thenReturn(new Word("exampleWord","exampleHint"));
        stateManager.setState(winState);
        stateManager.executeState(gameContext);
        verify(winState).initializeStage(gameContext, stateManager);
    }

    @Test
    @DisplayName("Проверка выполнения состояния после победы")
    public void testExecuteStateAfterLose() {
        when(gameContext.word()).thenReturn(new Word("exampleWord","exampleHint"));
        stateManager.setState(loseState);
        stateManager.executeState(gameContext);
        verify(loseState).initializeStage(gameContext, stateManager);
    }

    @Test
    @DisplayName("Проверка корректности состояния игры (победа/поражение)")
    public void testIsGameClosed() {
        stateManager.setState(winState);
        stateManager.executeState(gameContext);
        assertThat(stateManager).extracting("currentState").isInstanceOf(WinState.class);

        stateManager.setState(loseState);
        stateManager.executeState(gameContext);
        assertThat(stateManager).extracting("currentState").isInstanceOf(LoseState.class);
    }
}
