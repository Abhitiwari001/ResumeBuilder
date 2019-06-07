package com.abhishek.resumebuilder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int STORAGE_CODE=1000;
    EditText et;
Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    et=findViewById(R.id.et);
    bt=findViewById(R.id.bt);
    bt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){

                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                    String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
requestPermissions(permissions,STORAGE_CODE);
                }
else {
save();
                }
            }
else {
            }
            }
    });


    }

    private void save() {
        Document d= new Document();
String s= new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis());
String filepath= Environment.getExternalStorageDirectory()+"/"+s+".pdf";
try {
    PdfWriter.getInstance(d, new FileOutputStream(filepath));
    d.open();
    String t=et.getText().toString();
    d.addAuthor("Abhishek");
    d.add(new Paragraph(t));
    d.close();
    Toast.makeText(this,s+".pdf\n is saved to \n"+filepath,Toast.LENGTH_LONG).show();
}
catch (Exception e){
    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1000:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
save();
                }
                else {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
        }
    }
}



