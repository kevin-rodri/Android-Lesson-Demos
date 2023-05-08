package edu.quinnipiac.ser210.ls3navdemo

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import java.math.BigDecimal


class SpecifyAmountFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   recipient = arguments!!.getString("recipient")
        // evokes the getter of the arguments and the getter of the arguments are the arguments that arrive to the fragment
        val bundle = arguments
        // check if bundle exists / checking if there is something in the bundle
        if (bundle == null){
            Log.e("SpecifyAmountFragment", "SpecifyAmountFragment did not receive recipient information")
            return
        }
        // reading in the recipient
        recipient = SpecifyAmountFragmentArgs.fromBundle(bundle).recipient.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        val message = "Sending money to $recipient"
        view.findViewById<TextView>(R.id.recipient).text = message
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn ->{
                // read in the input name
                // requireView() -> another way of accessing a UI component
                val input_amount: EditText= requireView().findViewById(R.id.input_amount)
                // check if there's input in the edittext
                if (!TextUtils.isEmpty(input_amount.text.toString())){
                    // read in recipient
                    val amount = Money (BigDecimal(input_amount.text.toString()))
                    // use action
                    // for every fragment that sends parameters/information there is a class that got created
                    // From ChooseRecipientFragmentDirections I will call the action that causes me to send the argument
                    // put in recipient on the action
                    val action = SpecifyAmountFragmentDirections.actionSpecifyAmountFragmentToConfirmationFragment(recipient, amount)
                    // Use Navigation component to navigate using this action
                    v.findNavController().navigate(action)
                } else {
                    Toast.makeText(activity, "Enter your name", Toast.LENGTH_SHORT).show()
                }

            }
            // go back
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }

}
