import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.intent.Flight
import com.example.intent.R

class FlightAdapter (private val context: Context,private val flight: List<Flight>) :BaseAdapter(){
    override fun getCount(): Int {
      return flight.size
    }

    override fun getItem(position: Int): Any {
      return flight[position]
    }

    override fun getItemId(position: Int): Long {
    return  position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val view:View =convertView?: LayoutInflater.from(context).inflate(R.layout.flight_list_item,parent,false)
        val flightName = view.findViewById<TextView>(R.id.tvflightname)
        var flightno = view.findViewById<TextView>(R.id.tvflightno)
        val flighttime = view.findViewById<TextView>(R.id.tvTime)
        val flightfare = view.findViewById<TextView>(R.id.tvfare)
        val flight = flight[position]
        flightno.text = flight.fno.toString()
        flightName.text = flight.fname
        flighttime.text = flight.ftime
        flightfare.append( " " + flight.ffare)
        return view
    }

}