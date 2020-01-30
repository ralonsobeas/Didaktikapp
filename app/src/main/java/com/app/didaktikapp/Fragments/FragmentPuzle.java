package com.app.didaktikapp.Fragments;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.Puzle.adapter.PuzzleAdapter;
import com.app.didaktikapp.Puzle.adapter.StorePreference;
import com.app.didaktikapp.Puzle.models.Pieces;
import com.app.didaktikapp.Puzle.models.PuzzlePiece;
import com.app.didaktikapp.Puzle.puzzle.Puzzle;
import com.app.didaktikapp.R;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPuzle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPuzle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPuzle extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Long idActividad;
    private int imagen;

    private OnFragmentInteractionListener mListener;

    private View view;



    RelativeLayout relativeLayout;
    FrameLayout scrollView;
    ImageView imageView;
    Context context;
    List<Pieces> piecesModelListMain = new ArrayList<Pieces>();
    HashMap<String, Pieces> piecesModelHashMap = new HashMap<String, Pieces>();
    int countGrid = 0;
    ArrayList<PuzzlePiece> puzzlePiecesList = new ArrayList<PuzzlePiece>();
    private RecyclerView rvPuzzle;
    private RecyclerView.LayoutManager linearLayoutManager;
    private PuzzleAdapter puzzleListAdapter;
    StorePreference storePreference;
    Puzzle puzzle;
    MediaPlayer mediaPlayer ;

    public FragmentPuzle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentPuzle.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPuzle newInstance(Long param1, int param2) {
        FragmentPuzle fragment = new FragmentPuzle();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idActividad = getArguments().getLong(ARG_PARAM1);
            imagen = getArguments().getInt(ARG_PARAM2);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.puzzle_activity, container, false);

        context = getContext();
        imageView = view.findViewById(R.id.frameImage);
        scrollView = view.findViewById(R.id.scrollView);
        scrollView.setOnDragListener(new FragmentPuzle.MyDragListener(null));
        relativeLayout = view.findViewById(R.id.relativeLayout);
        relativeLayout.setOnDragListener(new FragmentPuzle.MyDragListener(null));
        rvPuzzle = view.findViewById(R.id.listView2);
        rvPuzzle.setOnDragListener(new FragmentPuzle.MyDragListener(null));
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvPuzzle.setLayoutManager(linearLayoutManager);
        storePreference=new StorePreference(context);
        puzzle = new Puzzle();
        puzzlePiecesList.clear();

        puzzlePiecesList = puzzle.createPuzzlePieces(getActivity(), null, imageView, "/puzzles/", 3, 3,imagen);
        RelativeLayout.LayoutParams params;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // System.out.println("image count" +puzzlePiecesList.get(countGrid).getPath());
                PuzzlePiece piece = puzzlePiecesList.get(countGrid);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                int dimX = piece.getAnchorPoint().x - piece.getCenterPoint().x;
                int dimY = piece.getAnchorPoint().y - piece.getCenterPoint().y;

                System.out.println("dimX-" + dimX + "dimY-" + dimY);
                params.setMargins(dimX, dimY, 0, 0);
                final ImageView button2 = new ImageView(context);
                button2.setId(generateViewId());
                button2.setTag(i + "," + j);

                button2.setImageResource(R.drawable.ic_1);

                button2.setOnDragListener(new FragmentPuzle.MyDragListener(button2));
                button2.setLayoutParams(params);
                relativeLayout.addView(button2);

                Pieces piecesModel = new Pieces();
                piecesModel.setpX(i);
                piecesModel.setpY(j);
                piecesModel.setPosition(countGrid);
                piecesModel.setOriginalResource(puzzlePiecesList.get(countGrid).getImage());
                piecesModelListMain.add(piecesModel);
                Collections.shuffle(piecesModelListMain);
                piecesModelHashMap.put(i + "," + j, piecesModel);
                piecesModel = null;

                countGrid++;

            }
        }

        Log.e("size", "" + piecesModelListMain.size());
        setPuzzleListAdapter();
        return view;
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

    public int generateViewId() {
        final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public void setPuzzleListAdapter() {
        if (puzzleListAdapter != null)
            puzzleListAdapter = null;

        puzzleListAdapter = new PuzzleAdapter(context, piecesModelListMain);
        rvPuzzle.setHasFixedSize(true);
        rvPuzzle.setAdapter(puzzleListAdapter);

        puzzleListAdapter.notifyDataSetChanged();
    }


    static public class MyClickListener implements View.OnLongClickListener {

        // called when the item is long-clicked
        @Override
        public boolean onLongClick(View view) {
            // TODO Auto-generated method stub

            // create it from the object's tag
            ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            view.startDrag(data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );

            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    public class MyDragListener implements View.OnDragListener {

        final ImageView imageView;

        public MyDragListener(final ImageView imageView) {
            this.imageView = imageView;
        }


        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundResource(R.drawable.target_shape);    //change the shape of the view
                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundResource(R.drawable.normal_shape);    //change the shape of the view back to normal
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    //v is the dynamic grid imageView, we accept the drag item
                    //view is listView imageView the dragged item
                    if (v == imageView) {
                        View view = (View) event.getLocalState();

                        ViewGroup owner = (ViewGroup) v.getParent();
                        if (owner == relativeLayout) {
                            String selectedViewTag = view.getTag().toString();

                            Pieces piecesModel = piecesModelHashMap.get(v.getTag().toString());
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();

                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) v;
                                imageView.setImageBitmap(piecesModel.getOriginalResource());
                                piecesModelListMain.remove(piecesModel);
                                setPuzzleListAdapter();
                                piecesModel = null;
                                System.out.println("sizeoflist" + piecesModelListMain.size());
                                if (piecesModelListMain.size() == 0) {
                                    StyleableToast.makeText(context, getString(R.string.TrenPuzleTerminado), Toast.LENGTH_LONG, R.style.mytoastCorrecta  ).show();

                                    openPopup();
                                } else {
//                                    Toast.makeText(context, "The correct Puzzle", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                piecesModel = null;
                                view.setVisibility(View.VISIBLE);
//                                Toast.makeText(context, "Not the correct Puzzle", Toast.LENGTH_LONG).show();
                                break;
                            }
                        } else {
                            View view1 = (View) event.getLocalState();
                            view1.setVisibility(View.VISIBLE);
//                            Toast.makeText(context, "You can't drop the image here", Toast.LENGTH_LONG).show();
                            break;
                        }
                    } else if (v == scrollView) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);
//                        Toast.makeText(context, "You can't drop the image here", Toast.LENGTH_LONG).show();
                        break;
                    } else if (v == rvPuzzle) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);
