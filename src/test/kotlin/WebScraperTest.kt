import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class WebScraperTest {
    private val correctHabrUrl = Url("https://habr.com/ru/")
    private val invalidHabrUrl = Url("https://habr.com/notfound")
    private val title = "Скрипт для создания OpenVPN сервера, или как один админ удаленку облегчал"

    private val clientMock = HttpClient(MockEngine) {
        expectSuccess = false
        engine {
            addHandler { request ->
                when (request.url) {
                    correctHabrUrl -> {
                        respond(
                            "<html><body><h2 class='post__title'>${title}</h2></body></html>",
                            status = HttpStatusCode.OK
                        )
                    }
                    invalidHabrUrl -> {
                        respond(
                            "Page was not found",
                            status = HttpStatusCode.NotFound
                        )
                    }
                    else -> error("Unhandled ${request.url}")
                }
            }
        }
    }

    @Test
    fun `receive first title from habr main page`() = runBlocking {
        val scraper = WebScraper(clientMock, correctHabrUrl)
        assertEquals(scraper.getFirstTitleFromHabrMainPage(), title)
    }

    @Test
    fun `try to get page from invalid url`() = runBlocking {
        val scraper = WebScraper(clientMock, invalidHabrUrl)
        assertNull(scraper.getFirstTitleFromHabrMainPage())
    }
}