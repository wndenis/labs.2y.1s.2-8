package com.deniska;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by WinDA on 19.10.2017))))))).
 */
public class UI {
    private Editor editor;
    private Scanner scanner;
    private boolean running;
    private String lastCommand;
    private LinkedList<String> lastCommandSplitted; // последовательный доступ
    private CommandList commandList;

    UI(){
        editor = new Editor();
        scanner = new Scanner(System.in);
        running = false;
        commandList = new CommandList();
        lastCommandSplitted = new LinkedList<>();
    }

    public void run(){
        commandList.help();
        running = true;
        //main cycle?
    }

    public boolean isRunning(){
        return running;
    }

    public void exit(){
        running = false;
    }

    public CommandList commandList(){
        return commandList;
    }

    private void getInput(){
        lastCommand = scanner.nextLine().toLowerCase();
        //System.out.println("COMMAND: " + lastCommand);
        lastCommandSplitted.clear();

        for (String piece:lastCommand.split(" ")) {
            lastCommandSplitted.addLast(piece);
        }
    }

    public void update(){
        if (running) {

            getInput();
            if (!lastCommandSplitted.isEmpty()) {
                switch (lastCommandSplitted.pop()) {
                    case "circle": {commandList.circle(); break;}
                    case "line":{commandList.line(); break;}
                    case "copy":{commandList.copy(); break;}
                    case "edit":{commandList.edit(); break;}
                    case "rem":{commandList.rem(); break;}
                    case "clear":{commandList.clear(); break;}
                    case "intersection":{commandList.intersection(); break;}

                    case "l":
                    case "list": {commandList.list(); break;}

                    case "?":
                    case "help": {commandList.help(); break;}

                    case "e":
                    case "exit": {commandList.exit(); break;}
                    default: {
                        System.out.println("Команда не найдена. Воспользуйтесь help, чтобы посмотреть список команд.");
                        break;
                    }
                }
            }
        }
    }

