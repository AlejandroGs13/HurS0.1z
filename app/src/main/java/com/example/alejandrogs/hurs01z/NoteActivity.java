package com.example.alejandrogs.hurs01z;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.hurs.alejandrogs.hurs01z.BDHelper;
import com.hurs.alejandrogs.hurs01z.StringEncrypt;
import com.hurs.alejandrogs.hurs01z.keysAlgoritmos;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

public  class NoteActivity extends AppCompatActivity {
    keysAlgoritmos keys = new keysAlgoritmos();
    StringEncrypt  encrypt = new StringEncrypt();
    private FloatingActionButton fab;
    private EditText note,titulo;
    private String edt;
    BDHelper db;
    String ID;
    String oldID;
    String Nota;
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
            ID = bundle.getString("ID");
            Nota = bundle.getString("Nota");
            ID  = ID.replaceAll("´","'");
            Nota  = Nota.replaceAll("´","'");
            note.setText(Nota);
            titulo.setText(ID);
            note.setFocusableInTouchMode(false);
            oldID = String.valueOf(titulo.getText());
            if (db.getCIfrado(ID).equals("1")){
                fab.setImageResource(R.drawable.ic_vpn_key_white_24dp);
            }
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt.equals("0")) {
                    fab.setImageResource(R.drawable.ic_save_black_24dp);
                    if (!String.valueOf(titulo.getText()).equals("")) {
                        if (!String.valueOf(note.getText()).equals("")) {
                            String ti = String.valueOf(titulo.getText());
                            String no = String.valueOf(note.getText());
                            ti = ti.replaceAll("'","´");
                            no =  no.replaceAll("'","´");
                            db.addNota(ti, no, v);
                            snackbarMessage("Nota Guardada");
                            edt ="1";
                        } else {
                            snackbarMessage("No a escrito nada");
                        }
                    } else {
                        snackbarMessage("No tiene titulo");
                    }
                }else if (db.getCIfrado(ID).equals("0")){
                    fab.setImageResource(R.drawable.ic_save_black_24dp);
                    if (!String.valueOf(titulo.getText()).equals("")) {
                        if (!String.valueOf(note.getText()).equals("")) {
                            String ti = String.valueOf(titulo.getText());
                            String no = String.valueOf(note.getText());
                            ti = ti.replaceAll("'","´");
                            no =  no.replaceAll("'","´");
                            db.updateNOte(ti,no,v);
                            db.updateIDs(oldID,String.valueOf(titulo.getText()));
                            snackbarMessage("Nota actualizada");
                            oldID = String.valueOf(titulo.getText());
                        } else {
                            snackbarMessage("No a escrito nada");
                        }
                    } else {
                        snackbarMessage("No tiene titulo");
                    }
                }else{
                    fab.setImageResource(R.drawable.ic_vpn_key_white_24dp);
                    password(1,v);
                    try {

                    }catch (Exception ex){
                        snackbarMessage("Contraseña incorrecta");
                    }
                }
            }
        });
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setFocusableInTouchMode(true);
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
        if (id == R.id.action_cifrar_note) {
            if (String.valueOf(db.getCIfrado(ID)).equals("1")) {
                snackbarMessage("mensaje ya esta cifrado");
            } else {

                try {
                    password(0, null);
                } catch (Exception es) {
                    snackbarMessage("Error al cifrar");
                }
                //String textcifrado =
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void cifrar(String lavve,View v) throws Exception {
        String nota = db.getNote(ID);
        String llave =  keys.key(lavve);
        String ive = keys.key(ID);
        nota = encrypt.encrypt(llave,ive,nota);
        note.setText(nota);
        db.updateCifrar(ID,"1");
        db.updateNOte(ID,nota,v);
        fab.setImageResource(R.drawable.ic_vpn_key_white_24dp);
    }
    private void decifrar(String llave,View v) throws Exception {
        String nota = db.getNote(ID);
        String llave2 = keys.key(llave);
        String ive = keys.key(ID);
        nota = encrypt.decrypt(llave2,ive,nota);
        note.setText(nota);
        db.updateCifrar(ID,"0");
        db.updateNOte(ID,nota,v);
        fab.setImageResource(R.drawable.ic_vpn_key_white_24dp);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivityForResult(intent,0);
        finish();
    }


    public  void password(int CE,View v){
        final int ce = CE;
        AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_password,null);
        final EditText pass = (EditText)view.findViewById(R.id.edt_pass);
        Button act = (Button)view.findViewById(R.id.btn_acp);
        Button can = (Button)view.findViewById(R.id.btn_can);
        builder.setView(view);
        final AlertDialog alertDialog =builder.create();
        alertDialog.show();
        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ce==0){
                    try {
                        cifrar(String.valueOf(pass.getText()),v);
                        alertDialog.hide();
                    }catch (Exception ex){
                        snackbarMessage("Error"+ex);

                    }
                }
                if (ce==1){
                    try {
                        decifrar(String.valueOf(pass.getText()),v);
                        fab.setImageResource(R.drawable.ic_save_black_24dp);
                        alertDialog.hide();
                    }catch (Exception ex){
                        snackbarMessage("Contraseña incorrecta");

                    }
                }
            }
        });
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
            }
        });

    }

}
