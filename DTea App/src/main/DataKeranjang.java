package main;

public class DataKeranjang {
	private String foto;
	private String nama;
	private double harga;
	
	public DataKeranjang(String foto, String nama, double harga) {
		this.foto = foto;
		this.nama = nama;
		this.harga = harga;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public double getHarga() {
		return harga;
	}
	public void setHarga(double harga) {
		this.harga = harga;
	}
	
}
