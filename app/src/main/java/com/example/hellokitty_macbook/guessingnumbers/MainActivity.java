package com.example.hellokitty_macbook.guessingnumbers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    private Random rand;
    private Integer target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rand = new Random();
        this.target = this.rand.nextInt(1000);
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

        if (currentNumberStr.matches("\\d+")) {
            Integer currentNumber = Integer.parseInt(currentNumberStr);
            String msg;
            if (currentNumber > this.target) {
                msg = String.format("Smaller than %s", currentNumber);
            } else if (currentNumber < this.target) {
                msg = String.format("Larger than %s", currentNumber);
            } else {
                msg = "BOOM!";
                this.target = this.rand.nextInt(1000);
            }
            tv.setText(msg);
        } else {
            tv.setText("Not a number!");
        }
    }
}
