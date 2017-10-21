package com.example.celprincipal.myapplication;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Main2Activity extends AppCompatActivity {
    LinearLayout l;
    Snackbar snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        l = (LinearLayout) findViewById(R.id.milayout);
        snack = Snackbar.make(l,"Olvidaste dar permisos",Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(Main2Activity.this,new String[]{
                        CAMERA,
                        ACCESS_FINE_LOCATION,
                        ACCESS_COARSE_LOCATION,
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE
                },200);
            }
        });
        if(verificarPermisos())
        {
            iniciar();
        }
        else
        {
            solicitarPermisos();
        }
    }
    private boolean verificarPermisos() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return true;
        }
        if(ContextCompat.checkSelfPermission(Main2Activity.this,CAMERA) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(Main2Activity.this,ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(Main2Activity.this,ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(Main2Activity.this,READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(Main2Activity.this,WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        return false;
    }
    private void solicitarPermisos()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this,CAMERA)||
            ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this,ACCESS_FINE_LOCATION)||
            ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this,ACCESS_COARSE_LOCATION)||
            ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this,READ_EXTERNAL_STORAGE)||
            ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this,WRITE_EXTERNAL_STORAGE))
        {
            snack.show();
        }
        else
        {
            ActivityCompat.requestPermissions(Main2Activity.this,new String[]{
                    CAMERA,
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION,
                    READ_EXTERNAL_STORAGE,
                    WRITE_EXTERNAL_STORAGE
            },200);
        }
    }
    private void iniciar()
    {
        Toast.makeText(getApplicationContext(),"La aplicacion ya puede iniciar",Toast.LENGTH_LONG).show();
    }

    public void onRequestPermissionsResult(int indice,String[] permisos,int[] respuesta)
    {
        switch (indice)
        {
            case 200:
                if(respuesta.length == 0 ||
                        respuesta[0] == PackageManager.PERMISSION_DENIED ||
                        respuesta[1] == PackageManager.PERMISSION_DENIED ||
                        respuesta[2] == PackageManager.PERMISSION_DENIED ||
                        respuesta[3] == PackageManager.PERMISSION_DENIED ||
                        respuesta[4] == PackageManager.PERMISSION_DENIED)
                {
                    snack.show();
                }
                else
                {
                    iniciar();
                }
                break;
            default:
                break;
        }
    }
}