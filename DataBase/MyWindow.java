//класс она приложения
//подключаемые библиотки
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//создание окна приложения
public class MyWindow extends JFrame {
    Socket s; // Сокет
    DataOutputStream out; //
    DataInputStream in;


    public MyWindow() {

        setTitle("DataBase");
        setSize(600, 400);
        setLocation(400, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SQLHandler.connect(); //подключение к базе данных
        SQLHandler1.connect();
        JTextArea jta1 = new JTextArea();
        jta1.setBackground(Color.white);
        add(jta1);
        JPanel sPanel = new JPanel( new GridLayout(2,4));

        add(sPanel, BorderLayout.SOUTH);
        JPanel sPanel1 = new JPanel(new GridLayout(1,4));
        JButton jb4 = new JButton("Регистрация");
        JButton jb6 = new JButton("Вход");
        JButton jb2 = new JButton("Поиск по нику");
        JButton jb1 = new JButton("Поиск по логину");
        JTextField jtf4 = new JTextField();
        JTextField jtf5 = new JTextField();
        sPanel1.add(jtf4);
        sPanel1.add(jtf5);
        sPanel1.add(jb4);
        sPanel1.add(jb6);
        JButton jb3 = new JButton("Удалить по логину");
        JButton jb7 = new JButton("Показать БД");
        add(sPanel1, BorderLayout.NORTH);
        JButton jb5 = new JButton("Добавить");
        JTextField jtf1 = new JTextField();
        sPanel.add(jtf1, BorderLayout.CENTER);
        JTextField jtf2 = new JTextField();
        sPanel.add(jtf2, BorderLayout.CENTER);
        JTextField jtf3 = new JTextField();
        sPanel.add(jtf3, BorderLayout.CENTER);
        sPanel.add(jtf1);
        sPanel.add(jtf2);
        sPanel.add(jtf3);
        sPanel.add(jb5);
        sPanel.add(jb1);
        sPanel.add(jb2);
        sPanel.add(jb3);
        sPanel.add(jb7);

        jb1.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtf1.getText().isEmpty()){
                    jta1.setText(SQLHandler.showFull()); //при отсутствии текста в поле логина выводится вся бд
                }else{
                    jta1.setText(SQLHandler.getEntryByLogin(jtf1.getText())); //поиск по логину, написанному в поле, в бд
                }


            }
        });
        jb2.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtf3.getText().isEmpty()){
                    jta1.setText(SQLHandler.showFull());
                }else{
                    jta1.setText(SQLHandler.getEntryByNickname(jtf3.getText())); //поиск по нику, написанному в поле, в бд
                }


            }
        });
        jb3.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtf1.getText().isEmpty()){
                    jta1.setText(SQLHandler.showFull());
                }else{
                    jta1.setText(SQLHandler.delByLogin(jtf1.getText())); //удаление по логину, написанному в поле, в бд
                }


            }
        });
        jb5.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtf1.getText().isEmpty()){
                    jta1.setText(SQLHandler.showFull());
                }else{
                    jta1.setText(SQLHandler.ins(jtf1.getText(),jtf2.getText(),jtf3.getText())); //добавление в бд
                }


            }
        });
        jb4.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLHandler1.CheckIn(jtf4.getText(),jtf5.getText()); //добавление в бд
                jta1.setText("Вы успешно зарегистрировались!");
                }
        });

        jb5.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLHandler1.Authorization(jtf4.getText(),jtf5.getText()); //проверка данных в бд
                jta1.setText("Вы успешно авторизовались!");
            }
        });

        jb7.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                jta1.setText(SQLHandler.showFull());
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                SQLHandler.closeConnect(); //при закрытии окна коннект обрывается
                SQLHandler1.closeConnect();
            }
        });
        setVisible(true); //видимость окна


    }


        }

