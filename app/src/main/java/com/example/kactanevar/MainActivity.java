package com.example.kactanevar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TableLayout;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText editText,etBul;
    TableLayout tbSecenek;
    GridLayout glBul;
    CheckBox chKelime,chNoktalama,chSesliharf,chSessizharf,chArama;
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
        etBul = (EditText) findViewById(R.id.etBul);
        tbSecenek = (TableLayout) findViewById(R.id.tbSecenek);
        glBul = (GridLayout) findViewById(R.id.glBul);
        chKelime = (CheckBox) findViewById(R.id.chKelime);
        chNoktalama = (CheckBox) findViewById(R.id.chNoktalama);
        chSesliharf = (CheckBox) findViewById(R.id.chSesliharf);
        chSessizharf = (CheckBox) findViewById(R.id.chSessizharf);
        chArama = (CheckBox) findViewById(R.id.chArama);
        btnHesapla=(Button)findViewById(R.id.btnHesapla);

        chArama.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    glBul.setVisibility(View.VISIBLE);
                }
                else{
                    glBul.setVisibility(View.GONE);
                }
            }
        });

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
                if (chArama.isChecked()){
                    String aranan = etBul.getText().toString().toLowerCase();
                    //boolean bulundu=metin.contains(aranan);
                    int count = 0;
                    Scanner scanner = new Scanner(metin);
                    while (scanner.hasNextLine())
                    {
                        String nextWord = scanner.next();
                        if (nextWord.equalsIgnoreCase(aranan))
                            count++;
                    }

                    if (count>0){
                        sonuc=sonuc+"\n"+"\""+aranan+"\" Sayısı :"+count;
                    }
                    else{
                        sonuc=sonuc+"\n"+" Metinde \""+aranan+"\" bulunamdı";
                    }
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