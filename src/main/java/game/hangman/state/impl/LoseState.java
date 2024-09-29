package game.hangman.state.impl;

import game.hangman.context.GameContext;
import game.hangman.state.GameState;
import game.hangman.state.StateManager;
import game.hangman.visual.HangmanVisualizer;
import java.io.PrintStream;

/**
 * Класс {@code LoseState} представляет состояние игры, когда пользователь проиграл.
 * Этот класс реализует интерфейс {@link GameState} и отвечает за вывод сообщения о проигрыше
 * и визуализацию состояния игры, когда игрок не смог угадать слово.
 */
public class LoseState implements GameState {
    /**
     * Инициализирует стадию игры, когда игрок проиграл.
     * Этот метод выводит сообщение о поражении игрока и показывает загаданное слово,
     * а также визуализирует текущее состояние виселицы с помощью {@link HangmanVisualizer}.
     *
     * @param context объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @param manager объект {@link StateManager}, который управляет состояниями игры
     */
    @Override
    public void initializeStage(GameContext context, StateManager manager) {
        String secretWord = context.word().word();
        PrintStream outputStream = context.outputStream();
        outputStream.println("К сожалению вы проиграли! А загаданное слово было: "
            + secretWord.toUpperCase());
        HangmanVisualizer.draw(context.wrongAttempts(), context.maxAttempts(), outputStream);
    }
}
