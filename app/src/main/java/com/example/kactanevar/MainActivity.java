package com.example.kactanevar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TableLayout tbSecenek;
    CheckBox chKelime,chNoktalama,chSesliharf,chSessizharf;
    Button btnHesapla;
    Intent intent;
    Bundle bundle;
    Context context=this;
    String sonuc=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.etMetin);
        tbSecenek = (TableLayout) findViewById(R.id.tbSecenek);
        chKelime = (CheckBox) findViewById(R.id.chKelime);
        chNoktalama = (CheckBox) findViewById(R.id.chNoktalama);
        chSesliharf = (CheckBox) findViewById(R.id.chSesliharf);
        chSessizharf = (CheckBox) findViewById(R.id.chSessizharf);
        btnHesapla=(Button)findViewById(R.id.btnHesapla);


        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonuc=" ";
                String metin = editText.getText().toString().toLowerCase();

                int kelimesayisi = 0;
                int noktalamaisareti = 0;
                int sesliharf = 0;
                int sessizharf = 0;

                String noktalama = ".,;:!?";
                String sesliler = "aeıioöuü";
                String sesiz = "qwrtypğsdfghjklşzxcvbnmç";

                if (chKelime.isChecked()) {
                    for (int i = 0; i < metin.length(); i++) {
                        if (metin.charAt(i) == ' ' || metin.charAt(i) == '\n' ) {
                            if (i!=metin.length()-1){
                                for (int a=0;a<noktalama.length();a++) {
                                    if (metin.charAt(i+1)==noktalama.charAt(a)){
                                        kelimesayisi--;
                                    }
                                }
                            }
                            kelimesayisi++;
                        }
                    }
                    kelimesayisi++;  //metnin sonuna gelindiginde ' ' olmadıgı için
                    sonuc=sonuc+"\n Kelime Sayısı :"+(String.valueOf(kelimesayisi));
                }
                if (chNoktalama.isChecked()) {
                    for (int i = 0; i < metin.length(); i++) {
                        for (int j = 0; j < noktalama.length(); j++) {
                            if (metin.charAt(i) == noktalama.charAt(j)) {
                                noktalamaisareti++;
                            }
                        }
                    }
                    sonuc=sonuc+"\n Noktalama İşareti Sayisi :"+(String.valueOf(noktalamaisareti));
                }
                if (chSesliharf.isChecked()) {
                    for (int i = 0; i < metin.length(); i++) {
                        for (int j = 0; j < sesliler.length(); j++) {
                            if (metin.charAt(i) == sesliler.charAt(j)) {
                                sesliharf++;
                            }
                        }
                    }
                    sonuc=sonuc+"\n Sesli Harf :"+(String.valueOf(sesliharf));
                }
                if (chSessizharf.isChecked()) {
                    for (int i = 0; i < metin.length(); i++) {
                        for (int j = 0; j < sesiz.length(); j++) {
                            if (metin.charAt(i) == sesiz.charAt(j)) {
                                sessizharf++;
                            }
                        }
                    }
                    sonuc=sonuc+"\n Sesiz Harf Sayısı :"+(String.valueOf(sessizharf));
                }

                intent=new Intent(context,sonucActivity.class);
                bundle=new Bundle();
                bundle.putString("key",sonuc.toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}