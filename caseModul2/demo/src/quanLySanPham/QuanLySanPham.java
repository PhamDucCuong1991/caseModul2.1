package quanLySanPham;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class QuanLySanPham {
    static Scanner scanner = new Scanner(System.in);
    static List<SanPham> danhSachSanPham = new ArrayList<>();

    public SanPham taoSanPham() {

        SanPham sanPham;
        System.out.println("Nhập mã sản phẩm");
        String maSanPham = scanner.nextLine();

        System.out.println("Nhập tên sản phẩm");
        String tenSanPham = scanner.nextLine();

        System.out.println("Nhập giá sản phẩm");
        int giaSanPham;

        do {
            try {
                giaSanPham = Integer.parseInt(scanner.nextLine());
                if (giaSanPham > 0) {
                    break;
                } else {
                    System.err.println("Cho không à má? Nhập lại đê");
                }
            } catch (Exception e) {
                System.err.println("Gía mà má ơi, má nhập cái gì vậy ");
                System.out.println("Nhập lại giá sản phẩm");
            }
        } while (true);

        System.out.println("Nhập mô tả sản phẩm");
        String moTaSanPham = scanner.nextLine();

        sanPham = new SanPham(maSanPham, tenSanPham, giaSanPham, moTaSanPham);
        return sanPham;
    }

    public void themSanPham(SanPham sanPham) {
        danhSachSanPham.add(sanPham);
        System.out.println("Thêm sản phẩm thành công");
        xuatRaFile();
    }

    public SanPham timKiem(String id) {
        for (SanPham sanPham : danhSachSanPham) {
            if (id.equals(sanPham.getMaSanPham())) {
                return sanPham;
            }
        }
        return null;
    }

    public void show() {
        for (SanPham sanpham : danhSachSanPham) {
            System.out.println(sanpham);
        }
    }

    public void suaSanPham(String id) {

        System.out.println("Nhập thuộc tính cần sửa");
        System.out.println("1. Sửa mã sản phẩm");
        System.out.println("2. Sửa tên sản phẩm");
        System.out.println("3. Sửa giá sản phẩm");
        System.out.println("4. Sửa mô tả");

        int luaChonSua;
        do {
            try {
                luaChonSua = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.err.println("Phải nhập số!");
            }
        } while (true);

        switch (luaChonSua) {
            case 1:
                System.out.println("Nhập mã sản phẩm");
                String maSp = scanner.nextLine();
                timKiem(id).setMaSanPham(maSp);
                System.out.println("Thay đổi thành công");
                break;
            case 2:
                System.out.println("Nhập tên sản phẩm");
                String tenSp = scanner.nextLine();
                timKiem(id).setTenSanPham(tenSp);
                System.out.println("Thay đổi thành công");
                break;
            case 3:
                System.out.println("Nhập giá sản phẩm");
                int giaSanPham;
                do {
                    try {
                        giaSanPham = Integer.parseInt(scanner.nextLine());
                        if (giaSanPham > 0) {
                            break;
                        } else {
                            System.err.println("Cho không à má? Nhập lại đê");
                        }
                    } catch (NumberFormatException | InputMismatchException e) {
                        System.out.println("Gía mà má ơi, má nhập cái gì vậy ");
                        System.out.println("Nhập lại giá sản phẩm");
                    }
                } while (true);
                timKiem(id).setGiaSanPham(giaSanPham);
                System.out.println("Thay đổi thành công");
                break;
            case 4:
                System.out.println("Nhập mô tả");
                String moTaSp = scanner.nextLine();
                timKiem(id).setMoTaSanPham(moTaSp);
                System.out.println("Thay đổi thành công");
                break;
            default:
                System.out.println("Không có lựa chọn này \n");
        }
        xuatRaFile();
    }

    public void xoaSanPham(String id) {
        if (timKiem(id) == null) {
            System.err.println("Sản phẩm không tồn tại");
        } else {
            System.out.println(timKiem(id));
            danhSachSanPham.remove(timKiem(id));
            System.out.println("Thao tác xóa thành công!");
        }
        xuatRaFile();
    }

    public void suaToanBoThongTin(SanPham sanPham) {
        danhSachSanPham.set(danhSachSanPham.indexOf(sanPham), taoSanPham());
        xuatRaFile();
    }

    public void xuatRaFile() {
        File luuDanhSach = new File("danhSachSP.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(luuDanhSach))) {
            for (SanPham sanPham : danhSachSanPham) {
                writer.write(sanPham.getMaSanPham() + "\n");
                writer.write(sanPham.getTenSanPham() + "\n");
                writer.write(sanPham.getGiaSanPham() + "\n");
                writer.write(sanPham.getMoTaSanPham() + "\n");
            }
        } catch (Exception e) {
            System.err.println("Có lỗi lúc xuất file!");
            e.printStackTrace();
        }
    }

    public void docTuFile() {
        File layDanhSach = new File("danhSachSP.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(layDanhSach))) {
            while (true) {
                String maSanPham = reader.readLine();
                if (maSanPham == null) break;
                String tenSanPham = reader.readLine();
                int giaSanPham = Integer.parseInt(reader.readLine());
                String moTaSanPham = reader.readLine();

                SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaSanPham, moTaSanPham);
                danhSachSanPham.add(sanPham);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra lúc đọc file!");
            e.printStackTrace();
        }

    }


    public void sapXepTheoMaSP() {
        danhSachSanPham.sort(Comparator.comparing(SanPham::getMaSanPham));
    }

    public void sapXepTheoGiaSP() {
        danhSachSanPham.sort(Comparator.comparing(SanPham::getGiaSanPham));
    }

    public void sapXepTheoTenSP() {
        danhSachSanPham.sort(Comparator.comparing(SanPham::getTenSanPham));
    }

    public SanPham timKiemTheoTenSP(String tenSanPham) {

        for (SanPham sanPham1 : danhSachSanPham) {
            if (tenSanPham.equals(sanPham1.getTenSanPham())) {
                return sanPham1;
            }
        }
        return null;
    }
}
