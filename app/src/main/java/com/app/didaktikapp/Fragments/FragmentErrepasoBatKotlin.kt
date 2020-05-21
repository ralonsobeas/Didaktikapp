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

// TODO: Rename parameter arguments, choose names that match
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
class FragmentErrepasoBatKotlin : Fragment(), CardStackListener {
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idActividad = it.getLong(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        views = inflater.inflate(R.layout.fragment_errepaso_bat, container, false)
        setupNavigation()
        setupCardStackView()
        setupButton()

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

                        .addSpotlight(views!!.findViewById(R.id.helpButton), getString(R.string.AyudaErrepasoTituloPregunta), getString(R.string.AyudaErrepasoDetallePregunta), "preguntaEB"+  rndGenerator!!.nextInt(999999999) )
                        .addSpotlight(views!!.findViewById(R.id.like_button), getString(R.string.AyudaErrepasoTituloCorrecta), getString(R.string.AyudaErrepasoDetalleCorrecta), "correctaEB"+ rndGenerator!!.nextInt(999999999) )
                        .addSpotlight(views!!.findViewById(R.id.skip_button), getString(R.string.AyudaErrepasoTituloIncorrecta), getString(R.string.AyudaErrepasoDetalleIncorrecta), "incorrectaEB"+ rndGenerator!!.nextInt(999999999) )
                        .addSpotlight(views!!.findViewById(R.id.rewind_button), getString(R.string.AyudaErrepasoTituloVolver), getString(R.string.AyudaErrepasoDetalleVolver), "volverEB"+ rndGenerator!!.nextInt(999999999) )
                        .startSequence()
            }, 0)
        }
        return views
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

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
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentSanMiguelTinderKotlin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(idActividad: Long) =
                FragmentErrepasoBatKotlin().apply {
                    arguments = Bundle().apply {
                        putLong(ARG_PARAM1, idActividad)
                    }
                }
    }



    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == adapter.itemCount - 9) {
            paginate()

        }

        when (manager.topPosition) {
            1, 2, 4, 6, 7, 8 -> {
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

            fragmentManager!!.beginTransaction().remove(this@FragmentErrepasoBatKotlin).commit()

        }
    }

    private fun vibrar() { // Get instance of Vibrator from current Context
        val v = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        // Vibrate for 400 milliseconds
        v.vibrate(400)
    }

    private fun guardarBBDD() {
        val actividadRepaso1 = DatabaseRepository.getAppDatabase().repaso1Dao.getRepaso1(idActividad)

        actividadRepaso1.estado = 1

        actividadRepaso1.fragment = 1

        actividadRepaso1.frases = "$correcta/10"

        DatabaseRepository.getAppDatabase().repaso1Dao.updateRepaso1(actividadRepaso1)
        ClassToFtp.send(activity, ClassToFtp.TIPO_REPASO1)

    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

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

    private fun setupCardStackView() {
        initialize()
    }

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

    private fun paginate() {
        val old = adapter.getSpots()
        val new = old.plus(createSpots())
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

    private fun reload() {
        val old = adapter.getSpots()
        val new = createSpots()
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

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

    private fun createSpot(): Spot {
        return Spot(
                id = count++,
                name = "",
                city = "",
                url = R.drawable.errepaso1zumeltzegi
        )
    }

    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()
        /*spots.add(Spot(id = count++,name = "Arantzazuko Santutegia", city = "", url =  R.drawable.errepaso1arantzazuko))
        spots.add(Spot(id = count++,name = "Araotz", city = "", url =  R.drawable.errepaso1araotz))
        spots.add(Spot(id = count++,name = "Arrikrutzeko Kobak", city = "", url =  R.drawable.errepaso1arrikrutzeko))*/
        spots.add(Spot(id = count++,name = "San Miguel Errota", city = "", url =  R.drawable.errepaso1errota))
        spots.add(Spot(id = count++,name = "Gernikako Arbola", city = "", url =  R.drawable.errepaso1gernika))
        spots.add(Spot(id = count++,name = "San Miguel Errota", city = "", url =  R.drawable.errepaso1parrokia))
        spots.add(Spot(id = count++,name = "San Miguel Parrokia", city = "", url =  R.drawable.errepaso1parrokia))
        spots.add(Spot(id = count++,name = "Zumeltzegi Dorrea", city = "", url =  R.drawable.errepaso1unibertsitatea))
        spots.add(Spot(id = count++,name = "Oñatiko Trena", city = "", url =  R.drawable.errepaso1trena))
        spots.add(Spot(id = count++,name = "Zumeltzegi Dorrea", city = "", url =  R.drawable.errepaso1zumeltzegi))
        spots.add(Spot(id = count++,name = "Sancti Spiritus Unibertsitatea", city = "", url =  R.drawable.errepaso1unibertsitatea))
        spots.add(Spot(id = count++,name = "San Miguel Parrokia", city = "", url =  R.drawable.errepaso1errota))

        return spots
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MapActivity?)!!.cambiarLocalizacion()
    }

}
