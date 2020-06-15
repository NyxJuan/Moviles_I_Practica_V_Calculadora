package com.example.practica_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.MultiTapKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private boolean Suma = false;
    private boolean Resta = false;
    private boolean Multiplicacion = false;
    private boolean Division = false;
    private boolean Calcular = false;
    private String Indicador = "";
    private int Valor1 = 0;
    private int Valor2 = 0;
    private double Resultado = 0;

    Button btn_Calcular;
    Button btn_Suma;
    Button btn_Resta;
    Button btn_Multiplicacion;
    Button btn_Division;
    EditText etValor1;
    EditText etValor2;
    TextView tvResultado;

    Button Comando;

    private boolean VerificarTextos() {
        etValor1 = findViewById(R.id.valor_1);
        etValor2 = findViewById(R.id.valor_2);

        String e1 = etValor1.getText().toString();
        String e2 = etValor2.getText().toString();

        if (e1.equals("") && e2.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void HabilitarControles() {
        Suma = true; Resta = true; Multiplicacion = true; Division = true;
    }

    private void InhabilitarBotones() {
        btn_Suma.setEnabled(Suma);
        btn_Resta.setEnabled(Resta);
        btn_Multiplicacion.setEnabled(Multiplicacion);
        btn_Division.setEnabled(Division);
        btn_Calcular.setEnabled(Calcular);
    }

    private void VerificarEstado() {
        if (Suma == true && Resta == true && Multiplicacion == true && Division == true) {
            Calcular = true;
        } else {
            Calcular = false;
            Suma = true;
            Resta = true;
            Multiplicacion = true;
            Division = true;
        }
        InhabilitarBotones();
    }

    private void Resolucion(int opc) {
        VerificarEstado();
        etValor1 = (EditText) findViewById(R.id.valor_1);
        etValor2 = (EditText) findViewById(R.id.valor_2);
        Valor1 = Integer.parseInt(etValor1.getText().toString());
        Valor2 = Integer.parseInt(etValor2.getText().toString());

        switch (opc) {
            case 1: //Primer Caso Suma
                Comando = (Button) findViewById(R.id.btn_Suma);
                Indicador = Comando.getText().toString();
                Resultado = Valor1 + Valor2;
                Toast.makeText(getApplicationContext(), "SUMA", Toast.LENGTH_SHORT).show();
                break;

            case 2: //Segundo Caso Resta
                Comando = (Button) findViewById(R.id.btn_Resta);
                Indicador = Comando.getText().toString();
                Resultado = Valor1 - Valor2;
                Toast.makeText(getApplicationContext(), "RESTA", Toast.LENGTH_SHORT).show();
                break;

            case 3: //Tercer Caso Division
                Comando = (Button) findViewById(R.id.btn_Division);
                Indicador = Comando.getText().toString();
                double dnum1 = Double.parseDouble(etValor1.getText().toString());
                double dnum2 = Double.parseDouble(etValor2.getText().toString());
                Resultado = dnum1 / dnum2;
                Toast.makeText(getApplicationContext(), "DIVISION", Toast.LENGTH_SHORT).show();
                break;

            case 4: //Cuarto Caso Multiplicacion
                Comando = (Button) findViewById(R.id.btn_Multiplicacion);
                Indicador = Comando.getText().toString();
                Resultado = Valor1 * Valor2;
                Toast.makeText(getApplicationContext(), "MULTIPLICACION", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getApplicationContext(), "Debe seleccionar una operacion", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getApplicationContext(),ServicioMusica.class));


        //Boton Calcular
        btn_Calcular = (Button)findViewById(R.id.btn_Calcular);
        btn_Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResultado = (TextView) findViewById(R.id.ver_Resultado);

                if (VerificarTextos()) {
                    tvResultado.setText(String.valueOf(Resultado));
                    HabilitarControles();
                } else {
                    Toast.makeText(getApplicationContext(), "No ha ingresado numeros", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Boton Suma
        btn_Suma = (Button)findViewById(R.id.btn_Suma);
        btn_Suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VerificarTextos() == true) {
                    Resolucion(1);
                } else {
                    Toast.makeText(getApplicationContext(), "No hay numeros", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Boton Resta
        btn_Resta = (Button)findViewById(R.id.btn_Resta);
        btn_Resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VerificarTextos() == true) {
                    Resolucion(2);
                } else {
                    Toast.makeText(getApplicationContext(), "No hay numeros", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Boton Division
        btn_Division = (Button)findViewById(R.id.btn_Division);
        btn_Division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VerificarTextos() == true) {
                    Resolucion(3);
                } else {
                    Toast.makeText(getApplicationContext(), "No hay numeros", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Boton Multiplicacion
        btn_Multiplicacion = (Button)findViewById(R.id.btn_Multiplicacion);
        btn_Multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VerificarTextos() == true) {
                    Resolucion(4);
                } else {
                    Toast.makeText(getApplicationContext(), "No hay numeros", Toast.LENGTH_SHORT).show();
                }
            }
        });

        VerificarEstado();
    }


    @Override
    protected void onPause() {
        super.onPause();
        startService(new Intent(getApplicationContext(),ServicioMusica.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(getApplicationContext(),ServicioMusica.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getApplicationContext(),ServicioMusica.class));
    }

}
