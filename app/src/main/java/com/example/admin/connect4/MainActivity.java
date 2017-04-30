package com.example.admin.connect4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    static final int numFilas = 6;
    static final int numColumnas = 7;
    static final int VACIO=0;
    static final int ROJO=1;
    static final int AMARILLO = 2;

    private Casilla[][] celdas;
    private boolean activo=true;
    private int jugador[]= {ROJO,AMARILLO};
    private int jug = 0;
    private Tablero fondo;
    private TextView tv1,mC,mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Quitar titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Pantalla completa
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView)findViewById(R.id.tv1);
        mC = (TextView)findViewById(R.id.marcadorCris);
        mA = (TextView)findViewById(R.id.marcadorAlber);
        LinearLayout l = (LinearLayout)findViewById(R.id.layout);
        fondo = new Tablero(this);
        l.addView(fondo);
        fondo.setOnTouchListener(this);
        celdas = new Casilla[numFilas][numColumnas];
        for (int f = 0; f < numFilas;f++){
            for (int c =0; c<numColumnas;c++){
                celdas[f][c] = new Casilla();
                celdas[f][c].setContenido(VACIO);
            }
        }

        getSupportActionBar().hide();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (activo){
            for (int f = 0; f< numFilas; f++){
               for (int c = 0; c< numColumnas; c++){
                   if (celdas[f][c].dentro(event.getX(),event.getY())){
                       for (int f2=numFilas-1; f2>=0; f2-- ){
                           if (celdas[f2][c].getContenido()==VACIO){
                               celdas[f2][c].setContenido(jugador[jug]);
                               jug=(jug+1)%2;
                               if (jug==ROJO){
                                   //Cambia texto tv1 a: Turno de Alber
                                   tv1.setText("Player 2");
                               }
                               else {
                                   //Cambia texto tv1 a: Turno de Cris
                                   tv1.setText("Player 1");
                               }
                               fondo.invalidate();
                               if (gano(f2,c)){
                                   if (jugador[jug]==ROJO){
                                       tv1.setText("Player 2 WINS!!!");
                                       int p = Integer.parseInt(mA.getText().toString());
                                       System.out.println(p);
                                       p+= 1;
                                       mA.setText(String.valueOf(p));
                                   }
                                   else{
                                       tv1.setText("Player 1 WINS!!!");
                                       int p = Integer.parseInt(mC.getText().toString());
                                       System.out.println(p);
                                       p+=1;
                                       mC.setText(String.valueOf(p));
                                   }

                                   activo = false;
                               }
                               break;

                           }
                       }
                   }
               }
           }
        }
        return false;
    }

    public boolean gano(int x, int y){
        int color = celdas[x][y].getContenido();
        int contador=1;
        if (y-1 >=0 && celdas[x][y-1].getContenido()==color){
            contador++;
            if (y-2 >=0 && celdas[x][y-2].getContenido()==color){
                contador++;
                if (y-3 >=0 && celdas[x][y-3].getContenido()==color){
                    contador++;
                }
            }
        }
        if (y+1 <numColumnas && celdas[x][y+1].getContenido()==color && contador != 4){
            contador++;
            if (y+2 <numColumnas && celdas[x][y+2].getContenido()==color && contador != 4){
                contador++;
                if (y+3 <numColumnas && celdas[x][y+3].getContenido()==color && contador != 4){
                    contador++;
                }
            }
        }
        if (contador == 4) return true;
        contador = 1;
        if (x-1 >=0 && celdas[x-1][y].getContenido()==color){
            contador++;
            if (x-2 >=0 && celdas[x-2][y].getContenido()==color){
                contador++;
                if (x-3 >=0 && celdas[x-3][y].getContenido()==color){
                    contador++;
                }
            }
        }
        if (x+1 <numFilas && celdas[x+1][y].getContenido()==color && contador != 4){
            contador++;
            if (x+2 <numFilas && celdas[x+2][y].getContenido()==color && contador != 4){
                contador++;
                if (x+3 <numFilas && celdas[x+3][y].getContenido()==color && contador != 4){
                    contador++;
                }
            }
        }
        if (contador == 4) return true;
        contador = 1;
        if (x-1 >=0 && y-1 >=0 && celdas[x-1][y-1].getContenido()==color){
            contador++;
            if (x-2 >=0 && y-2 >=0 && celdas[x-2][y-2].getContenido()==color){
                contador++;
                if (x-3 >=0 && y-3 >=0 && celdas[x-3][y-3].getContenido()==color){
                    contador++;
                }
            }
        }
        if (x+1 <numFilas && y+1< numColumnas && celdas[x+1][y+1].getContenido()==color && contador != 4){
            contador++;
            if (x+2 <numFilas && y+2< numColumnas && celdas[x+2][y+2].getContenido()==color && contador != 4){
                contador++;
                if (x+3 <numFilas && y+3< numColumnas && celdas[x+3][y+3].getContenido()==color && contador != 4){
                    contador++;
                }
            }
        }
        if (contador == 4) return true;
        contador = 1;
        if (x+1 <numFilas && y-1 >=0 && celdas[x+1][y-1].getContenido()==color){
            contador++;
            if (x+2 <numFilas && y-2 >=0 && celdas[x+2][y-2].getContenido()==color){
                contador++;
                if (x+3 <numFilas && y-3 >=0 && celdas[x+3][y-3].getContenido()==color){
                    contador++;
                }
            }
        }
        if (x-1 >=0 && y+1< numColumnas && celdas[x-1][y+1].getContenido()==color && contador != 4){
            contador++;
            if (x-2 >=0 && y+2< numColumnas && celdas[x-2][y+2].getContenido()==color && contador != 4){
                contador++;
                if (x-3 >=0 && y+3< numColumnas && celdas[x-3][y+3].getContenido()==color && contador != 4){
                    contador++;
                }
            }
        }
        if (contador == 4) return true;
        return false;
    }

    public void reiniciar(View v){
        for (int f = 0; f < numFilas;f++){
            for (int c =0; c<numColumnas;c++){
                celdas[f][c] = new Casilla();
                celdas[f][c].setContenido(VACIO);
            }
        }
        tv1.setText("Player 1");
        activo = true;
        jug = 0;
        fondo.invalidate();
    }

    public class Tablero extends View {
        public Tablero(Context context) {
            super(context);
        }
        public void onDraw(Canvas canvas){
            int ancho = canvas.getWidth();
            int anchoCasilla = ancho/numColumnas;
            int radio = (anchoCasilla/2)-10;
            Paint circulo = new Paint();
            for (int f = 0; f<numColumnas;f++){
                for (int c = 0; c<numFilas;c++){
                    celdas[c][f].fijarXY(f*anchoCasilla,c*anchoCasilla,anchoCasilla);
                    switch(celdas[c][f].getContenido()){
                        case VACIO:
                            circulo.setARGB(255,0,0,0);
                            break;
                        case ROJO:
                            circulo.setARGB(255,255,0,0);
                            break;
                        case AMARILLO:
                            circulo.setARGB(255,255,255,0);
                            break;
                    }
                    canvas.drawCircle(f*anchoCasilla+(anchoCasilla/2),c*anchoCasilla+(anchoCasilla/2),radio,circulo);
                }
            }
        }
    }
}
