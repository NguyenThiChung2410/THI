package model;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import util.FileHelper;

/*
  Tác giả: Họ tên sinh viên
 */
public class QLKhachHang {

    private ArrayList<KhachHang> dsKhachHang;

    public QLKhachHang() {
        dsKhachHang = new ArrayList<>();
    }

    public QLKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    //sinh viên cải đặt cho các phương thức xử lý sau
    public void DocKhachHang(String filename) {
        ArrayList<String> data = FileHelper.readFileText(filename); 
        
        dsKhachHang.clear();
        for (String item : data) {
            String[] arr = item.split(";");
            KhachHang kh = new KhachHang();
            kh.setMaso(arr[0]);
            kh.setHoten(arr[1]);

            kh.setChisocu(Double.parseDouble(arr[3]));
            kh.setChisomoi(Double.parseDouble(arr[4]));
            
            dsKhachHang.add(kh);
        }
    }

    public void GhiHoaDon(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (KhachHang kh : dsKhachHang) {
                String maso=kh.getMaso();
                String hoten=kh.getHoten();
                double sokwm=kh.getTieuThu();
                double tientra=kh.getTienTra();
                bw.write(kh.getMaso() + ";" + kh.getHoten()+";" + ";" + kh.getTieuThu() + ";" + kh.getTienTra());
                bw.newLine();
            }
            System.out.println("Xuất hoá đơn thành công");
        } catch (Exception e) {
            System.out.println("Xuất hoá đơn thất bại");
            e.printStackTrace();
        }
    }

    public void sapXepTheoLoaiHinh() {
        Comparator<KhachHang> cmp = (kh1, kh2) -> {
            return Double.compare(kh2.getLoai(), kh1.getLoai());
        };
        Collections.sort(dsKhachHang, cmp);
    }

    public double getTieuThuCaoNhat() {
        double max = 0;
        for (KhachHang kh : dsKhachHang) {
            if (kh.getTieuThu() > max) {
                max = kh.getTieuThu();
            }
        }
        return max;
    }

    public double getTieuThuThapNhat() {
        return 0;
    }

    public double getTieuThuTrungBinh() {
        double tong = 0;
        for (KhachHang kh : dsKhachHang) {
            tong += kh.getTieuThu();
        }
        return tong / dsKhachHang.size();
    }
}
