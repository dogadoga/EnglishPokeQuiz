package eigodog.dogadoga.android.englishquizforpokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eigodog.dogadoga.android.englishquizforpokemon.databinding.ActivityResultBinding

/**
 * クイズの結果を表示するアクティビティ
 */
class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_result)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //正解数を取得
        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)

        //TextViewに表示する
        binding.resultLabel.text = getString(R.string.result_score, score)

        //もう一度ボタン(setOnClickListener)
        binding.tryAgainBtn.setOnClickListener{
            startActivity(Intent(this@ResultActivity, QuizActivity::class.java))
        }

        //戻るボタン
        binding.return2MainButton.setOnClickListener{
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }
    }
}