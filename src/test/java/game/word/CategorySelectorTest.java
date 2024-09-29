package game.word;

import game.hangman.enums.Category;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import game.hangman.word.CategorySelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CategorySelectorTest {
    private PrintStream printStream;

    @BeforeEach
    public void setUp() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        printStream = new PrintStream(outContent);
    }

    @Test
    @DisplayName("Выбор категории при вводе '1' должен вернуть ANIMALS.")
    public void testCategorySelectorAnimals() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        Category category = CategorySelector.selectCategory(in, printStream);
        assertThat(category).isEqualTo(Category.ANIMALS);
    }

    @Test
    @DisplayName("Выбор категории при вводе '2' должен вернуть CITIES.")
    public void testCategorySelectorCities() {
        ByteArrayInputStream in = new ByteArrayInputStream("2\n".getBytes());
        Category category = CategorySelector.selectCategory(in, printStream);
        assertThat(category).isEqualTo(Category.CITIES);
    }

    @Test
    @DisplayName("Выбор категории при вводе '3' должен вернуть FOOD.")
    public void testCategorySelectorFood() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\n".getBytes());
        Category category = CategorySelector.selectCategory(in, printStream);
        assertThat(category).isEqualTo(Category.FOOD);
    }

    @Test
    @DisplayName("Выбор категории при вводе пустой строки должен вернуть одну из доступных категорий(рандомно).")
    public void testCategorySelectorInvalidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());
        Category category = CategorySelector.selectCategory(in, printStream);
        assertThat(category).isIn(Category.ANIMALS, Category.FOOD, Category.CITIES);
    }
}
