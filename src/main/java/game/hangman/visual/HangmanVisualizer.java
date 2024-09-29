package game.hangman.visual;

import java.io.PrintStream;
import lombok.experimental.UtilityClass;

/**
 * Класс {@code HangmanVisualizer} предназначен для визуализации состояния игры.
 */
@UtilityClass
public class HangmanVisualizer {
    private static final String[] HANGMAN_STAGES = {
        """
              _______
              |/
              |
              |
              |
              |
              |
              |
              |
            __|________
            |         |
            """,
        """
              _______
              |/     |
              |     (_)
              |
              |
              |
              |
              |
              |
            __|________
            |         |
            """,
        """
              _______
              |/     |
              |     (_)
              |      |
              |      |
              |      |
              |
              |
              |
            __|________
            |         |
            """,

        """
              _______
              |/     |
              |     (_)
              |     _|
              |    / |
              |      |
              |
              |
              |
            __|________
            |         |
            """,

        """
              _______
              |/     |
              |     (_)
              |     _|_
              |    / | \\
              |      |
              |
              |
              |
            __|________
            |         |
            """,

        """
              _______
              |/     |
              |     (_)
              |     _|_
              |    / | \\
              |      |
              |     /
              |    /
              |
            __|________
            |         |
            """,

        """
            ----------------------
                   YOU DIED
            ----------------------
              _______
              |/     |
              |     (_)
              |     _|_
              |    / | \\
              |      |
              |     / \\
              |    /   \\
              |
            __|________
            |         |
            """
    };

    /**
     * Отображает текущее состояние виселицы в зависимости от количества оставшихся попыток.
     * Метод выбирает соответствующий рисунок в зависимости от количества оставшихся
     * попыток игрока и выводит его в {@link PrintStream}.
     *
     * @param attempts     текущее количество неправильных попыток
     * @param maxAttempts  максимальное количество попыток
     * @param outputStream поток вывода, в который выводится рисунок виселицы
     * @throws IllegalArgumentException если количество попыток некорректное
     */
    public static void draw(int attempts, int maxAttempts, PrintStream outputStream) {
        if (attempts < 0 || maxAttempts <= 0) {
            throw new IllegalArgumentException("Некорректное количество попыток.");
        }

        int stageIndex = (int) Math.floor(((double) attempts / maxAttempts) * (HANGMAN_STAGES.length - 1));

        outputStream.println(HANGMAN_STAGES[stageIndex]);
    }
}
