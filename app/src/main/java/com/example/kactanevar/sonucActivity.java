package com.example.kactanevar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class sonucActivity extends AppCompatActivity {
    TextView txtSonuc;
    Button btnOncekiSayfa;
    ImageButton imgbtn;
    Context context=this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        txtSonuc=(TextView)findViewById(R.id.txtSonuc);
        btnOncekiSayfa=(Button)findViewById(R.id.btnOncekiSayfa);
        imgbtn=(ImageButton)findViewById(R.id.imgbtn);
        Bundle sonuclar=getIntent().getExtras();
        txtSonuc.setText(sonuclar.getString("key"));
        btnOncekiSayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TalipSalihoglu"));
                startActivity(intent2);
            }
        });


    }
}
