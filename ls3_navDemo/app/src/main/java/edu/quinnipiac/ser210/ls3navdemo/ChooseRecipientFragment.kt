package edu.quinnipiac.ser210.ls3navdemo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import org.w3c.dom.Text


class ChooseRecipientFragment : Fragment(), View.OnClickListener {


    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.next_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        // logic for two buttons
        /*
        1. send button/ recipient button
        2. cancel button
         */
        // !! since it's nullable
        // when this id of the view (in this case it's the button)
        when(v!!.id){
            R.id.next_btn ->{
                // read in the input name
                // requireView() -> another way of accessing a UI component
                val input_recipient:EditText= requireView().findViewById(R.id.input_recipient)
                // check if there's input in the edittext
                if (!TextUtils.isEmpty(input_recipient.text.toString())){
                    // read in recipient
                    val recpient_name = input_recipient.text.toString()
                    // use action
                    // for every fragment that sends parameters/information there is a class that got created
                    // From ChooseRecipientFragmentDirections I will call the action that causes me to send the argument
                    // put in recipient on the action
                    val action = ChooseRecipientFragmentDirections.actionChooseRecipientFragmentToSpecifyAmountFragment(recpient_name)
                    // Use Navigation component to navigate using this action
                    v.findNavController().navigate(action)
                }

            }
            // go back
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}

