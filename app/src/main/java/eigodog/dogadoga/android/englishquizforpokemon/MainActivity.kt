package eigodog.dogadoga.android.englishquizforpokemon

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import CsvReader
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.Log
import eigodog.dogadoga.android.englishquizforpokemon.databinding.ActivityMainBinding
import android.widget.TextView
import androidx.core.text.HtmlCompat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var question: String? = null
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    //valは定数
    private val QUIZ_COUNT = 5

    private val quizData = mutableListOf(
        mutableListOf("北海道", "札幌市", "長崎市", "福島市", "前橋市"),
        mutableListOf("青森県", "青森市", "広島市", "甲府市", "岡山市"),
        mutableListOf("岩手県", "盛岡市", "大分市", "秋田市", "福岡市"),
        mutableListOf("宮城県", "仙台市", "水戸市", "岐阜市", "福井市"),
        mutableListOf("秋田県", "秋田市", "横浜市", "鳥取市", "仙台市"),
        mutableListOf("山形県", "山形市", "青森市", "山口市", "奈良市"),
        mutableListOf("福島県", "福島市", "盛岡市", "新宿区", "京都市"),
        mutableListOf("茨城県", "水戸市", "金沢市", "名古屋市", "奈良市"),
        mutableListOf("栃木県", "宇都宮市", "札幌市", "岡山市", "奈良市"),
        mutableListOf("群馬県", "前橋市", "福岡市", "松江市", "福井市")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //CsvReaderクラスをインスタンス化
        val csvReader = CsvReader(this.assets, "data.csv")
        //1行目はヘッダーなので飛ばす。
        val csvContents = csvReader.readCsv(1)
        for(contents in csvContents){
            for(content in contents){
                Log.i("[Result]", "content = $content")
            }
        }

        quizData.shuffle()

        showNextQuiz()
    }

    /**
     * クイズを出題する
     */
    fun showNextQuiz(){
        //カウントラベルの更新
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        //クイズを1問取り出す
        val quiz = quizData[0]

        //問題をセット
        question = quiz[0]
        binding.questionLabel.text = question

        //正解をセット
        rightAnswer = quiz[1]

        //問題のポケモンを削除
        quiz.removeAt(0)

        //正解と選択肢3つをシャッフル
        quiz.shuffle()

        //選択肢をセット
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        //出題したクイズを削除する
        quizData.removeAt(0)
    }

    /**
     * 解答ボタンが押されたら呼ばれる
     */
    fun checkAnswer(view: View){
        //どの解答ボタンが押されたか
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        //ダイアログのタイトルを作成
        val alertTitle: String
        if (btnText == rightAnswer){
            alertTitle = "正解！"
            rightAnswerCount++
        }else{
            alertTitle = "不正解..."
        }

        /**
         * ダイアログ内で表示させるためのhtml。
         * Google画像検索リンク入り。
         */
        val html = """
            答え： $rightAnswer <br><br>
            <a href="https://www.google.com/search?q=$question&tbm=isch">画像</a>を開く
        """.trimIndent()

        /**
         * 問題の正解・不正解を表示するAlertDialog
         */
        val dialog = AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage(transformHtml(html))
            .setPositiveButton("OK"){ dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .create()

        dialog.show()

        //movementMethodでリンクを開けるようにする
        dialog.findViewById<TextView>(android.R.id.message)?.movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * 出題数をチェックする
     */
    fun checkQuizCount(){
        if(quizCount==QUIZ_COUNT) {
            //結果画面を表示(.javaであってるの？)
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount)
            startActivity(intent)
        }else{
            quizCount++
            showNextQuiz()
        }
    }

    /**
     * HTMLを適合させるための関数
     */
    fun transformHtml(text: String): Spanned {
        return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}