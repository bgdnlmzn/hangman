package game.hangman.state.impl;

import game.hangman.context.GameContext;
import game.hangman.state.GameState;
import game.hangman.state.StateManager;
import game.hangman.visual.HangmanVisualizer;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Класс {@code InputState} представляет собой состояние игры, в котором игрок вводит букву для отгадывания слова.
 * Этот класс реализует интерфейс {@link GameState} и отвечает за обработку пользовательского ввода,
 * отображение текущего состояния слова и виселицы, а также за проверку правильности угаданной буквы.
 */
public class InputState implements GameState {
    private static final int ATTEMPTS_TO_SHOW_HINT = 2;

    @Override
    public void initializeStage(GameContext context, StateManager manager) {
        InputStream inputStream = context.inputStream();
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        PrintStream outputStream = context.outputStream();

        displayGameState(context, outputStream);

        if (shouldShowHint(context)) {
            outputStream.println("(!) Подсказка: " + context.word().hint());
        }

        outputStream.println("Введите букву:");
        String input = scanner.nextLine().toLowerCase();

        if (!handleInput(context, outputStream, input)) {
            return;
        }

        char guessedLetter = input.charAt(0);
        context.addGuessLetter(guessedLetter);

        if (!processGuess(context, guessedLetter)) {
            context.plusWrongAttempts();
        }

        if (!(checkIfLost(context, manager) || checkIfWon(context, manager))) {
            manager.setState(new InputState());
        }
    }

    /**
     * Отображает текущее состояние игры: слово, виселицу и количество оставшихся попыток.
     *
     * @param context      объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @param outputStream поток вывода
     */
    private void displayGameState(GameContext context, PrintStream outputStream) {
        outputStream.println("Текущее слово: " + context.getHiddenWord());
        outputStream.println("Попыток осталось: "
            + (context.maxAttempts() - context.wrongAttempts()));
        HangmanVisualizer.draw(context.wrongAttempts(), context.maxAttempts(), outputStream);
    }

    /**
     * Определяет, нужно ли показывать подсказку на основе оставшихся попыток.
     *
     * @param context объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @return {@code true}, если нужно показать подсказку; {@code false} в противном случае
     */
    private boolean shouldShowHint(GameContext context) {
        return context.maxAttempts() - context.wrongAttempts() <= ATTEMPTS_TO_SHOW_HINT;
    }

    /**
     * Обрабатывает ввод игрока и проверяет его на корректность.
     *
     * @param context      объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @param outputStream поток вывода
     * @return {@code true}, если ввод пользователя корректен; {@code false} в противном случае
     */
    private boolean handleInput(GameContext context, PrintStream outputStream, String input) {
        if (input.length() != 1) {
            outputStream.println("(!) Буквы можно вводить только по одной.");
            return false;
        }

        char guessedLetter = input.charAt(0);

        if (Character.isDigit(guessedLetter)) {
            outputStream.println("(!) Можно вводить только буквы.");
            return false;
        }

        if (context.isLetterGuessed(guessedLetter)) {
            outputStream.println("(!) Вы уже вводили эту букву.");
            return false;
        }

        return true;
    }

    /**
     * Обрабатывает угаданную игроком букву.
     *
     * @param context       объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @param guessedLetter буква, которую ввел пользователь
     * @return {@code true} если пользователь угадал; {@code false} в противном случае
     */
    private boolean processGuess(GameContext context, char guessedLetter) {
        return context.word().word().contains(String.valueOf(guessedLetter));
    }

    /**
     * Проверяет, проиграл ли игрок.
     *
     * @param context объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @param manager объект {@link StateManager}, который управляет состояниями игры
     * @return {@code true}, если игрок проиграл; {@code false} в противном случае
     */
    private boolean checkIfLost(GameContext context, StateManager manager) {
        if (context.wrongAttempts() >= context.maxAttempts()) {
            manager.setState(new LoseState());
            return true;
        }
        return false;
    }

    /**
     * Проверяет, выиграл ли игрок.
     *
     * @param context объект {@link GameContext}, который содержит информацию о текущем игровом процессе
     * @param manager объект {@link StateManager}, который управляет состояниями игры
     * @return {@code true}, если игрок выиграл; {@code false} в противном случае
     */
    private boolean checkIfWon(GameContext context, StateManager manager) {
        if (context.getHiddenWord().equals(context.word().word())) {
            manager.setState(new WinState());
            return true;
        }
        return false;
    }
}
