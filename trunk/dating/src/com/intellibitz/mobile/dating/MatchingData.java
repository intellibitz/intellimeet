package com.intellibitz.mobile.dating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 *
 * @author jyothsna
 */
public class MatchingData extends Activity {

    private ViewFlipper mFlipper;

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        setContentView(R.layout.matching_profile);
        mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
        mFlipper.startFlipping();
        Button button = (Button) findViewById(R.id.chating);
        button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent intobject = new Intent(MatchingData.this, Matching_Profile.class);
                startActivity(intobject);
            }
        });
    }
}
