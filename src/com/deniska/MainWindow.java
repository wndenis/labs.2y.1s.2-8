package com.deniska;

import javax.swing.*;
import java.awt.*;

/**
 * Created by WinDA on 06.12.2017))))))).
 */
public class MainWindow extends JFrame{
    UI ui = new UI();
    JTextField jta = new JTextField(10);
    Font font = new Font("Times new roman", Font.BOLD, 20);

    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();
    JList figuresList = new JList();
    JScrollPane scrollPane = new JScrollPane();
    JButton circleB = new JButton("Добавить круг");
    JButton lineB = new JButton("Добавить отрезок");
    JButton copyB = new JButton("Копировать");
    JButton editB = new JButton("Редактировать");
    JButton removeB = new JButton("Удалить");
    JButton clearB = new JButton("Очистить фигуры");

    MainWindow(){
        super("Mega Graphing Editor");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        panelLeft.setLayout(new GridLayout(1,2));
        figuresList.setLayoutOrientation(JList.VERTICAL);
        scrollPane.add(figuresList);
        panelLeft.add(scrollPane);

        panelRight.setLayout(new GridLayout(7,1));
        panelRight.add(circleB);
        panelRight.add(lineB);
        panelRight.add(copyB);
        panelRight.add(editB);
        panelRight.add(removeB);
        panelRight.add(clearB);

        panelLeft.setBackground(Color.RED);
        panelRight.setBackground(Color.BLUE);

        add(panelLeft);
        add(panelRight);

        setSize(800, 600);
        setVisible(true);
        cycle();
    }
    private void cycle(){
        ui.run();
        ui.commandList().c

        while (ui.isRunning()){
            ui.update();
        }
    }
}
