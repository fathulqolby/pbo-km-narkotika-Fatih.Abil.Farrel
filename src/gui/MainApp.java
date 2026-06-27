package gui;

import controller.KnowledgeController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Putusan;
import model.PutusanNarkotika;
import java.util.ArrayList;
import java.util.HashMap;

public class MainApp extends Application {

    private KnowledgeController controller;
    private TableView<Putusan> table;
    private PieChart pieChart;

    // Variabel untuk melacak data mana yang sedang diedit
    private String nomorTerpilih = null;

    @Override
    public void init() {
        controller = new KnowledgeController();
    }

    @Override
    public void start(Stage primaryStage) {
        Label lblTitle = new Label("Sistem Manajemen Putusan Narkotika");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitle.setPadding(new Insets(0, 0, 15, 0));

        setupTable(); // Tabel harus dibuat lebih dulu sebelum Form

        VBox leftArea = new VBox(10);
        leftArea.getChildren().addAll(createSearchBox(), table);
        HBox.setHgrow(leftArea, Priority.ALWAYS);

        VBox rightArea = new VBox(20);
        rightArea.setPrefWidth(350);
        rightArea.getChildren().addAll(createInputForm(), createChart());

        HBox mainLayout = new HBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(leftArea, rightArea);

        VBox root = new VBox(lblTitle, mainLayout);
        root.setPadding(new Insets(20));

        refreshDataLayar(controller.getSemuaData());

        Scene scene = new Scene(root, 1100, 700);
        primaryStage.setTitle("Knowledge Management System - GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createSearchBox() {
        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Masukkan Nomor / Jenis...");
        txtSearch.setPrefWidth(200);

        Button btnCari = new Button("Cari Nomor");
        btnCari.setOnAction(e -> {
            Putusan p = controller.cariData(txtSearch.getText());
            if (p != null) {
                ArrayList<Putusan> hasilCari = new ArrayList<>();
                hasilCari.add(p);
                refreshDataLayar(hasilCari);
            } else {
                showAlert("Pencarian", "Data tidak ditemukan!");
            }
        });

        Button btnFilter = new Button("Filter Jenis");
        btnFilter.setOnAction(e -> {
            ArrayList<Putusan> hasilFilter = controller.filterDataJenis(txtSearch.getText());
            refreshDataLayar(hasilFilter);
        });

        Button btnReset = new Button("Tampilkan Semua");
        btnReset.setOnAction(e -> {
            txtSearch.clear();
            refreshDataLayar(controller.getSemuaData());
        });

        return new HBox(10, new Label("Pencarian:"), txtSearch, btnCari, btnFilter, btnReset);
    }

    private VBox createInputForm() {
        VBox formBox = new VBox(10);
        formBox.setStyle("-fx-padding: 15; -fx-border-color: #bdc3c7; -fx-border-width: 1; -fx-border-radius: 5;");

        Label lblForm = new Label("Form Data Putusan");
        lblForm.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        TextField txtNomor = new TextField(); txtNomor.setPromptText("Nomor Perkara");
        TextField txtNama = new TextField(); txtNama.setPromptText("Nama Terdakwa");
        TextField txtJenis = new TextField(); txtJenis.setPromptText("Jenis Narkotika");
        TextField txtVonis = new TextField(); txtVonis.setPromptText("Vonis (Bulan) - Angka");
        TextField txtBerat = new TextField(); txtBerat.setPromptText("Berat Bukti (Gram) - Angka");

        Button btnTambah = new Button("Tambah Data Baru");
        btnTambah.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
        btnTambah.setMaxWidth(Double.MAX_VALUE);

        Button btnEdit = new Button("Simpan Perubahan");
        btnEdit.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        btnEdit.setMaxWidth(Double.MAX_VALUE);
        btnEdit.setDisable(true); // Dimatikan di awal

        Button btnBatal = new Button("Batal / Bersihkan");
        btnBatal.setMaxWidth(Double.MAX_VALUE);
        btnBatal.setDisable(true); // Dimatikan di awal

        // --- FITUR KLIK TABEL ---
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Memasukkan data dari tabel ke form
                txtNomor.setText(newSelection.getNomorPerkara());
                txtNama.setText(newSelection.getNamaTerdakwa());
                txtVonis.setText(String.valueOf(newSelection.getVonisHukuman()));
                if (newSelection instanceof PutusanNarkotika) {
                    txtJenis.setText(((PutusanNarkotika) newSelection).getJenisNarkotika());
                    txtBerat.setText(String.valueOf(((PutusanNarkotika) newSelection).getBeratBarangBukti()));
                }
                nomorTerpilih = newSelection.getNomorPerkara();

                // Ubah status tombol
                btnTambah.setDisable(true);
                btnEdit.setDisable(false);
                btnBatal.setDisable(false);
            }
        });

        // Event Tambah Data
        btnTambah.setOnAction(e -> {
            try {
                controller.tambahDataManual(txtNomor.getText(), txtNama.getText(), txtJenis.getText(),
                        Integer.parseInt(txtVonis.getText()), Double.parseDouble(txtBerat.getText()));
                refreshDataLayar(controller.getSemuaData());
                kosongkanForm(txtNomor, txtNama, txtJenis, txtVonis, txtBerat, btnTambah, btnEdit, btnBatal);
                showAlert("Sukses", "Data putusan berhasil ditambahkan!");
            } catch (NumberFormatException ex) {
                showAlert("Error Input", "Vonis dan Berat harus berupa angka!");
            }
        });

        // Event Edit Data
        btnEdit.setOnAction(e -> {
            try {
                boolean sukses = controller.editDataManual(nomorTerpilih, txtNomor.getText(), txtNama.getText(),
                        txtJenis.getText(), Integer.parseInt(txtVonis.getText()), Double.parseDouble(txtBerat.getText()));

                if (sukses) {
                    refreshDataLayar(controller.getSemuaData());
                    kosongkanForm(txtNomor, txtNama, txtJenis, txtVonis, txtBerat, btnTambah, btnEdit, btnBatal);
                    showAlert("Sukses", "Data putusan berhasil diperbarui!");
                }
            } catch (NumberFormatException ex) {
                showAlert("Error Input", "Vonis dan Berat harus berupa angka!");
            }
        });

        // Event Batal
        btnBatal.setOnAction(e -> kosongkanForm(txtNomor, txtNama, txtJenis, txtVonis, txtBerat, btnTambah, btnEdit, btnBatal));

        formBox.getChildren().addAll(lblForm, txtNomor, txtNama, txtJenis, txtVonis, txtBerat, btnTambah, btnEdit, btnBatal);
        return formBox;
    }

    // Fungsi utilitas untuk mereset form ke kondisi awal
    private void kosongkanForm(TextField t1, TextField t2, TextField t3, TextField t4, TextField t5,
                               Button btnTambah, Button btnEdit, Button btnBatal) {
        t1.clear(); t2.clear(); t3.clear(); t4.clear(); t5.clear();
        table.getSelectionModel().clearSelection();
        nomorTerpilih = null;
        btnTambah.setDisable(false);
        btnEdit.setDisable(true);
        btnBatal.setDisable(true);
    }

    private VBox createChart() {
        VBox chartBox = new VBox();
        chartBox.setStyle("-fx-padding: 10; -fx-border-color: #bdc3c7; -fx-border-width: 1; -fx-border-radius: 5;");
        pieChart = new PieChart();
        pieChart.setTitle("Statistik Jenis Narkotika");
        pieChart.setLegendVisible(true);
        chartBox.getChildren().add(pieChart);
        return chartBox;
    }

    private void setupTable() {
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Putusan, String> colNomor = new TableColumn<>("Nomor Perkara");
        colNomor.setCellValueFactory(new PropertyValueFactory<>("nomorPerkara"));

        TableColumn<Putusan, String> colNama = new TableColumn<>("Nama Terdakwa");
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaTerdakwa"));

        TableColumn<Putusan, Integer> colVonis = new TableColumn<>("Vonis (Bulan)");
        colVonis.setCellValueFactory(new PropertyValueFactory<>("vonisHukuman"));

        TableColumn<Putusan, String> colJenis = new TableColumn<>("Jenis Narkotika");
        colJenis.setCellValueFactory(cellData -> {
            Putusan p = cellData.getValue();
            if (p instanceof PutusanNarkotika) {
                return new SimpleStringProperty(((PutusanNarkotika) p).getJenisNarkotika());
            }
            return new SimpleStringProperty("-");
        });

        table.getColumns().addAll(colNomor, colNama, colJenis, colVonis);
    }

    private void refreshDataLayar(ArrayList<Putusan> listData) {
        ObservableList<Putusan> dataTable = FXCollections.observableArrayList(listData);
        table.setItems(dataTable);

        HashMap<String, Integer> statistik = new HashMap<>();
        for (Putusan p : listData) {
            if (p instanceof PutusanNarkotika) {
                String jenis = ((PutusanNarkotika) p).getJenisNarkotika();
                jenis = (jenis == null || jenis.isEmpty()) ? "Tidak Diketahui" : jenis;
                statistik.put(jenis, statistik.getOrDefault(jenis, 0) + 1);
            }
        }

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        for (String key : statistik.keySet()) {
            chartData.add(new PieChart.Data(key + " (" + statistik.get(key) + ")", statistik.get(key)));
        }
        pieChart.setData(chartData);
    }

    private void showAlert(String judul, String pesan) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}