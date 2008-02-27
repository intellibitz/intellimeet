package com.intellibitz.mobile.dating;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

public class DatingService extends Service {

    private static int MOOD_NOTIFICATIONS = R.layout.status_bar_notifications;
    private NotificationManager mNM;
    Thread thr;

    @Override
    protected void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        thr = new Thread(null, mTask, "NotifyingService");
        thr.start();
    }
    private Runnable mTask = new Runnable() {

        public void run() {
            try {
                for (;;) {
                    showNotification(R.drawable.stat_happy, R.string.status_bar_notifications_happy_message);
                    Thread.sleep(2000);
                    showNotification(R.drawable.stat_neutral, R.string.status_bar_notifications_happy);
                    Thread.sleep(2000);
                    showNotification(R.drawable.stat_sad, R.string.status_bar_notifications_sad_message);
                    Thread.sleep(2000);
                }
            } catch (Exception ex) {
            }

            DatingService.this.stopSelf();
            DatingService.this.stopSelf();
            DatingService.this.stopSelf();
        }
    };

    @SuppressWarnings("deprecation")
    @Override
    protected void onDestroy() {
        mNM.cancel(MOOD_NOTIFICATIONS);
        thr.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @SuppressWarnings("deprecation")
    private void showNotification(int moodId, int textId) {

        Intent contentIntent = new Intent(this, DatingServiceController.class);
        Intent appIntent = new Intent(this, DatingServiceController.class);
        CharSequence text = getText(textId);
        mNM.notify(MOOD_NOTIFICATIONS, new Notification(this, moodId, null, System.currentTimeMillis(),
                getText(R.string.status_bar_notifications_mood_title), text, contentIntent,
                R.drawable.app_sample_code, getText(R.string.activity_sample_code), appIntent));
        thr.stop();
    }
    private final IBinder mBinder = new Binder() {

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) {
            return super.onTransact(code, data, reply, flags);
        }
    };
}
