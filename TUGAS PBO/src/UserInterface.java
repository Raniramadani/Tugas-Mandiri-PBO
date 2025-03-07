import java.util.Scanner;
import java.util.Locale;

public class UserInterface {
    public static void tampilkanMenu() {
        System.out.println();
        System.out.println("+===============+");
        System.out.println("|  Pilih Menu:  |");
        System.out.println("+---------------+");
        System.out.println("|  [C] : Create |");
        System.out.println("|  [R] : Read   |");
        System.out.println("|  [U] : Update |");
        System.out.println("|  [D] : Delete |");
        System.out.println("|  [X] : Exit   |");
        System.out.println("+===============+");
    }

    public static void main(String[] args) {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // Gunakan format desimal dengan titik

        System.out.println("APLIKASI SIMPLE CRUD TEXT DATABASE");

        while (true) {
            tampilkanMenu();
            System.out.print("Pilih : ");
            String pilihan = sc.nextLine().toUpperCase();

            switch (pilihan) {
                case "C":
                    System.out.println("INFO: Anda memilih menu Create");
                    System.out.print("NIM          : ");
                    String nim = sc.nextLine();
                    System.out.print("NAMA MAHASISWA : ");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT       : ");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER     : ");
                    int semester = Integer.parseInt(sc.nextLine());
                    System.out.print("SKS          : ");
                    int sks = Integer.parseInt(sc.nextLine());
                    System.out.print("IPK          : ");
                    double ipk = Double.parseDouble(sc.nextLine().replace(",", ".")); // Fix desimal

                    boolean status = db.insert(nim, nama, alamat, semester, sks, ipk);
                    if (status) {
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    } else {
                        System.err.println("NIM " + nim + " sudah ada di database. GAGAL MENAMBAHKAN DATA.");
                    }
                    break;

                case "R":
                    System.out.println("INFO: Anda memilih menu Read");
                    db.view();
                    break;

                case "U":
                    System.out.println("INFO: Anda memilih menu Update");
                    db.view();
                    System.out.print("Input key (NIM Mahasiswa yang akan di update): ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0) {
                        System.out.println("DATA SAAT INI: " + db.getData().get(index));
                        System.out.println("INPUT DATA BARU");
                        System.out.print("NIM          : ");
                        nim = sc.nextLine();
                        System.out.print("NAMA MAHASISWA : ");
                        nama = sc.nextLine();
                        System.out.print("ALAMAT       : ");
                        alamat = sc.nextLine();
                        System.out.print("SEMESTER     : ");
                        semester = Integer.parseInt(sc.nextLine());
                        System.out.print("SKS          : ");
                        sks = Integer.parseInt(sc.nextLine());
                        System.out.print("IPK          : ");
                        ipk = Double.parseDouble(sc.nextLine().replace(",", "."));

                        status = db.update(index, nim, nama, alamat, semester, sks, ipk);
                        if (status) {
                            System.out.println("DATA BERHASIL DIPERBAHARUI");
                        } else {
                            System.out.println("GAGAL MEMPERBAHARUI DATA");
                        }
                    } else {
                        System.err.println("Mahasiswa dengan NIM: " + key + " tidak ada di database");
                    }
                    break;

                case "D":
                    System.out.println("INFO: Anda memilih menu Delete");
                    db.view();
                    System.out.print("Input Key (NIM mahasiswa yang akan dihapus): ");
                    key = sc.nextLine();
                    index = db.search(key);

                    if (index >= 0) {
                        System.out.println("APAKAH ANDA YAKIN AKAN MENGHAPUS DATA " + db.getData().get(index) + "? (Y/N)");
                        System.out.print("Pilih : ");
                        pilihan = sc.nextLine();
                        if (pilihan.equalsIgnoreCase("Y")) {
                            status = db.delete(index);
                            if (status) {
                                System.out.println("DATA BERHASIL DIHAPUS");
                            } else {
                                System.out.println("GAGAL MENGHAPUS DATA");
                            }
                        }
                    } else {
                        System.out.println("Mahasiswa dengan NIM: " + key + " tidak ada di database");
                    }
                    break;

                case "X":
                    System.out.println("INFO: Anda memilih menu EXIT");
                    System.out.print("APAKAH ANDA YAKIN KELUAR DARI APLIKASI? (Y/N): ");
                    pilihan = sc.nextLine();
                    if (pilihan.equalsIgnoreCase("Y")) {
                        System.out.println("Terima kasih telah menggunakan aplikasi!");
                        sc.close();
                        System.exit(0);
                    }
                    break;

                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi!");
            }
        }
    }
}
