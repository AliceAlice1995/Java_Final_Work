import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class Program {

    public static void main(String[] args) {

        PriorityQueue<Toy> toysQueue = generateToysQueue(5);

        if (toysQueue.size() > 0) {
            Toy prizeToy = getPrizeToy(toysQueue);
            prizeToy.setQuantity(prizeToy.getQuantity() - 1);
            writeToFileToys(prizeToy);
        }
        
    }

    public static PriorityQueue<Toy> generateToysQueue(int queueSize) {

        Random random = new Random();
        String[] names = {"Лего", "Барби", "Вертолет", "Машина на пульте управления", "Плюшевый медведь"};

        PriorityQueue<Toy> toysQueue = new PriorityQueue<Toy>();
        int maxFreq = 100;

        for (int i = 0; i < queueSize; i++) {
            int freq = random.nextInt(1, 30);
            
            if ( i == queueSize - 1 ) freq = maxFreq;
            else {
                if (freq < maxFreq) maxFreq = maxFreq - freq;
                else {
                    freq = random.nextInt(1, maxFreq);
                    maxFreq = maxFreq - freq;
                }
            }

            Toy toy = new Toy(names[random.nextInt(0, names.length)], 10, freq);

            toysQueue.add(toy);
        }

        return toysQueue;
    }

    public static void writeToFileToys(Toy toy) {

        String fileName = "result.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("\n------\nРезультаты розыгрыша призов:");
            writer.write(String.format(toy.toString()));
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи файла.");
        }

    }

    private static Toy getPrizeToy(PriorityQueue<Toy> toysQueue){

        return toysQueue.poll();
             
    }
}