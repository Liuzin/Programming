//класс подключения к бд
import java.sql.*;

public class SQLHandler1 {
    private static Connection c1;
    private static Statement stmt1;

    public static void connect() {
        try{
            Class.forName("org.sqlite.JDBC"); // регистрируем драйвер для работы с БД
            c1 = DriverManager.getConnection("jdbc:sqlite:authorization.db"); // открываем соединение
            stmt1 = c1.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void closeConnect(){ //метод закрытия коннекта
        try {
            if(c1!=null && !c1.isClosed()){
                c1.close();
            }
        } catch (SQLException e) { //ииииисключения
            e.printStackTrace();
        }
    }
    public static String Authorization(String login, String password){ //авторизация
        ResultSet rs1;
        String str = "";
        try {
            rs1 = stmt1.executeQuery("SELECT * FROM authorization WHERE Login = '" + login + "', Password = '" + password + "'");
            while (rs1.next()) { // Читаем до тех пор пока еще есть строки в ответной таблице
                String log = rs1.getString(2);
                String pass = rs1.getString(3);
                if ((log == "admin") && (pass == "admin")){
                    str = "Добро пожаловать, хозяин!";
                }else{
                    str = "Добро пожаловать, незнакомый человек!";
                }
            }
        } catch (SQLException e) { //иииииисключения
            e.printStackTrace();
        }
        return str;
    }
    public static String CheckIn(String login, String pass){ //регистрация
        String str = "";
        try {

            String query = "INSERT INTO authorization (Login, Password) VALUES ('"+ login +"', '"+ pass +"')";

            stmt1.executeUpdate(query);
        } catch (SQLException e) { //иииииисключения
            e.printStackTrace();
        }
        return str;
    }



}
