package com.example.hellokitty_macbook.guessingnumbers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hellokitty_macbook.guessingnumbers.model.NumberGuesser;
import com.plattysoft.leonids.ParticleSystem;

public class MainActivity extends Activity {

    private NumberGuesser ng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ng = new NumberGuesser(1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onGoButtonClicked(View view) {
        EditText numberField = (EditText) findViewById(R.id.editText);
        TextView tv = (TextView) findViewById(R.id.message);

        String currentNumberStr = numberField.getText().toString();
        numberField.setText("");

        String message;

        if(!currentNumberStr.matches("\\d+")) {
            message = "The input isn't an unsigned integer.";
        } else {
            Integer number = Integer.parseInt(currentNumberStr);
            message = ng.guess(number);
            if (message.equals("BOOM!")){
                ng.reset();
                new ParticleSystem(this, 100, R.drawable.pusheen, 800)
                        .setSpeedRange(0.5f, 0.75f)
                        .oneShot(view, 100);
            }
        }
        tv.setText(message);
    }
}

