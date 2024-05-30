package main;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	Scene scene;
	
	VBox vbox, vboxJudul, vboxBawah, vboxLay;
	
	HBox hbox, hboxLay;
	
	Label title, email, password, emailR, nameR, passwordR, confirmPasswordR, label;
	
	TextField emailTF,emailRTF, nameTF;
	
	PasswordField passwordF,passwordRF, confirmPasswordF;
	
	Button loginBtn, registerBtn, notMemberBtn, memberBtn, logout, zoomIn, zoomOut, rotateLeft, rotateRight, lanjutBtn;
	
	File file;
	
	Image img;
	
	BorderPane bp, bpAdmin;
	
	GridPane gp;
	
	Alert alert;
	
	Media media;
	
	MediaPlayer mp;
	
	MediaView mv;

	ArrayList<Minuman> listMinuman = new ArrayList<>();
	ArrayList<DataPelanggan> listData = new ArrayList<>();
	ArrayList<DataKeranjang> listDataKeranjang = new ArrayList<>();
	
	void catalogueAndCartWindow(Stage primaryStage) {
		hbox = new HBox();
		hboxLay = new HBox();
		vbox = new VBox();
		vboxJudul = new VBox();
		vboxBawah = new VBox();
		vboxLay = new VBox();
		VBox vboxCart = new VBox();
		VBox vboxInt = new VBox();
		GridPane gp = new GridPane();
		ScrollPane scrollPane = new ScrollPane();
		ScrollPane scrollPane2 = new ScrollPane();
		bp = new BorderPane();
		VBox newvbox = new VBox();		
		newvbox.setPrefHeight(280);
		newvbox.setPrefWidth(200);
		
		title = new Label("Enjoy our tea !");
		title.setStyle("-fx-font-size: 40;");
		
		logout = new Button("Logout");
		logout.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
		
		vboxJudul.getChildren().addAll(title);
		vboxJudul.setAlignment(Pos.TOP_CENTER);
		
		vboxBawah.getChildren().addAll(logout);
		vboxBawah.setAlignment(Pos.BOTTOM_RIGHT);
		VBox.setMargin(logout, new Insets(10,10,10,10));
		
		registerBtn = new Button("Purchase");
		registerBtn.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
		registerBtn.setPrefWidth(160);
		
		for (int i = 0; i < listMinuman.size(); i++) {
			VBox vbinternal = new VBox();
			DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
	    	Minuman minuman = (Minuman)listMinuman.get(i);	
	    	
	    	int kolom = i%2;
	    	int baris = i/2;
	    	
	    	File fileMinuman = new File(minuman.getFoto());
	    	Image imgMinuman = new Image(fileMinuman.toURI().toString());
	    	ImageView iv = new ImageView(imgMinuman);
	    	iv.setFitHeight(220);
	    	iv.setFitWidth(230);			
	   		Label itemNameT = new Label(minuman.getNamaMinuman());
	   		itemNameT.setStyle("-fx-font-size: 20;");
	   		Label itemPrice = new Label("Price: Rp " + df.format(minuman.getHargaMinuman()));
	   		Label itemStock = new Label("Stock: "+ minuman.getStokMinuman() );		
    		Label itemDesc = new Label(minuman.getDeskripsiMinuman());
    		vbinternal.setUserData(itemStock);
    		
	    	vbinternal.getChildren().addAll(iv, itemNameT, itemPrice, itemStock, itemDesc);
	    	vbinternal.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-border-radius: 5;");
	    	vbinternal.setPrefWidth(261);
	    	itemDesc.setMaxWidth(240);
	    	itemDesc.setWrapText(true);
	    	VBox.setMargin(iv, new Insets(10,10,10,10));
	    	VBox.setMargin(itemNameT, new Insets(0,0,5,10));
	    	VBox.setMargin(itemPrice, new Insets(0,0,0,10));
	    	VBox.setMargin(itemStock, new Insets(0,0,5,10));
	    	VBox.setMargin(itemDesc, new Insets(0,0,5,10));
	    	GridPane.setMargin(vbinternal,  new Insets(5,5,5,5));
	    	gp.add(vbinternal, kolom, baris);
	    	
	    	int angka = i;
	    	iv.setOnMouseClicked(e->{
				viewImageWindow(primaryStage, angka);
			});
	    	
	    	vbinternal.setOnDragDetected(e->{
	    		int a = angka;
	    		String b = String.valueOf(a);
	    		Dragboard db = vbinternal.startDragAndDrop(TransferMode.ANY);
	    		ClipboardContent cc = new ClipboardContent();
	    		cc.putString(b);
	    		db.setContent(cc);
	    	});
	    	vbinternal.setOnMouseDragged(e->{
	    		e.setDragDetect(true);
	    	});
	    	vboxInt.setOnDragOver(e->{
	    		if(e.getGestureSource() != vboxInt && e.getDragboard().hasString()) {
	    			e.acceptTransferModes(TransferMode.ANY);
	    		}
	    	});
	    	vboxInt.setOnDragDropped(e->{
	    		Dragboard db = e.getDragboard();
	    		VBox dragvbox = (VBox) e.getGestureSource();
                Object userData = dragvbox.getUserData();
                
	    		Minuman minumanUp = (Minuman)listMinuman.get(Integer.parseInt(db.getString()));	
	    		int stock = minumanUp.getStokMinuman();
	    		int jumlahDataKeranjang = listDataKeranjang.size();
	    		String nama = minumanUp.getNamaMinuman();
	    		
	    		if(db.hasString() && stock > 0) {
	    			
	    			HBox newhbox = new HBox();
	    			newhbox.setPrefHeight(50);
	    			newhbox.setPrefWidth(180);
	    			VBox newvbox1 = new VBox();
	    			VBox.setMargin(newhbox, new Insets(10,10,10,10));
	    			//kalo keranjang masih kosong, langsung masukin produk ke keranjang
	    			if(jumlahDataKeranjang == 0) {
	    				minumanUp.setStokMinuman(stock - 1);
	    				Label ubahStok = (Label) userData;
    					ubahStok.setText("Stock: " + minumanUp.getStokMinuman());
    					
    					File fileMinumanImg = new File(minumanUp.getFoto());
    					Image imgMinumanImg = new Image(fileMinumanImg.toURI().toString());
    					ImageView ivImg = new ImageView(imgMinumanImg);
    					ivImg.setFitHeight(50);
    					ivImg.setFitWidth(50);			
    					Label namaProductCart = new Label(nama);
    					Label hargaProductCart = new Label("Price: Rp " + String.valueOf(df.format(minumanUp.getHargaMinuman())));
    					
    					listDataKeranjang.add(new DataKeranjang(minumanUp.getFoto(), minumanUp.getNamaMinuman(), minumanUp.getHargaMinuman()));
    					
    					newvbox1.getChildren().addAll(namaProductCart, hargaProductCart);
	    	            newhbox.getChildren().addAll(ivImg, newvbox1);
	    	            newvbox.getChildren().add(newhbox);

    					e.setDropCompleted(true);
	    			}else{//ini kalo ada barang di keranjang harus di cek
	    				boolean found = false;
	    				//kalo ada data sama, bakal error
	    	            for (int j = 0; j < listDataKeranjang.size(); j++) {
	    	            	DataKeranjang dataKeranjang =  (DataKeranjang)listDataKeranjang.get(j);
	    	                if (nama.equals(dataKeranjang.getNama())) {
	    	                    showAlert("ERROR", "Item already in cart");
	    	                    found = true;
	    	                    break;
	    	                }
	    	            }
	    	            //kalo data ga ada yang sama, masuk kesini
	    	            if (!found) {
	    	                minumanUp.setStokMinuman(stock - 1);
	    	                Label ubahStok = (Label) userData;
	    	                ubahStok.setText("Stock: " + minumanUp.getStokMinuman());
	    	                
	    	                File fileMinumanImg = new File(minumanUp.getFoto());
	    	                Image imgMinumanImg = new Image(fileMinumanImg.toURI().toString());
	    	                ImageView ivImg = new ImageView(imgMinumanImg);
	    	                ivImg.setFitHeight(50);
	    	                ivImg.setFitWidth(50);
	    	                Label namaProductCart = new Label(nama);
	    	                Label hargaProductCart = new Label("Price: Rp " + String.valueOf(df.format(minumanUp.getHargaMinuman())));

	    	                listDataKeranjang.add(new DataKeranjang(minumanUp.getFoto(), minumanUp.getNamaMinuman(), minumanUp.getHargaMinuman()));
	    	                
	    	                newvbox1.getChildren().addAll(namaProductCart, hargaProductCart);
	    	                newhbox.getChildren().addAll(ivImg, newvbox1);	    	                
	    	                newvbox.getChildren().addAll(newhbox);
	    	                
	    	                e.setDropCompleted(true);
	    	            }
	    			}
	    			
	    			
	    		}else if(db.hasString() && stock == 0) {
	    			showAlert("ERROR", "Drink stock is empty");
	    		}else {
	    			e.setDropCompleted(false);
	    		}
	    		e.consume();
	    	});
	    	
	    	registerBtn.setOnMouseClicked(e->{
	    		listDataKeranjang.clear();
	    	    newvbox.getChildren().clear();

			});
	    	
			logout.setOnMouseClicked(e->{
				primaryStage.close();
				listDataKeranjang.clear();
	    	    newvbox.getChildren().clear();
				mp.stop();
				loginWindow(primaryStage);
			});
		}
		scrollPane2.setContent(newvbox);
		scrollPane2.setFitToWidth(true);
		scrollPane.setContent(gp);
        scrollPane.setFitToWidth(true);
		scrollPane.setPadding(new Insets(10,10,10,10));
		hbox.getChildren().add(scrollPane);
		hbox.setPrefWidth(580);
		hbox.setMaxHeight(400);
		HBox.setMargin(scrollPane, new Insets(10,10,10,10));
		
		HBox spHbox = new HBox();
		spHbox.getChildren().add(scrollPane2);
		spHbox.setPrefWidth(190);
		spHbox.setAlignment(Pos.CENTER);
		
		label = new Label("Your Cart");
		label.setStyle("-fx-font-size: 20;");
		
		bp.setTop(label);
		bp.setCenter(spHbox);
		bp.setBottom(registerBtn);
		BorderPane.setAlignment(registerBtn, Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(label, Pos.TOP_CENTER);
		BorderPane.setMargin(registerBtn, new Insets(10,10,10,10));
		BorderPane.setMargin(label, new Insets(0,10,10,10));
		
		vboxCart.getChildren().addAll(bp);
		vboxCart.setPrefWidth(200);
		vboxCart.setPrefHeight(375);
		vboxCart.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-border-radius: 5;");
		VBox.setMargin(vboxCart, new Insets(10,10,0,0));
		
		vboxInt.getChildren().add(vboxCart);
		vboxInt.setPrefWidth(220);
		vboxInt.setMaxHeight(400);
		
		hboxLay.getChildren().addAll(hbox, vboxInt);
		
		vboxLay.getChildren().addAll(vboxJudul, hboxLay, vboxBawah);

		scene = new Scene(vboxLay, 800,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	void welcomeCustomerWindow(Stage primaryStage) {
		VBox vbinternalWelcome = new VBox();
		
		title = new Label("Welcome to DTea!");
		title.setStyle("-fx-font-size: 40;");

		file = new File("video.mp4");
		media = new Media(file.toURI().toString());
		mp = new MediaPlayer(media);
		mv = new MediaView(mp);
		mv.setFitWidth(370);
		
		lanjutBtn = new Button("Continue");
		lanjutBtn.setPrefWidth(370);
		lanjutBtn.setPrefHeight(40);
		lanjutBtn.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
		
		vbinternalWelcome.getChildren().addAll(title, mv, lanjutBtn);
		vbinternalWelcome.setAlignment(Pos.CENTER);
		VBox.setMargin(mv, new Insets(40,20,40,20));
		
		mv.getMediaPlayer().play();
		
		lanjutBtn.setOnMouseClicked(e->{
			mv.getMediaPlayer().stop();
			file = new File("piano.mp3");
			media = new Media(file.toURI().toString());
			mp = new MediaPlayer(media);
			mp.setCycleCount(MediaPlayer.INDEFINITE);
			mp.play();
			
			catalogueAndCartWindow(primaryStage);		
		});
		scene = new Scene(vbinternalWelcome, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	void viewImageWindow(Stage primaryStage, int nomor) {
		Stage viewImageStage = new Stage();
		viewImageStage.setTitle("Image Preview");
		viewImageStage.getIcons().add(img);
		HBox hboxVi = new HBox();
		VBox VboxVi = new VBox();
		bp = new BorderPane();
		
		Minuman minuman = (Minuman)listMinuman.get(nomor);

		File fileMinumana = new File(minuman.getFoto());
		Image imgMinumana = new Image(fileMinumana.toURI().toString());
		ImageView iv1 = new ImageView(imgMinumana);
		iv1.setFitHeight(250);
		iv1.setFitWidth(250);
		
		zoomIn = new Button("Zoom In");
		zoomOut = new Button("Zoom Out");
		rotateLeft = new Button("Rotate Left");
		rotateRight = new Button("Rotate Right");
		hboxVi.getChildren().addAll(zoomIn, zoomOut, rotateLeft, rotateRight);
		hboxVi.setAlignment(Pos.CENTER);
		HBox.setMargin(zoomIn, new Insets(50,10,10,10));
		HBox.setMargin(zoomOut, new Insets(50,10,10,10));
		HBox.setMargin(rotateLeft, new Insets(50,10,10,10));
		HBox.setMargin(rotateRight, new Insets(50,10,10,10));
		
		VboxVi.getChildren().addAll(iv1, hboxVi);
		VboxVi.setAlignment(Pos.CENTER);
		bp.setCenter(VboxVi);
		
		zoomIn.setOnMouseClicked(e->{
			iv1.setScaleX(iv1.getScaleX()*1.2);
			iv1.setScaleY(iv1.getScaleY()*1.2);
		});
		zoomOut.setOnMouseClicked(e->{
			iv1.setScaleX(iv1.getScaleX()*0.8);
			iv1.setScaleY(iv1.getScaleY()*0.8);
		});
		rotateLeft.setOnMouseClicked(e->{
			iv1.setRotate(iv1.getRotate()-90);
		});
		rotateRight.setOnMouseClicked(e->{
			iv1.setRotate(iv1.getRotate()+90);
		});
		Scene scene = new Scene(bp, 500, 500);
		viewImageStage.setScene(scene);
		viewImageStage.show();
		
	}
	
	void adminWindow(Stage primaryStage) {
		ScrollPane scrollPane = new ScrollPane();
		ScrollPane scrollPane2 = new ScrollPane();
		HBox hbexternal1 = new HBox();
		vboxLay = new VBox();
		vbox = new VBox();
		bp = new BorderPane();
		bpAdmin = new BorderPane();
		BorderPane bpAdd = new BorderPane();
		HBox addProductHB1 = new HBox();
		addProductHB1.setPrefWidth(500);
		HBox addProductHB2 = new HBox();
		addProductHB2.setPrefWidth(500);
		HBox addProductHB3 = new HBox();
		addProductHB3.setPrefWidth(500);
		VBox addProductVB1 = new VBox();
		addProductVB1.setPrefWidth(500);
		VBox addProductVB2 = new VBox();
		addProductVB2.setPrefWidth(500);
		VBox addProductVB3 = new VBox();
		addProductVB3.setPrefWidth(500);
		VBox addProductVB4 = new VBox();
		addProductVB4.setPrefWidth(500);
		VBox addProductVB5 = new VBox();
		addProductVB5.setPrefWidth(500);
		VBox addProductVB6 = new VBox();
		addProductVB6.setPrefWidth(500);
		
		VBox addProductVB7 = new VBox();
		addProductVB7.setPrefWidth(500);
		VBox ubahProduk = new VBox();
		
		Label addProduct = new Label("Add Product");
		addProduct.setStyle("-fx-font-size: 20;");
		
		Label itemFotoAdd = new Label("Photo File Name");
		TextField itemFotoTFAdd = new TextField();
		
		Label itemNameAdd = new Label("Item Name");
		TextField itemNameTFAdd = new TextField();
		
		Label itemPriceAdd = new Label("Item Price");
		TextField itemPriceTFAdd = new TextField();
		
		Label itemStockAdd = new Label("Item Stock");
		Spinner<Integer> itemStockSAdd = new Spinner<Integer>();
		itemStockSAdd.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
		
		Label itemDescAdd = new Label("Description");
		TextArea descTAAdd = new TextArea();
		descTAAdd.setPrefRowCount(3);
        descTAAdd.setWrapText(true);  
        Button addProductBtn = new Button("Add Product");
        addProductBtn.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
        
		addProductVB1.getChildren().addAll(itemNameAdd, itemNameTFAdd );
		addProductVB2.getChildren().addAll(itemPriceAdd, itemPriceTFAdd);
		addProductVB3.getChildren().addAll(itemStockAdd, itemStockSAdd);
		addProductVB4.getChildren().addAll(itemDescAdd, descTAAdd);
		addProductVB5.getChildren().addAll(itemFotoAdd, itemFotoTFAdd);
		addProductHB1.getChildren().addAll(addProductVB1, addProductVB2, addProductVB3);
		addProductHB2.getChildren().addAll(addProductVB4, addProductVB5);
		
		HBox.setMargin(addProductVB1, new Insets(0,20,0,20));
		HBox.setMargin(addProductVB2, new Insets(0,20,0,20));
		HBox.setMargin(addProductVB3, new Insets(0,20,0,20));
		HBox.setMargin(addProductVB4, new Insets(0,20,0,20));
		HBox.setMargin(addProductVB5, new Insets(0,20,0,20));
		HBox.setMargin(addProductHB1, new Insets(20,20,20,20));
		HBox.setMargin(addProductHB2, new Insets(20,20,20,20));
		
		
		bpAdd.setBottom(addProductBtn);
		BorderPane.setMargin(addProductBtn, new Insets(20,20,20,20));
		BorderPane.setAlignment(addProductBtn, Pos.BOTTOM_RIGHT);
		addProductVB6.getChildren().addAll(addProduct, addProductHB1, addProductHB2, bpAdd);
		addProductVB6.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-border-radius: 5;");
		VBox.setMargin(addProductVB6, new Insets(20,20,20,20));
		VBox.setMargin(addProduct, new Insets(20,20,20,20));
		
		addProductBtn.setOnMouseClicked(e->{
			try {
				String fotoNewP = itemFotoTFAdd.getText();
				String namaNewP = itemNameTFAdd.getText();
				double hargaNewP = Integer.parseInt(itemPriceTFAdd.getText());
				int stokNewP = itemStockSAdd.getValue();
				String deskripsiNewP = descTAAdd.getText();
				if(namaNewP.isEmpty()) {
					showAlert("Error", "Item’s Namae must be filled");
				}else if(fotoNewP.isEmpty()){
					showAlert("Error", "Item’s Foto must be filled");
				}else if(namaNewP.isEmpty()){
					showAlert("Error", "Item’s name must be filled");
				}else if(hargaNewP < 10000){
					showAlert("Error", "Item’s Price must be at least 10000");
				}else if(deskripsiNewP.isEmpty()){
					showAlert("Error", "Item’s Description must be filled");
				}else {
					listMinuman.add(new Minuman(fotoNewP, namaNewP, hargaNewP, stokNewP, deskripsiNewP));
					adminWindow(primaryStage);
				}
			}catch(Exception E) {
				
			}
			
			
		});
		
		
		title = new Label("Manage Items");
		title.setStyle("-fx-font-size: 40;");
		
		for (int i = 0; i < listMinuman.size(); i++) {
			VBox vbinternal1 = new VBox();
			VBox vbinternal2 = new VBox();
			VBox vbinternal3 = new VBox();
			VBox vbinternal4 = new VBox();
			VBox vbinternal5 = new VBox();
			HBox hbinternal1 = new HBox();
			HBox hbinternal2 = new HBox();
			HBox hboxbutton = new HBox();
			
	    	Minuman minuman = (Minuman)listMinuman.get(i);
	    	
	    	DecimalFormat df = new DecimalFormat("0.0", DecimalFormatSymbols.getInstance(Locale.US));
	    	
	    	File fileMinuman = new File(minuman.getFoto());
			Image imgMinuman = new Image(fileMinuman.toURI().toString());
			ImageView iv = new ImageView(imgMinuman);
			iv.setFitHeight(200);
			iv.setFitWidth(200);
							
			Label itemNameT = new Label(minuman.getNamaMinuman());
			itemNameT.setStyle("-fx-font-size: 20;");
			
			Label itemName = new Label("Item Name");
			TextField itemNameTF = new TextField();
			itemNameTF.setText((minuman.getNamaMinuman()));
			
			Label itemPrice = new Label("Item Price");
			TextField itemPriceTF = new TextField();
			itemPriceTF.setText(String.valueOf(df.format(minuman.getHargaMinuman())));
			
			Label itemStock = new Label("Item Stock");
			Spinner<Integer> itemStockS = new Spinner<Integer>();
			itemStockS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, minuman.getStokMinuman()));
			
			Label itemDesc = new Label("Description");
			TextArea descTA = new TextArea();
			descTA.setText(minuman.getDeskripsiMinuman());
			descTA.setPrefRowCount(3);
	        descTA.setWrapText(true);  
			
			Button saveChanges = new Button("Save Changes");
			
			vbinternal1.getChildren().addAll(itemName, itemNameTF);
			vbinternal2.getChildren().addAll(itemPrice, itemPriceTF);
			vbinternal3.getChildren().addAll(itemStock, itemStockS);
			vbinternal4.getChildren().addAll(itemDesc, descTA);
			
			hbinternal1.getChildren().addAll(vbinternal1, vbinternal2, vbinternal3);
			
			hboxbutton.getChildren().add(saveChanges);
			hboxbutton.setAlignment(Pos.CENTER_RIGHT);
			HBox.setMargin(saveChanges, new Insets(0,0,20,0));
			saveChanges.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
			
			vbinternal5.getChildren().addAll(itemNameT, hbinternal1, vbinternal4, hboxbutton);
			
			hbinternal2.getChildren().addAll(iv, vbinternal5);
			hbinternal2.setPrefWidth(600);
			vbox.getChildren().add(hbinternal2);
			
			saveChanges.setOnMouseClicked(e->{
				try {
					String nama = itemNameTF.getText();
					if(nama.isEmpty()) {
						showAlert("Error", "Item’s Name must be filled");
					}else {
						minuman.setNamaMinuman(nama);				
						itemNameT.setText(nama);
						itemNameTF.setText(nama);
					}
				}catch(Exception E) {
					
				}
				try {
					double harga = Integer.parseInt(itemPriceTF.getText());
					if(harga<10000) {
						showAlert("Error", "Item’s Price must be at least 10000");
					}else{
						minuman.setHargaMinuman(harga);
						itemPriceTF.setText(df.format(harga));
					}
				}catch(Exception E) {
					showAlert("Error", "Item’s Price must be numeric");
				}
				try {
					int stok = itemStockS.getValue();
					if(stok < 1) {
						showAlert("Error", "Item’s Stock must be at least 1");
					}else if(stok != minuman.getStokMinuman() ) {
						minuman.setStokMinuman(stok);
						itemStockS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, stok));
					}
				}catch(Exception E) {
					
				}
				try {
					String deskripsi = descTA.getText();
					if(deskripsi.length() < 10) {
						showAlert("Error", "Item’s Description must be at least 10 characters");
					}else if(!deskripsi.isEmpty()) {
						minuman.setDeskripsiMinuman(deskripsi);				
						descTA.setText(deskripsi);
					}
				}catch(Exception E) {
					
				}		
			});
			int angka = i;
			iv.setOnMouseClicked(e->{
				
				viewImageWindow(primaryStage, angka);
			});
			VBox.setMargin(itemNameTF, new Insets(0, 10, 10, 0));
			VBox.setMargin(itemPriceTF, new Insets(0, 10, 10, 0));
			VBox.setMargin(itemStockS, new Insets(0, 10, 10, 0));
			VBox.setMargin(itemNameT, new Insets(0, 0, 10, 0));
			VBox.setMargin(descTA, new Insets(0, 0, 10, 0));
			HBox.setMargin(iv, new Insets(0, 10, 10, 0));
		}
		bpAdmin.setCenter(vbox);
		BorderPane.setAlignment(vbox, Pos.CENTER);
		
		Label listProduct = new Label("List Product");
		listProduct.setStyle("-fx-font-size: 20;");
		VBox.setMargin(listProduct, new Insets(0,20,20,0));
		
		addProductVB7.getChildren().addAll(listProduct, bpAdmin);
		addProductVB7.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-border-radius: 5;");
		VBox.setMargin(addProductVB7, new Insets(20,20,20,20));
		addProductVB7.setPadding(new Insets(20,20,20,20));
		
		logout = new Button("Logout");
		logout.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
		logout.setOnMouseClicked(e->{
			primaryStage.close();
			loginWindow(primaryStage);
		});
		
		hbexternal1.getChildren().add(logout);
		hbexternal1.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		ubahProduk.getChildren().addAll(addProductVB6, addProductVB7);
		scrollPane.setContent(ubahProduk);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefWidth(500);
		scrollPane.setPadding(new Insets(10,10,10,10));
		
		bp.setTop(title);
		bp.setCenter(scrollPane);
		bp.setBottom(logout);
		BorderPane.setMargin(logout, new Insets(10,10,10,10));
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(logout, Pos.BOTTOM_RIGHT);
		
		scene = new Scene(bp, 800,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	void registerWindow(Stage primaryStage) {
		title = new Label("Register");
		title.setStyle("-fx-font-size: 40;");
		
		emailRTF = new TextField();
		emailRTF.setPrefWidth(250);
		
		nameR = new Label("Name");
		nameTF = new TextField();
		nameTF.setPrefWidth(250);
		
		passwordRF = new PasswordField();
		passwordRF.setPrefWidth(250);
		
		confirmPasswordR = new Label("Confirm Password");
		confirmPasswordF = new PasswordField();
		confirmPasswordF.setPrefWidth(250);
		
		registerBtn = new Button("Register");
		registerBtn.setPrefWidth(250);
		registerBtn.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
		
		memberBtn = new Button("Already a member? Login");
		memberBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #14b6b9; -fx-underline: true;");
		
		vbox = new VBox();
		vboxJudul = new VBox();
		vboxBawah = new VBox();
		vboxLay = new VBox();
		gp = new GridPane();
		
		vboxJudul.getChildren().add(title);
		vboxJudul.setAlignment(Pos.CENTER);
		vboxBawah.getChildren().add(memberBtn);
		vboxBawah.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(email, emailRTF, nameR, nameTF, password, 
				passwordRF, confirmPasswordR,confirmPasswordF, registerBtn);
		VBox.setMargin(registerBtn, new Insets(10,0,10,0));
		
		vboxLay.getChildren().addAll(vboxJudul, vbox, vboxBawah);
        
		gp.getChildren().addAll(vboxLay);
        gp.setAlignment(Pos.CENTER);
		
		scene= new Scene(gp, 500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		registerBtn.setOnMouseClicked(e->{
			String emailR = emailRTF.getText();
			String nameR = nameTF.getText();
			String passwordR = passwordRF.getText();
			String confirmPasswordR = confirmPasswordF.getText();
			int a = emailR.length();
			int count = 0; 
			for(int i = 0; i<a; i++) {
				if(emailR.substring(i, i+1).equals("@")) {
					count++;
				}
			}

			if(emailR.isEmpty()) {
				showAlert("Error", "Email cannot be empty !");
			}else if(!emailR.contains("@")) {
				showAlert("Error", "Invalid Email !");
			}else if(count > 1) {
				showAlert("Error", "Email must only have one ‘@’ !");				
			}else if(!emailR.endsWith(".com")) {
				showAlert("Error", "Email must end with ‘.com’ !");			
			}else if(nameR.isEmpty()) {
				showAlert("Error", "Name cannot be empty !");
			}else if(passwordR.isEmpty()) {
				showAlert("Error", "Password cannot be empty !");;
			}else if(confirmPasswordR.isEmpty()) {
				showAlert("Error", "Confirm Password cannot be empty !");
			}else if(!confirmPasswordR.equals(passwordR)) {
				showAlert("Error", "Confirm Password must be matched with the Password !");
			}else {
				if(listData.size() == 0) {
					listData.add(new DataPelanggan(emailR, nameR, passwordR, confirmPasswordR));
					showAlertSuccess("Success", "Registration Successful !");
					loginWindow(primaryStage);
				}else {
					for(int i = 0; i<listData.size(); i++) {
						DataPelanggan dataPelanggan = (DataPelanggan)listData.get(i);
						if(emailR.equals(dataPelanggan.getEmail())) {
							showAlert("Error", "Email must be unique, have not been used for any registration before !");
							loginWindow(primaryStage);
						}else {
							listData.add(new DataPelanggan(emailR, nameR, passwordR, confirmPasswordR));
							showAlertSuccess("Success", "Registration Successful !");
							loginWindow(primaryStage);
							return;
						}
					}
				}
				
				
			}
		});
		memberBtn.setOnMouseClicked(e->{
			loginWindow(primaryStage);
			
		});
	}
	
	void loginWindow(Stage primaryStage) {
		title = new Label("Login");
		title.setStyle("-fx-font-size: 40;");
		
		email = new Label("Email");
		emailTF = new TextField();
		emailTF.setPrefWidth(250);
		
		password = new Label("Password");
		passwordF = new PasswordField();
		passwordF.setPrefWidth(250);
		
		loginBtn = new Button("Login");
		loginBtn.setPrefWidth(250);
		loginBtn.setStyle("-fx-background-color: #14b6b9; -fx-text-fill: white;");
		
		notMemberBtn = new Button("Not a member? Register");
		notMemberBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #14b6b9; -fx-underline: true;");
		
		vbox = new VBox();
		vboxJudul = new VBox();
		vboxBawah = new VBox();
		vboxLay = new VBox();
		gp = new GridPane();

		vboxJudul.getChildren().add(title);
		vboxJudul.setAlignment(Pos.CENTER);
		vboxBawah.getChildren().add(notMemberBtn);
		vboxBawah.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(email, emailTF, password, passwordF,loginBtn);
		VBox.setMargin(loginBtn, new Insets(10,0,10,0));
		
		vboxLay.getChildren().addAll(vboxJudul, vbox, vboxBawah);
        
		gp.getChildren().addAll(vboxLay);
        gp.setAlignment(Pos.CENTER);
        
        scene = new Scene(gp, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		loginBtn.setOnMouseClicked(e->{
			String email = emailTF.getText().toString();
			String password = passwordF.getText().toString();
			if(email.isEmpty()) {
				showAlert("Error", "Email cannot be empty !");
			}else if(password.isEmpty()) {
				showAlert("Error", "Password cannot be empty !");
			}else if(email.equals("admin") && password.equals("admin123")) {	
				primaryStage.close();
				adminWindow(primaryStage);	
			}else {
				if(listData.size() == 0) {
					showAlert("Error", "Email has not been registered !");
					registerWindow(primaryStage);
				}
				else {
					for(int i = 0; i < listData.size(); i++) {
						DataPelanggan dataPelanggan = (DataPelanggan)listData.get(i);
						if(!email.equals(dataPelanggan.getEmail())){
							showAlert("Error", "Email has not been registered !");
							registerWindow(primaryStage);
							return;
						}else if(email.equals(dataPelanggan.getEmail())) {
							if(!password.equals(dataPelanggan.getPassword())) {
								showAlert("Error", "Wrong Password !");
							}else {
								welcomeCustomerWindow(primaryStage);
								return;
							}
								
						}
					}
				}
				
			}
		});
		notMemberBtn.setOnMouseClicked(e->{
			registerWindow(primaryStage);
		});
	}

	void showAlert(String judul, String isi) {
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(judul);
		alert.setHeaderText(null);
		alert.setContentText(isi);
		alert.showAndWait();
	}
	
	void showAlertSuccess(String judul, String isi) {
		alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(judul);
		alert.setHeaderText(null);
		alert.setContentText(isi);
		alert.showAndWait();
	}

	void initialized(Stage primaryStage) {
		listMinuman.add(new Minuman("lemon_tea.jpg", "Lemon Tea", 20000, 10, "A refreshing "
				+ "infusion of black tea subtly brightened by the zesty essence of fresh "
				+ "lemons, offering a tangy and invigorating flavor profile"));
		listMinuman.add(new Minuman("black_tea.jpg", "Black Tea", 10000, 20, "A robust and "
				+ "full-bodied brew, characterized by its deep, malty notes and a bold, "
				+ "satisfying taste that makes it a classic choice for tea enthusiasts"));
		listMinuman.add(new Minuman("apple_tea.jpg", "Apple Tea", 25000, 20, "A delightful "
				+ "infusion blending the sweetness of ripe apples with the comforting "
				+ "warmth of tea, creating a fruity and aromatic beverage that evokes "
				+ "the essence of a crisp autumn day"));
		listMinuman.add(new Minuman("honey_tea.jpg", "Honey Tea", 30000, 20, "A soothing "
				+ "concoction that combines the natural sweetness of honey with the mellow "
				+ "tones of tea, resulting in a comforting and mildly sweetened drink that "
				+ "is perfect for relaxation"));
		listMinuman.add(new Minuman("milk_tea.jpg", "Milk Tea", 35000, 0, "A rich and creamy "
				+ "fusion of tea and milk, offering a harmonious balance of bold tea flavors "
				+ "and the velvety smoothness of milk, creating a comforting and indulgent "
				+ "beverage enjoyed worldwide"));
		file = new File("logo.png");
		img = new Image(file.toURI().toString());
		
		loginWindow(primaryStage);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		initialized(primaryStage);
		primaryStage.setTitle("DTea Application");
		primaryStage.getIcons().add(img);
	}
}
