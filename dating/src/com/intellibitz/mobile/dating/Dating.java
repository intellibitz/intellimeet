package com.intellibitz.mobile.dating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Menu.Item;
import android.widget.ViewFlipper;

public class Dating extends Activity {

    private ViewFlipper mFlipper;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.welcome);
        mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
        mFlipper.startFlipping();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean ret = super.onCreateOptionsMenu(menu);
        menu.add(0, 1, "Get into");
        return ret;
    }

    @Override
    public boolean onOptionsItemSelected(Item item) {
        switch (item.getId()) {
            case 1:
                Intent intent = new Intent(Dating.this, Seeker.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
