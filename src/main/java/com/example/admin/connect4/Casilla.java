package com.example.admin.connect4;

/**
 * Created by admin on 18/6/16.
 */
public class Casilla {
    private int x,y,ancho,contenido;

    public void fijarXY(int x, int y, int ancho){
        this.x=x;
        this.y=y;
        this.ancho = ancho;
    }

    public void setContenido(int cont){
        contenido = cont;
    }
    public int getContenido(){
        return contenido;
    }

    public boolean dentro(float x, float y){
        return (x>=this.x && x<=this.x+ancho && y>=this.y && y<=this.y + ancho);
    }


}