//                        Toast.makeText(context, "You can't drop the image here", Toast.LENGTH_LONG).show();
                        break;
                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
//                        Toast.makeText(context, "You can't drop the image here", Toast.LENGTH_LONG).show();
                        break;
                    }
                    break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundResource(R.drawable.normal_shape);	//go back to normal shape

                default:
                    break;
            }

            return true;
        }
    }

    private void openPopup() {
        if(imagen == R.drawable.tren) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.trena);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {


                }
            });
        }
        final Dialog aDialog = new Dialog(context);
        aDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        aDialog.setContentView(R.layout.pop_up_upload_doc_alert);
        aDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        aDialog.setCancelable(true);

        aDialog.show();
        ImageView btnOk = aDialog.findViewById(R.id.popupimage);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagen == R.drawable.tren) {
                    mediaPlayer.stop();

                    //                        guardarBBDD();

                    FragmentTrenTexto fragment = FragmentTrenTexto.newInstance(idActividad);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    transaction.replace(R.id.fragment_frame, fragment);
                    transaction.commit();
                    transaction.addToBackStack("Fragment");
                }

                if(imagen == R.drawable.gernika){

                    view.findViewById(R.id.layoutLottie).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.layoutLottie).setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            getFragmentManager().beginTransaction().remove(FragmentPuzle.this).commit();
                        }
                    });
//                    LottieAnimationView animationView1 = (LottieAnimationView) view.findViewById(R.id.animation_view1);
//                    animationView1.setAnimation("run_man_run.json");
//                    animationView1.loop(true);
//                    animationView1.playAnimation();

                    LottieAnimationView animationView2 = view.findViewById(R.id.animation_view2);
                    animationView2.setAnimation("fireworks.json");
                    animationView2.loop(true);
                    animationView2.playAnimation();

                    LottieAnimationView animationView3 = view.findViewById(R.id.animation_view3);
                    animationView3.setAnimation("trophy.json");
                    animationView3.loop(true);
                    animationView3.playAnimation();


                }
                aDialog.cancel();
            }
        });

    }
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    @Override
    public void onDestroy() {
        if(imagen == R.drawable.tren && mediaPlayer!=null)
            mediaPlayer.stop();
        ((MapActivity)getActivity()).cambiarLocalizacion();

        super.onDestroy();
    }

    @Override
    public void onPause() {
        if(imagen == R.drawable.tren && mediaPlayer!=null)
            mediaPlayer.stop();
        super.onPause();
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
}
