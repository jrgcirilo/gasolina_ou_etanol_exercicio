package com.example.gasolina_ou_etanol_exercicio;

import java.text.NumberFormat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView gasTextView;
    private TextView etnTextView;
    private TextView resTextView;
    private SeekBar gasSeekBar;
    private SeekBar etnSeekBar;
    private ImageView calcImageView;

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasTextView = findViewById(R.id.gasTextView);
        etnTextView = findViewById(R.id.etnTextView);
        resTextView = findViewById(R.id.resTextView);
        gasSeekBar = findViewById(R.id.gasSeekBar);
        etnSeekBar = findViewById(R.id.etnSeekBar);
        calcImageView = findViewById(R.id.calcImageView);

        gasSeekBar.setOnSeekBarChangeListener(onGasSeekBarChangeListener);
        etnSeekBar.setOnSeekBarChangeListener(onEtnSeekBarChangeListener);

    }

    private SeekBar.OnSeekBarChangeListener onGasSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            double preco = progress / 100d;

            gasTextView.setText("Preço do litro da gasolina: "+currencyFormat.format(preco));

            double etnPreco = Double.parseDouble((String.valueOf(etnSeekBar.getProgress()))) / 100D;

            if(preco == 0 || etnPreco == 0){
                resTextView.setText("");
                calcImageView.setImageResource(R.drawable.calculadora);
            }
            else{
                double percentual = etnPreco / preco;

                if(percentual <= 0.7){
                    resTextView.setText("Abasteça com: etanol");
                    calcImageView.setImageResource(R.drawable.etanol);
                }
                else{
                    resTextView.setText("Abasteça com: gasolina");
                    calcImageView.setImageResource(R.drawable.gasolina);
                }
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener onEtnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            double preco = progress / 100d;

            etnTextView.setText("Preço do litro do etanol: "+currencyFormat.format(preco));

            double gasPreco = Double.parseDouble((String.valueOf(gasSeekBar.getProgress()))) / 100D;

            if(preco == 0 || gasPreco == 0){
                resTextView.setText("");
            }
            else{
                double percentual = preco / gasPreco;

                if(percentual <= 0.7){
                    resTextView.setText("Abasteça com: etanol");
                    calcImageView.setImageResource(R.drawable.etanol);
                }
                else{
                    resTextView.setText("Abasteça com: gasolina");
                    calcImageView.setImageResource(R.drawable.gasolina);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}

