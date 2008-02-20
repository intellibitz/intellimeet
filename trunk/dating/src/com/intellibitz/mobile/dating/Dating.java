/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intellibitz.mobile.dating;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author jailani
 */
public class Dating extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here     
        setContentView(R.layout.main);
        Button button = (Button) findViewById(R.id.Partner);
        button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                setContentView(R.layout.partner);
                Button backbutton = (Button) findViewById(R.id.back);
                backbutton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View v) {
                        setContentView(R.layout.main);
                    }
                });
            }
        });
    }
}
