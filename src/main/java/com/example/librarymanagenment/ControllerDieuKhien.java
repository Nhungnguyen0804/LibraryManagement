package com.example.librarymanagenment;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.io.File;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class ControllerDieuKhien implements Initializable {
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    //---------profile-------------------
    @FXML
    private Label profile_hoten_label;

    @FXML
    private Label profile_msv_label;

    @FXML
    private Label profile_address_label;

    @FXML
    private Label profile_num_book_saved_label;

    @FXML
    private Label profile_phone_label;


    @FXML
    private TextField ho_textfield;

    @FXML
    private TextField mid_textfield;

    @FXML
    private TextField sdt_textfield;

    @FXML
    private TextField ten_textfield;

    @FXML
    private TextField msv_textfield;
    @FXML
    private TextField addr_textfield;

    @FXML
    private Label mes_save_profile;

    //-------book-----------
    @FXML
    private TableView<Book> book_tableView;

    @FXML
    private TableColumn<Book, String> cot_b_author;

    @FXML
    private TableColumn<Book, String> cot_b_chuDe;

    @FXML
    private TableColumn<Book, String> cot_b_id;

    @FXML
    private TableColumn<Book, String> cot_b_nxb;

    @FXML
    private TableColumn<Book, String> cot_b_title;

    @FXML
    private ImageView book_imageView;
    @FXML
    private Button muon_btn;
    @FXML
    private Label msv_label;


    @FXML
    private Label bk_au_label;

    @FXML
    private Label bk_cd_label;

    @FXML
    private Label bk_nxb_label;

    @FXML
    private Label bk_title_label;

    private Image image;


    @FXML
    private AnchorPane borrowed_form;

    @FXML
    private AnchorPane borrowing_form;


    @FXML
    private AnchorPane nav_form;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private AnchorPane book_form;


    @FXML
    private Button book_btn;

    @FXML
    private Button borrowed_btn;
    @FXML
    private Button borrowing_btn;
    @FXML
    private Button profile_btn;

    //----------------------


    //-------borrowing---------------


    @FXML
    private TextField borrowing_bk_id_textfield;

    @FXML
    private TextField borrowing_title_textfield;

    @FXML
    private TextField borrowing_user_id_textfield;
    @FXML
    private TextField date_dinh_tra;

    @FXML
    private TextField date_muon;

    @FXML
    private Label borrowing_cd_label;

    @FXML
    private Label borrowing_nxb_label;

    @FXML
    private Label borrowing_title_label;
    @FXML
    private Label borrowing_au_label;

    @FXML
    private ImageView borrowing_imgView;

    @FXML
    private Rectangle rec_img;

    //----------------------

    //--------------saved book -----------
    @FXML
    private TableColumn<Book, String> s_cot_a;

    @FXML
    private TableColumn<Book, String> s_cot_auth;

    @FXML
    private TableColumn<Book, String> s_cot_book_id;

    @FXML
    private TableColumn<Book, String> s_cot_cd;

    @FXML
    private TableColumn<Book, String> s_cot_nxb;

    @FXML
    private TableColumn<Book, String> s_cot_title;

    @FXML
    private TableView<Book> save_tableView;

    @FXML
    private Label s_auth_label;

    @FXML
    private Label s_cd_label;

    @FXML
    private Label s_nxb_label;

    @FXML
    private Label s_title_label;

    @FXML
    private ImageView save_Img;

    @FXML
    private Label mes_return;

    @FXML
    private Label s_bk_id_label;

    @FXML
    private ImageView img_profile;

    @FXML
    private Circle circle_img;

    @FXML
    private Label tieu_de;

    //----------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTableBook();
        hien_ma_user();
        hien_thi_mac_dinh_msv();
        hien_thi_mac_dinh_date();

        //----profile-----
        hien_thi_mac_dinh_thong_tin_profile();
        //showProfile();
        //-------save--------
        showTableSaveBook();

    }

    public ObservableList<Book> dataList() {

        ObservableList<Book> listBooks = FXCollections.observableArrayList();

        String sql = "SELECT * FROM book";

        connect = Database.connectDB();

        try {

            Book aBooks;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                aBooks = new Book(result.getString("book_id"),
                        result.getString("title"), result.getString("author"),
                        result.getString("nxb"), result.getString("chu_de"), result.getString("image"));

                listBooks.add(aBooks);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBooks;
    }

    private ObservableList<Book> listBook;

    public void showTableBook() {

        listBook = dataList();
        cot_b_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        cot_b_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        cot_b_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        cot_b_nxb.setCellValueFactory(new PropertyValueFactory<>("nxb"));
        cot_b_chuDe.setCellValueFactory(new PropertyValueFactory<>("chuDe"));


        book_tableView.setItems(listBook);

    }

    public void hien_ma_user() {
        // hien thi msv ma sv sd
        msv_label.setText(getData.msv);
    }

    public void selectBookInTable() {

        Book bookData = book_tableView.getSelectionModel().getSelectedItem();

        int n = book_tableView.getSelectionModel().getFocusedIndex();

        if ((n - 1) < -1) {
            return;
        }

        bk_title_label.setText(bookData.getTitle());
        bk_au_label.setText(bookData.getAuthor());
        bk_nxb_label.setText(bookData.getNxb());
        bk_cd_label.setText(bookData.getChuDe());

        String url = "file:" + bookData.getImage();

        image = new Image(url);

        book_imageView.setImage(image);


    }

    // bam nut nao thi hien scene nut day
    public void Btn_menu_act(ActionEvent event) {

        if (event.getSource() == profile_btn) {

            profile_form.setVisible(true);
            book_form.setVisible(false);
            borrowing_form.setVisible(false);
            borrowed_form.setVisible(false);

            profile_btn.setStyle("-fx-background-color:#faafff;");
            book_btn.setStyle("-fx-background-color:#8364e8;");
            borrowing_btn.setStyle("-fx-background-color:#8364e8;");
            borrowed_btn.setStyle("-fx-background-color:#8364e8;");

            tieu_de.setText("PROFILE");


        } else if (event.getSource() == book_btn) {

            profile_form.setVisible(false);
            book_form.setVisible(true);
            borrowing_form.setVisible(false);
            borrowed_form.setVisible(false);

            profile_btn.setStyle("-fx-background-color:#8364e8;");
            book_btn.setStyle("-fx-background-color:#faafff;");
            borrowing_btn.setStyle("-fx-background-color:#8364e8;");
            borrowed_btn.setStyle("-fx-background-color:#8364e8;");

            tieu_de.setText("LIST BOOK");

        } else if (event.getSource() == borrowing_btn) {

            profile_form.setVisible(false);
            book_form.setVisible(false);
            borrowing_form.setVisible(true);
            borrowed_form.setVisible(false);

            profile_btn.setStyle("-fx-background-color:#8364e8;");
            book_btn.setStyle("-fx-background-color:#8364e8;");
            borrowing_btn.setStyle("-fx-background-color:#faafff;");
            borrowed_btn.setStyle("-fx-background-color:#8364e8;");


            tieu_de.setText("MƯỢN SÁCH");

        } else if (event.getSource() == borrowed_btn) {

            profile_form.setVisible(false);
            book_form.setVisible(false);
            borrowing_form.setVisible(false);
            borrowed_form.setVisible(true);

            profile_btn.setStyle("-fx-background-color:#8364e8;");
            book_btn.setStyle("-fx-background-color:#8364e8;");
            borrowing_btn.setStyle("-fx-background-color:#8364e8;");
            borrowed_btn.setStyle("-fx-background-color:#faafff;");

            tieu_de.setText("CÁC SÁCH ĐÃ MƯỢN");
        }
    }


    public void press_muon_btn(ActionEvent event) {

        if (event.getSource() == muon_btn) {
            profile_form.setVisible(false);
            book_form.setVisible(false);
            borrowing_form.setVisible(true);
            borrowed_form.setVisible(false);

            profile_btn.setStyle("-fx-background-color:#8364e8;");
            book_btn.setStyle("-fx-background-color:#8364e8;");
            borrowing_btn.setStyle("-fx-background-color:#faafff;");
            borrowed_btn.setStyle("-fx-background-color:#8364e8;");




        }
    }

    // ---------------muon sach--------------------------

    public void clearFindData() {

        borrowing_title_label.setText("");
        borrowing_au_label.setText("");
        borrowing_nxb_label.setText("");
        borrowing_cd_label.setText("");
        borrowing_imgView.setImage(null);

//        borrowing_title_textfield.setText("");
//        borrowing_bk_id_textfield.setText("");

    }

    public void findBookByBookID(ActionEvent event) {

        clearFindData();

        String sql = "SELECT * FROM book WHERE book_id = '" + borrowing_bk_id_textfield.getText() + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            boolean check = false;

            Alert alert;

            if (borrowing_bk_id_textfield.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book.");
                alert.showAndWait();

            } else {

                while (result.next()) {

                    borrowing_title_textfield.setText(result.getString("title"));
                    borrowing_title_label.setText(result.getString("title"));
                    borrowing_au_label.setText(result.getString("author"));
                    borrowing_nxb_label.setText(result.getString("nxb"));
                    borrowing_cd_label.setText(result.getString("chu_de"));

                    getData.path = result.getString("image");

                    String uri = "file:" + getData.path;

                    image = new Image(uri, 127, 162, false, true);
                    borrowing_imgView.setImage(image);

                    check = true;
                }

                if (!check) {
                    borrowing_title_label.setText("Book is not available!");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void findBookByTitle(ActionEvent event) {

        clearFindData();

        String sql = "SELECT * FROM book WHERE title = '" + borrowing_title_textfield.getText() + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            boolean check = false;

            Alert alert;

            if (borrowing_title_textfield.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book.");
                alert.showAndWait();

            } else {

                while (result.next()) {

                    borrowing_bk_id_textfield.setText(result.getString("title"));
                    borrowing_title_label.setText(result.getString("title"));
                    borrowing_au_label.setText(result.getString("author"));
                    borrowing_nxb_label.setText(result.getString("nxb"));
                    borrowing_cd_label.setText(result.getString("chu_de"));

                    getData.path = result.getString("image");

                    String uri = "file:" + getData.path;

                    image = new Image(uri, 127, 162, false, true);
                    borrowing_imgView.setImage(image);

                    check = true;
                }

                if (!check) {
                    borrowing_title_label.setText("Book is not available!");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // mac dinh hien msv
    public void hien_thi_mac_dinh_msv() {
        borrowing_user_id_textfield.setText(getData.msv);
        msv_textfield.setText(getData.msv);
        profile_msv_label.setText(getData.msv);
    }

    // tra ve string la ngay hien tai
    public String ngay_hien_tai() {
        // Lấy ngày tháng năm hiện tại
        LocalDate currentDate = LocalDate.now();
        // Định dạng ngày tháng năm thành chuỗi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = currentDate.format(formatter);
        return dateString;
    }
    // hien mac dinh ngay muon la ngay hom nay
    public void hien_thi_mac_dinh_date() {


        String dateString = ngay_hien_tai();

        date_muon.setText(dateString);
    }


    public void press_muon_kook() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO book_loan(book_id, borrower_id, ngay_muon, ngay_dinh_tra, ngay_tra,status) VALUES (?,?,?,?,?,?)";

        connect = Database.connectDB();
        String string_date_muon = date_muon.getText();
        String string_date_dinh_tra = date_dinh_tra.getText();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date_ngay_muon = format.parse(string_date_muon);
            Date date_ngay_dinh_tra = format.parse(string_date_dinh_tra);

            java.sql.Date sql_date_muon = new java.sql.Date(date_ngay_muon.getTime());
            java.sql.Date sql_date_dinh_tra = new java.sql.Date(date_ngay_dinh_tra.getTime());

            Alert alert;

            if (borrowing_bk_id_textfield.getText().isEmpty()
                    || borrowing_title_textfield.getText().isEmpty()
                    || date_dinh_tra.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank!");
                alert.showAndWait();
            } else {

                prepare = connect.prepareStatement(sql);


                prepare.setString(1, borrowing_bk_id_textfield.getText());
                prepare.setString(2, borrowing_user_id_textfield.getText());
                prepare.setDate(3, sql_date_muon);
                prepare.setDate(4, sql_date_dinh_tra);
                prepare.setDate(5, null);
                prepare.setString(6, "borrowing");

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Bạn đã mượn sách thành công!");
                alert.showAndWait();

                showTableSaveBook();


            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //------------profile-----------------------------------

    public void hien_thi_mac_dinh_thong_tin_profile() {

        String sql = "SELECT * FROM borrower WHERE login_id = '" + profile_msv_label.getText() + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();




            while (result.next()) {

                String ho = result.getString("b_ho");
                String mid = result.getString("b_mid");
                String ten = result.getString("b_ten");

                String pr_mssv = result.getString("login_id");
                String pr_addr = result.getString("address");
                String pr_phone = result.getString("phone_number");
                String hoTen = ho + " " + mid + " " + ten;

                profile_hoten_label.setText(hoTen);
                profile_msv_label.setText(pr_mssv);
                profile_address_label.setText(pr_addr);
                profile_phone_label.setText(pr_phone);

                msv_textfield.setText(pr_mssv);
                ho_textfield.setText(ho);
                mid_textfield.setText(mid);
                ten_textfield.setText(ten);

                addr_textfield.setText(pr_addr);
                sdt_textfield.setText(pr_phone);
                showSoSachDaMuon();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // bam nut save
    public void press_save_profile() {



        String sql = "UPDATE borrower SET b_ho = ?, b_mid = ?, b_ten = ?, address = ?, phone_number = ? WHERE login_id = " + profile_msv_label.getText();

        connect = Database.connectDB();

        try {

                prepare = connect.prepareStatement(sql);


                prepare.setString(1, ho_textfield.getText());
                prepare.setString(2, mid_textfield.getText());
                prepare.setString(3, ten_textfield.getText());
            prepare.setString(4, addr_textfield.getText());

            prepare.setString(5, sdt_textfield.getText());

                prepare.executeUpdate();






                mes_save_profile.setText("Bạn đã thay đổi profile thành công!");
            hien_thi_mac_dinh_thong_tin_profile();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }






    public ObservableList<Book> data_book_savelist() {

        ObservableList<Book> listBooks = FXCollections.observableArrayList();

        String sql = "SELECT * FROM book_loan INNER JOIN book ON book_loan.book_id =book.book_id WHERE book_loan.status = 'borrowing' AND book_loan.borrower_id ='" + getData.msv +"'";

        connect = Database.connectDB();

        try {

            Book aBooks;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                aBooks = new Book(result.getString("book_id"),
                        result.getString("title"), result.getString("author"),
                        result.getString("nxb"), result.getString("chu_de"), result.getString("image"));

                listBooks.add(aBooks);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBooks;
    }

    private ObservableList<Book> listSaveBook;

    public void showTableSaveBook() {

        listSaveBook = data_book_savelist();
        s_cot_book_id.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        s_cot_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        s_cot_auth.setCellValueFactory(new PropertyValueFactory<>("author"));
        s_cot_nxb.setCellValueFactory(new PropertyValueFactory<>("nxb"));
        s_cot_cd.setCellValueFactory(new PropertyValueFactory<>("chuDe"));


        save_tableView.setItems(listSaveBook);

    }



    public void selectBookInSaveTable() {

        Book bookData = save_tableView.getSelectionModel().getSelectedItem();

        int n = save_tableView.getSelectionModel().getFocusedIndex();

        if ((n - 1) < -1) {
            return;
        }
        s_bk_id_label.setText(bookData.getBookId());

        s_title_label.setText(bookData.getTitle());
        s_auth_label.setText(bookData.getAuthor());
        s_nxb_label.setText(bookData.getNxb());
        s_cd_label.setText(bookData.getChuDe());

        String url = "file:" + bookData.getImage();

        image = new Image(url);

        save_Img.setImage(image);


    }


    public void press_btn_return() {


        String sql = "UPDATE book_loan SET  ngay_tra = ?,status =? WHERE book_id = '" + s_bk_id_label.getText() +"'";

        connect = Database.connectDB();

        String str_ngay_tra = ngay_hien_tai();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {

            Date date_ngay_tra = format.parse(str_ngay_tra);
            java.sql.Date sql_date_tra = new java.sql.Date(date_ngay_tra.getTime());
            prepare = connect.prepareStatement(sql);

            prepare.setDate(1, sql_date_tra);
            prepare.setString(2, "return");



            prepare.executeUpdate();





            showTableSaveBook();


            //mes_return.setText("Bạn đã trả sách thành công!");
            Alert alert;
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Bạn đã trả sách thành công!");
            alert.showAndWait();

            hien_thi_mac_dinh_thong_tin_profile();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void showSoSachDaMuon() {
        String sql = "SELECT COUNT(*) FROM book_loan WHERE status = 'borrowing';";

        connect = Database.connectDB();

        try {


            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                profile_num_book_saved_label.setText(result.getString("count(*)"));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit(){
        System.exit(0);
    }
}
