import com.sun.glass.ui.Size;

import java.util.Random;
import java.util.Scanner;
public class TicTakToe {
    // Переменные - параметры игры
    public static final int SIZE = 5; //(2) psfi + tab Выведено в константу
    public static final int DOT_TO_WIN = 4; // Сколько площадок
    public static final int LINE_LIMIT = 10; //(6) Количество строк которые мы пропускаем.
    int count;
    // Констатнты • Х О. Пишутся только капсом
    // final = перменная меняться не будет, значение изменить не сможем. psfs + tab.
    public static final char DOT_EMPTY = '•'; // Переменные видны везде. Нельзя изменить, присваивать
    public static final char DOT_X = 'X'; // (1)
    public static final char DOT_O = 'O'; //(1)
    // Перменные - игровое поле , работа с клавиатурой, случайные числа
    public static char [][] map = new char[5][5] ;  //  Создаем поле игровое  карта (3) Обьявили
    public static Scanner scanner = new Scanner(System.in); // (4) Обьявили Scanner
    public static Random random = new Random(); // (4) Обьявили псевдо случайные числа.
    // Обьявили на уровне класса, чтобы мы могли из любой точки метода main могли пользоваться ими.
    // Основной метод
    public static void main(String[] args) {
        // shift  + ctrl + "-" сворачивание методов
        // Инициализация игрового поля  (5) // alt + enter кодеры
        initMap();
        // Вывод игрового поля игрового  в консоль (6)
        printMap(); // Пишим printMap + ALT + ENTER
        // Главный игровой цикл
        while(true) { // (9)
            // Ход игрока (10)  Было бы круто сделать стрелочками ход
            humanTurn(); // Обьявили метод ALT + ENTER (10)
            // Печать игрового поля после хода игрока
            printMap(); //(13) Печатаем игровое поле снова, после хода игроком. В том состоянии в котормо оно есть
            // Проверка победителя Пишем некрасиво (16)
            if(checkWin(DOT_X)){ // Если человек победил
                System.out.println("Человеческий ирок одержал победу");
                break;
            }
            //ToBeImplemented // TODO: 04.11.2020
            //  FIXME: 03.11.2020 tofix
            // Проверка полностью заполненного поля. Проверка что полностью закончились ходы???
            //(19) Если ходы не остались то ничья
            if(isMapFull()){
                System.out.println("Ничья");
                break;
            }
            // Ход ИИ (14) Создаем ход ИИ. Он такой же как у пользователя. Такие же рамки заданы
            aiTurn(); // (14) alt + enter
            // Вывод игрового поля
            printMap(); // (15) После хода игрока СНОВА ОБНОВЛЯЕМ ПОЛЕ после хода ИИ
            // Проверка победителя.  ИИ игрока
            if(checkWin(DOT_O)){ // (17)
                System.out.println("Совершенное существо победило");
                break;
            }
            // Проверка заполненной карты После ИИ игрока
            if(isMapFull()){ // (20)
                System.out.println("Ничья");
                break;
            }
        }
        // Игра закончена
        System.out.println("Игра завершена!!");
        scanner.close(); // Закрыли консоль
    }
    private static boolean isMapFull() { // (21) Проверка чтобы не было DOT_EMPTY
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (map[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    private static boolean checkWin(char symbol) {
        // Создадим 2 вложенных цикла и счетчик.
        // Счетчик будет считать количество одинаковых значений
        // Если кол-во одинаковых значений = длине массива то победа.
        // Для прохода по строкам
        int counterOne;
        int counterTwo;
        for (int y = 0; y < map.length; y++) {
            counterOne = 0;
            for (int x = 0; x < map.length; x++) {
                if (map[y][x] == symbol) {
                    counterOne++;
                    if (counterOne ==DOT_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        // Для прохода по стоолбцам
        for (int x = 0; x < map.length; x++) {
            counterOne = 0;
            for (int y = 0; y < map.length; y++) {
                if (map[y][x] == symbol) {
                    counterOne++;
                    if (counterOne == DOT_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        // Для прохода по диагонали
        counterOne=0;
        counterTwo=0;
        for (int i = 0; i < map.length; i++) {

              if (map[i][i]  == symbol)   // Проход по диагонали из lesson 2 одним циклом
                      counterOne++;
              if(map[i][map.length-i-1] == symbol)
                      counterTwo++;
        }
        if(counterOne == DOT_TO_WIN || counterTwo == DOT_TO_WIN)
            return true;
        else return false;
    }

    private static void aiTurn() { // Как и humanTurn
        int x,y; // Имеем координаты x, y
        do{ // Бот у нас будет примитивный
            x= random.nextInt(SIZE); // (14) Присваиваем рандом от  [0 ..SIZE)
            y = random.nextInt(SIZE); // (14) Присваиваем рандом от  [0 ..SIZE)
        }while(!isCellValid(x,y)); // (14) Так же вводим координаты пока не isCellValid Проверка на пустое значение!!
        map[y][x] = DOT_O; // И после всех проверок ставим НОЛИК
    }
    /**
     * Предоставлем ход игроку. Игрок отвечает за крестики. А ИИ за нолики
     */
    private static void humanTurn() { // Метод будет запрашивать у игрока с консоли координаты
        int x, y; // (11)Объявили координаты. Будем запрашивать у польователя их
        do { // Чтобы пользователь не ввел то,что не надо или  Нужная координата  + свободное поле
            System.out.println("Введите координаты в формате х у в диапазаоне от 1 до " + SIZE); // Задаем вопрос
            x = scanner.nextInt() - 1; // (11)  Запрос х. Когда игрок введет 1.1. Мы переводим в 0 0
            y = scanner.nextInt() - 1; // (11)
        } while (!isCellValid(x,y)); // (12) while (true) a - человек может ввести 1-3 по xy. б пустаяклетка
        map[y][x] = DOT_X;  // Пока клетка isCellValid не валидная мы должны повторить цикл
        /*
         Мы знаем,что человек играет крестиком. В ячейку которую выбрал пользователь ставим крестик.
        */
    }
    // Проверяем что координата введенная правильная
    private static boolean isCellValid(int x, int y) {  // 12 Создался метод после Alt+enter
        // Проверка на предмет соответсвия правилам. Если правила не соотвесвует  - false
        if(x<0 || x>= SIZE) return false; // (12) Если вдруг х < 0 или х >= SIZE то false
        if(y<0 || y>= SIZE) return false; // (12) Если вдруг y < 0 или y >= SIZE то false
        if (map[y][x] != DOT_EMPTY) return  false;  // (12) Если ячейка занята то false
        return  true; // Если все окей то возвращаем true
    }
    private static void printMap() {  // (6)
        // Сделали отступ Просто 15 пустых строк отступили вниз
        for (int i = 0; i < LINE_LIMIT; i++) { // (6) ALT + ENTER
            System.out.println(); // (6) Просто количество строк
        }
        // Верхняя "Легенда" Координаты (Номера строк для удобства)
        for (int i = 0; i <= SIZE ; i++) { // (7) 0 1 2 3
            System.out.print(i + " ");
        }
        System.out.println();
        // Выводим игровое поле (Заполняем точками)
        for (int  y = 0;  y< SIZE; y++) { // (8) Этот цикл отвеает за строки y координата (x на оси)
            System.out.print((y+1)  + " " ); // (8) +1 Записываем по оси Х + 1 вниз. Для удобства
            for (int x = 0; x < SIZE; x++) {  // (8) Отвечает за ось (у)
                System.out.print(map[y][x] + " "); // (8) Заполняем точками наше поле.
            }
            System.out.println(); // Отступ  для игрового поля
        }
        // Доп отступ
        System.out.println();
    }
    private static void initMap() { // Инициализация игрового поля
        map = new char[SIZE][SIZE]; //  Проинициализировали игровое поле (5) Присвоили значения массиву
        for (int i = 0; i < SIZE; i++) { // Заполнили  пустой клетки (5) Внешний цикл вертикаль
            for (int j = 0;  j < SIZE; j++) { // Внутренний по горизантали
                map[i][j] = DOT_EMPTY; // Этот символ отвечает за пустое поле.
            }
        }
    }
}


