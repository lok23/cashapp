package com.example.cashapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException


class CashAppViewModelTest {
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder().build()
    private lateinit var viewModel: CashAppViewModel
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = CashAppViewModel()
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CashAppAPIService::class.java)
    }

    @Test
    fun `test valid cashapp api call`() = runBlocking {
        val server = MockWebServer()
        val response = MockResponse().setResponseCode(200).setBody(
            "{\n" +
                    "  \"stocks\": [\n" +
                    "    {\n" +
                    "      \"ticker\": \"^GSPC\",\n" +
                    "      \"name\": \"S&P 500\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 318157,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"RUNINC\",\n" +
                    "      \"name\": \"Runners Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 3614,\n" +
                    "      \"quantity\": 5,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"BAC\",\n" +
                    "      \"name\": \"Bank of America Corporation\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 2393,\n" +
                    "      \"quantity\": 10,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"EXPE\",\n" +
                    "      \"name\": \"Expedia Group, Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 8165,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"GRUB\",\n" +
                    "      \"name\": \"Grubhub Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 6975,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"TRUNK\",\n" +
                    "      \"name\": \"Trunk Club\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 17632,\n" +
                    "      \"quantity\": 9,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"FIT\",\n" +
                    "      \"name\": \"Fitbit, Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 678,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"UA\",\n" +
                    "      \"name\": \"Under Armour, Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 844,\n" +
                    "      \"quantity\": 7,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"VTI\",\n" +
                    "      \"name\": \"Vanguard Total Stock Market Index Fund ETF Shares\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 15994,\n" +
                    "      \"quantity\": 1,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"RUN\",\n" +
                    "      \"name\": \"Run\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 6720,\n" +
                    "      \"quantity\": 12,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"VWO\",\n" +
                    "      \"name\": \"Vanguard FTSE Emerging Markets\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 4283,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"JNJ\",\n" +
                    "      \"name\": \"Johnson & Johnson\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 14740,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"BRKA\",\n" +
                    "      \"name\": \"Berkshire Hathaway Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 28100000,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"^DJI\",\n" +
                    "      \"name\": \"Dow Jones Industrial Average\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 2648154,\n" +
                    "      \"quantity\": 5,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"^TNX\",\n" +
                    "      \"name\": \"Treasury Yield 10 Years\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 61,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"RUNWAY\",\n" +
                    "      \"name\": \"Rent The Runway\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 24819,\n" +
                    "      \"quantity\": 20,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}"
        )
        server.enqueue(response)

        viewModel.getValidCashAppData()
        Assert.assertEquals(16, viewModel.cashAppData.value?.stocks?.size)
    }

    @Test(expected = IOException::class)
    fun `test malformed json api call`() = runBlocking {
        val server = MockWebServer()
        val response = MockResponse().setResponseCode(200).setBody(
            "{\n" +
                    "  \"stocks\": [\n" +
                    "    {\n" +
                    "      \"ticker\": \"^GSPC\",\n" +
                    "      \"name\": \"S&P 500\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 318157,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"RUNINC\",\n" +
                    "      \"name\": \"Runners Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 3614,\n" +
                    "      \"quantity\": 5,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"BAC\",\n" +
                    "      \"name\": \"Bank of America Corporation\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 2393,\n" +
                    "      \"quantity\": 10,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"EXPE\",\n" +
                    "      \"name\": \"Expedia Group, Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 8165,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"GRUB\",\n" +
                    "      \"name\": \"Grubhub Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 6975,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"TRUNK\",\n" +
                    "      \"name\": \"Trunk Club\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 17632,\n" +
                    "      \"quantity\": 9,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"FIT\",\n" +
                    "      \"name\": \"Fitbit, Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 678,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"UA\",\n" +
                    "      \"name\": \"Under Armour, Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 844,\n" +
                    "      \"quantity\": 7,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"VTI\",\n" +
                    "      \"name\": \"Vanguard Total Stock Market Index Fund ETF Shares\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 15994,\n" +
                    "      \"quantity\": 1,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"RUN\",\n" +
                    "      \"name\": \"Run\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 6720,\n" +
                    "      \"quantity\": 12,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"VWO\",\n" +
                    "      \"name\": \"Vanguard FTSE Emerging Markets\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 4283,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"JNJ\",\n" +
                    "      \"name\": \"Johnson & Johnson\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 14740,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"BRKA\",\n" +
                    "      \"name\": \"Berkshire Hathaway Inc.\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 28100000,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"^DJI\",\n" +
                    "      \"name\": \"Dow Jones Industrial Average\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 2648154,\n" +
                    "      \"quantity\": 5,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"^TNX\",\n" +
                    "      \"name\": \"Treasury Yield 10 Years\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 61,\n" +
                    "      \"quantity\": null,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"ticker\": \"RUNWAY\",\n" +
                    "      \"name\": \"Rent The Runway\",\n" +
                    "      \"currency\": \"USD\",\n" +
                    "      \"current_price_cents\": 24819,\n" +
                    "      \"quantity\": 20,\n" +
                    "      \"current_price_timestamp\": 1681845832\n" +
                    "    }\n" +
                    "  ]\n" +
                    "} + malformedmalformedmalformed"
        )
        server.enqueue(response)

        viewModel.getMalformedCashAppData()
    }

    @Test
    fun `test empty json api call`() = runBlocking {
        val server = MockWebServer()
        val response = MockResponse().setResponseCode(200).setBody(
            "{\"stocks\":[]}"
        )
        server.enqueue(response)

        viewModel.getEmptyCashAppData()
        Assert.assertEquals("[]", viewModel.cashAppData.value?.stocks?.toString())
    }
}