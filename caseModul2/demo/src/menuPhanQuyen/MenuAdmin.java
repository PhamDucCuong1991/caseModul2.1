package menuPhanQuyen;

import quanLySanPham.QuanLySanPham;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdmin {
    public static void menuSanPham() {

        Scanner scanner = new Scanner(System.in);
        QuanLySanPham quanLySanPham = QuanLySanPham.getInstance();
        quanLySanPham.docTuFile();
        while (true) {

            System.out.println("|-----------------MENU-----------------|");
            System.out.println("|    1. Tạo sản phẩm mới               |");
            System.out.println("|    2. Tìm kiếm sản phẩm              |");
            System.out.println("|    3. Hiển thị danh sách sản phẩm    |");
            System.out.println("|    4. Sắp xếp danh sách sản phẩm     |");
            System.out.println("|    5. Sửa sản phẩm                   |");
            System.out.println("|    6. Xóa sản phẩm                   |");
            System.out.println("|    7. Đăng xuất,trở về menu đăng nhập|");
            System.out.println("|______________________________________|");

            String id;
            int choice;
            int count = 0;
            String regexNumber = "[1-7]{1}", input;
            do {
                input = scanner.nextLine();
                if (input.matches(regexNumber)) {
                    choice = Integer.parseInt(input);
                    break;
                } else {
                    System.err.println("Nhập sai, hãy nhập lại từ 1 đến 7");
                    count++;
                    if (count == 3) {
                        System.out.println("Bạn đã nhập sai 3 lần, hệ thống tự động dừng.");
                        System.exit(0);
                    }
                }

            } while (true);

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
                            System.err.println("Có lỗi xảy ra, bạn phải chọn 1 hoặc 2");
                            count++;
                            if (count == 3) {
                                System.out.println("Bạn đã nhập sai 3 lần, hệ tống tự động trở về!");
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
                            System.err.println("Có lỗi xảy ra, bạn phải chọn từ 1-3");
                            count++;
                            if (count == 3) {
                                System.err.println("Bạn đã nhập sai 3 lần, hệ tống tự động trở về!");
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
                    }
                    break;
                case 5:
                    System.out.println("Nhập sản phẩm cần sửa");
                    id = scanner.nextLine();
                    if (quanLySanPham.timKiem(id) == null) {
                        System.err.println("Sản phẩm này không tồn tại");
                    } else {
                        System.out.println(quanLySanPham.timKiem(id));
                        System.out.println("1. Sửa toàn bộ thông tin");
                        System.out.println("2. Sửa theo thuộc tính");

                        while (true) {
                            try {
                                choice = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (InputMismatchException | NumberFormatException e) {
                                System.err.println("Có lỗi xảy ra, bạn phải chọn 1 hoặc 2");
                                count++;
                                if (count == 3) {
                                    System.out.println("Bạn đã nhập sai 3 lần, hệ tống tự động trở về!");
                                    return;
                                }
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