    protected class CommandList{
        //private console-only methods
        private void circle(){
            try {//TODO: ПРОДОЛЖИТЬ ОТСЮДА
                Double x = Double.parseDouble(lastCommandSplitted.pop());
                Double y = Double.parseDouble(lastCommandSplitted.pop());
                Double r = Double.parseDouble(lastCommandSplitted.pop());
                Integer position = 0;
                boolean usePosition = false;

                if (!lastCommandSplitted.isEmpty()) {
                    position = Integer.parseInt(lastCommandSplitted.pop());
                    if (position != editor.getFiguresCount()){ // иначе это просто добавление в конец списка
                        usePosition = true;
                        System.out.printf("Новая окружность заменяет %s на позиции %d",
                                editor.getFigure(position).represent(), position);
                    }
                }
                if (!lastCommandSplitted.isEmpty())
                    throw new IllegalArgumentException("Лишние аргументы");
            }
            catch (NumberFormatException e){
                System.out.println("Аргументы должны быть числами");
            }

        }
        private void line(){}
        private void copy(){}
        private void edit(){
            try {
                Integer index = Integer.parseInt(lastCommandSplitted.pop());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        private void rem(){}
        private void intersection(){}
        private void list(){
            System.out.printf("Список фигур (%s):\n", editor.getFiguresCount());
            editor.representFigures();
        }
        private void help(){
            System.out.println("Команды:");

            System.out.println("circle [x] [y] [radius] <index> - добавить круг с центром в x,y и радиусом radius");
            System.out.println("\tradius - положительное действительное число");
            System.out.println("\tx, y - действительное число");
            System.out.println("\tindex - целое число, опциональный аргумент. Указывает, какую фигуру заменит этот круг");
            System.out.println("line [x1] [y1] [x2] [y2] <index> - добавить отрезок с координатами начала и конца x1,y1 и x2,y2");
            System.out.println("\tx1, y1, x2, y2 - действительное число");
            System.out.println("\tindex - целое число, опциональный аргумент. Указывает, какую фигуру заменит этот отрезок");
            System.out.println("copy [index] - создать копию фигуры с индексом index");
            System.out.println("\tindex - положительное целое число");
            System.out.println("edit [index] - редактировать фигуру с индексом index");
            System.out.println("\tindex - положительное целое число");
            System.out.println("rem [index] - удалить фигуру с индексом index");
            System.out.println("\tindex - положительное целое число");
            System.out.println("clear - удалить все фигуры");
            System.out.println("intersection [index1] [index2] - проверить пересечение двух фигур");
            System.out.println("\tindex1, index2 - положительное целое число");
            System.out.println("l, list - список фигур");
            System.out.println("?, help - этот список команд");
            System.out.println("e, exit - выход\n");

            /*
            System.out.println("\"a\", \"add\" - добавить фигуру");
            System.out.println("\"act\", \"action\" - действия над фигурами");
            System.out.println("\"l\", \"list\" - список фигур");
            System.out.println("\"e\", \"exit\" - выход");
            System.out.println("\n");*/
        }


        //common implementation
        void editFigure(FigureBase figureBase){
            if (figureBase instanceof Circle){
                editFigure((Circle) figureBase);
            }
            else if (figureBase instanceof Line){
                editFigure((Line) figureBase);
            }
            else {
                System.out.println("Эту фигуру нельзя редактировать");
            }
        }

        void editFigure(Circle circle){
            final String[] commands = {
                    "setpos [x] [y] - задать центр в x,y",
                    "setradius [radius] - задать радиус в radius",
                    "back - отменить редактирование"
            };

            for (String command: commands) {
                System.out.println(command);
            }

            while (true) {
                getInput();
                if (!lastCommandSplitted.isEmpty()) {
                    switch (lastCommandSplitted.pop()) {
                        case "setpos": {
                            try {
                                Double x = Double.parseDouble(lastCommandSplitted.pop());
                                Double y = Double.parseDouble(lastCommandSplitted.pop());
                                circle.setPosition(new Vector2(x, y));
                                System.out.println(circle.represent());
                                System.out.println("Новые координаты заданы");
                                continue;
                            } catch (Exception e) {
                                System.out.println("Некорректные аргументы");
                                continue;
                            }
                        }
                        case "setradius": {
                            try {
                                Double r = Double.parseDouble(lastCommandSplitted.pop());
                                circle.setRadius(r);
                                System.out.println(circle.represent());
                                System.out.println("Новый радиус задан");
                                continue;
                            } catch (Exception e) {
                                System.out.println("Некорректные аргументы");
                                continue;
                            }
                        }
                        case "back": {
                            help();
                            return;
                        }
                        default: {
                            System.out.println("Команда не найдена. back - выйти из редактирования.");
                        }
                    }
                }
            }
        }

        void editFigure(Line line){
            final String[] commands = {
                    "setstart [x] [y] - задать точку начала в x,y",
                    "setend [x] [y] - задать точку конца в x,y",
                    "back - отменить редактирование"
            };

            for (String command: commands) {
                System.out.println(command);
            }


            while (true) {
                getInput();
                if (!lastCommandSplitted.isEmpty()) {
                    switch (lastCommandSplitted.pop()) {
                        case "setstart": {
                            try {
                                Double x = Double.parseDouble(lastCommandSplitted.pop());
                                Double y = Double.parseDouble(lastCommandSplitted.pop());
                                line.setStart(new Vector2(x, y));
                                System.out.println(line.represent());
                                System.out.println("Новые координаты начала заданы");
                                continue;
                            } catch (Exception e) {
                                System.out.println("Некорректные аргументы");
                                continue;
                            }
                        }
                        case "setend": {
                            try {
                                Double x = Double.parseDouble(lastCommandSplitted.pop());
                                Double y = Double.parseDouble(lastCommandSplitted.pop());
                                line.setEnd(new Vector2(x, y));
                                System.out.println(line.represent());
                                System.out.println("Новые координаты конца заданы");
                                continue;
                            } catch (Exception e) {
                                System.out.println("Некорректные аргументы");
                                continue;
                            }
                        }
                        case "back": {
                            help();
                            return;
                        }
                        default: {
                            System.out.println("Команда не найдена. back - выйти из редактирования.");
                        }
                    }
                }
            }
        }

        void edit(Integer index) {
            try{
                if (index < 0 || index >= editor.getFiguresCount()){
                    System.out.println("Нет фигуры с таким индексом");
                    return;
                }

                FigureBase f = editor.getFigure(index);

                System.out.println("Выбрана фигура для редактирования:");
                System.out.println(f.represent());

                System.out.println();
                System.out.println("Доступные команды:");
                editFigure(f);
                return;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("Использование: edit [index]");
        } //TODO: доделать

        void circle(Double x, Double y, Double r, Integer position) {
            if (position < 0 || position > editor.getFiguresCount())
                throw new IllegalArgumentException("Индекс должен быть от 0 до количества элементов в списке");
            if (r < 0)
                throw new IllegalArgumentException("Радиус должен быть положительным");

        }
        void circle(Double x, Double y, Double r) {
            // x, y, r

                Circle newCircle = new Circle(x, y, r);
                if (usePosition)
                    editor.addFigure(newCircle, position);
                else
                    editor.addFigure(newCircle);
                System.out.println(newCircle.represent());
                System.out.println("Окружность добавлена.");
                return;
            }

            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (NoSuchElementException e){
                System.out.println("Недостаточно аргументов");
            }
            System.out.println("Использование: circle [x] [y] [radius] <index>");
        }

        void line(Double x1, Double y1, Double x2, Double y2) {
            //x1, y1, x2, y2
            try {
                Double x1 = Double.parseDouble(lastCommandSplitted.pop());
                Double y1 = Double.parseDouble(lastCommandSplitted.pop());
                Double x2 = Double.parseDouble(lastCommandSplitted.pop());
                Double y2 = Double.parseDouble(lastCommandSplitted.pop());

                Integer position = 0;
                boolean usePosition = false;

                if (!lastCommandSplitted.isEmpty()) {
                    position = Integer.parseInt(lastCommandSplitted.pop());
                    if (!(position == editor.getFiguresCount())) { // иначе это просто добавление в конец списка
                        usePosition = true;
                        System.out.printf("Новый отрезок заменяет %s на позиции %d",
                                editor.getFigure(position).represent(), position);
                    }
                }

                if (!lastCommandSplitted.isEmpty())
                    throw new IllegalArgumentException("Лишние аргументы");

                Line newLine = new Line(x1, y1, x2, y2);
                if (usePosition)
                    editor.addFigure(newLine, position);
                else
                    editor.addFigure(newLine);
                System.out.println(newLine.represent());
                System.out.println("Отрезок добавлен.");
                return;
            }
            catch (NumberFormatException e){
                System.out.println("Аргументы должны быть числами");
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (NoSuchElementException e){
                System.out.println("Недостаточно аргументов");
            }
            System.out.println("Использование: line [x1] [y1] [x2] [y2] <index>");
        }

        void copy(Integer index) {
            //index
            try{
                Integer index = Integer.parseInt(lastCommandSplitted.pop());

                if (index < 0 || index >= editor.getFiguresCount())
                    throw new IndexOutOfBoundsException("Нет фигуры с таким индексом");

                if (!lastCommandSplitted.isEmpty())
                    throw new IllegalArgumentException("Лишние аргументы");

                editor.addFigure(editor.getFigure(index).copy());
                System.out.println("Фигура скопирована и добавлена в конец списка");
                return;
            }
            catch (NumberFormatException e){
                System.out.println("Аргумент должен быть целым числом");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("Использование: copy [index]");
        }

        void rem(Integer index) {
            try{
                Integer index = Integer.parseInt(lastCommandSplitted.pop());

                if (index < 0 || index >= editor.getFiguresCount())
                    throw new IndexOutOfBoundsException("Нет фигуры с таким индексом");

                if (!lastCommandSplitted.isEmpty())
                    throw new IllegalArgumentException("Лишние аргументы");

                editor.remFigure(index);
                System.out.println("Фигура удалена");
                return;
            }
            catch (NumberFormatException e){
                System.out.println("Аргумент должен быть целым числом");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("Использование: rem [index]");
        }

        void clear(){
            if (editor.getFiguresCount() == 0){
                System.out.println("В списке уже нет фигур");
            }
            else {
                editor.clearFigures();
                System.out.println("Все фигуры удалены");
            }
        }

        void intersection(Integer index1, Integer index2) {
            try{
                Integer index1 = Integer.parseInt(lastCommandSplitted.pop());
                Integer index2 = Integer.parseInt(lastCommandSplitted.pop());

                if (index1 < 0 || index1 >= editor.getFiguresCount()
                        || index2 < 0 || index2 >= editor.getFiguresCount())
                    throw new IndexOutOfBoundsException("Нет фигуры с таким индексом");

                if (!lastCommandSplitted.isEmpty())
                    throw new IllegalArgumentException("Лишние аргументы");

                System.out.print(editor.getFigure(index1).represent());
                if (editor.isIntersects(index1, index2))
                    System.out.print(" пересекает ");
                else
                    System.out.print(" не пересекает ");
                System.out.println(editor.getFigure(index2).represent());
                return;
            }
            catch (NumberFormatException e){
                System.out.println("Аргумент должен быть целым числом");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("Использование: intersection [index1] [index2]");
        }

        void exit(){
            System.out.println("Выход из программы");
            UI.this.exit();
        }
    }
}
