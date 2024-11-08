package com.example.cashapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.cashapp.ui.theme.CashappTheme
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CashappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CashAppData(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CashAppData(modifier: Modifier = Modifier, viewModel: CashAppViewModel = viewModel()) {
    val data = viewModel.cashAppData.observeAsState().value
    val errorData = viewModel.errorData.observeAsState().value

    // loading state - haven't loaded any json data, and haven't run into an error
    if (data == null && errorData == "") {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    }

    // successfully loaded valid json data
    if (data != null) {
        LazyColumn {
            items(data.stocks?.size ?: 0) { index ->
                // todo apply weights
                Row() {
                    Text(
                        text = data.stocks?.get(index)?.name ?: "",
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f),
                    )
                    Text(
                        text = data.stocks?.get(index)?.current_price_cents.toString(),
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f),
                    )
                }
                Divider()
            }
        }
    }

    // received an empty json response
    if (data?.stocks?.isEmpty() == true) {
        Text(
            text = "empty json response",
            modifier = modifier
        )
    }

    // received malformed json
    if (errorData != "") {
        Text(
            text = errorData.toString(),
            modifier = modifier
        )
    }
}
