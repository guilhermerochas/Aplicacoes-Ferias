package com.example.aplicacaoferias.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplicacaoferias.R;
import com.google.android.material.textfield.TextInputEditText;

import java.net.URI;


public class ContatosFragment extends Fragment {

    private Cursor cursor;
    private ContentResolver contentResolver;
    private Button pesquisaContato;
    private TextInputEditText buscaNomeContato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contatos, container, false);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1);
        pesquisaContato = v.findViewById(R.id.pesquisaContatos);

        pesquisaContato.setOnClickListener(view -> {

            buscaNomeContato = v.findViewById(R.id.buscar_contato);

            String nomeContato = buscaNomeContato.getText().toString();

            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle(nomeContato);
            dialog.setMessage(PesquisaPorContato(getActivity(), nomeContato));
            dialog.show();
        });

        return v;
    }

    private String PesquisaPorContato(Context context, String contact) {
        contentResolver = context.getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection = ContactsContract.Contacts.DISPLAY_NAME + " = ?";
        String[] args = new String[]{ contact };
        cursor = contentResolver.query(uri,null, selection, args, null);

        assert cursor != null;
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            final String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
            return number;
        }

        return contact + " not found";
    }

}