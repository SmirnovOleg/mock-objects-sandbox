import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class WebScraper(private val client: HttpClient, private val url: Url) {
    suspend fun getFirstTitleFromHabrMainPage(): String? {
        val response: HttpResponse = client.get(url)

        if (response.status != HttpStatusCode.OK) {
            println("Status code = ${response.status}, failed.")
            return null
        }

        val document = Jsoup.parse(response.readText())
        val title = document.select("h2.post__title").first()
        return title.text()
    }
}

fun main() = runBlocking {
    val client = HttpClient(CIO) {
        expectSuccess = false
    }
    val scraper = WebScraper(client, Url("https://habr.com/ru/"))
    println(scraper.getFirstTitleFromHabrMainPage())
}