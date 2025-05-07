import java.util.Scanner;

public class SistemPenilaian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("SISTEM PENILAIAN MAHASISWA");
        System.out.println("===========================");

        while (true) {
            System.out.print("Masukkan NIM (atau 'exit' untuk keluar): ");
            String nim = scanner.nextLine();

            if (nim.equalsIgnoreCase("exit")) break;

            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan Nilai: ");
            double nilai = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer

            if (nilai < 0 || nilai > 100) {
                System.out.println("Error: Nilai harus antara 0-100");
                continue;
            }

            MahasiswaNilai mhs = new MahasiswaNilai(nim, nama, nilai);
            mhs.displayInfo();
        }

        MahasiswaNilai.tampilkanStatistik();
        scanner.close();
    }
}