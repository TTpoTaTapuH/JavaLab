package laba2;

import java.util.Arrays;

interface ISize{
    static final int min_size= 3;
    static final int max_size= 10;
    static final String require_num= "0";
}

interface IWork{
    void getSum(String[] args);
}

class myException extends Exception {
    String text;
    myException(String text){
        this.text = text;
    }
    public String toString () { return "Called exception: "+text;}
}

class task1 implements ISize, IWork{
    static int sum_list1 = 0;
    static int sum_list2 = 0;

    public void getSum(String[] args){
        for(String x:args){
            if(Integer.parseInt(x)%2 == 0 || Integer.parseInt(x)<0){
                sum_list1+=Integer.parseInt(x);

            }
            if(Integer.parseInt(x)%2 == 1 || Integer.parseInt(x)<0){
                sum_list2+=Integer.parseInt(x);
            }
        }
    }
    public static void main(String[] args) throws myException{
        task1 test = new task1();
        if(args.length< test.min_size) throw new myException("Мало аргументов!");
        if(args.length> test.max_size) throw new myException("Много аргументов!");
        System.out.println(Arrays.toString(args));
        boolean check = false;
        for(String x:args){
            if(x.equals(test.require_num)){
                check = true;
            }
        }
        if(check==false){throw new myException("Нет обязательного аргумента 0");}
        test.getSum(args);
        System.out.println("сумма чётных и отрицательных: "+test.sum_list1);
        System.out.println("сумма нечётных и отрицательных: "+test.sum_list2);
    }
}