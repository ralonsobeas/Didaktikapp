package com.app.didaktikapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.Service.ZumeltzegiService;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.MainActivity;
import com.app.didaktikapp.R;

import com.app.didaktikapp.wordsearch.features.gameover.GameOverActivity;
import com.app.didaktikapp.wordsearch.features.gameplay.GamePlayActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.muddzdev.styleabletoast.StyleableToast;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static android.app.PendingIntent.FLAG_NO_CREATE;
import static android.app.PendingIntent.getActivities;

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
    private Long idActividad;
    private String mParam2;

    private View view;

    private OnFragmentInteractionListener mListener;

    private TextInputEditText respuesta1;
    private TextInputEditText respuesta2;


    public FragmentZumeltzegi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parámetro con el id de la actividad.
     * @return A new instance of fragment FragmentZumeltzegi.
     * @author gennakk
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentZumeltzegi newInstance(Long param1) {
        FragmentZumeltzegi fragment = new FragmentZumeltzegi();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    /**
     *  Método para crear el fragment.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ((MapActivity)getActivity()).onPause();
            idActividad = getArguments().getLong(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Método para crear la vista del fragment.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return La vista del fragment Zumeltzegi.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_zumeltzegi, container, false);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            //Verifica permisos para Android 6.0+
//            int permissionCheck = ContextCompat.checkSelfPermission(
//                    getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
//            } else {
//
//            }
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            //Verifica permisos para Android 6.0+
//            int permissionCheck = ContextCompat.checkSelfPermission(
//                    getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
//            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 225);
//            } else {
//
//            }
//        }
//
//        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA},
//                    REQUEST_IMAGE_CAPTURE1);
//        }

        TextView tv= view.findViewById(R.id.tvTitulo);
        Typeface type =  ResourcesCompat.getFont(getActivity(), R.font.youthtouch);
        tv.setTypeface(type);
        tv.setText(Html.fromHtml(getString(R.string.ZumeltzegiTitulo)));

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
        respuesta1 = view.findViewById(R.id.ivPregunta1);
        respuesta2 = view.findViewById(R.id.ivPregunta2);

//        Button btnPregunta1 = view.findViewById(R.id.btnCameraPregunta1);
//        btnPregunta1.setOnClickListener(new ListenerBoton());
//
//        Button btnPregunta2 = view.findViewById(R.id.btnCameraPregunta2);
//        btnPregunta2.setOnClickListener(new ListenerBoton());

        Button btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                guardarImagen(ivPregunta1);
//                guardarImagen(ivPregunta2);

//                StyleableToast.makeText(getContext(), getResources().getString(R.string.ToastImagenes), Toast.LENGTH_LONG, R.style.mytoast).show();

                Intent intent = new Intent(getActivity(), GamePlayActivity.class);
                intent.putExtra(GamePlayActivity.EXTRA_ROW_COUNT, 10);
                intent.putExtra(GamePlayActivity.EXTRA_COL_COUNT, 10);
                intent.putExtra(GamePlayActivity.NOMBRE_FRAGMENT, GamePlayActivity.FRAGMENT_ZUMELTZEGI);
                startActivity(intent);


                //Añadir imagenes a base de datos
                ActividadZumeltzegi actividadZumeltzegi = DatabaseRepository.getAppDatabase().getZumeltzegiDao().getZumeltzegi(idActividad);
                actividadZumeltzegi.setEstado(2);
                actividadZumeltzegi.setFragment(1);
                actividadZumeltzegi.setFoto1(respuesta1.getText().toString());
                actividadZumeltzegi.setFoto2(respuesta2.getText().toString());

                DatabaseRepository.getAppDatabase().getZumeltzegiDao().updateZumeltzegi(actividadZumeltzegi);

                ClassToFtp.send(getActivity(),ClassToFtp.TIPO_ZUMELTZEGI);

                getFragmentManager().beginTransaction().remove(FragmentZumeltzegi.this).commit();


            }
        });

        /*
        Botón flotante de ayuda
         */
        FloatingActionButton floatingActionButton = view.findViewById(R.id.helpButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewBoton) {

                SpotlightConfig config = new SpotlightConfig();
                config.setMaskColor( Color.parseColor("#E63A3A3A"));
                config.setIntroAnimationDuration(400);
                config.setFadingTextDuration(400);
                config.setPadding(20);
                config.setDismissOnTouch(true);
                config.setDismissOnBackpress(true);
                config.setPerformClick(false);
                config.setHeadingTvSize(24);
                config.setHeadingTvColor(Color.parseColor("#2B82C5"));
                config.setSubHeadingTvSize(24);
                config.setSubHeadingTvColor(Color.parseColor("#FAFAFA"));
                config.setLineAnimationDuration(300);
                config.setLineStroke(Utils.dpToPx(4));
                config.setLineAndArcColor( Color.parseColor("#2B82C5"));
                config.setShowTargetArc(true);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        java.util.Random rndGenerator = new java.util.Random();

                        SpotlightSequence.getInstance(getActivity(),config)
                                .addSpotlight(view.findViewById(R.id.helpButton), getString(R.string.AyudaZumTituloPregunta), getString(R.string.AyudaZumDetallePregunta), "preguntaZ" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.helpButton),  getString(R.string.AyudaZumTituloRespuesta),  getString(R.string.AyudaZumDetalleRespuesta), "respuestaZ" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuarZ" + rndGenerator.nextInt(999999999))
                                .startSequence();
                    }
                },0);
            }
        });


        return view;
    }

    /**
     * Método para convertir imagen a Base64.
     * @param iv ImageView
     * @return String Base64 con la imagen.
     * @author gennakk
     */
    private String imageToBase64(ImageView iv){
        iv.buildDrawingCache();
        Bitmap bitmap = iv.getDrawingCache();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        return  Base64.encodeToString(image, 0);
    }

    /**
     * Clase para imlpementar listener a un bóton de cámara.
     * @author gennakk
     */
    private class ListenerBoton implements View.OnClickListener {
        @Override
        public void onClick(View v) {




            dispatchTakePictureIntent(v);

        }


        /**
         * Método para ejecutar la cámara.
         * @param v Vista del fragment.
         * @author gennakk
         */
        private void dispatchTakePictureIntent(View v){
//            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//            int request;
//            if(v == view.findViewById(R.id.btnCameraPregunta1)){
//                request = REQUEST_IMAGE_CAPTURE1;
//            }else{
//                request = REQUEST_IMAGE_CAPTURE2;
//            }
//
//            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                startActivityForResult(takePictureIntent, request);
//            }
        }


    }

    /**
     * Fragment interaction.
     * @param uri
     * @author gennakk
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * Método para implementar fragment.
     * @param context Contexto de la app.
     * @author gennakk
     */
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

    /**
     * Método para quitar el fragment.
     * @author gennakk
     */
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

    /**
     * Recibe el result del intent de la cámara con su imagen.
     * @param requestCode
     * @param resultCode
     * @param data
     * @author gennakk
     */
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

//        if(ivPregunta1.getDrawable() != null && ivPregunta2.getDrawable() != null){
//            view.findViewById(R.id.btnContinuar).setEnabled(true);
//        }
    }

    /**
     * Resultado de pedir los permisos.
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @author gennakk
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Guarda imagen en una galería.
     * @param iv ImageView
     * @author gennakk
     */
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

    /**
     * Método onPause del fragment Zumeltzegi.
     * @author gennakk
     */
    @Override
    public void onPause() {
        super.onPause();

    }

    /**
     * Método onDestroy del fragment Zumeltegi
     * @author gennakk
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }





}
