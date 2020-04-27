package com.app.didaktikapp.FTP;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.content.ContextCompat;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.app.didaktikapp.R;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Clase asíncrona encargada de el upload de datos al FTP
 *
 */
public class Ftp extends Worker {

    public static final String JSON = "json";

    public Ftp(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    /**
     * Método para llamar cuando se cumpla una condicion
     * @return
     */
    @NonNull
    @Override
    public Result doWork() {



        Context context = getApplicationContext();

        String jsonString = getInputData().getString(JSON);

        FTPClient con = null;
        try
        {
            con = new FTPClient();
            con.connect(context.getString(R.string.hostNameFTP));
            Log.v("upload result", "loging");
            if (con.login(context.getString(R.string.hostUserFTP), context.getString(R.string.hostpassFTP)))
            {
                Log.v("upload result", "logged");
                con.enterLocalPassiveMode(); // important!
                con.setFileType(FTP.BINARY_FILE_TYPE);
                File file;
                file = File.createTempFile("json", null, context.getCacheDir());
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(jsonString);

                writer.close();

                FileInputStream in = new FileInputStream(file);


                boolean result = con.storeFile("/json.json", in);
                in.close();
                if (result) Log.v("upload result", "succeeded");
                con.logout();
                con.disconnect();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * Método encargado de la llamada
//     * @param jsonString string con el json a enviar
//     * @param context contexto de la aplicación
//     */
//    public static void sendData(String jsonString, Context context){
//
//        Constraints constraints = new Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build();
//
//        OneTimeWorkRequest onetimeJob = new OneTimeWorkRequest.Builder(YourJob.class)
//                .setConstraints(constraints).build(); // or PeriodicWorkRequest
//        WorkManager.getInstance().enqueue(onetimeJob);
//
//        FTPClient con = null;
//
//        try
//        {
//            con = new FTPClient();
//            con.connect(context.getString(R.string.hostNameFTP));
//            Log.v("upload result", "loging");
//            if (con.login(context.getString(R.string.hostUserFTP), context.getString(R.string.hostpassFTP)))
//            {
//                Log.v("upload result", "logged");
//                con.enterLocalPassiveMode(); // important!
//                con.setFileType(FTP.BINARY_FILE_TYPE);
//                File file;
//                file = File.createTempFile("json", null, context.getCacheDir());
//                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//                writer.write(jsonString);
//
//                writer.close();
//
//                FileInputStream in = new FileInputStream(file);
//
//
//                boolean result = con.storeFile("/json.json", in);
//                in.close();
//                if (result) Log.v("upload result", "succeeded");
//                con.logout();
//                con.disconnect();
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//
//
//
//
//
//    }
//
//
//    @Override
//    protected Object doInBackground(Object[] objects) {
//
//        sendData((String)objects[0],(Context) objects[1]);
//        return null;
//    }


}
