package game.hangman.state;

import game.hangman.context.GameContext;

/**
 * Интерфейс {@code GameState} представляет собой состояние игры.
 * Каждый класс, реализующий этот интерфейс, должен предоставить логику инициализации и выполнения стадии игры
 * в зависимости от контекста игры и состояния.
 */
public interface GameState {
    /**
     * Инициализирует и выполняет логику текущего состояния игры
     *
     * @param context объект {@link GameContext}, который хранит данные игрового процесса
     * @param manager объект {@link StateManager}, управляющий состояниями игры и изменяющий
     *                их по ходу игрового процесса
     */
    void initializeStage(GameContext context, StateManager manager);
}
