package Assigment4;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Assignment4 {
    public static void calculate(String inputFile, String outputFile) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);

        ReaderThread reader = new ReaderThread(queue, "input.txt");
        WriterThread writer = new WriterThread(queue, "output.txt");

        new Thread(reader).start();
        new Thread(writer).start();
    }

    public static void main (String[] args) {
        calculate("input.txt", "output");
    }
}

class ReaderThread implements Runnable{

    protected BlockingQueue<String> blockingQueue = null;
    protected String inputFile;

    public ReaderThread(BlockingQueue<String> blockingQueue, String inputFile){
        this.blockingQueue = blockingQueue;
        this.inputFile =  System.getProperty("user.dir") + "/" + inputFile;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(this.inputFile)));
            String buffer = null;
            while((buffer = br.readLine()) != null){
                if(!buffer.trim().isEmpty()) {
                    int result = calculate(buffer);
                    buffer = buffer.concat(" = " + Integer.toString(result));
                }
                blockingQueue.put(buffer);
            }
            blockingQueue.put("EOF");  //When end of file has been reached

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally{
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int calculate(String line) {
        int result = 0;
        String operator = "+";
        String[] arr = line.split(" ");
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i].equals("+") || arr[i].equals("-")) {
                operator = arr[i];
            } else {
                if (operator.equals("+")) {
                    result = result + Integer.parseInt(arr[i]);
                }
                if (operator.equals("-")) {
                    result = result - Integer.parseInt(arr[i]);
                }
            }
        }
        return result;
    }
}

class WriterThread implements Runnable{

    protected BlockingQueue<String> blockingQueue = null;
    protected String outputFile;

    public WriterThread(BlockingQueue<String> blockingQueue, String outputFile){
        this.blockingQueue = blockingQueue;
        this.outputFile = System.getProperty("user.dir") + "/" + outputFile;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("output.txt"));

            while(true){
                String buffer = blockingQueue.take();
                //Check whether end of file has been reached
                if(buffer.equals("EOF")){
                    break;
                }
                writer.println(buffer);
            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }finally{
            writer.close();
        }
    }
}