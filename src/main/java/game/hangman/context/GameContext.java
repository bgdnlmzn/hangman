package game.hangman.context;

import game.hangman.records.Word;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

/**
 * Класс {@code GameContext} представляет контекст, содержащий информацию о текущей игре.
 */
@Getter
public class GameContext {
    private final Word word;
    private int wrongAttempts = 0;
    private final Set<Character> guessLetters = new HashSet<>();
    private final InputStream inputStream;
    private final PrintStream outputStream;
    private final int maxAttempts;

    /**
     * Конструктор для создания нового контекста игры.
     *
     * @param word         загаданное слово
     * @param inputStream  поток ввода для получения пользовательских данных
     * @param outputStream поток вывода для отображения информации пользователю
     * @param maxAttempts  максимальное количество попыток в игре
     */
    public GameContext(Word word, InputStream inputStream, PrintStream outputStream, int maxAttempts) {
        this.word = word;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.maxAttempts = maxAttempts;
    }

    /**
     * Метод возвращает текущее состояние слова с замененными неотгаданными буквами на символы подчеркивания.
     *
     * @return Строка, представляющая текущее состояние слова
     */
    public String getHiddenWord() {
        StringBuilder hiddenWord = new StringBuilder();
        for (char letter : word.word().toCharArray()) {
            if (isLetterGuessed(letter)) {
                hiddenWord.append(letter);
            } else {
                hiddenWord.append('_');
            }
        }
        return hiddenWord.toString();
    }

    /**
     * Метод проверяет, была ли буква уже загадана пользователем.
     *
     * @param letter буква для проверки
     * @return {@code true}, если буква была угадана; {@code false} в противном случае
     */
    public boolean isLetterGuessed(char letter) {
        return guessLetters.contains(letter);
    }

    /**
     * Добавляет угаданную букву в множество угаданных букв
     *
     * @param letter угаданная буква
     */
    public void addGuessLetter(char letter) {
        guessLetters.add(letter);
    }

    /**
     * Метод увеличивает количество неправильных попыток на единицу
     */
    public void plusWrongAttempts() {
        wrongAttempts++;
    }
}
