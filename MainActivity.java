package com.example.admin.sqldbwithautotext;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private SQLiteItemSearch sqllitebb;
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AutoCompleteTextView actv=(AutoCompleteTextView)findViewById(R.id.autotext);
        sqllitebb=new SQLiteItemSearch(MainActivity.this);
        sqllitebb.openDB();
        // Insert a few item list statically//
        sqllitebb.insertitmSearch("Color Monitor");
        sqllitebb.insertitmSearch("Compact Disk");
        sqllitebb.insertitmSearch("Computer");
        sqllitebb.insertitmSearch("Copy Righter");
        sqllitebb.insertitmSearch("Hard Disk");
        sqllitebb.insertitmSearch("HP Printer");
        sqllitebb.insertitmSearch("HP Laser Printer");
        sqllitebb.insertitmSearch("HP Injet Printer");
        //  sqllitebb.removeitmsearch("Computer");
        // sqllitebb.updateitmSearch("Computer","DELL");
        final  String[] deal = sqllitebb.getAllItemFilter();
        // Print out the values to the log
        for(int i = 0; i < deal.length; i++){
            Log.i(this.toString(), deal[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,deal);
        actv.setAdapter(adapter);
        actv.setThreshold(1);
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                tv=(TextView)findViewById(R.id.selecteditem_tv);
                tv.setText(deal[arg2]);
                arg0.getItemAtPosition(arg2);
                Log.i("SELECTED TEXT WAS-->",  deal[arg2]);
            }
        });
    }
    public void onDestroy(){
        super.onDestroy();
        sqllitebb.close();
    }
}
