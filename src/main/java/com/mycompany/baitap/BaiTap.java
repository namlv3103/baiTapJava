    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.baitap;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    /**
     *
     * @author 84931
     */
    class PhanSo {
        private int tuSo;
        private int mauSo;

        // Ham tao mac dinh
        public PhanSo() {
            this(0, 1);
        }

        // Ham tao co doi so
        public PhanSo(int tuSo, int mauSo) {
            if (mauSo == 0) {
                throw new IllegalArgumentException("Mau so khong duoc bang 0.");
            }
            this.tuSo = tuSo;
            this.mauSo = mauSo;
            chuanHoaDau();
        }

        // Ham tao sao chep
        public PhanSo(PhanSo p) {
            this(p.tuSo, p.mauSo);
        }

        public int getTuSo() {
            return tuSo;
        }

        public void setTuSo(int tuSo) {
            this.tuSo = tuSo;
        }

        public int getMauSo() {
            return mauSo;
        }

        public void setMauSo(int mauSo) {
            if (mauSo == 0) {
                throw new IllegalArgumentException("Mau so khong duoc bang 0.");
            }
            this.mauSo = mauSo;
            chuanHoaDau();
        }

        private void chuanHoaDau() {
            if (mauSo < 0) {
                tuSo = -tuSo;
                mauSo = -mauSo;
            }
        }

        private static long ucln(long a, long b) {
            a = Math.abs(a);
            b = Math.abs(b);
            while (b != 0) {
                long temp = a % b;
                a = b;
                b = temp;
            }
            return a == 0 ? 1 : a;
        }

        // Rut gon phan so va tra ve phan so moi
        public PhanSo rutGon() {
            long u = ucln(tuSo, mauSo);
            return new PhanSo((int) (tuSo / u), (int) (mauSo / u));
        }

        public PhanSo cong(PhanSo p) {
            return new PhanSo(tuSo * p.mauSo + p.tuSo * mauSo,
                              mauSo * p.mauSo).rutGon();
        }

        public PhanSo tru(PhanSo p) {
            return new PhanSo(tuSo * p.mauSo - p.tuSo * mauSo,
                              mauSo * p.mauSo).rutGon();
        }

        public PhanSo nhan(PhanSo p) {
            return new PhanSo(tuSo * p.tuSo, mauSo * p.mauSo).rutGon();
        }

        public PhanSo chia(PhanSo p) {
            if (p.tuSo == 0) {
                throw new ArithmeticException("Khong the chia cho phan so bang 0.");
            }
            return new PhanSo(tuSo * p.mauSo, mauSo * p.tuSo).rutGon();
            }

        // So sanh: tra ve so am neu nho hon, 0 neu bang, so duong neu lon hon
        public int soSanh(PhanSo p) {
            long trai = (long) tuSo * p.mauSo;
            long phai = (long) p.tuSo * mauSo;
            return Long.compare(trai, phai);
        }

        public boolean laSoNguyen() {
            return tuSo % mauSo == 0;
        }

        @Override
        public String toString() {
            PhanSo p = rutGon();
            if (p.mauSo == 1) {
                return String.valueOf(p.tuSo);
            }
            return p.tuSo + "/" + p.mauSo;
        }
    }

    public class BaiTap {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            List<PhanSo> danhSach = new ArrayList<>();

            System.out.println("=== BAI TAP 1: TAP HOP PHAN SO ===");
            int n;
            do {
                System.out.print("Nhap so luong phan so n (n >= 1): ");
                while (!sc.hasNextInt()) {
                    System.out.print("Vui long nhap so nguyen: ");
                    sc.next();
                }
                n = sc.nextInt();
                if (n < 1) {
                    System.out.println("n phai lon hon hoac bang 1.");
                }
            } while (n < 1);

            for (int i = 0; i < n; i++) {
                System.out.println("Nhap phan so thu " + (i + 1) + ":");
                int tu;
                int mau;
                System.out.print("  Tu so: ");
                while (!sc.hasNextInt()) {
                    System.out.print("  Vui long nhap tu so la so nguyen: ");
                    sc.next();
                }
                tu = sc.nextInt();

                do {
                    System.out.print("  Mau so (khac 0): ");
                    while (!sc.hasNextInt()) {
                        System.out.print("  Vui long nhap mau so la so nguyen: ");
                        sc.next();
                    }
                    mau = sc.nextInt();
                    if (mau == 0) {
                        System.out.println("  Mau so khong duoc bang 0.");
                    }
                } while (mau == 0);

                danhSach.add(new PhanSo(tu, mau));
            }

            System.out.println("\nDanh sach phan so:");
            for (int i = 0; i < danhSach.size(); i++) {
                System.out.println("P" + (i + 1) + " = " + danhSach.get(i));
            }

            // Tim phan so lon nhat
            PhanSo lonNhat = danhSach.get(0);
            for (int i = 1; i < danhSach.size(); i++) {
                if (danhSach.get(i).soSanh(lonNhat) > 0) {
                    lonNhat = danhSach.get(i);
                }
            }
            System.out.println("\nPhan so lon nhat: " + lonNhat);

            // Tinh tong cac phan so
            PhanSo tong = new PhanSo(0, 1);
            for (PhanSo p : danhSach) {
                tong = tong.cong(p);
            }
            System.out.println("Tong cac phan so: " + tong);

            // Dem phan so khong phai so nguyen
            int demKhongNguyen = 0;
            for (PhanSo p : danhSach) {
                if (!p.laSoNguyen()) {
                    demKhongNguyen++;
                }
            }
            System.out.println("So phan so khong phai so nguyen: " + demKhongNguyen);

            sc.close();
        }
    }
