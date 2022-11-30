package account;

import main.Main;

import java.io.*;
import java.util.*;

public class ManagerAcc {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Account> danhSach = new HashMap<>();

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

    public void dangki(Account account1) {
        danhSach.put(account1.getUsername(), account1);
        xuatRaFile();
    }


    public Account taoTaiKhoan() {

        String regexPassword = "^[!@#$%^&*][A-Za-z1-9]{6,10}", password;

        Account account1 = null;
        System.out.println("nhâp tài khoản");
        String username = scanner.nextLine();

        while (true) {

            System.out.println("Nhập mật khẩu");
            while (true) {
                System.out.println("Mật khẩu phải có 6-10 ký tự, ký tự đầu phải là ký tự đặc biệt (!@#$%^&*)");
                password = scanner.nextLine();
                if (!password.matches(regexPassword)) {
                    System.out.println("hãy nhập đúng yêu cầu");
                }
                else break;
            }
            System.out.println("Xác nhận lai mật khẩu");
            String password2 = scanner.nextLine();

            if (!password2.equals(password)) {
                System.out.println("Sai mật khẩu, vui lòng nhập lại \n");
            } else {
                if (danhSach.containsKey(username)) {
                    System.out.println("tài khoản đã tồn tại");
                    break;
                } else {
                    account1 = new Account(username, password);
                    System.out.println("Đăng ký thành công!");
                    break;
                }
            }
        }

        return account1;

    }

    public void edit() {

        String regexPassword = "^[!@#$%^&*][A-Za-z1-9]{6,10}", newPassword;
        int count = 0;

        while (true) {
            System.out.println("Xin nhập username");
            String taiKhoan = scanner.nextLine();

            if (!danhSach.containsKey(taiKhoan)) {
                System.out.println("Nhập sai tên tài khoản rồi, vui lòng nhập lại \n");
                count++;
            } else {

                while (true) {

                    System.out.println("Nhập mật khẩu cũ");
                    String matKhauCu = scanner.nextLine();

                    if (matKhauCu.equals(danhSach.get(taiKhoan).getPassword())) {
                        System.out.println("Nhập mật khẩu moi");
                        while (true) {
                            System.out.println("Mật khẩu phải có 6-10 ký tự, ký tự đầu phải là ký tự đặc biệt (!@#$%^&*)");
                            newPassword = scanner.nextLine();
                            if (!newPassword.matches(regexPassword)) {
                                System.out.println("hãy nhập đúng yêu cầu");
                            }
                            else break;
                        }
                        danhSach.get(taiKhoan).setPassword(newPassword);
                        System.out.println("Đổi mật khẩu thành công");
                        xuatRaFile();
                        return;
                    } else {
                        System.out.println("Sai mật khẩu");
                        count++;
                    }

                    if (count == 3) {
                        System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng.");
                        System.exit(0);
                    }
                }

            }
            if (count == 3) {
                System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng.");
                System.exit(0);
            }
        }
    }

    public void dangNhap() {
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
                Main.menuSanPham();
                break;
            } else {
                System.out.println("tài khoản không tồn tại");
                count++;
                if (count == 3) {
                    System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động đóng");
                    System.exit(0);
                }
            }
        }
    }

    public void layLaiMk(String taiKhoan) {
        int count = 0;
        if (!danhSach.containsKey(taiKhoan)) {
            System.out.println("Tài khoản không tồn tại");
        } else {
            System.out.println("Mật khẩu tài khoản này là: " + danhSach.get(taiKhoan).getPassword());
            System.out.println("Mời đăng nhập hệ thống");
            while (true) {
                System.out.println("Nhập vào tài khoản");
                String username = scanner.nextLine();

                if (!username.equals(taiKhoan)) {
                    System.out.println("Bạn phải nhập bằng tài khoản: " + taiKhoan);
                } else {
                    System.out.println("nhập mật khẩu");
                    String password = scanner.nextLine();
                    if (!password.equals(danhSach.get(taiKhoan).getPassword())) {
                        System.err.println("Sai mật khẩu! Mời nhập lại \n");
                        count++;
                        if (count == 3) {
                            System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động thoát");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Xin chào: " + taiKhoan + "! Bạn đã đăng nhập hệ thống thành công");
                        break;
                    }
                }
            }
        }
        Main.menuSanPham();
    }
}

