package com.example.alejandrogs.hurs01z;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hurs.alejandrogs.hurs01z.BDHelper;

public class NoteActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private EditText note,titulo;
    BDHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        note = (EditText)findViewById(R.id.edit_nota);
        titulo = (EditText)findViewById(R.id.edit_titulo);
        db= new BDHelper(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!String.valueOf(titulo.getText()).equals("")){
                    if (!String.valueOf(note.getText()).equals("")){
                            db.addNota(String.valueOf(titulo.getText()),String.valueOf(note.getText()),v);
                    }else {
                        snackbarMessage(v,"No a escrito nada");
                    }
                }else{
                    snackbarMessage(v,"No tiene titulo");
                }
            }
        });
    }

    public void snackbarMessage(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent,0);
        finish();
    }
}
