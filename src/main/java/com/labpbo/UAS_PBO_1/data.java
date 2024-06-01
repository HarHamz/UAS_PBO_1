package com.labpbo.UAS_PBO_1;

import javafx.beans.property.*;

public class data {
    private final IntegerProperty no = new SimpleIntegerProperty();
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty nim = new SimpleStringProperty();
    private final StringProperty tel = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    private final BooleanProperty selected;

    public data(int no, String nama, String nim, String tel, String email) {
        this.nama.set(nama);
        this.nim.set(nim);
        this.tel.set(tel);
        this.email.set(email);
        this.selected = new SimpleBooleanProperty(false);

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

    // Property getter methods
    public IntegerProperty noProperty() {
        return no;
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