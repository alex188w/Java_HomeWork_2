import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

public class Task_2 {
    public static void main(String[] args) throws IOException {

        // Реализуйте алгоритм сортировки пузырьком числового массива, результат после
        // каждой итерации запишите в лог-файл.
        int[] array = { 23, 15, 5, 3, 41, 9, 5, 55 };

        Logger logger = Logger.getLogger("Task_2");
        logger.setUseParentHandlers(false); // отключаем вывод в консоль
        FileHandler xmlFile = new FileHandler("log.xml");
        SimpleFormatter sFormat = new SimpleFormatter();
        xmlFile.setFormatter(sFormat);
        logger.addHandler(xmlFile);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                logger.info(Arrays.toString(array));
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
