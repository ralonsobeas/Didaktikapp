package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.R;

import com.zomato.photofilters.geometry.Point;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ToneCurveSubfilter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSanMiguelImagenes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSanMiguelImagenes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSanMiguelImagenes extends Fragment {

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view = null;

    private OnFragmentInteractionListener mListener;

    private int contadorCorrectas = 0;

    private Button btnContinuar;

    public void setmListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public FragmentSanMiguelImagenes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSanMiguelImagenes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSanMiguelImagenes newInstance(String param1, String param2) {
        FragmentSanMiguelImagenes fragment = new FragmentSanMiguelImagenes();
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
        view = inflater.inflate(R.layout.fragment_san_miguel_imagenes, container, false);


        int[] arrayRecurso  = {R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8};
        HashMap<String,Integer> mapaDrawable = new HashMap<>();

        mapaDrawable.put("sanmiguelcorrecta1",R.drawable.sanmiguelcorrecta1);
        mapaDrawable.put("sanmiguelcorrecta2",R.drawable.sanmiguelcorrecta2);
        mapaDrawable.put("sanmiguelcorrecta3",R.drawable.sanmiguelcorrecta3);
        mapaDrawable.put("sanmiguelincorrecta1",R.drawable.sanmiguelincorrecta1);
        mapaDrawable.put("sanmiguelincorrecta2",R.drawable.sanmiguelincorrecta2);
        mapaDrawable.put("sanmiguelincorrecta3",R.drawable.sanmiguelincorrecta3);

        List<String> keys = new ArrayList<String>(mapaDrawable.keySet());
        Collections.shuffle(keys);

        int i=0;
        for(String imagen : keys) {

            ImageView imageView = view.findViewById(arrayRecurso[i]);
            imageView.setImageResource(mapaDrawable.get(imagen));
            imageView.setTag(imagen);
            imageView.setOnClickListener(new ListenerImagen());
            i++;

        }


        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(FragmentSanMiguelImagenes.this).commit();
                mListener.onFragmentInteraction(FragmentSanMiguelImagenes.this,true);

            }
        });



        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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
        void onFragmentInteraction(Fragment fragment,boolean terminado);
    }

    private class ListenerImagen implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            view.setEnabled(false);

            ImageView imageView = view.findViewById(view.getId());

            String tag = imageView.getTag().toString();

            if(tag.equals("sanmiguelcorrecta1") || tag.equals("sanmiguelcorrecta2") || tag.equals("sanmiguelcorrecta3") ){
                filtroVerde(imageView,true);
                contadorCorrectas++;
                if(contadorCorrectas == 3){
                    btnContinuar.setEnabled(true);
                }
            }else{
                filtroVerde(imageView,false);
                int[] arrayRecurso  = {R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8};
                HashMap<String,Integer> mapaDrawable = new HashMap<>();

                mapaDrawable.put("sanmiguelcorrecta1",R.drawable.sanmiguelcorrecta1);
                mapaDrawable.put("sanmiguelcorrecta2",R.drawable.sanmiguelcorrecta2);
                mapaDrawable.put("sanmiguelcorrecta3",R.drawable.sanmiguelcorrecta3);
                mapaDrawable.put("sanmiguelincorrecta1",R.drawable.sanmiguelincorrecta1);
                mapaDrawable.put("sanmiguelincorrecta2",R.drawable.sanmiguelincorrecta2);
                mapaDrawable.put("sanmiguelincorrecta3",R.drawable.sanmiguelincorrecta3);

                List<String> keys = new ArrayList<String>(mapaDrawable.keySet());
                Collections.shuffle(keys);

                int i=0;
                for(String imagen : keys) {

                    ImageView imageView1 = view.getRootView().findViewById(arrayRecurso[i]);
                    imageView1.setImageResource(mapaDrawable.get(imagen));
                    imageView1.setTag(imagen);
                    imageView1.setOnClickListener(new ListenerImagen());
                    i++;

                }

                view.getRootView().findViewById(R.id.btnContinuar).setEnabled(false);
                contadorCorrectas=0;
            }

        }



        private void filtroVerde(ImageView imageView, boolean verde){
            Filter imageFilter = new Filter();
            Point[] rgbKnots;
            rgbKnots = new Point[3];
            rgbKnots[0] = new Point(0, 0);
            rgbKnots[1] = new Point(175, 139);
            rgbKnots[2] = new Point(255, 255);
            if(verde)
                imageFilter.addSubFilter(new ColorOverlaySubfilter(100, .2f, .5f, .0f));
            else
                imageFilter.addSubFilter(new ColorOverlaySubfilter(100, .5f, .0f, .0f));

            imageFilter.addSubFilter(new ToneCurveSubfilter(rgbKnots, null, null, null));
            imageFilter.addSubFilter(new BrightnessSubfilter(30));
            imageFilter.addSubFilter(new ContrastSubfilter(1.1f));

            Log.i("TAG",imageView.getTag().toString());
            Log.i("TAG",getActivity().getPackageName());
            int id = getResources().getIdentifier(imageView.getTag().toString(),
                    "drawable", getActivity().getPackageName());



            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inMutable = true;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
               id, opts);

            Bitmap outputImage = imageFilter.processFilter(bitmap);

            imageView.setImageBitmap(outputImage);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }


}


