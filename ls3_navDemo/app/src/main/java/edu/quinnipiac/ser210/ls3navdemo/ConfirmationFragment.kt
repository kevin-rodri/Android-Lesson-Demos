package edu.quinnipiac.ser210.ls3navdemo


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class ConfirmationFragment : Fragment() {

    lateinit var recipient: String
    var money: Money? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        // check if bundle exists / checking if there is something in the bundle
        if (bundle == null){
            Log.e("ConfirmationFragment", "ConfirmationAmountFragment did not receive recipient and money information")
            return
        }
        recipient = ConfirmationFragmentArgs.fromBundle(bundle).recipient.toString()
        money = ConfirmationFragmentArgs.fromBundle(bundle).money
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val money_amount = money!!.amount
        val confirmation_message = "You have sent $money_amount to $recipient"
        view.findViewById<TextView>(R.id.confirmation_message).text = confirmation_message
    }


}