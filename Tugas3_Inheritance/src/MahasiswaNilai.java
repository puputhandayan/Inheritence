import java.util.ArrayList;

public class MahasiswaNilai extends Mahasiswa {
    private double nilai;
    private static int totalMahasiswa = 0;
    private static int totalLulus = 0;
    private static int totalTidakLulus = 0;
    private static double totalNilai = 0;
    private static ArrayList <MahasiswaNilai> daftarMahasiswa = new ArrayList<>();

    public MahasiswaNilai(String nim, String nama, double nilai) {
        super(nim, nama);
        this.nilai = nilai;
        totalMahasiswa++;
        totalNilai += nilai;

        if (isLulus()) {
            totalLulus++;
        } else {
            totalTidakLulus++;
        }

        daftarMahasiswa.add(this);
    }

    public String getGrade() {
        if (nilai > 100 || nilai < 0) return "Input nilai salah";
        else if (nilai >= 80) return "A";
        else if (nilai >= 70) return "B";
        else if (nilai >= 60) return "C";
        else if (nilai >= 50) return "D";
        else return "E";
    }

    public boolean isLulus() {
        String grade = getGrade();
        return grade.equals("A") || grade.equals("B") || grade.equals("C");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Nilai : " + nilai);
        System.out.println("Grade : " + getGrade());
        System.out.println("=========================================");
    }

    public static void tampilkanStatistik() {
        System.out.println("\nSTATISTIK KELULUSAN");
        System.out.println("Jumlah Mahasiswa : " + totalMahasiswa);

        // Daftar mahasiswa lulus
        System.out.print("Jumlah Lulus : " + totalLulus + " yaitu ");
        daftarMahasiswa.stream()
                .filter(MahasiswaNilai::isLulus)
                .forEach(mhs -> System.out.print(mhs.getNama() + ", "));

        // Daftar mahasiswa tidak lulus
        System.out.print("\nJumlah Tidak Lulus : " + totalTidakLulus + " yaitu ");
        daftarMahasiswa.stream()
                .filter(mhs -> !mhs.isLulus())
                .forEach(mhs -> System.out.print(mhs.getNama() + ", "));

        // Rata-rata nilai
        System.out.printf("\nRata-rata nilai : %.2f\n", (totalNilai / totalMahasiswa));
    }
}