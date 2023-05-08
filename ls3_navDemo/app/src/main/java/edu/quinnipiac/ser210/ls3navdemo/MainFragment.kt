package edu.quinnipiac.ser210.ls3navdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), View.OnClickListener {

    // Define our navigation Controller
    lateinit var navController: NavController

    // responsible for inflating
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        // tells the transaction button that that the onClick listener is here
        view.findViewById<Button>(R.id.view_transactions_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.send_money_btn).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        // checking for the id of the buttons
        when(v!!.id){
            R.id.view_transactions_btn -> // telling the navigation controller to when you click, follow the navigation from action main fragment, which will replace (transition) the main fragment to the  view transaction fragment and transaction fragment will be displayed on app
                navController.navigate(R.id.action_mainFragment_to_viewTransactionFragment)
            R.id.send_money_btn -> // telling the navigation controller to when you click, navigate to the action_mainFragment_to_chooseRecipientFragment
            navController.navigate(R.id.action_mainFragment_to_chooseRecipientFragment)
        }
    }


}