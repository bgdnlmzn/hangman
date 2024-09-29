package game.hangman.starter;

import game.hangman.context.GameContext;
import game.hangman.enums.Category;
import game.hangman.enums.Difficulty;
import game.hangman.records.Word;
import game.hangman.state.GameState;
import game.hangman.state.StateManager;
import game.hangman.state.impl.InputState;
import game.hangman.storage.Storage;
import game.hangman.storage.impl.WordStorage;
import game.hangman.word.CategorySelector;
import game.hangman.word.DifficultySelector;
import game.hangman.word.WordProvider;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Класс {@code GameStarter} отвечает за инициализацию и запуск новой сессии игры.
 * Этот класс предоставляет методы для начала игры, её завершения и определения
 * количества попыток на основе выбранной сложности и категории.
 */
public class GameStarter {
    private GameContext context;
    private StateManager manager;
    private static final int EASY_ATTEMPTS = 8;
    private static final int MEDIUM_ATTEMPTS = 6;
    private static final int HARD_ATTEMPTS = 4;
    private static final String INTRO_TEXT = """
         _                                            \s
        | |                                           \s
        | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __ \s
        | '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\\s
        | | | | (_| | | | | (_| | | | | | | (_| | | | |
        |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|
                            __/ |                     \s
                           |___/
        Добро пожаловать в игру 'Виселица'!
        Ваша задача это разными схемами угадать слово по буквам, избегая повешения.
        """;

    /**
     * Запускает новую игру с заданным потоком ввода и вывода.
     * В этом методе выводится приветственное сообщение, запрашивается выбор категории и сложности игры,
     * затем выбирается случайное слово из хранилища в зависимости от этих параметров.
     * После этого инициализируется игровое состояние и запускается игровой процесс.
     *
     * @param inputStream  поток ввода для получения данных от игрока
     * @param outputStream поток вывода для отображения данных игроку
     */
    public void startNewGame(InputStream inputStream, PrintStream outputStream) {
        outputStream.println(INTRO_TEXT);
        Category category = CategorySelector.selectCategory(inputStream, outputStream);
        Difficulty difficulty = DifficultySelector.selectDifficulty(inputStream, outputStream);

        Storage wordStorage = new WordStorage();
        WordProvider wordProvider = new WordProvider(wordStorage);
        Word word = wordProvider.getWord(category, difficulty);

        int maxAttempts = getAttemptsBasedOnDifficulty(difficulty);

        GameState initialState = new InputState();
        this.manager = new StateManager(initialState);

        this.context = new GameContext(word, inputStream, outputStream, maxAttempts);

        start();
        endGame(inputStream, outputStream);
    }

    /**
     * Запускает процесс игры, используя текущее состояние.
     * Если контекст игры не инициализирован, выбрасывается исключение {@link IllegalStateException}.
     */
    public void start() {
        if (context == null) {
            throw new IllegalStateException("Игра не инициализирована.");
        }
        manager.executeState(context);
    }

    /**
     * Завершает игру и предлагает пользователю начать новую игру.
     * Если игрок вводит "y", игра начинается заново. Если "n", игра завершена.
     *
     * @param inputStream  поток ввода для получения данных от игрока
     * @param outputStream поток вывода для отображения данных игроку
     */
    public void endGame(InputStream inputStream, PrintStream outputStream) {
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        outputStream.println("Хотите начать новую игру? (y/n)");

        String input = scanner.nextLine().trim();
        if ("y".equalsIgnoreCase(input)) {
            startNewGame(inputStream, outputStream);
        } else {
            outputStream.println("Игра завершена. До свидания!");
        }
    }

    /**
     * Определяет количество попыток на основе выбранной сложности игры.
     *
     * @param difficulty сложность игры {@link Difficulty}.
     * @return количество попыток в зависимости от сложности.
     */
    private int getAttemptsBasedOnDifficulty(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> EASY_ATTEMPTS;
            case MEDIUM -> MEDIUM_ATTEMPTS;
            case HARD -> HARD_ATTEMPTS;
        };
    }
}
