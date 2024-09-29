package game.starter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import game.hangman.starter.GameStarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class GameStarterTest {

    private GameStarter gameStarter;
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    public void setUp() {
        gameStarter = new GameStarter();
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    @DisplayName("Проверка завершения игры и запроса на начало новой.")
    public void endGameTest() {
        String input = "n\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        gameStarter.endGame(inputStream, printStream);

        assertThat(outputStream.toString()).contains("Хотите начать новую игру? (y/n)");
        assertThat(outputStream.toString()).contains("Игра завершена. До свидания!");
    }

    @Test
    @DisplayName("Проверка исключения при запуске игры, если контекст не инициализирован.")
    public void shouldThrowExceptionWhenContextIsNull() {
        assertThatThrownBy(() -> gameStarter.start())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Игра не инициализирована.");
    }
}
