package eigodog.dogadoga.android.englishquizforpokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gen1Button = findViewById<Button>(R.id.gen1Button)

        //ボタンクリックイベントリスナー
        gen1Button.setOnClickListener{
            //Intentオブジェクト生成、繊維画面定義
            val intent2Gen1Quiz = Intent(this, QuizActivity::class.java)
            startActivity(intent2Gen1Quiz)
        }
    }
}