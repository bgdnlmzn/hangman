package game.hangman.state.impl;

import game.hangman.context.GameContext;
import game.hangman.state.GameState;
import game.hangman.state.StateManager;
import game.hangman.visual.HangmanVisualizer;
import java.io.PrintStream;

/**
 * Класс {@code WinState} представляет состояние игры, когда пользователь победил.
 * Этот класс реализует интерфейс {@link GameState} и отвечает за вывод сообщения о победе
 * и визуализацию состояния игры, когда игрок правильно угадал слово.
 */
public class WinState implements GameState {
    /**
     * Инициализирует стадию игры, когда игрок победил.
     * Этот метод выводит сообщение о победе игрока и показывает загаданное слово,
     * а также визуализирует текущее состояние виселицы с помощью {@link HangmanVisualizer}.
     *
     * @param context объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @param manager объект {@link StateManager}, который управляет состояниями игры
     */
    @Override
    public void initializeStage(GameContext context, StateManager manager) {
        PrintStream outputStream = context.outputStream();
        String secretWord = context.word().word();
        outputStream.println("Ура!!! Вы победили! Загаданное слово: "
            + secretWord.toUpperCase());
        HangmanVisualizer.draw(context.wrongAttempts(), context.maxAttempts(), outputStream);
    }
}
