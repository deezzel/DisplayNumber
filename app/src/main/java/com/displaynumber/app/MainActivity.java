package com.displaynumber.app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button btnShowPopup;
    Button btnSendNumber;
    private PopupWindow popup_window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView tw = (TextView) findViewById(R.id.number);
        tw.append("No input");
        btnShowPopup = (Button) findViewById(R.id.btn_show_popup);
        btnShowPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiatePopupWindow();
            }
        });
    }

    private void initiatePopupWindow() {
        try{
            LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,(ViewGroup)findViewById(R.id.popup_element));
            popup_window = new PopupWindow(layout,570,450,true);
            popup_window.showAtLocation(layout, Gravity.CENTER, 0, 0);
            popup_window.setOutsideTouchable(true);
            popup_window.setFocusable(true);
            popup_window.setBackgroundDrawable(new BitmapDrawable());
            popup_window.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE){
                        popup_window.dismiss();
                        return true;
                    }
                    return false;
                }
            });
            btnSendNumber = (Button)layout.findViewById(R.id.btn_send_number);
            btnSendNumber.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popup_window.dismiss();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
