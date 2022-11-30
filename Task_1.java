import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task_1 {

    // В файле содержится строка с исходными данными в такой форме:
    // {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
    // Требуется на её основе построить и вывести на экран новую строку, в форме SQL
    // запроса:
    // SELECT * FROM students WHERE name = "Ivanov" AND country = "Russia" AND city
    // = "Moscow";
    // Для разбора строки используйте String.split. Сформируйте новую строку,
    // используя StringBuilder. Значения null не включаются в запрос.
    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("Task_1.txt")) {
            String text = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
            writer.write(text);
            writer.flush();

            FileInputStream inFile = new FileInputStream("Task_1.txt");
            byte[] string = new byte[inFile.available()];
            inFile.read(string);
            String newText = new String(string);
            inFile.close();
            String str = newText.replace("\"", "").replace("{", "").replace("}", "");
            String[] str2 = str.split(", ");

            Map<String, String> dictionary = new HashMap<String, String>();
            for (String items : str2) {
                String[] strNew = items.split(":");

                for (int i = 0; i < strNew.length; i++) {
                    dictionary.put(strNew[0], strNew[1]);
                }
            }

            String new1 = "SELECT * FROM students  WHERE ";
            StringBuilder builder = new StringBuilder();
            builder.append(new1);

            // for (Map.Entry<String, String> entry: dictionary.entrySet()) {
            // builder.append(entry.getKey() + " = " + entry.getValue() + " ");
            // }
            // выводит не в нужном порядке

            String check_null = "null";
            if (dictionary.get("name").equals(check_null) == false) {
                builder.append(" name = " + dictionary.get("name"));
            }
            if (dictionary.get("country").equals(check_null) == false) {
                builder.append(" AND country = " + dictionary.get("country"));
            }
            if (dictionary.get("city").equals(check_null) == false) {
                builder.append(" AND city = " + dictionary.get("city"));
            }
            if (dictionary.get("age").equals(check_null) == false) {
                builder.append(" AND age = " + dictionary.get("age"));
            }
            System.out.println(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}