package com.example.eva3_8_asynk_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.txtText);

        MyAsynClass myAsynClass = new MyAsynClass();
        myAsynClass.execute(10, 1000);//Manda el doInBackground a ejecuccion con sus respectivos parametros
    }
    //                  DATO QUE RECIBE EL BACKGROUND  DATO QUE RECIBE EL PROGRES   DATO QUE RECIBE EL POSTEXECUTE
    public class MyAsynClass extends AsyncTask<Integer,String,String>{
        @Override
        protected void onPreExecute() { //INICIO
            super.onPreExecute();
            text.setText("AsyncTask Begin");
        }
        //TODOS LOS METODOS INTERACTUAN CON LA INTERFAZ MENOS DOINBACKGROUND / ES COMO EL THREAD
        @Override
        protected String doInBackground(Integer... integers) { //DESARROLLO, EJECUCION
            int veces = integers[0];
            int demora = integers[1];
            for (int i=0;i<veces;i++){
                try {
                    Thread.sleep(demora);
                    publishProgress(""+i);//Llama al progres update y manda un dato para saber el progreso actual
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "AsyncTask Finished"; //Al terminar el doinBackground manda o retorna el resultado al onPostExecute
        }

        @Override
        protected void onProgressUpdate(String... values) { //MANTENER PROGRESO DE TAREA
            super.onProgressUpdate(values);
            text.setText(values[0]);
        }
        @Override
        protected void onPostExecute(String s) {//NOTIFICACION CUANDO ACABA
            super.onPostExecute(s);
            text.setText(s);
        }
    }
}
