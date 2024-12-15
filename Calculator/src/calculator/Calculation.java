package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculation {
    private int total = 0;
    private Queue<Integer> queueValue = new LinkedList<>();
    private Queue<String> queueOpr = new LinkedList<>();

    // Menambahkan nilai ke antrian nilai
    public void addToQueueValue(int inputValue) {
        this.queueValue.add(inputValue);
    }

    // Menambahkan operator ke antrian operator
    public void addToQueueOpr(String inputOpr) {
        this.queueOpr.add(inputOpr);
    }

    // Membersihkan antrian
    public void clearQueue() {
        queueValue.clear();
        queueOpr.clear();
        total = 0;
    }

    // Metode utama untuk menghitung nilai total
    public void calculatingValue() {
        if (!queueValue.isEmpty()) {
            total = queueValue.poll(); // Ambil nilai awal
        }

        while (!queueOpr.isEmpty() && !queueValue.isEmpty()) {
            String operator = queueOpr.poll(); // Ambil operator
            int nextValue = queueValue.poll(); // Ambil nilai berikutnya
            total = operation(total, nextValue, operator); // Hitung nilai total
        }

        // Cetak total akhir
        System.out.println("Total: " + getTotal());
    }

    // Melakukan operasi matematika
    private int operation(int value1, int value2, String opr) {
        switch (opr) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                if (value2 == 0) {
                    System.err.println("Error: Division by zero.");
                    return value1; // Kembalikan nilai awal jika pembagian dengan nol
                }
                return value1 / value2;
            default:
                System.err.println("Error: Undefined operator '" + opr + "'.");
                return value1;
        }
    }

    // Mendapatkan total sebagai string
    public String getTotal() {
        return Integer.toString(this.total);
    }
}
