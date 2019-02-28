package laba4;
import java.io.*;
import java.net.*;
import java.util.Date;

public class laba4_c implements Runnable{
    public static int PORT = 8887;
    public static String HOST = "localhost";
    public static final int READ_BUFFER_SIZE = 10;
    private String name = null;
    public String history_client = "";

    public laba4_c(String history_client){
        this.history_client = history_client;
    }

    public void run(){
            try {
                Socket socket = new Socket(HOST, PORT);
                System.out.println("socket = " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                // Вывод автоматически Output быталкивается PrintWriter'ом.
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                File f1 = new File(history_client);
                try{
                    //проверка файла
                    if(!f1.exists()) f1.createNewFile();
                    FileWriter pw = new FileWriter(history_client, true);
                    try{
                        out.println("1+");
                        out.println("1+");
                        out.println("15-");
                        out.println("19+");
                        out.println("97-");
                        out.println("81=");
                        String str = in.readLine();
                        System.out.println(str);
                        Date date = new Date();
                        pw.write(date.toString()+":   " + str+"\n");
                        socket.close();
                    }finally{pw.close();}
                }catch(IOException e){throw new RuntimeException();}
        } catch (UnknownHostException e) {
            System.err.println("Исключение: " + e.toString());
        } catch (IOException e) {
            System.err.println("Исключение: " + e.toString());
        }
    }

    public static void main(String[] args) {
        if(args.length>2)
        {
            HOST = args[0];
            PORT = Integer.parseInt(args[1]);
            String name_client = args[2];
            laba4_c ja1 = new laba4_c(name_client);
            Thread th = new Thread(ja1);
            th.start();
        }
        else
            System.out.println("Введи 3 аргумента!");
    }
}

