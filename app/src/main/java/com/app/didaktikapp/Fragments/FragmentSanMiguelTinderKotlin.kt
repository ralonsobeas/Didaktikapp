package com.app.didaktikapp.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
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
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel
import com.app.didaktikapp.BBDD.database.DatabaseRepository
import com.app.didaktikapp.CardStack.CardStackAdapter
import com.app.didaktikapp.CardStack.Spot
import com.app.didaktikapp.CardStack.SpotDiffCallback
import com.app.didaktikapp.R
import com.muddzdev.styleabletoast.StyleableToast
import com.yuyakaido.android.cardstackview.*

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idActividad = it.getLong(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        views = inflater.inflate(R.layout.fragment_san_miguel_tinder, container, false)
        setupNavigation()
        setupCardStackView()
        setupButton()
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
                FragmentSanMiguelTinderKotlin().apply {
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

                }
            }
            else -> {
                if (direction.toString() == "Left"){
                    correcta++
                    StyleableToast.makeText(context!!, resources.getString(R.string.Correcta), Toast.LENGTH_SHORT, R.style.mytoastCorrecta  ).show()

                }else{
                    StyleableToast.makeText(context!!, resources.getString(R.string.Incorrecta), Toast.LENGTH_SHORT, R.style.mytoastIncorrecta  ).show()

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

    private fun guardarBBDD() {
        val actividadSanMiguel: ActividadSanMiguel? = DatabaseRepository.getAppDatabase().sanMiguelDao.getSanMiguel(idActividad)

        actividadSanMiguel!!.estado = 2


        actividadSanMiguel.fragment = 2
        actividadSanMiguel.fotos = "${correcta}/6"

        DatabaseRepository.getAppDatabase().sanMiguelDao.updateSanMiguel(actividadSanMiguel)

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
                url = R.drawable.sanmiguelcorrecta1
        )
    }

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

}