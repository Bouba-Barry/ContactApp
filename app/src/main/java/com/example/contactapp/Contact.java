package com.example.contactapp;

import android.graphics.Bitmap;

public class Contact {
        private String nom;
        private Bitmap photo;

        private String image;

        public Contact(String nom, Bitmap photo) {
            this.nom = nom;
            this.photo = photo;
        }

        public String getNom() {
            return nom;
        }

        public Bitmap getPhoto() {
            return photo;
        }

    public Contact(String nom, String image) {
        this.nom = nom;
        this.image = image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

