package main;

public class Minuman {
	private String foto;
	private String namaMinuman;
	private double hargaMinuman;
	private int stokMinuman;
	private String deskripsiMinuman;
	
	public Minuman(String foto, String namaMinuman, double hargaMinuman, int stokMinuman, String deskripsiMinuman) {
		this.foto = foto;
		this.namaMinuman = namaMinuman;
		this.hargaMinuman = hargaMinuman;
		this.stokMinuman = stokMinuman;
		this.deskripsiMinuman = deskripsiMinuman;
	}
	public String getNamaMinuman() {
		return namaMinuman;
	}
	public void setNamaMinuman(String namaMinuman) {
		this.namaMinuman = namaMinuman;
	}
	public int getStokMinuman() {
		return stokMinuman;
	}
	public void setStokMinuman(int stokMinuman) {
		this.stokMinuman = stokMinuman;
	}
	public String getDeskripsiMinuman() {
		return deskripsiMinuman;
	}
	public void setDeskripsiMinuman(String deskripsiMinuman) {
		this.deskripsiMinuman = deskripsiMinuman;
	}
	public double getHargaMinuman() {
		return hargaMinuman;
	}
	public void setHargaMinuman(double harga) {
		this.hargaMinuman = harga;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}
