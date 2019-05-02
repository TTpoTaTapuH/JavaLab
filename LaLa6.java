package laba6;

import java.util.*;
        import java.util.LinkedList;
        import java.awt.*;
        import java.awt.event.*;

public class LaLa6 {
    static int count;
    public static void main(String[] args){
        Balls1 balls = new Balls1();
    }
}
class Balls1 extends Frame implements Observer, ActionListener, ItemListener {
    private Ball1[] LL = new Ball1[6];
    private Color col=Color.blue;
    private Frame f;
    private int count=0;
    private int max_count= 6;
    private Choice type;
    private Button color;
    private int col_count = 0;
    private TextField speed;
    private TextField number;
    private TextField new_number;
    private Choice new_speed;
    private Button edit_number;
    private Button edit_speed;
    private Button b;
    Balls1(){
        this.addWindowListener(new WindowAdapter21());
        f = new Frame();
        f.setSize(new Dimension(500,100));
        f.setTitle("Контроль");
        f.setLayout(new GridLayout());
        f.addWindowListener(new WindowAdapter21());
        b = new Button("Пуск");
        b.setSize(new Dimension(10,40));
        b.setActionCommand("OK");
        b.addActionListener(this);
        f.add(b, new Point(20,20));

        type = new Choice();
        type.add("Надпись");
        type.add("Картинка");
        f.add(type, new Point(60, 20));

        color = new Button("Цвет");
        color.setSize(new Dimension(10, 40));
        color.setActionCommand("Color");
        color.addActionListener(this);
        f.add(color, new Point(40, 20));

        speed = new TextField("Скорость", 10);
        f.add(speed);

        number = new TextField("Номер", 10);
        f.add(number);

        new_number = new TextField("Нов_номер", 10);
        f.add(new_number);

        new_speed = new Choice();
        new_speed.add("Очень медленно");
        new_speed.add("Медленно");
        new_speed.add("Нормально");
        new_speed.add("Быстро");
        new_speed.add("Очень быстро");
        f.add(new_speed, new Point(60, 20));

        edit_number = new Button("Изменить номер");
        edit_number.setSize(new Dimension(10, 40));
        edit_number.setActionCommand("Edit_number");
        edit_number.addActionListener(this);
        f.add(edit_number, new Point(40, 20));

        edit_speed = new Button("Изменить скорость");
        edit_speed.setSize(new Dimension(10, 40));
        edit_speed.setActionCommand("Edit_speed");
        edit_speed.addActionListener(this);
        f.add(edit_speed, new Point(40, 20));

        f.setVisible(true);
        this.setSize(500,200);
        this.setVisible(true);
        this.setLocation(100, 150);
    }
    public void update(Observable o, Object arg) {
        Ball1 ball = (Ball1)arg;
        System.out.println ("x= " + ball.thr.getName() + ball.x);
        repaint();
    }
    public void paint (Graphics g) {
        if (count>0){
            for(int i=0; i<count; i++){
                Ball1 ball = LL[i];
                g.setColor(ball.col);
                g.drawString(ball.number+""+ball.text1, ball.x, ball.y);
            }
        }
    }
    public void itemStateChanged (ItemEvent iE) {}
    public void actionPerformed (ActionEvent aE) {
        String str = aE.getActionCommand();
        if (str.equals ("OK")){
            Ball1 ball = null;
            if(count<max_count){
                int sped = Integer.parseInt(speed.getText());
                double speeding = 1;
                switch(sped){
                    case 1:
                        speeding = 1;
                        break;
                    case 2:
                        speeding = 2;
                        break;
                    case 3:
                        speeding = 3;
                        break;
                    case 4:
                        speeding = 4;
                        break;
                    default:
                        speeding = 5;
                        break;
                }
                if (type.getSelectedIndex() == 0) {
                    ball = new Ball1(col, "Cars", count, speeding);
                }
                if (type.getSelectedIndex() == 1) {
                    ball = new Ball1(col, "(^_^)", count, speeding);
                }
                //Ball1 ball = new Ball1(col, this.tf.getText());
                LL[count]=(ball);
                count++;
            }
            ball.addObserver(this);
        }
        if (str.equals("Color")) {
            col_count++;
            if(col_count>4){
                col_count=0;
            }
            switch(col_count){
                case 0: col= Color.blue; break;
                case 1: col= Color.green; break;
                case 2: col= Color.red; break;
                case 3: col= Color.black; break;
                case 4: col= Color.yellow; break;
            }
        }
        if (str.equals("Edit_number")) {
            int test_number = Integer.parseInt(number.getText());
            int new_num = Integer.parseInt(new_number.getText());
            Ball1 ball= null;
            boolean permission = true;
            for(int i=0; i<count; i++){
                if(LL[i].number==new_num) {
                    permission = false;
                    break;
                }
            }
            if(permission){
                for(int i=0; i<count; i++){
                    if(LL[i].number==test_number) {
                        ball=LL[i];
                        break;
                    }
                }
                if(ball != null){
                    ball.set_Number(new_num);
                }
            }
        }
        if (str.equals("Edit_speed")) {
            int test_number = Integer.parseInt(number.getText());
            int speed = 1;
            switch(new_speed.getSelectedIndex()){
                case 0: speed = 1; break;
                case 1: speed = 2; break;
                case 2: speed = 3; break;
                case 3: speed = 4; break;
                case 4: speed = 5; break;
                default : speed = 5; break;
            }
            Ball1 ball= null;
            for(int i=0; i<count; i++){
                if(LL[i].number==test_number) {
                    ball=LL[i];
                    break;
                }
            }
            if(ball != null){
                ball.set_Speed(speed);
            }
        }
        repaint();
    }
}
class Ball1 extends Observable implements Runnable {
    Thread thr;
    private boolean xplus;
    private boolean yplus;
    int number = 0;
    int x; int y;
    double v_speed = 1;
    String text1 = "";
    Color col;
    public Ball1 (Color col, String text, int numb, double sped) {
        xplus = true; yplus = true;
        x = 0; y = 30;
        this.col = col;
        number = numb;
        text1 = text;
        v_speed = sped;
        Test.count++;
        thr = new Thread(this,number+":"+text1+":");
        thr.start();
    }
    public void set_Number(int num){number = num;}
    public void set_Speed(int sped){v_speed = sped;}
    public void run(){
        while (true){
            if(x>=475) xplus = false;
            if(x<=-1) xplus = true;
            if(y>=175) yplus = false;
            if(y<=29) yplus = true;
            if(xplus) x+=v_speed; else x-=v_speed;
            if(yplus) y+=v_speed; else y-=v_speed;
            setChanged();notifyObservers (this);
            try{Thread.sleep (10);}
            catch (InterruptedException e){}
        }
    }
}
class WindowAdapter21 extends WindowAdapter {
    public void windowClosing (WindowEvent wE) {System.exit (0);}
}