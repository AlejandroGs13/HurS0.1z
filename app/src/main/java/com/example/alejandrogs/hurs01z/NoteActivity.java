package com.example.alejandrogs.hurs01z;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hurs.alejandrogs.hurs01z.BDHelper;

public class NoteActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private EditText note,titulo;
    private String edt;
    BDHelper db;
    String oldID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        note = (EditText)findViewById(R.id.edit_nota);
        titulo = (EditText)findViewById(R.id.edit_titulo);
        db= new BDHelper(this);

        Bundle bundle = getIntent().getExtras();
        edt = bundle.getString("Edit");

        if (edt.equals("1")) {
            String ID = bundle.getString("ID");
            String Nota = bundle.getString("Nota");
            note.setText(Nota);
            titulo.setText(ID);
            oldID = String.valueOf(titulo.getText());
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt.equals("0")) {
                    if (!String.valueOf(titulo.getText()).equals("")) {
                        if (!String.valueOf(note.getText()).equals("")) {
                            db.addNota(String.valueOf(titulo.getText()), String.valueOf(note.getText()), v);
                            snackbarMessage("Nota Guardada");
                            edt ="1";
                        } else {
                            snackbarMessage("No a escrito nada");
                        }
                    } else {
                        snackbarMessage("No tiene titulo");
                    }
                }else {
                    if (!String.valueOf(titulo.getText()).equals("")) {
                        if (!String.valueOf(note.getText()).equals("")) {
                            db.updateNOte(String.valueOf(titulo.getText()),String.valueOf(note.getText()),v);
                            db.updateIDs(oldID,v,String.valueOf(titulo.getText()));
                            snackbarMessage("Nota actualizada");
                            oldID = String.valueOf(titulo.getText());
                        } else {
                            snackbarMessage("No a escrito nada");
                        }
                    } else {
                        snackbarMessage("No tiene titulo");
                    }
                }
            }
        });
    }

    public void snackbarMessage(String text) {
        //Snackbar.make(view, text, Snackbar.LENGTH_LONG)
          //      .setAction("Action", null).show();
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_note) {
            db.DeleteNote(oldID);
            snackbarMessage("Elimina");
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivityForResult(intent,0);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent,0);
        finish();
    }
}
