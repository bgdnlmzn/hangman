package game.hangman;

import game.hangman.starter.GameStarter;
import lombok.experimental.UtilityClass;

/**
 * Основной класс для запуска игры "Виселица".
 */
@UtilityClass
public class Main {
    /**
     * Создает экземпляр {@link GameStarter} и запускает новую игру,
     * используя {@link System#in} для ввода и {@link System#out} для вывода.
     */
    public static void main(String[] args) {
        GameStarter newGame = new GameStarter();
        newGame.startNewGame(System.in, System.out);
    }
}
