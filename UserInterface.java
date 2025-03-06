import java.util.Scanner;

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
        System.out.println("APLIKASI SIMPLE CRUD TEXT DATABASE");

        while (true) {
            tampilkanMenu();
            System.out.print("Pilih : ");
            String pilihan = sc.nextLine().toUpperCase();

            switch (pilihan) {
                case "C":
                    System.out.println("INFO: Anda memilih menu Create");
                    System.out.println("--------------------------------------------");
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
                    double ipk = Double.parseDouble(sc.nextLine());

                    System.out.println("----------------------------------------------");
                    boolean status = db.insert(nim, nama, alamat, semester, sks, ipk);
                    if (status) {
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    } else {
                        System.out.println("NIM " + nim + " sudah ada di database");
                        System.err.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    System.out.println("----------------------------------------------");
                    break;

                case "R":
                    System.out.println("INFO: Anda memilih menu Read");
                    db.view();
                    break;

                case "U":
                    System.out.println("INFO: Anda memilih menu Update");
                    db.view();
                    System.out.print("Input Key (NIM Mahasiswa yang akan diupdate): ");
                    String key = sc.nextLine();
                    int index = db.search(key);

                    if (index >= 0) {
                        System.out.println("Anda akan meng-update data " + db.getData().get(index));
                        // Implementasikan proses update
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
