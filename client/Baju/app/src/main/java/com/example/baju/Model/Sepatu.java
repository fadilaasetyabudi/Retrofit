package com.example.baju.Model;

import com.google.gson.annotations.SerializedName;

public class Sepatu {
    @SerializedName("id_spt")
    private String id_spt;
    @SerializedName("size")
    private String size;
    @SerializedName("color")
    private String color;
//    @SerializedName("foto")
//    private String foto;

    public Sepatu(){}

//    public Sepatu(String id, String nama, String nomor, String foto) {
//        this.id = id;
//        this.size = size;
//        this.color = color;
//        this.foto = foto;
//    }

    public Sepatu(String id_spt, String size, String color) {
        this.id_spt = id_spt;
        this.size = size;
        this.color = color;
    }

    public String getId_spt() {
        return id_spt;
    }

    public void setId(String id_spt) {
        this.id_spt = id_spt;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public void setFoto(String foto) {
//        this.foto = foto;
//    }
//
//    public String getFoto() {
//        return foto;
//    }
}
