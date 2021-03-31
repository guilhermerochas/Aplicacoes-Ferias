package com.example.aplicacaoferias.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacaoferias.R;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;


public class CalculaTrianguloFragment extends Fragment {

    TextInputEditText base, altura;
    Button calcular;
    TextView resultado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =  inflater.inflate(R.layout.fragment_calcula_triangulo, container, false);

        calcular = v.findViewById(R.id.calcular_area);
        base = v.findViewById(R.id.triangulo_Base);
        altura = v.findViewById(R.id.triangulo_Altura);
        resultado = v.findViewById(R.id.resultado_area);

        String texto = resultado.getText().toString();

        calcular.setOnClickListener(view ->{
            try {
                float b = Float.parseFloat(base.getText().toString());
                float a = Float.parseFloat(altura.getText().toString());
                float r = (a * b) / 2;
                resultado.setText(texto + " " +  r);
            }
            catch (Exception e){
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Campos Requeridos");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                dialog.show();
            }
        });

        return v;
    }
}