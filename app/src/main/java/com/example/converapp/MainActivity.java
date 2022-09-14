package com.example.converapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner monedaActualSP;
    private Spinner monedaCambioSP;
    private EditText valorCambioET;
    private TextView resultadoTV;

    final private double factorDolarEuro= 1.003;
    final private double factorPesoDolar= 0.0011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickConvertir(View v){

        monedaActualSP =(Spinner) findViewById(R.id.monedaActualSP);
        monedaCambioSP =(Spinner) findViewById(R.id.monedaCambioSP);
        valorCambioET =(EditText) findViewById(R.id.valorCambioD);
        resultadoTV =(TextView) findViewById(R.id.resultadoTV);

        String monedaActual = monedaActualSP.getSelectedItem().toString();
        String monedaCambio = monedaCambioSP.getSelectedItem().toString();

        double valorCambio = Double.parseDouble(valorCambioET.getText().toString());
        double resultado = procesarConversion(monedaActual,monedaCambio,valorCambio);

        if(resultado > 0){
            resultadoTV.setText(String.format("Por %5.2f %s, usted recibirá %5.2f %s",valorCambio,monedaActual,resultado,monedaCambio));
            valorCambioET.setText("");

        }else{
            resultadoTV.setText(String.format("Usted recibirá"));
            Toast.makeText(MainActivity.this, "Las opciones elegidas no tienen un factor de conversion", Toast.LENGTH_SHORT).show();

        }

    }

    private double procesarConversion(String monedaActual, String monedaCambio, double valorCambio){
        double resultadoConversion = 0;

        switch (monedaActual){
            case "Dólar":
                if(monedaCambio.equals("Euro"))
                    resultadoConversion = valorCambio * factorDolarEuro;

                if(monedaCambio.equals("Peso Chileno"))
                    resultadoConversion = valorCambio / factorPesoDolar;

                break;

            case "Euro":
                if(monedaCambio.equals("Dólar"))
                    resultadoConversion = valorCambio / factorDolarEuro;

                if (monedaCambio.equals("Peso Chileno"))
                    resultadoConversion = valorCambio / factorPesoDolar;



                break;

            case "Peso Chileno":
                if(monedaCambio.equals("Dólar"))
                    resultadoConversion = valorCambio * factorPesoDolar;

                if (monedaCambio.equals("Euro"))
                    resultadoConversion = valorCambio * factorPesoDolar;


                break;

        }

        return resultadoConversion;
    }

}