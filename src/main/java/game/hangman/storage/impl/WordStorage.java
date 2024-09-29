package game.hangman.storage.impl;

import game.hangman.enums.Category;
import game.hangman.enums.Difficulty;
import game.hangman.records.Word;
import game.hangman.records.WordKey;
import game.hangman.storage.Storage;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс {@code WordStorage} представляет собой реализацию интерфейса {@link Storage}
 * и используется для хранения и извлечения слов, сгруппированных по категориям и уровням сложности.
 */
public class WordStorage implements Storage {
    /**
     * Внутреннее хранилище слов, организованное как словарь, где ключ — это объект {@link WordKey},
     * представляющий собой комбинацию категории и сложности, а значение — массив объектов {@link Word}.
     */
    private final Map<WordKey, Word[]> words = new HashMap<>();

    /**
     * Инициализирует хранилище с предопределённым набором слов.
     */
    public WordStorage() {
        initializeWords();
    }

    /**
     * Метод для инициализации хранилища предопределённым набором слов, распределённым
     * по категориям и уровням сложности. Включает слова для категорий "Животные", "Города" и "Еда"
     * на разных уровнях сложности.
     */
    private void initializeWords() {
        addWords(Category.ANIMALS, Difficulty.EASY, new Word[] {
            new Word("волк", "Хищник, обитающий в лесах и степях, родственный собаке."),
            new Word("рысь", "Хищная кошка с кисточками на ушах."),
            new Word("лось", " Крупное травоядное животное с большими рогами, обитающее в лесах.")
        });
        addWords(Category.ANIMALS, Difficulty.MEDIUM, new Word[] {
            new Word("верблюд", "Животное пустыни с одним или двумя горбами."),
            new Word("медведь", "Большой лесной зверь, очень любит мёд."),
            new Word("носорог", "Крупное травоядное животное с рогом.")
        });
        addWords(Category.ANIMALS, Difficulty.HARD, new Word[] {
            new Word("бородавочник", "Дикий кабан с бородавками на морде."),
            new Word("выхухоль", "Водное млекопитающее с длинным гибким носом, напоминающее крота."),
            new Word("гиббон", "Примат, известный своим умением быстро передвигаться по деревьям.")
        });

        addWords(Category.CITIES, Difficulty.EASY, new Word[] {
            new Word("париж", "Город любви."),
            new Word("москва", "Популярное место в этом городе - Красная площадь."),
            new Word("лондон", "В этом городе ездят большие красные автобусы.")
        });

        addWords(Category.CITIES, Difficulty.MEDIUM, new Word[] {
            new Word("токио", "Страна суши, самураев и аниме."),
            new Word("берлин", "Когда-то там пала большая стена."),
            new Word("сидней", "Многие люди считают этот город столицей Австралии.")
        });

        addWords(Category.CITIES, Difficulty.HARD, new Word[] {
            new Word("новосибирск", "Город в Сибири, на реке Обь"),
            new Word("буэнос-айрес", "Столица страны, за которую играет Месси."),
            new Word("александровск-сахалинский", "Город с самым длинным названием в России.")
        });

        addWords(Category.FOOD, Difficulty.EASY, new Word[] {
            new Word("яблоко", "Логотип одной известной компании бытовой электроники."),
            new Word("хлеб", "Основной продукт питания, выпекается из теста."),
            new Word("сыр", "Молочный продукт, бывает твердым или мягким.")
        });

        addWords(Category.FOOD, Difficulty.MEDIUM, new Word[] {
            new Word("спагетти", "Длинные итальянские макароны."),
            new Word("котлеты", "Обычно это блюдо подают с пюре."),
            new Word("лазанья", "Это блюдо любил кот Гарфилд.")
        });

        addWords(Category.FOOD, Difficulty.HARD, new Word[] {
            new Word("шакшука", "Блюдо из яиц, входит в кухню арабских стран."),
            new Word("онигири", "Японское блюдо, быстрый перекус между парами."),
            new Word("пахлава", "Известная восточная сладость.")
        });
    }

    /**
     * Возвращает массив слов для указанной категории и уровня сложности.
     *
     * @param category   категория слов
     * @param difficulty уровень сложности игры
     * @return массив объектов {@link Word}, соответствующих указанной категории и уровню сложности
     */
    @Override
    public Word[] getWords(Category category, Difficulty difficulty) {
        WordKey key = new WordKey(category, difficulty);
        return words.get(key);
    }

    /**
     * Добавляет массив слов в хранилище для указанной категории и уровня сложности.
     *
     * @param category   категория слов
     * @param difficulty уровень сложности игры
     * @param words      массив объектов {@link Word}, которые нужно добавить в хранилище
     */
    @Override
    public void addWords(Category category, Difficulty difficulty, Word[] words) {
        WordKey key = new WordKey(category, difficulty);
        this.words.put(key, words);
    }
}
