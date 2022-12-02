package main;

import account.Account;
import account.ManagerAcc;
import quanLySanPham.QuanLySanPham;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ManagerAcc managerAcc = new ManagerAcc();
        managerAcc.docTuFileAccount();
        managerAcc.docTuFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("|-------------------------------------|");
            System.out.println("|          1.Đăng nhập                |");
            System.out.println("|          2.Đăng kí                  |");
            System.out.println("|          3.Đổi mật khẩu             |");
            System.out.println("|          4.Quên mật khẩu            |");
            System.out.println("|          5.Thoát chương trình       |");
            System.out.println("|-------------------------------------|");
            int choice;
            int count = 0;
            String regexNumber = "[1-5]{1}", input;
            do {
                input = scanner.nextLine();
                if (input.matches(regexNumber) == true) {
                    choice = Integer.parseInt(input);
                    break;
                } else {
                    System.err.println("Nhập sai, hãy nhập lại từ 1 đến 5");
                    count++;
                    if (count == 3) {
                        System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động dừng.");
                        System.exit(0);
                    }
                }

            } while (true);
            switch (choice) {
                case 1:
                    System.out.println("Bạn là Admin hay người dùng?");
                    System.out.println("Chọn 1. Admin; 2. User");
                    String luaChonDangNhap = scanner.nextLine();
                    if (luaChonDangNhap.equals("1") ) {
                        managerAcc.dangNhapAdmin();
                    } else if (luaChonDangNhap.equals("2")) {
                        managerAcc.dangNhapUser();
                    } else {
                        System.out.println("Không có lựa chọn này");
                    }
                    break;
                case 2:
                  managerAcc.taoTaiKhoan();
                    break;
                case 3:
                    managerAcc.edit();
                    break;
                case 4:
                    System.out.println("Nhập vào tài khoản");
                    String taiKhoan = scanner.nextLine();
                    managerAcc.layLaiMk(taiKhoan);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Không có chức năng, hãy nhập lại");
            }
        }
    }
}