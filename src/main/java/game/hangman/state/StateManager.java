package game.hangman.state;

import game.hangman.context.GameContext;
import game.hangman.state.impl.LoseState;
import game.hangman.state.impl.WinState;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Класс {@code StateManager} отвечает за управление текущим состоянием игры.
 * <p>
 * Он хранит текущее состояние игры, может изменять его и запускать выполнение состояний на основе контекста игры.
 * Менеджер завершает выполнение, если игра достигла состояния победы ({@link WinState})
 * или поражения ({@link LoseState}).
 * </p>
 */
@Getter
@AllArgsConstructor
public class StateManager {
    /**
     * Текущее состояние игры, представленное объектом, реализующим интерфейс {@link GameState}.
     */
    private GameState currentState;

    /**
     * Устанавливает новое состояние игры.
     *
     * @param newState новое состояние игры
     */
    public void setState(GameState newState) {
        this.currentState = newState;
    }

    /**
     * Выполняет игровой цикл, вызывая метод {@link GameState#initializeStage(GameContext, StateManager)}
     * для текущего состояния до тех пор, пока игра не достигнет конечного состояния (победа/поражение).
     *
     * @param context контекст игры, содержащий текущие данные и состояние игрового процесса
     */
    public void executeState(GameContext context) {
        while (!(isGameClosed())) {
            currentState.initializeStage(context, this);
        }

        currentState.initializeStage(context, this);
    }

    /**
     * Проверяет, завершена ли игра. Игра считается завершенной, если текущее состояние является
     * состоянием победы ({@link WinState}) или поражения ({@link LoseState}).
     *
     * @return {@code true}, если игра завершена (победа или поражение); {@code false} в противном случае
     */
    private boolean isGameClosed() {
        return currentState instanceof WinState || currentState instanceof LoseState;
    }
}
