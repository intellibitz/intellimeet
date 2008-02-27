/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intellibitz.mobile.dating;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

/**
 *
 * @author jailani
 */
public class Dating extends Activity {

	Button butSend;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here     0
        setContentView(R.layout.main);
        Button partnerbutton = (Button) findViewById(R.id.Partner);
        //Button cancelbutton = (Button) findViewById(R.id.cancel);
        Button clearbutton = (Button) findViewById(R.id.clear);
        if (partnerbutton != null) {
            partnerbutton.setOnClickListener(new Button.OnClickListener() {

                public void onClick(View v) {
                    EditText ageEditText = (EditText) findViewById(R.id.Age);
                    EditText heightEditText = (EditText) findViewById(R.id.Height);
                    EditText weightEditText = (EditText) findViewById(R.id.Weight);
                    EditText locationEditText = (EditText) findViewById(R.id.Location);
                    if ((ageEditText.getText().toString().equals("") || (heightEditText.getText().toString().equals("")) || (weightEditText.getText().toString().equals("")) ||
                            (locationEditText.getText().toString().equals("")))) {
                        showAlert("Error", 0, "Information Required!", "OK", false);
                    } else {
                        setContentView(R.layout.partner);
                        butSend = (Button)findViewById(R.id.send);
                        if(butSend != null) {
                            butSend.setOnClickListener(new OnClickListener() {
                        	public void onClick(View v)
                        	{
                        		 Intent myIntent1 = new Intent(Dating.this, DatingServiceController.class); 
                                 startActivity(myIntent1);     
                        	}
                        });
                      }
                        Button backbutton = (Button) findViewById(R.id.back);
                        backbutton.setOnClickListener(new Button.OnClickListener() {
                            public void onClick(View v) {
                                setContentView(R.layout.main);
                            }
                        });
                    }
                }
            });
        }
        if (clearbutton != null) {
            clearbutton.setOnClickListener(new Button.OnClickListener() {

                public void onClick(View v1) {
                    EditText ageEditText = (EditText) findViewById(R.id.Age);
                    EditText heightEditText = (EditText) findViewById(R.id.Height);
                    EditText weightEditText = (EditText) findViewById(R.id.Weight);
                    EditText locationEditText = (EditText) findViewById(R.id.Location);
                    ageEditText.setText("");
                    heightEditText.setText("");
                    weightEditText.setText("");
                    locationEditText.setText("");
                }
            });
        }
        
      
    }
}
