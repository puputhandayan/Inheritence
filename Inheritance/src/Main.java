import java.util.Scanner;
import java.util.ArrayList;

// Class induk
class Mahasiswa {
    protected String nim;
    protected String nama;
    protected double nilai;

    public Mahasiswa(String nim, String nama, double nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public double getNilai() {
        return nilai;
    }

    public String getGrade() {
        if (nilai > 100 || nilai < 0) {
            return "Input nilai salah";
        } else if (nilai >= 80) {
            return "A";
        } else if (nilai >= 70) {
            return "B";
        } else if (nilai >= 60) {
            return "C";
        } else if (nilai >= 50) {
            return "D";
        } else {
            return "E";
        }
    }

    public boolean isLulus() {
        String grade = getGrade();
        return grade.equals("A") || grade.equals("B") || grade.equals("C");
    }

    public void displayInfo() {
        System.out.println("NIM   : " + nim);
        System.out.println("Nama  : " + nama);
        System.out.println("Nilai : " + nilai);
        System.out.println("Grade : " + getGrade());
        System.out.println("===========================================================");
    }
}

// Class turunan untuk manajemen data mahasiswa
class ManajemenNilai extends Mahasiswa {
    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    public ManajemenNilai(String nim, String nama, double nilai) {
        super(nim, nama, nilai);
        daftarMahasiswa.add(this);
    }

    public static void tampilkanStatistik() {
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        int jumlahA = 0, jumlahB = 0, jumlahC = 0, jumlahD = 0, jumlahE = 0;
        double totalNilai = 0;
        ArrayList<String> namaLulus = new ArrayList<>();
        ArrayList<String> namaTidakLulus = new ArrayList<>();
        ArrayList<String> namaA = new ArrayList<>();
        ArrayList<String> namaB = new ArrayList<>();
        ArrayList<String> namaC = new ArrayList<>();
        ArrayList<String> namaD = new ArrayList<>();
        ArrayList<String> namaE = new ArrayList<>();

        for (Mahasiswa mhs : daftarMahasiswa) {
            totalNilai += mhs.getNilai();

            if (mhs.isLulus()) {
                jumlahLulus++;
                namaLulus.add(mhs.getNama());
            } else {
                jumlahTidakLulus++;
                namaTidakLulus.add(mhs.getNama());
            }

            switch (mhs.getGrade()) {
                case "A":
                    jumlahA++;
                    namaA.add(mhs.getNama());
                    break;
                case "B":
                    jumlahB++;
                    namaB.add(mhs.getNama());
                    break;
                case "C":
                    jumlahC++;
                    namaC.add(mhs.getNama());
                    break;
                case "D":
                    jumlahD++;
                    namaD.add(mhs.getNama());
                    break;
                case "E":
                    jumlahE++;
                    namaE.add(mhs.getNama());
                    break;
            }
        }

        System.out.println("Jumlah Mahasiswa : " + daftarMahasiswa.size());
        System.out.println("Jumlah Mahasiswa yg Lulus : " + jumlahLulus + " yaitu " + String.join(", ", namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + jumlahTidakLulus + " yaitu " + String.join(", ", namaTidakLulus));
        System.out.println("Jumlah Mahasiswa dengan Nilai A = " + jumlahA + " yaitu " + String.join(", ", namaA));
        System.out.println("Jumlah Mahasiswa dengan Nilai B = " + jumlahB + " yaitu " + String.join(", ", namaB));
        System.out.println("Jumlah Mahasiswa dengan Nilai C = " + jumlahC + " yaitu " + String.join(", ", namaC));
        System.out.println("Jumlah Mahasiswa dengan Nilai D = " + jumlahD + " yaitu " + String.join(", ", namaD));
        System.out.println("Jumlah Mahasiswa dengan Nilai E = " + jumlahE + " yaitu " + String.join(", ", namaE));
        System.out.printf("Rata-rata nilai mahasiswa adalah : %.2f\n", (totalNilai / daftarMahasiswa.size()));
    }
}

public class SistemPenilaian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();

        System.out.println("SISTEM PENILAIAN MAHASISWA");
        System.out.println("==========================");

        while (true) {
            System.out.print("Masukkan NIM (atau ketik 'selesai' untuk keluar): ");
            String nim = scanner.nextLine();

            if (nim.equalsIgnoreCase("selesai")) {
                break;
            }

            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan Nilai: ");
            double nilai = scanner.nextDouble();
            scanner.nextLine(); // Membersihkan buffer

            if (nilai < 0 || nilai > 100) {
                System.out.println("Input nilai anda salah (harus 0-100)");
                continue;
            }

            ManajemenNilai mhs = new ManajemenNilai(nim, nama, nilai);
            mhs.displayInfo();
        }

        System.out.println("\nSTATISTIK NILAI MAHASISWA");
        System.out.println("=========================");
        ManajemenNilai.tampilkanStatistik();

        scanner.close();
    }
}