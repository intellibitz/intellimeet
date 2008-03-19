package com.intellibitz.mobile.dating;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import android.os.Bundle;
import android.view.Menu;
import android.view.Menu.Item;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayController;
import com.google.android.maps.Point;

/**
 *
 * @author jyothsna
 */
public class MatchingData extends MapActivity {

    private MapView mMapView;
    private ViewFlipper mFlipper;
    private Point p1,  p2,  p3,  p4;

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        setContentView(R.layout.matching_profile);
        mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
        mFlipper.startFlipping();
        Button button = (Button) findViewById(R.id.chating);
        mMapView = (MapView) findViewById(R.id.map_whereami);
        p1 = new Point((int) (13098200), (int) (80225937));
        p2 = new Point((int) (17630996), (int) (79303084));
        p3 = new Point((int) (13312070), (int) (77633171));
        p4 = new Point((int) (19069509), (int) (72931043));
        MapController mc = mMapView.getController();
        MyLocationOverlay mylocation = new MyLocationOverlay();
        OverlayController oc = mMapView.createOverlayController();
        oc.add(mylocation, true);
        mc.animateTo(p1);
        mc.zoomTo(6);
        //Enable Sattelite-Mode, so we will se the
        // Statue of liberty instantly on the screen
        mMapView.toggleSatellite();
        button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent intobject = new Intent(MatchingData.this, Matching_Profile.class);
                startActivity(intobject);
            }
            });
    }

    class MyLocationOverlay extends Overlay {

        @Override
        public void draw(Canvas c, PixelCalculator calc, boolean shadow) {
            super.draw(c, calc, shadow);
            int coords[] = new int[2];
            int coords1[] = new int[2];
            int coords2[] = new int[2];
            int coords3[] = new int[2];
            calc.getPointXY(p1, coords);
            RectF oval = new RectF(coords[0] - 7, coords[1] + 7, coords[0] + 7, coords[1] - 7);
            Paint paint = new Paint();
            paint.setStyle(Style.FILL);
            paint.setARGB(255, 85, 117, 30);
            c.drawText("you", coords[0] + 9, coords[1], paint);
            paint.setARGB(200, 34, 234, 34);
            //paint.setStrokeWidth(1);
            c.drawOval(oval, paint);

            calc.getPointXY(p2, coords1);
            RectF oval1 = new RectF(coords1[0] - 7, coords1[1] + 7, coords1[0] + 7, coords1[1] - 7);
            Paint paint1 = new Paint();
            paint1.setStyle(Style.FILL);
            paint1.setARGB(255, 0, 0, 0);
            calc.getPointXY(p2, coords1);
            c.drawText("Dreammate1", coords1[0] + 9, coords1[1], paint1);
            paint1.setARGB(80, 255, 0, 0);
            paint1.setStrokeWidth(2);
            c.drawLine(coords[0], coords[1], coords1[0], coords1[1], paint1);
            paint1.setStrokeWidth(1);
            paint1.setARGB(80, 255, 0, 0);
            c.drawOval(oval1, paint1);

            calc.getPointXY(p3, coords2);
            RectF oval2 = new RectF(coords2[0] - 7, coords2[1] + 7, coords2[0] + 7, coords2[1] - 7);
            Paint paint2 = new Paint();
            paint2.setStyle(Style.FILL);
            paint2.setARGB(255, 0, 0, 0);
            // calc.getPointXY(p2, coords1);
            c.drawText("Dreammate2", coords2[0] + 9, coords2[1], paint2);
            paint2.setARGB(255, 255, 0, 0);
            paint2.setStrokeWidth(2);
            c.drawLine(coords[0], coords[1], coords2[0], coords2[1], paint2);
            paint2.setStrokeWidth(1);
            paint2.setARGB(80, 255, 0, 0);
            c.drawOval(oval2, paint2);

            calc.getPointXY(p4, coords3);
            RectF oval3 = new RectF(coords3[0] - 7, coords3[1] + 7, coords3[0] + 7, coords3[1] - 7);
            Paint paint3 = new Paint();
            paint3.setStyle(Style.FILL);
            paint3.setARGB(255, 0, 0, 0);
            //calc.getPointXY(p4, coords1);
            c.drawText("Dreammate3", coords3[0] + 9, coords3[1], paint3);
            paint3.setARGB(255, 255, 0, 0);
            paint3.setStrokeWidth(2);
            c.drawLine(coords[0], coords[1], coords3[0], coords3[1], paint3);
            paint3.setStrokeWidth(1);
            paint3.setARGB(80, 255, 0, 0);
            c.drawOval(oval3, paint3);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_I) {
            // Zoom In
            int level = mMapView.getZoomLevel();
            mMapView.getController().zoomTo(level + 1);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_O) {
            // Zoom Out
            int level = mMapView.getZoomLevel();
            mMapView.getController().zoomTo(level - 1);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_S) {
            // Switch on the satellite images
            mMapView.toggleSatellite();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_T) {
            // Switch on traffic overlays
            mMapView.toggleTraffic();
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean ret = super.onCreateOptionsMenu(menu);
        menu.add(0, 1, "Back to WelcomePage");
        return ret;
    }

    @Override
    public boolean onOptionsItemSelected(Item item) {
        switch (item.getId()) {
            case 1:
                Intent intent = new Intent(MatchingData.this, Dating.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
