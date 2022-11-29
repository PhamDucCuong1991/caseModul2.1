package quanLySanPham;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private int giaSanPham;
    private String moTaSanPham;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, int giaSanPham, String moTaSanPham) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.moTaSanPham = moTaSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }

    @Override
    public String toString() {
        return "Sản phẩm: " + tenSanPham + ", có mã SP là: " + maSanPham + "\n" +
                "Có giá là: " + giaSanPham + ",sản phẩm này là " + moTaSanPham + "\n";
    }
}
