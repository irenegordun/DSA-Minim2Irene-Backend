package edu.upc.eetac.dsa.models;

//CLASSE CREADA PEL MINIM 2 IRENE GORDUN
public class Denuncia {
    //atributs enunciat
    private String date;
    private String informer;
    private String message;

    //Getters i Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformer() {
        return informer;
    }

    public void setInformer(String informer) {
        this.informer = informer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //constructor

    public Denuncia(String date, String informer, String message) {
        this.date = date;
        this.informer = informer;
        this.message = message;
    }
    public Denuncia () {}
}
