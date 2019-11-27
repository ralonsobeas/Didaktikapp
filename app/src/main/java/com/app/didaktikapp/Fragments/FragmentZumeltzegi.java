package com.app.didaktikapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentZumeltzegi.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentZumeltzegi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentZumeltzegi extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    final int REQUEST_IMAGE_CAPTURE1 = 1888;
    final int REQUEST_IMAGE_CAPTURE2 = 1889;

    private ImageView ivPregunta1, ivPregunta2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    private OnFragmentInteractionListener mListener;


    public FragmentZumeltzegi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentZumeltzegi.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentZumeltzegi newInstance(String param1, String param2) {
        FragmentZumeltzegi fragment = new FragmentZumeltzegi();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_zumeltzegi, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
            int permissionCheck = ContextCompat.checkSelfPermission(
                    getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
            } else {

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
            int permissionCheck = ContextCompat.checkSelfPermission(
                    getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 225);
            } else {

            }
        }

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_IMAGE_CAPTURE1);
        }

//        final VideoView videoView = view.findViewById(R.id.videoView);
//        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.sanmiguele;
//        videoView.setVideoURI(Uri.parse(path));
//
//        MediaController mediaController = new MediaController(getActivity());
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);
//        videoView.setZOrderOnTop(true);
//
//
//        videoView.requestFocus();
//
//        videoView.start();
//
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                videoView.setVisibility(View.INVISIBLE);
//            }
//        });


        ScrollView scroll = view.findViewById(R.id.scroll);
        ivPregunta1 = view.findViewById(R.id.ivPregunta1);
        ivPregunta2 = view.findViewById(R.id.ivPregunta2);

        ImageButton btnPregunta1 = view.findViewById(R.id.btnCameraPregunta1);
        btnPregunta1.setOnClickListener(new ListenerBoton());

        ImageButton btnPregunta2 = view.findViewById(R.id.btnCameraPregunta2);
        btnPregunta2.setOnClickListener(new ListenerBoton());

        Button btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                guardarImagen(ivPregunta1);
                guardarImagen(ivPregunta2);

            }
        });

        return view;
    }

    private class ListenerBoton implements View.OnClickListener {
        @Override
        public void onClick(View v) {




            dispatchTakePictureIntent(v);

        }


        private void dispatchTakePictureIntent(View v){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            int request;
            if(v == view.findViewById(R.id.btnCameraPregunta1)){
                request = REQUEST_IMAGE_CAPTURE1;
            }else{
                request = REQUEST_IMAGE_CAPTURE2;
            }

            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, request);
            }
        }


    }

        // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if ((requestCode == REQUEST_IMAGE_CAPTURE1 || requestCode == REQUEST_IMAGE_CAPTURE2) && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if (requestCode == REQUEST_IMAGE_CAPTURE1)
                ivPregunta1.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
            else
                ivPregunta2.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));

        }

        if(ivPregunta1.getDrawable() != null && ivPregunta2.getDrawable() != null){
            view.findViewById(R.id.btnContinuar).setEnabled(true);
        }
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void guardarImagen(ImageView iv){
        //to get the image from the ImageView (say iv)
        BitmapDrawable draw = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = draw.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File dir = new File(sdCard.getAbsolutePath() + "/Didaktikapp");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        Log.i("FILE",fileName);
        Log.i("DIR",dir.getPath());
        File outFile = new File(dir, fileName);
        try {
            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Titulo" , "descripcion");
    }
}
