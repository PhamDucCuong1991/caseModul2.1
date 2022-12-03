package menuPhanQuyen;

import quanLySanPham.QuanLySanPham;
import quanLySanPham.SanPham;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuUser {
    public static void menuUser() {

        Scanner scanner = new Scanner(System.in);
        QuanLySanPham quanLySanPham = QuanLySanPham.getInstance();
        quanLySanPham.docTuFile();
        while (true) {

            System.out.println("|-----------------MENU-----------------|");
            System.out.println("|    1. Hiển thị danh sách sản phẩm    |");
            System.out.println("|    2. Tìm kiếm sản phẩm              |");
            System.out.println("|    3. Chọn mua hàng                  |");
            System.out.println("|    4. Giỏ hàng của tôi               |");
            System.out.println("|    5. Xóa SP khỏi giỏ hàng           |");
            System.out.println("|    6. Đăng xuất, về menu đăng nhập   |");
            System.out.println("|______________________________________|");

            String id;
            int choice;
            int count = 0;
            String regexNumber = "[1-6]{1}", input;
            do {
                input = scanner.nextLine();
                if (input.matches(regexNumber)) {
                    choice = Integer.parseInt(input);
                    break;
                } else {
                    System.err.println("Nhập sai, hãy nhập lại từ 1 đến 6");
                    count++;
                    if (count == 3) {
                        System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động trở về.");
                        return;
                    }
                }
            } while (true);

            switch (choice) {
                case 1 -> {
                    System.out.println("1. Sắp xếp theo giá từ thấp đến cao");
                    System.out.println("2. Sắp xếp theo mã SP");
                    System.out.println("3. Sắp xếp theo tên SP");
                    do {
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (InputMismatchException | NumberFormatException e) {
                            System.err.println("Có lỗi xảy ra, bạn phải chọn từ 1-3");
                            count++;
                            if (count == 3) {
                                System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động trở về!");
                                return;
                            }
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
                            count++;
                            if (count == 3) {
                                System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động trở về.");
                                return;
                            }
                    }
                }

                case 2 -> {
                    System.out.println("1. Tìm theo mã sản phẩm");
                    System.out.println("2. Tìm theo tên sản phẩm");

                    do {
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (InputMismatchException | NumberFormatException e) {
                            System.err.println("Có lỗi xảy ra, bạn phải chọn 1 hoặc 2");
                            count++;
                            if (count == 3) {
                                System.err.println("Bạn đã nhập sai 3 lần, hệ thống tự động trở về!");
                                return;
                            }
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
                            if (quanLySanPham.timKiemTuongDoi(tenSP).size() == 0) {
                                System.err.println("Sản phẩm không tồn tại! Không có kết quả");
                            } else {
                                System.out.println(quanLySanPham.timKiemTuongDoi(tenSP));
                            }
                            break;
                        default:
                            System.err.println("Không có chức năng này");
                    }
                }

                case 3 -> quanLySanPham.chonMuaHang();

                case 4 -> quanLySanPham.showGioHang();

                case 5 -> quanLySanPham.xoaSanPhamGioHang();

                case 6 -> {
                    return;
                }

                default -> System.out.println("Không có lựa chọn này!");
            }
        }
    }
}