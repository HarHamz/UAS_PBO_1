package com.labpbo.UAS_PBO_1;


import javafx.beans.property.*;

public class Data {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty nim = new SimpleStringProperty();
    private final StringProperty tel = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty img_str = new SimpleStringProperty();

    private final BooleanProperty selected;

    public Data(int id, String nama, String nim, String tel, String email, String img_str) {
        this.id.set(id);
        this.nama.set(nama);
        this.nim.set(nim);
        this.tel.set(tel);
        this.email.set(email);
        this.img_str.set(img_str);
        this.selected = new SimpleBooleanProperty(false);
    }

    public Integer getId() {
        return id.get();
    }
    public String getNama() {
        return nama.get();
    }

    public String getNim() {
        return nim.get();
    }

    public String getTel() {
        return tel.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getImgStr() { return img_str.get();};

    // Property getter methods
    public IntegerProperty noProperty() {
        return id;
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public StringProperty nimProperty() {
        return nim;
    }

    public StringProperty telProperty() {
        return tel;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}