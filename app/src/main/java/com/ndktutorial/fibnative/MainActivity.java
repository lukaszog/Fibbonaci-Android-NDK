package com.ndktutorial.fibnative;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    public RadioGroup type;
    private TextView output;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
        type = (RadioGroup) findViewById(R.id.type);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s = input.getText().toString();

                if(TextUtils.isEmpty(s))
                {
                    return;
                }
                final int fibType = type.getCheckedRadioButtonId();

                final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "Licze...", "licze");
                final long n = Long.parseLong(s);

                Log.d("lab", "Print: " + fibType);

                new AsyncTask<Void,Void,String>(){

                    @Override
                    protected String doInBackground(Void... params) {

                        long result=0;
                        long t = System.currentTimeMillis();
                        switch (fibType){

                            case R.id.type_fib_jr:
                                result = Fiblib.fibJR(n);
                                break;

                            case R.id.type_fib_ji:
                                result = Fiblib.fibJI(n);
                                break;

                            case R.id.type_fib_nr:
                                result = Fiblib.fibNR(n);
                                break;

                            case R.id.type_fib_ni:
                                result = Fiblib.fibNI(n);
                                break;

                        }
                        t = System.currentTimeMillis() - t;

                        return String.format("fib(%d)=%d in %d ms", n,result,t);
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        dialog.dismiss();
                        MainActivity.this.output.setText(result);
                    }

                }.execute();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
