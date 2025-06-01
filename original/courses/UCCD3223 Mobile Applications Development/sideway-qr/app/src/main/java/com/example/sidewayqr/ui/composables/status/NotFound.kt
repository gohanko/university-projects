package com.example.sidewayqr.ui.composables.status

import androidx.compose.runtime.Composable

@Composable
fun NotFound() {
    BaseStatus(
        kaomoji =
        "               ／＞　 フ\n" +
        "               | 　_　_| \n" +
        "          ／` ミ＿xノ \n" +
        "         /　　　　 |\n" +
        "        /　 ヽ　　 ﾉ\n" +
        "        │　　|　|　|\n" +
        "／￣|　　 |　|　|\n" +
        "(￣ヽ＿_ヽ_)__)\n" +
        "＼二)",
        statusText = "No results found"
    )
}
