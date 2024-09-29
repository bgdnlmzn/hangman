package game.hangman.word;

import game.hangman.enums.Difficulty;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

/**
 * Класс {@code DifficultySelector} предназначен для выбора уровня сложности игры.
 * Если пользователь не вводит допустимый уровень сложности, то выбирается случайный уровень.
 */
@UtilityClass
public class DifficultySelector {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Запрашивает у пользователя выбор уровня сложности и возвращает выбранный уровень.
     * Метод выводит на экран доступные уровни сложности и ждет ввода пользователя. В зависимости от
     * введенного значения выбирается соответствующий уровень сложности. Если введенное значение
     * не соответствует ни одному из доступных уровней, выбирается случайный уровень сложности.
     *
     * @param inputStream  поток ввода, из которого читается ввод пользователя
     * @param outputStream поток вывода, в который выводятся сообщения для пользователя
     * @return выбранный {@link Difficulty} уровень сложности
     */
    public static Difficulty selectDifficulty(InputStream inputStream, PrintStream outputStream) {
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        outputStream.println("Выберите сложность игры:");
        Difficulty[] difficulties = Difficulty.values();
        for (int i = 0; i < difficulties.length; i++) {
            outputStream.println((i + 1) + ". " + difficulties[i].name());
        }
        outputStream.println("(!) Нажмите ENTER, чтобы выбрать сложность игры наугад.");

        String input = scanner.nextLine();
        Difficulty difficulty;
        try {
            int chosenDifficulty = Integer.parseInt(input);
            difficulty = difficulties[chosenDifficulty - 1];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            difficulty = getRandomDifficulty(outputStream, difficulties);
        }

        returnDifficultyToUser(difficulty, outputStream);

        return difficulty;
    }

    /**
     * Возвращает случайную категорию.
     *
     * @param outputStream поток вывода пользователю
     * @param difficulties список сложности игры
     * @return случайная {@link Difficulty}
     */
    private static Difficulty getRandomDifficulty(PrintStream outputStream, Difficulty[] difficulties) {
        outputStream.println("(!) Выбираем категорию наугад...");
        return difficulties[RANDOM.nextInt(difficulties.length)];
    }

    /**
     * Метод выводит на экран выбранную категорию слов.
     *
     * @param difficulty   выбранная {@link Difficulty} категория слов
     * @param outputStream поток вывода, в который выводится выбранная категория
     */
    private static void returnDifficultyToUser(Difficulty difficulty, PrintStream outputStream) {
        outputStream.println("Выбранная категория: " + difficulty.name());
    }
}
