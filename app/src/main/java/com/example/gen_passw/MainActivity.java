package com.example.gen_passw;
import android.content.ClipData;
import android.content.ClipboardManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.security.SecureRandom;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String Symbols = "0123456789!@#%&_";
    public String generate_password(int le){
        if(check_lang.isChecked()){
            Letters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        }else{
            Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if(check_size.isChecked()){
            Letters = Letters.toLowerCase();
        }else{
            Letters = Letters.toUpperCase();
        }
        if(check_add_symbols.isChecked()){
            Letters = Letters+Symbols;
        }
        String password = "";
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < le;i++){
            int index = random.nextInt(Letters.length());
            password = password.concat(String.valueOf(Letters.charAt(index)));
        }
        return password;
    }
    private SeekBar seekBar1;
    private Switch check_lang,check_size,check_add_symbols;
    private TextView text;
    private Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        seekBar1 = findViewById(R.id.seekBar1);
        text = findViewById(R.id.textView2);
        butt = findViewById(R.id.button);
        check_lang = findViewById(R.id.switch1);
        check_size = findViewById(R.id.switch2);
        check_add_symbols = findViewById(R.id.switch3);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text.setText(generate_password(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) MainActivity.this.getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", text.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });

    }
}