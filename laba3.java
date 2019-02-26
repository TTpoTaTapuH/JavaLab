package laba3;
import java.io.*;

interface IEE {
    void HandlerArray();
    void HandlerInputConsole();
    void HandlerOutputConsole();
} // Интерфейс события

class Action {// Класс источника события
    IEE iee;
    Action(IEE iee) {
        this.iee= iee;
    } // Конструктор
    void genArray() {
        iee.HandlerArray();
    } // Генерировать событие
    void genInConsole(){
        iee.HandlerInputConsole();
    }
    void genOutConsole(){
        iee.HandlerOutputConsole();
    }
}

class MyEvent implements IEE {// Класс приёмника события
    public void HandlerArray() {System.out.println ("Сейчас будем обращаться к массиву");}// Обработчик
    public void HandlerInputConsole() {System.out.println ("Сейчас будем обращаться к потоку ввода с консоли");}// Обработчик
    public void HandlerOutputConsole() {System.out.println ("Сейчас будем обращаться к потоку вывода на консоль");}// Обработчик
}

public class laba3 {
    public String filename = "";
    public String out_filename = "";
    public File f;
    public int sum1=0, sum2=0;
    void open_file(String name){
        filename = name;
        File f = new File(filename);
        if(!f.exists()){
            System.out.println("Файл с именем "+filename+"не найден!");
            throw new RuntimeException();
        }
    }
    void read_file(){
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try{
                String s;
                out_filename = br.readLine();
                while((s = br.readLine())!=null){//построчное чтение
                    sb.append(s);
                    sb.append("\n");
                    if(Integer.parseInt(s)%2 == 0 || Integer.parseInt(s)<0){
                        sum1+=Integer.parseInt(s);
                    }
                    if(Integer.parseInt(s)%2 == 1 || Integer.parseInt(s)<0){
                        sum2+=Integer.parseInt(s);
                    }
                }
            }finally{br.close();}
        }catch(IOException e){throw new RuntimeException();}
    }
    void save_file(){
        File f1 = new File(out_filename);
        try{
            if(!f1.exists()) f1.createNewFile();
            PrintWriter pw = new PrintWriter(out_filename);
            try{
                pw.println("Последние операции:");
                pw.println("сумма чётных и отрицательных: "+sum1);
                pw.println("сумма нечётных и отрицательных: "+sum2);
            }finally{pw.close();}
        }catch(IOException e){throw new RuntimeException();}
    }
    public static void main(String[] args){
        laba3 test = new laba3();
        MyEvent event = new MyEvent();
        Action action = new Action(event);
        action.genInConsole();
        if(args.length>0){
            action.genArray();
            test.open_file(args[0]);
            test.read_file();
            test.save_file();
            action.genOutConsole();
            System.out.println("сумма чётных и отрицательных: "+test.sum1);
            System.out.println("сумма нечётных и отрицательных: "+test.sum2);
        }
        else{
            System.out.println("введите название файла!");
        }
    }
}