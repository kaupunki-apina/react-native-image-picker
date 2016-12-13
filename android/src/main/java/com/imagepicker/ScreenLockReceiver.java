package com.imagepicker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


public class ScreenLockReceiver extends BroadcastReceiver {
    private Listener listener;

    public ScreenLockReceiver(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null) listener.onScreenLocked();
        context.unregisterReceiver(this);
    }

    public interface Listener {
        void onScreenLocked();
    }

    public IntentFilter intentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        return filter;
    }
}
