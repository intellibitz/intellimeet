/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intellibitz.mobile.dating;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

/**
 *
 * @author jyothsna
 */
public class Seeker extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here 
        setContentView(R.layout.main);

        TabHost tabs = (TabHost) this.findViewById(R.id.tabs);
        tabs.setup();

        TabHost.TabSpec one = tabs.newTabSpec("one");
        one.setContent(R.id.yourProfile);
        one.setIndicator("Your's", this.getResources().getDrawable(R.drawable.yourprofile));
        tabs.addTab(one);

        TabHost.TabSpec two = tabs.newTabSpec("two");
        two.setContent(R.id.partner);
        two.setIndicator("Partner", this.getResources().getDrawable(R.drawable.partnerprofile));
        tabs.addTab(two);

        Spinner s1 = (Spinner) findViewById(R.id.agespinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.age, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

        Spinner s2 = (Spinner) findViewById(R.id.heightspinner2);
        adapter = ArrayAdapter.createFromResource(this, R.array.height,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter);

        Spinner s3 = (Spinner) findViewById(R.id.weightspinner3);
        adapter = ArrayAdapter.createFromResource(this, R.array.weight,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(adapter);

        Spinner s4 = (Spinner) findViewById(R.id.locnspinner4);
        adapter = ArrayAdapter.createFromResource(this, R.array.location,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4.setAdapter(adapter);

        TabHost.TabSpec three = tabs.newTabSpec("three");
        three.setContent(R.id.serviceActivate);
        three.setIndicator("Service", this.getResources().getDrawable(R.drawable.chat));
        tabs.addTab(three);

        Button button = (Button) findViewById(R.id.Activate);
        button.setOnClickListener(new Button.OnClickListener() {

            private int count;

            public void onClick(View v) {
                if (count == 0) {
                    startService(new Intent(Seeker.this, DatingService.class), null);
                    count++;
                    //butActivate.setVisibility(Button.INVISIBLE);
                    Intent intobject1 = new Intent(Seeker.this, RepeatingAlarm.class);
                    Intent intobject = new Intent(Seeker.this, MatchingData.class);
                    startActivity(intobject);

                    long firstTime = SystemClock.elapsedRealtime();
                    firstTime += 10 * 1000;

                    // Schedule the alarm!
                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            firstTime, 10 * 1000, intobject1);

                    // Tell the user about what we did.
                    if (mToast != null) {
                        mToast.cancel();
                    }
                    mToast = Toast.makeText(Seeker.this, R.string.repeating_received,
                            Toast.LENGTH_LONG);
                    mToast.show();

                } else {
                    Toast.makeText(Seeker.this, "Service already activated", Toast.LENGTH_SHORT).show();
                }
            }
            Toast mToast;
        });

        TabHost.TabSpec four = tabs.newTabSpec("four");
        four.setContent(R.id.help);
        four.setIndicator("Help", this.getResources().getDrawable(R.drawable.help));
        tabs.addTab(four);
        tabs.setCurrentTab(0);
    }
}
