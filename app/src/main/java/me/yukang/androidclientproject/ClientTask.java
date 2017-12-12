package me.yukang.androidclientproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by yukang on 2017/12/12.
 */

public class ClientTask extends AsyncTask<String, Void, Void> {

    Socket s;
    PrintWriter pw;
    String message;
    Context c;
    Handler h = new Handler();

    ClientTask(Context c) {
        this.c = c;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            message = params[0];
            s = new Socket("192.168.1.101", 3000);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            h.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(c, "Message sent", Toast.LENGTH_LONG).show();
                }
            });
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
