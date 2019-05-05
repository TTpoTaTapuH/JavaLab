package jspappl;
public class NameHandler {
private String name;
private int counter;
public int[] sum;
public NameHandler(){
name = null;
counter = 0;
sum = new int[2];
}
/**
* @return the name
*/
public String getName() {
return name;
}
/**
* @param name the name to set
*/
public void setName(String name) {
this.name = name;
}
public void setSum(int s[]) {
this.sum[0]=s[0];
this.sum[1]=s[1];
}
public int[] getSum() {
    return sum;
}
/**
* @return the counter
*/
public int getCounter() {
return counter;
}
/**
* @param counter the counter to set
*/
public void setCounter(int counter) {
this.counter = counter;
}
public void addCounter(int i) {
this.counter = this.counter + i;
}
}