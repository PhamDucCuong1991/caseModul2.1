package account;

import main.Main;
import menuPhanQuyen.MenuAdmin;
import menuPhanQuyen.MenuUser;

import java.io.*;
import java.util.*;

public class ManagerAcc {
    Scanner scanner = new Scanner(System.in);
    Map<String, Account> danhSach = new HashMap<>();

    List<Account> danhSachBaoMat = new ArrayList<>();

    public void xuatRaFile() {
        File luuAccount = new File("account.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(luuAccount))) {
            for (Account account : danhSach.values()) {
                writer.write(account.getUsername() + "\n");
                writer.write(account.getPassword() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Có lỗi lúc xuất file!");
            e.printStackTrace();
        }
    }

    public void docTuFile() {
        File luuAccount = new File("account.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(luuAccount))) {
            while (true) {
                String username = reader.readLine();
                String password = reader.readLine();
                if (username == null || password == null) break;
                Account account = new Account(username, password);
                danhSach.put(username, account);
            }
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra lúc đọc file!");
            e.printStackTrace();
        }
    }

    public void xuatRaFileAccount() {

        try {
            FileOutputStream luuBaoMat = new FileOutputStream("dsAccount.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(luuBaoMat);
            outputStream.writeObject(danhSachBaoMat);
            outputStream.close();
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra");
            e.printStackTrace();
        }
    }

    public void docTuFileAccount() {

        try {
            FileInputStream luuBaoMat = new FileInputStream("dsAccount.txt");
            ObjectInputStream inputStream = new ObjectInputStream(luuBaoMat);
            danhSachBaoMat = (List<Account>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra");
            e.printStackTrace();
        }

    }

    public Account timKiemBaoMat(String username) {

        for (int i = 0; i < danhSachBaoMat.size(); i++) {
            Account account = danhSachBaoMat.get(i);
            if (username.equals(account.getUsername())) {
                return account;
            }
        }
        return null;
    }

    public void taoTaiKhoan() {

        String regexPassword = "^[!@#$%^&*][A-Za-z1-9]{5,10}", password;

        Account account;
        int count = 0;

        System.out.println("nhâp tài khoản");
        String username = scanner.nextLine();

        if (username.equalsIgnoreCase("admin")) {
            System.err.println("Bạn không thể đăng ký bằng tài khoản này!");
            return;
        }

        if (timKiemBaoMat(username) != null) {
            System.out.println("tài khoản đã tồn tại");
        } else {

            while (true) {
                System.out.println("Nhập mật khẩu");
                while (true) {
                    System.out.println("Mật khẩu phải có 6-10 ký tự, ký tự đầu phải là ký tự đặc biệt (!@#$%^&*)");
                    password = scanner.nextLine();
                    if (!password.matches(regexPassword)) {
                        System.out.println("hãy nhập đúng yêu cầu");
                    } else break;
                }

                System.out.println("Xác nhận lai mật khẩu");
                String password2 = scanner.nextLine();

                if (!password2.equals(password)) {
                    System.out.println("Sai mật khẩu, vui lòng nhập lại \n");
                } else {
                    System.out.println("Đăng ký thành công!");
                    System.out.println("Bạn có muốn bảo mật tài khoản của mình??");
                    System.out.println("Ấn 'y' đồng ý, other: Thoát ra ngoài ");
                    if (!scanner.nextLine().equals("y")) {
                        account = new Account(username, password);
                        danhSachBaoMat.add(account);
                        xuatRaFileAccount();
                        break;
                    } else {
                        System.out.println("Nhập mật khẩu cấp 2");
                        String mkc2 = scanner.nextLine();

                        System.out.println("Nhập số điện thoại");
                        int sdt;
                        do {
                            try {
                                sdt = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (InputMismatchException | NumberFormatException e) {
                                System.err.println("Phải nhập số!");
                            }
                        } while (true);

                        System.out.println("Nhập email");
                        String email = scanner.nextLine();

                        System.out.println("Đăng ký thành công!");

                        account = new Account(username, password, mkc2, sdt, email);
                        danhSachBaoMat.add(account);
                        xuatRaFileAccount();
                        break;
                    }
                }
            }

        }

    }

    public void edit() {

        String regexPassword = "^[!@#$%^&*][A-Za-z1-9]{5,10}", newPassword;
        int count = 0;

        while (true) {
            System.out.println("Xin nhập username");
            String taiKhoan = scanner.nextLine();

            if (!danhSach.containsKey(taiKhoan)) {
                System.err.println("Nhập sai tên tài khoản rồi, vui lòng nhập lại \n");
                count++;
                if (count == 3) {
                    System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng.");
                    System.exit(0);
                }
            } else {
                while (true) {

                    System.out.println("Nhập mật khẩu cũ");
                    String matKhauCu = scanner.nextLine();

                    if (matKhauCu.equals(danhSach.get(taiKhoan).getPassword())) {
                        System.out.println("Nhập mật khẩu moi");
                        int count1 = 0;
                        while (true) {
                            System.out.println("Mật khẩu phải có 6-10 ký tự, ký tự đầu phải là ký tự đặc biệt (!@#$%^&*)");
                            newPassword = scanner.nextLine();
                            if (!newPassword.matches(regexPassword)) {
                                System.out.println("hãy nhập đúng yêu cầu");
                                count1++;
                                if (count1 == 3) {
                                    System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng.");
                                    System.exit(0);
                                }
                            } else break;
                        }
                        danhSach.get(taiKhoan).setPassword(newPassword);
                        System.out.println("Đổi mật khẩu thành công");
                        xuatRaFile();
                        return;
                    } else {
                        System.err.println("Sai mật khẩu");
                        count++;
                        if (count == 3) {
                            System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng.");
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }

    public void dangNhapAdmin() {
        int count = 0;
        while (true) {

            System.out.println("Nhập vào tài khoản");
            String username = scanner.nextLine();
            System.out.println("nhập mật khẩu");
            String password = scanner.nextLine();
            Account bienPhu = danhSach.get(username);

            if (danhSach.containsKey(username)) {
                int count1 = 0;
                while (!password.equals(bienPhu.getPassword())) {
                    count1++;
                    System.out.println("sai mật khẩu, vui lòng nhập lại");
                    password = scanner.nextLine();
                    if (count1 == 3) {
                        System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng");
                        System.exit(0);
                    }
                }
                System.out.println("Xin chào: " + username + "! Bạn đã đăng nhập hệ thống thành công");
                MenuAdmin.menuSanPham();
                break;
            } else {
                System.err.println("tài khoản không tồn tại");
                count++;
                if (count == 3) {
                    System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng");
                    System.exit(0);
                }
            }
        }
    }

    public void dangNhapUser() {
        int count = 0;
        int count1 = 0;

        while (true) {

            System.out.println("Nhập vào tài khoản");
            String username = scanner.nextLine();
            System.out.println("Nhập mật khẩu");
            String password = scanner.nextLine();

            Account account = timKiemBaoMat(username);
            if (account == null) {
                System.err.println("Tài khoản hoặc mật khẩu không đúng");
                count++;
                if (count == 3) {
                    System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động dừng.");
                    System.exit(0);
                }
                break;
            }

            if (username.equals(account.getUsername())) {

                while (!password.equals(account.getPassword())) {
                    count1++;
                    System.err.println("sai mật khẩu, vui lòng nhập lại");
                    password = scanner.nextLine();
                    if (count1 == 3) {
                        System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng");
                        System.exit(0);
                    }
                }
                System.out.println("Xin chào Khách hàng: " + username + "! Bạn đã đăng nhập hệ thống thành công");
                MenuUser.menuUser();
                break;
            } else {
                System.err.println("tài khoản không tồn tại");
                count++;
                if (count == 3) {
                    System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng");
                    System.exit(0);
                }
            }

        }
    }

    public void layLaiMk(String taiKhoan) {
        docTuFileAccount();
        int count = 0;

        Account accountLose = timKiemBaoMat(taiKhoan);

        if (accountLose == null) {
            System.out.println("Tài khoản không tồn tại");
        } else {
            System.out.println("Để lấy lại mật khẩu, bạn cần nhập thông tin bảo mật!");
            System.out.println("Nhập mật khẩu cấp 2");
            String mkc2 = scanner.nextLine();

            System.out.println("Nhập số điện thoại");
            int sdt;
            do {
                try {
                    sdt = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.err.println("Phải nhập số!");
                }
            } while (true);

            System.out.println("Nhập email");
            String email = scanner.nextLine();

            if (mkc2.equals(accountLose.getMkcap2()) && sdt == accountLose.getSdt() && email.equals(accountLose.getEmail())) {

                System.out.println("Mật khẩu tài khoản này là: " + accountLose.getPassword());
                System.out.println("Mời đăng nhập hệ thống");
                while (true) {
                    System.out.println("Nhập vào tài khoản");
                    String username = scanner.nextLine();

                    if (!username.equals(taiKhoan)) {
                        System.out.println("Bạn phải nhập bằng tài khoản: " + taiKhoan);
                        count++;
                        if (count == 3) {
                            System.err.println("Bạn quá lì, nghỉ chơi!");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("nhập mật khẩu");
                        String password = scanner.nextLine();
                        if (!password.equals(accountLose.getPassword())) {
                            System.err.println("Sai mật khẩu! Mời nhập lại \n");
                            count++;
                            if (count == 3) {
                                System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động thoát");
                                System.exit(0);
                            }
                        } else {
                            System.out.println("Xin chào: " + taiKhoan + "! Bạn đã đăng nhập hệ thống thành công");
                            MenuUser.menuUser();
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Thông tin bảo mật sai!");
            }
        }
    }
}

