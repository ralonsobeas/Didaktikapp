package com.app.didaktikapp.FTP;

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class Ftp {

    public static void sendData(String jsonString){

        FTPClient con = null;

        try
        {
            con = new FTPClient();
            con.connect("192.168.2.57");

            if (con.login("Administrator", "KUjWbk"))
            {
                con.enterLocalPassiveMode(); // important!
                con.setFileType(FTP.BINARY_FILE_TYPE);

                FileInputStream in = new FileInputStream(new File(jsonString));
                boolean result = con.storeFile("/json.txt", in);
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






    }
}
