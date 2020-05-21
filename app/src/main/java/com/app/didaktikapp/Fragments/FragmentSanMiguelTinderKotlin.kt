package com.app.didaktikapp.Fragments

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.app.didaktikapp.Activities.MapActivity
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel
import com.app.didaktikapp.BBDD.database.DatabaseRepository
import com.app.didaktikapp.CardStack.CardStackAdapter
import com.app.didaktikapp.CardStack.Spot
import com.app.didaktikapp.CardStack.SpotDiffCallback
import com.app.didaktikapp.FTP.ClassToFtp
import com.app.didaktikapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.muddzdev.styleabletoast.StyleableToast
import com.wooplr.spotlight.SpotlightConfig
import com.wooplr.spotlight.utils.SpotlightSequence
import com.wooplr.spotlight.utils.Utils
import com.yuyakaido.android.cardstackview.*
import java.util.*
import kotlin.collections.ArrayList


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentSanMiguelTinderKotlin.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentSanMiguelTinderKotlin.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSanMiguelTinderKotlin : Fragment(), CardStackListener {
    // TODO: Rename and change types of parameters
    private var idActividad: Long? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var views :  View? = null
    private val drawerLayout by lazy { views!!.findViewById<DrawerLayout>(R.id.drawer_layout) }
    private val cardStackView by lazy { views!!.findViewById<CardStackView>(R.id.card_stack_view) }
    private val manager by lazy { CardStackLayoutManager(context, this) }
    private val adapter by lazy { CardStackAdapter(createSpots()) }
    private var count = 1L
    private var correcta = 0


    /**
     * Método para crear el fragment
     * @author gennakk
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idActividad = it.getLong(ARG_PARAM1)
        }
    }

    /**
     * Método para crear la vista.
     * @return Vista del fragment FragmentSanMiguelTinderKotlin
     * @author gennakk
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        views = inflater.inflate(R.layout.fragment_san_miguel_tinder, container, false)
        setupNavigation()
        setupCardStackView()
        setupButton()

        /*
        Botón flotante de ayuda
         */
        /*
        Botón flotante de ayuda
         */

        /*
        Botón flotante de ayuda
         */
        val floatingActionButton: FloatingActionButton = views!!.findViewById(R.id.helpButton)
        floatingActionButton.setOnClickListener {
            val config = SpotlightConfig()
            config.maskColor = Color.parseColor("#E63A3A3A")
            config.introAnimationDuration = 400
            config.fadingTextDuration = 400
            config.padding = 20
            config.isDismissOnTouch = true
            config.isDismissOnBackpress = true
            config.isPerformClick = false
            config.headingTvSize = 24
            config.headingTvColor = Color.parseColor("#2B82C5")
            config.subHeadingTvSize = 24
            config.subHeadingTvColor = Color.parseColor("#FAFAFA")
            config.lineAnimationDuration = 300
            config.lineStroke = Utils.dpToPx(4)
            config.lineAndArcColor = Color.parseColor("#2B82C5")
            config.setShowTargetArc(true)
            val handler = Handler()

            handler.postDelayed({
                var rndGenerator: Random? = Random()

                SpotlightSequence.getInstance(activity, config)
                        .addSpotlight(views!!.findViewById(R.id.helpButton), getString(R.string.AyudaErrepasoTituloPregunta), getString(R.string.AyudaErrepasoDetallePregunta), "preguntaSMT"+  rndGenerator!!.nextInt(999999999) )
                        .addSpotlight(views!!.findViewById(R.id.like_button), getString(R.string.AyudaSanMiguelTinderTituloCorrecta), getString(R.string.AyudaSanMiguelTinderDetalleCorrecta), "correctaSMT"+  rndGenerator!!.nextInt(999999999) )
                        .addSpotlight(views!!.findViewById(R.id.skip_button), getString(R.string.AyudaSanMiguelTinderTituloIncorrecta), getString(R.string.AyudaSanMiguelTinderDetalleIncorrecta), "incorrectaSMT"+  rndGenerator!!.nextInt(999999999) )
                        .addSpotlight(views!!.findViewById(R.id.rewind_button), getString(R.string.AyudaSanMiguelTinderTituloRetroceder), getString(R.string.AyudaSanMiguelTinderDetalleRetroceder), "retrocederSMT"+  rndGenerator!!.nextInt(999999999) )
                        .startSequence()
            }, 0)

        }
        return views
    }

    /**
     * Fragment interaction.
     * @param uri
     * @author gennakk
     */
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    /**
     * Método para implementar fragment.
     * @param context Contexto de la app.
     * @author gennakk
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    /**
     * Método para quitar el fragment.
     * @author gennakk
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Id de la actividad
         * @return A new instance of fragment FragmentSanMiguelTinderKotlin.
         */
        @JvmStatic
        fun newInstance(idActividad: Long) =
                FragmentSanMiguelTinderKotlin().apply {
                    arguments = Bundle().apply {
                        putLong(ARG_PARAM1, idActividad)
                    }
                }
    }


    /**
     * Evento para el drag de la carta.
     * @author gennakk
     */
    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    /**
     * Evento para el swipe de la carta.
     * @author gennakk
     */
    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == adapter.itemCount - 6) {
            paginate()

        }

        when (manager.topPosition) {
            1, 3, 5 -> {
                if (direction.toString() == "Right"){
                    correcta++
                    StyleableToast.makeText(context!!, resources.getString(R.string.Correcta), Toast.LENGTH_SHORT, R.style.mytoastCorrecta  ).show()

                }else{
                    StyleableToast.makeText(context!!, resources.getString(R.string.Incorrecta), Toast.LENGTH_SHORT, R.style.mytoastIncorrecta  ).show()
                    vibrar()

                }
            }
            else -> {
                if (direction.toString() == "Left"){
                    correcta++
                    StyleableToast.makeText(context!!, resources.getString(R.string.Correcta), Toast.LENGTH_SHORT, R.style.mytoastCorrecta  ).show()

                }else{
                    StyleableToast.makeText(context!!, resources.getString(R.string.Incorrecta), Toast.LENGTH_SHORT, R.style.mytoastIncorrecta  ).show()
                    vibrar()

                }
            }
        }

        //Cerrar fragment al swipear ultima imagen
        if(manager.topPosition == adapter.itemCount ){

            //Cargar resultado en BBDD
            guardarBBDD()

            fragmentManager!!.beginTransaction().remove(this@FragmentSanMiguelTinderKotlin).commit()

        }
    }

    /**
     * Método para vibrar el dispositivo.
     * @author gennakk
     */
    private fun vibrar() { // Get instance of Vibrator from current Context
        val v = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        // Vibrate for 400 milliseconds
        v.vibrate(400)
    }

    /**
     * Guardar en BBDD los ejercicios.
     * @author gennakk
     */
    private fun guardarBBDD() {
        val actividadSanMiguel: ActividadSanMiguel? = DatabaseRepository.getAppDatabase().sanMiguelDao.getSanMiguel(idActividad)

        actividadSanMiguel!!.estado = 2


        actividadSanMiguel.fragment = 2
        actividadSanMiguel.fotos = "${correcta}/6"

        DatabaseRepository.getAppDatabase().sanMiguelDao.updateSanMiguel(actividadSanMiguel)
        ClassToFtp.send(getActivity(), ClassToFtp.TIPO_SANMIGUEL);

    }

    /**
     * Evento para el rewound de la carta.
     * @author gennakk
     */
    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    /**
     * Evento para el cancel de la carta.
     * @author gennakk
     */
    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    /**
     * Evento para el la aparición de la carta.
     * @author gennakk
     */
    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    /**
     * Evento para el desaparición de la carta.
     * @author gennakk
     */
    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

    /**
     * Evento para el navegación de la carta.
     * @author gennakk
     */
    private fun setupNavigation() {
//        // Toolbar
//        val toolbar = views!!.findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//
//        // DrawerLayout
//        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
//        actionBarDrawerToggle.syncState()
//        drawerLayout.addDrawerListener(actionBarDrawerToggle)

//        // NavigationView
//        val navigationView = views!!.findViewById<NavigationView>(R.id.navigation_view)
//        navigationView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.reload -> reload()
//                R.id.add_spot_to_first -> addFirst(1)
//                R.id.add_spot_to_last -> addLast(1)
//                R.id.remove_spot_from_first -> removeFirst(1)
//                R.id.remove_spot_from_last -> removeLast(1)
//                R.id.replace_first_spot -> replace()
//                R.id.swap_first_for_last -> swap()
//            }
//            drawerLayout.closeDrawers()
//            true
//        }
    }

    /**
     * Set up de la vista CardStack.
     * @author gennakk
     */
    private fun setupCardStackView() {
        initialize()
    }

    /**
     * Set up de los botones de las cartas.
     * @author gennakk
     */
    private fun setupButton() {
        val skip = views!!.findViewById<View>(R.id.skip_button)
        skip.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }

        val rewind = views!!.findViewById<View>(R.id.rewind_button)
        rewind.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                    .setDirection(Direction.Bottom)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(DecelerateInterpolator())
                    .build()
            manager.setRewindAnimationSetting(setting)
            cardStackView.rewind()
        }

        val like = views!!.findViewById<View>(R.id.like_button)
        like.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(AccelerateInterpolator())
                    .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }
    }

    /**
     * Inicialización del CardStack View.
     * @author gennakk
     */
    private fun initialize() {

        manager.setStackFrom(StackFrom.Top)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    /**
     * Paginación del CardStack.
     * @author gennakk
     */
    private fun paginate() {
        val old = adapter.getSpots()
        val new = old.plus(createSpots())
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    /**
     * Recargar.
     * @author gennakk
     */
    private fun reload() {
        val old = adapter.getSpots()
        val new = createSpots()
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    /**
     * Añadir primera carta.
     * @author gennakk
     */
    private fun addFirst(size: Int) {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                add(manager.topPosition, createSpot())
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    /**
     * Añadir última carta.
     * @author gennakk
     */
    private fun addLast(size: Int) {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            addAll(List(size) { createSpot() })
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    /**
     * Borrar primera carta.
     * @author gennakk
     */
    private fun removeFirst(size: Int) {
        if (adapter.getSpots().isEmpty()) {
            return
        }

        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                removeAt(manager.topPosition)
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    /**
     * Borrar última carta.
     * @author gennakk
     */
    private fun removeLast(size: Int) {
        if (adapter.getSpots().isEmpty()) {
            return
        }

        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                removeAt(this.size - 1)
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    /**
     * Reemplazar carta.
     * @author gennakk
     */
    private fun replace() {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            removeAt(manager.topPosition)
            add(manager.topPosition, createSpot())
        }
        adapter.setSpots(new)
        adapter.notifyItemChanged(manager.topPosition)
    }

    /**
     * Intercambiar carta.
     * @author gennakk
     */
    private fun swap() {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            val first = removeAt(manager.topPosition)
            val last = removeAt(this.size - 1)
            add(manager.topPosition, last)
            add(first)
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    /**
     * Crear Spot.
     * @author gennakk
     * @return Spot
     */
    private fun createSpot(): Spot {
        return Spot(
                id = count++,
                name = "",
                city = "",
                url = R.drawable.sanmiguelcorrecta1
        )
    }

    /**
     * Crear lista de Spots.
     * @author gennakk
     * @return Lista con todos los Spots.
     */
    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()
        spots.add(Spot(id = count++,name = "", city = "", url =  R.drawable.sanmiguelcorrecta1))
        spots.add(Spot(id = count++,name = "", city = "", url =  R.drawable.sanmiguelincorrecta1))
        spots.add(Spot(id = count++,name = "", city = "", url =  R.drawable.sanmiguelcorrecta2))
        spots.add(Spot(id = count++,name = "", city = "", url =  R.drawable.sanmiguelincorrecta2))
        spots.add(Spot(id = count++,name = "", city = "", url =  R.drawable.sanmiguelcorrecta3))
        spots.add(Spot(id = count++,name = "", city = "", url =  R.drawable.sanmiguelincorrecta3))

        return spots
    }

    /**
     * Método onDestroy del fragment FragmentSanMiguelKotlin.
     * @author gennakk
     */
    override fun onDestroy() {
        super.onDestroy()
        (activity as MapActivity?)!!.cambiarLocalizacion()
    }

}
