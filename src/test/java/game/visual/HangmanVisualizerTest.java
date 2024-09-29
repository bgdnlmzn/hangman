package game.visual;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import game.hangman.visual.HangmanVisualizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HangmanVisualizerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream printStream;

    @BeforeEach
    public void setUp() {
        printStream = new PrintStream(outContent);
    }

    @DisplayName("Отрисовка первого этапа виселицы.")
    @Test
    public void testDrawFirstStage() {
        HangmanVisualizer.draw(0,6, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("Отрисовка второго этапа виселицы.")
    @Test
    public void testDrawSecondStage() {
        HangmanVisualizer.draw(1,6, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("Отрисовка третьего этапа виселицы.")
    @Test
    public void testDrawThirdStage() {
        HangmanVisualizer.draw(2,6, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("Отрисовка четвертого этапа виселицы.")
    @Test
    public void testDrawFourthStage() {
        HangmanVisualizer.draw(3,6, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("Отрисовка пятого этапа виселицы.")
    @Test
    public void testDrawFifthStage() {
        HangmanVisualizer.draw(4,6, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("Отрисовка шестого этапа виселицы.")
    @Test
    public void testDrawSixthStage() {
        HangmanVisualizer.draw(5,6, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("Отрисовка финальной стадии(проигрыш).")
    @Test
    public void testDrawFinalStage() {
        HangmanVisualizer.draw(6,6, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
        );
    }

    @DisplayName("Отрисовка этапа, при большем количестве попыток.")
    @Test
    public void testDrawMuchAttempts() {
        HangmanVisualizer.draw(1,7, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("Отрисовка этапа, при меньшем количестве попыток.")
    @Test
    public void testDrawFewerAttempts() {
        HangmanVisualizer.draw(2,4, printStream);
        assertThat(outContent.toString()).isEqualToIgnoringNewLines(
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
            """
        );
    }

    @DisplayName("При некорректном количестве попыток должно выбросить исключение.")
    @Test
    public void testDrawAttemptsThrowException() {
        assertThatThrownBy(() ->
            HangmanVisualizer.draw(-1,6, printStream))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Некорректное количество попыток.");
    }
}
