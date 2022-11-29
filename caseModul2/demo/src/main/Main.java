package main;

import account.Account;
import account.ManagerAcc;
import quanLySanPham.QuanLySanPham;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ManagerAcc managerAcc = new ManagerAcc();
        managerAcc.docTuFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.Đăng nhập");
            System.out.println("2.Đăng kí");
            System.out.println("3.Đổi mật khẩu");
            System.out.println("4.Quên mật khẩu");
            System.out.println("5.Thoát chương trình");

            int choice;
            int count = 0;
            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.err.println("Phải nhập số!");
                    count++;
                }
                if (count == 3) {
                    System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động dừng.");
                    System.exit(0);
                }
            } while (true);

            switch (choice) {
                case 1:
                    managerAcc.dangNhap();
                    break;
                case 2:
                    Account account1 = managerAcc.taoTaiKhoan();
                    managerAcc.dangki(account1);
                    managerAcc.xuatRaFile();
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

    public static void menuSanPham() {

        Scanner scanner = new Scanner(System.in);
        QuanLySanPham quanLySanPham = new QuanLySanPham();
        quanLySanPham.docTuFile();
        while (true) {
            String id;
            int choice;
            int count = 0;
            System.out.println("-------------MENU--------------");
            System.out.println("1. Tạo sản phẩm mới");
            System.out.println("2. Tìm kiếm sản phẩm");
            System.out.println("3. Hiển thị danh sách sản phẩm");
            System.out.println("4. Sắp xếp danh sách sản phẩm");
            System.out.println("5. Sửa sản phẩm");
            System.out.println("6. Xóa sản phẩm");
            System.out.println("7. Trở về menu đăng nhập");

            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (InputMismatchException | NumberFormatException e) {
                    System.err.println("Phải nhập số!");
                    count++;
                }
                if (count == 3) {
                    System.out.println("Bạn đã nhập sai 3 lần, hệ tống tự động đóng!");
                    System.exit(0);
                }
            }

            switch (choice) {
                case 1:
                    quanLySanPham.themSanPham(quanLySanPham.taoSanPham());
                    break;
                case 2:

                    System.out.println("1. Tìm theo mã sản phẩm");
                    System.out.println("2. Tìm theo tên sản phẩm");

                    do {
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (InputMismatchException | NumberFormatException e) {
                            System.err.println("Phải nhập số!");
                            count++;
                        }
                        if (count == 3) {
                            System.out.println("Bạn đã nhập sai 3 lần, hệ tống tự động trở về!");
                            return;
                        }
                    } while (true);
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập mã sản phẩm cần tìm");
                            id = scanner.nextLine();
                            if (quanLySanPham.timKiem(id) == null) {
                                System.err.println("Sản phẩm không tồn tại!");
                            } else {
                                System.out.println(quanLySanPham.timKiem(id));
                            }
                            break;
                        case 2:
                            System.out.println("Nhập tên sản phẩm cần tìm");
                            String tenSP = scanner.nextLine();
                            if (quanLySanPham.timKiemTheoTenSP(tenSP) == null) {
                                System.err.println("Sản phẩm không tồn tại!");
                            } else {
                                System.out.println(quanLySanPham.timKiemTheoTenSP(tenSP));
                            }
                            break;
                        default:
                            System.err.println("Không có chức năng này");
                    }
                    break;
                case 3:
                    quanLySanPham.show();
                    break;
                case 4:
                    System.out.println("1. Sắp xếp theo giá từ thấp đến cao");
                    System.out.println("2. Sắp xếp theo mã SP");
                    System.out.println("3. Sắp xếp theo tên SP");
                    do {
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (InputMismatchException | NumberFormatException e) {
                            System.err.println("Phải nhập số!");
                            count++;
                        }
                        if (count == 3) {
                            System.out.println("Bạn đã nhập sai 3 lần, hệ tống tự động trở về!");
                            return;
                        }
                    } while (true);

                    switch (choice) {
                        case 1:
                            quanLySanPham.sapXepTheoGiaSP();
                            quanLySanPham.show();
                            break;
                        case 2:
                            quanLySanPham.sapXepTheoMaSP();
                            quanLySanPham.show();
                            break;
                        case 3:
                            quanLySanPham.sapXepTheoTenSP();
                            quanLySanPham.show();
                            break;
                        default:
                            System.out.println("Không có chức năng này");
                    }
                    break;
                case 5:
                    System.out.println("Nhập sản phẩm cần sửa");
                    id = scanner.nextLine();
                    System.out.println(quanLySanPham.timKiem(id));
                    if (quanLySanPham.timKiem(id) == null) {
                        System.out.println("Sản phẩm này không tồn tại");
                    } else {
                        System.out.println("1. Sửa toàn bộ thông tin");
                        System.out.println("2. Sửa theo thuộc tính");

                        while (true) {
                            try {
                                choice = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (InputMismatchException | NumberFormatException e) {
                                System.err.println("Phải nhập số!");
                                count++;
                            }
                            if (count == 3) {
                                System.out.println("Bạn đã nhập sai 3 lần, hệ tống tự động trở về!");
                                return;
                            }
                        }

                        if (choice == 1) {
                            quanLySanPham.suaToanBoThongTin(quanLySanPham.timKiem(id));
                            System.out.println("Sửa thông tin thành công");
                        } else if (choice == 2) {
                            quanLySanPham.suaSanPham(id);

                        } else {
                            System.out.println("Không có lựa chọn này");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Nhập mã sản phẩm cần xóa");
                    id = scanner.nextLine();
                    quanLySanPham.xoaSanPham(id);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Không có chức năng này \n");

            }
        }
    }
}