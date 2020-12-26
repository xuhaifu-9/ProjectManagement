package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"s",Toast.LENGTH_LONG).show();
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) !=  PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,  new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
                else{
                    SeMsg();
                }
            }
        });

    }
    public void SeMsg()
    {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    SeMsg();
                }
                else{
                    Toast.makeText(MainActivity.this, "You denied the permission", Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

}