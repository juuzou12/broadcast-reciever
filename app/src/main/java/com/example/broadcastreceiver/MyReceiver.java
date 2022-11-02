package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyReceiver extends BroadcastReceiver {
    final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
    @Override
    public void onReceive(Context context, Intent intent) {
        final PendingResult pendingResult = goAsync();

        backgroundExecutor.execute(() -> {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("com.example.broadcastsender");
                sb.append(intent.getExtras().get("test"));
                String log = sb.toString();
//                                Toast.makeText(context, log, Toast.LENGTH_LONG).show();
                System.out.println(log);
            } finally {
                // Must call finish() so the BroadcastReceiver can be recycled
                pendingResult.finish();
            }
        });

    }
}
