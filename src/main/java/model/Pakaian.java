package model;

import jakarta.persistence.*;

@Entity
@Table(name = "pakaian")
public class Pakaian {

    @Id
    @Column(name = "id_pakaian")
    private String idPakaian;

    @Column(name = "nama_pakaian")
    private String namaPakaian;

    @Column(name = "kategori")
    private String kategori;

    @Column(name = "tarif_sewa")
    private double tarifSewa;

    @Column(name = "stok")
    private int stok;

    // Getter dan Setter
    public String getIdPakaian() { return idPakaian; }
    public void setIdPakaian(String idPakaian) { this.idPakaian = idPakaian; }

    public String getNamaPakaian() { return namaPakaian; }
    public void setNamaPakaian(String namaPakaian) { this.namaPakaian = namaPakaian; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public double getTarifSewa() { return tarifSewa; }
    public void setTarifSewa(double tarifSewa) { this.tarifSewa = tarifSewa; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}