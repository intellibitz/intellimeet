/**
 * 
 */
package com.intellibitz.mobile.dating;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * @author gunasekaran
 *
 */
public class Partner extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setTheme(android.R.style.Theme_Light);
        setContentView(R.layout.partner);
        Button activatebutton = (Button) findViewById(R.id.send);
        Button backbutton = (Button) findViewById(R.id.back);
        activatebutton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent1 = new Intent(Partner.this, DatingServiceController.class);
                startActivity(myIntent1);
            }
        });
        
        backbutton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Partner.this, Dating.class);
                startActivity(intent);
            }
        });
    }
}
