package com.app.didaktikapp.FTP;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.jibble.simpleftp.*;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

/**
 * Clase asíncrona encargada de el upload de datos al FTP
 * @author gennakk
 */
public class Ftp extends Worker {

    public static final String JSON = "json";
    public  static String IPDEFAULT = "88.27.169.221";
    public  static String USER = "didaktikapp";
    public  static String PASS = "Dw2*";

    public static final String IPASSETS = "ipservidor.txt";

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

        String ip = loadData(getApplicationContext());
        Log.v("upload result", ip);

        Context context = getApplicationContext();

        String jsonString = getInputData().getString(JSON);

        FTPClient con = null;
        try
        {
            con = new FTPClient();
            con.connect(ip);
            Log.v("upload result", "loging");
            if (con.login(USER, PASS))
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

    public static String loadData(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getResources().getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        return sharedPref.getString(context.getResources().getString(R.string.servidor), IPDEFAULT);

    }

    public static void sendImage(Context context, Bitmap bitmap, String mail, String idGrupo, String act){


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    try
                    {
                        SimpleFTP ftp = new SimpleFTP();
                        String ip = loadData(context);
                        // Connect to an FTP server on port 21.
                        ftp.connect(ip, 21, USER, PASS);

                        // Set binary mode.
                        ftp.bin();

                        // Change to a new working directory on the FTP server.
                        //ftp.cwd("web");
                        //create a file to write bitmap data
                        // path to /data/data/yourapp/app_data/imageDir
                        ContextWrapper cw = new ContextWrapper(context);
                        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                        File f = new File(directory, mail+"_"+idGrupo+"_"+act+".jpg");


                        //Convert bitmap to byte array
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();
                        FileOutputStream fos = new FileOutputStream(f);
                        /*
                        InputStream in = context.getContentResolver().openInputStream(bitmap);
                        //write the bytes in file
                        FileOutputStream fos = new FileOutputStream(f);
                        OutputStream out = new FileOutputStream(new File(mail+"_"+idGrupo+"_"+act+".jpg"));
                        byte[] buf = new byte[1024];
                        int len;
                        while((len=in.read(buf))>0){
                            out.write(buf,0,len);
                        }
                        out.close();
                        in.close();

                         */
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        // Upload some files.

                        ftp.stor(f);


                        // Quit from the FTP server.
                        ftp.disconnect();
                        Log.i("NOERROR","NOERROR");

                    }
                    catch (IOException e)
                    {
                        Log.i("ERROR","ERROR");
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("ERROR1","ERROR1");

                }
            }
        });

        thread.start();

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
