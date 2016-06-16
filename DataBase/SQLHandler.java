//класс подключения к бд
import java.sql.*;

public class SQLHandler {
    private static Connection c;
    private static Statement stmt;

    public static void connect() {
        try{
            Class.forName("org.sqlite.JDBC"); // регистрируем драйвер для работы с БД
            c = DriverManager.getConnection("jdbc:sqlite:database.db"); // открываем соединение
            stmt = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void closeConnect(){ //метод закрытия коннекта
        try {
            if(c!=null && !c.isClosed()){
                c.close();
            }
        } catch (SQLException e) { //ииииисключения
            e.printStackTrace();
        }
    }
    public static String showFull(){ //метод отображения всей бд
        ResultSet rs;
        String str = "";
        try {
            rs = stmt.executeQuery("SELECT * FROM database");
            while (rs.next()) { // Читаем до тех пор пока еще есть строки в ответной таблице
                str += rs.getInt(1) + " Логин: " + rs.getString(2) + "\n Пароль: " + rs.getString(3) + "\n Ник: " + rs.getString(4) + "\n";
            }
        } catch (SQLException e) { //иииииисключения
            e.printStackTrace();
        }
        return str;
    }
    public static String getEntryByLogin(String login){ //метод отображения из бд по логину
        ResultSet rs;
        String str = "";
        try {
            rs = stmt.executeQuery("SELECT * FROM database WHERE Login = '" + login + "'");
            while (rs.next()) { // Читаем до тех пор пока еще есть строки в ответной таблице
                str = rs.getInt(1) + " Логин: " + rs.getString(2) + "\n Пароль: " + rs.getString(3) + "\n Ник: " + rs.getString(4) + "\n";
            }
        } catch (SQLException e) { //иииииисключения
            e.printStackTrace();
        }
        return str;
    }
    public static String getEntryByNickname(String nick){ //метод отображения из бд по нику
        ResultSet rs;
        String str = "";
        try {
            rs = stmt.executeQuery("SELECT * FROM database WHERE Nickname = '" + nick + "'");
            while (rs.next()) { // Читаем до тех пор пока еще есть строки в ответной таблице
                str = rs.getInt(1) + " Логин: " + rs.getString(2) + "\n Пароль: " + rs.getString(3) + "\n Ник: " + rs.getString(4) + "\n";
            }
        } catch (SQLException e) { //иииииисключения
            e.printStackTrace();
        }
        return str;
    }
    public static String ins(String login, String pass, String nick) { //метод добавления в бд
String str = "";
        try {

            String query = "INSERT INTO database (Login, Password, Nickname) VALUES ('"+ login +"', '"+ pass +"','"+ nick +"')";

            stmt.executeUpdate(query);
        } catch (SQLException e) { //иииииисключения
            e.printStackTrace();
        }
        return str;
    }

   public static String delByLogin(String login){ //метод удаления из бд по логину
        String str = "";
        try {
            String query = "DELETE FROM database WHERE Login = '" + login + "'";
            stmt.executeUpdate(query);
        } catch (SQLException e) { //иииииисключения
            e.printStackTrace();
        }
       return str;
    }



}
