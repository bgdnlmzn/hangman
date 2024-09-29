package game.hangman.word;

import game.hangman.enums.Category;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

/**
 * Класс {@code CategorySelector} предназначен для выбора категории слов.
 * Если пользователь не вводит допустимую категорию, то выбирается случайная категория.
 */
@UtilityClass
public class CategorySelector {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Запрашивает у пользователя выбор категории слов и возвращает выбранную категорию.
     * Метод выводит на экран доступные категории и ждет ввода пользователя. В зависимости от
     * введенного значения выбирается соответствующая категория. Если введенное значение
     * не соответствует ни одной из доступных категорий, выбирается случайная категория.
     *
     * @param inputStream  поток ввода, из которого читается ввод пользователя
     * @param outputStream поток вывода, в который выводятся сообщения для пользователя
     * @return выбранная {@link Category} категория слов
     */
    public static Category selectCategory(InputStream inputStream, PrintStream outputStream) {
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        outputStream.println("Выберите категорию слов:");
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            outputStream.println((i + 1) + ". " + categories[i].name());
        }
        outputStream.println("(!) Нажмите ENTER, чтобы выбрать категорию наугад.");

        String input = scanner.nextLine();
        Category category;
        try {
            int chosenCategory = Integer.parseInt(input);
            category = categories[chosenCategory - 1];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            category = getRandomCategory(outputStream, categories);
        }

        returnCategoryToUser(category, outputStream);

        return category;
    }

    /**
     * Возвращает случайную категорию.
     * @param outputStream поток вывода пользователю
     * @return случайная {@link Category}
     */
    private static Category getRandomCategory(PrintStream outputStream, Category[] categories) {
        outputStream.println("(!) Выбираем категорию наугад...");
        return categories[RANDOM.nextInt(categories.length)];
    }

    /**
     * Метод выводит на экран выбранную категорию слов.
     *
     * @param category     выбранная {@link Category} категория слов
     * @param outputStream поток вывода, в который выводится выбранная категория
     */
    private static void returnCategoryToUser(Category category, PrintStream outputStream) {
        outputStream.println("Выбранная категория: " + category.name());
    }
}
