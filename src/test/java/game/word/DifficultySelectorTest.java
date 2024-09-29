package game.word;

import game.hangman.enums.Difficulty;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import game.hangman.word.DifficultySelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DifficultySelectorTest {
    private PrintStream printStream;

    @BeforeEach
    public void setUp() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        printStream = new PrintStream(outContent);
    }

    @Test
    @DisplayName("Выбор сложности при вводе '1' должен вернуть EASY.")
    public void testCategorySelectorEasy() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        Difficulty difficulty = DifficultySelector.selectDifficulty(in, printStream);
        assertThat(difficulty).isEqualTo(Difficulty.EASY);
    }

    @Test
    @DisplayName("Выбор сложности при вводе '2' должен вернуть MEDIUM.")
    public void testCategorySelectorMedium() {
        ByteArrayInputStream in = new ByteArrayInputStream("2\n".getBytes());
        Difficulty difficulty = DifficultySelector.selectDifficulty(in, printStream);
        assertThat(difficulty).isEqualTo(Difficulty.MEDIUM);
    }

    @Test
    @DisplayName("Выбор сложности при вводе '3' должен вернуть HARD.")
    public void testCategorySelectorHard() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\n".getBytes());
        Difficulty difficulty = DifficultySelector.selectDifficulty(in, printStream);
        assertThat(difficulty).isEqualTo(Difficulty.HARD);
    }

    @Test
    @DisplayName("Выбор сложности при вводе пустой строки должен вернуть одну из доступных сложностей(рандомно).")
    public void testCategorySelectorInvalidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());
        Difficulty difficulty = DifficultySelector.selectDifficulty(in, printStream);
        assertThat(difficulty).isIn(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD);
    }
}
