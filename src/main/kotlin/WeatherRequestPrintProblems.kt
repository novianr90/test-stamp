import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat
import kotlin.collections.List

data class Weather(
    val list: List<Forecast>
)

data class Forecast(
    val main: Main,
    val dt_txt: String
)

data class Main(
    val temp: Double
)

fun main() {

    val gson = Gson()

    val response = requestWeather()

    if (response != null) {
        printForecastWeather(response, gson)
    }
}

/**
 * A method to print Forecast Weather for 5 days.
 *
 * Use [requestWeather] for request.
 *
 * Use [SimpleDateFormat] for formatting the date.
 *
 * Use [Gson] for map from *ResponseBody* to [Weather] class.
 *
 * Use [Main.temp] from [Forecast.dt_txt] where dateTime is **00:00:00**
 *
 * @param Response
 * @param Gson
 * @return Unit
 */
fun printForecastWeather(response: Response, gson: Gson) {
    val formatter = SimpleDateFormat("EEE, dd MMM yyyy")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    val weatherData = response.body?.string().let {
        gson.fromJson(it, Weather::class.java)
    } ?: throw IOException("Response is null or empty")

    for (forecast in weatherData.list) {
        val time = forecast.dt_txt.split(" ")
        val dateFormatted = formatter.format(dateFormat.parse(time[0]))

        val dateTime = time[1].split(":")

        if (dateTime[0] == "00") {
            println("$dateFormatted: ${forecast.main.temp}°C")
        }
    }
}

/**
 * A method to execute request for getting Weather
 * OkHttp used for create a request
 * @return Response
 */
fun requestWeather(): Response? {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://api.openweathermap.org/data/2.5/forecast?q=jakarta&appid=c6f974d24fbba926360c8dec5fd3a402&units=metric")
        .build()

    val response: Response
    return try {
        response = client.newCall(request).execute()
        response
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}