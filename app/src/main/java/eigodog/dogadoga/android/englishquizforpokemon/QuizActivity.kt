package eigodog.dogadoga.android.englishquizforpokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.TextView
import androidx.core.text.HtmlCompat
import eigodog.dogadoga.android.englishquizforpokemon.databinding.ActivityQuizBinding


class QuizActivity : AppCompatActivity() {
    // bindingクラスをlateinit varで宣言する
    private lateinit var binding: ActivityQuizBinding

    private var question: String? = null
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    //valは定数
    private val QUIZ_COUNT = 5
    //配列のインデックス
    private val POKEDEX_NUMBER = 0
    private val POKE_NAME_EN = 1
    private val POKE_NAME_JA = 2
    private val CHOICE1 = 3
    private val CHOICE2 = 4
    private val CHOICE3 = 5
    private val POKE_GEN = 6
    private val ORIGIN1_EN = 7
    private val ORIGIN2_EN = 8
    private val ORIGIN3_EN = 9
    private val ORIGIN1_JA = 10
    private val ORIGIN2_JA = 11
    private val ORIGIN3_JA = 12
    //ポケモンナンバー
    private val GEN_1 = 151
    private val GEN_2 = 251
    private val GEN_3 = 386
    private val GEN_4 = 494
    private val GEN_5 = 649
    private val GEN_6 = 721
    private val GEN_7 = 809
    private val GEN_8 = 905
    //クイズ用配列
    private var dataGen1 = mutableListOf<MutableList<String>>()
    private var dataGen2 = mutableListOf<MutableList<String>>()
    private var dataGen3 = mutableListOf<MutableList<String>>()
    private var dataGen4 = mutableListOf<MutableList<String>>()
    private var dataGen5 = mutableListOf<MutableList<String>>()
    private var dataGen6 = mutableListOf<MutableList<String>>()
    private var dataGen7 = mutableListOf<MutableList<String>>()
    private var dataGen8 = mutableListOf<MutableList<String>>()


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
        mutableListOf("群馬県", "前橋市", "福岡市", "松江市", "福井市"),
        mutableListOf("群馬県", "前橋市", "福岡市", "松江市", "福井市")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        // bindingの初期化とついでにsetContentViewも行います
        binding = ActivityQuizBinding.inflate(layoutInflater)
            .apply { setContentView(this.root) }

//        //データベースをコピー
//        val adb = AssetDatabaseOpenHelper(this)
//        adb.openDatabase()

        //CsvReaderクラスをインスタンス化
        val csvReader = CsvReader(this.assets, "data.csv")
        //1行目はヘッダーなので飛ばす。
        val csvPokemons = csvReader.readCsv(1)

        /**
         * for文内のインデント用
         */
        var i = 0
        //気持ち悪い実装になっている
        for(pokemon in csvPokemons){
            if(i<GEN_1){
                var list = pokemon.toMutableList()
                dataGen1.add(list)
            }else if(i<GEN_2){
                var list = pokemon.toMutableList()
                dataGen2.add(list)
            }else if(i<GEN_3){
                var list = pokemon.toMutableList()
                dataGen3.add(list)
            }else if(i<GEN_4){
                var list = pokemon.toMutableList()
                dataGen4.add(list)
            }else if(i<GEN_5){
                var list = pokemon.toMutableList()
                dataGen5.add(list)
            }else if(i<GEN_6){
                var list = pokemon.toMutableList()
                dataGen6.add(list)
            }else if(i<GEN_7){
                var list = pokemon.toMutableList()
                dataGen7.add(list)
            }else{
                var list = pokemon.toMutableList()
                dataGen8.add(list)
            }
            i++
        }
//        for(pokemon in csvPokemons){
//            for(content in pokemon){
//                Log.i("[Result]", "content = $content")
//            }
//        }

        for(pokemon in dataGen1){
                Log.i("[Result]", "content = ${pokemon[0]}")
        }

        dataGen1.shuffle()

        showNextQuiz()
    }

    /**
     * クイズを出題する
     */
    fun showNextQuiz(){
        //カウントラベルの更新
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        //クイズを1問取り出す
        val poke = dataGen1[0]
        val quiz = mutableListOf<String>()
        quiz += poke[POKE_NAME_EN]
        quiz += poke[POKE_NAME_JA]
        quiz += poke[CHOICE1]
        quiz += poke[CHOICE2]
        quiz += poke[CHOICE3]

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
        dataGen1.removeAt(0)
    }
//    fun showNextQuiz(){
//        //カウントラベルの更新
//        binding.countLabel.text = getString(R.string.count_label, quizCount)
//
//        //クイズを1問取り出す
//        val quiz = quizData[0]
//
//        //問題をセット
//        question = quiz[0]
//        binding.questionLabel.text = question
//
//        //正解をセット
//        rightAnswer = quiz[1]
//
//        //問題のポケモンを削除
//        quiz.removeAt(0)
//
//        //正解と選択肢3つをシャッフル
//        quiz.shuffle()
//
//        //選択肢をセット
//        binding.answerBtn1.text = quiz[0]
//        binding.answerBtn2.text = quiz[1]
//        binding.answerBtn3.text = quiz[2]
//        binding.answerBtn4.text = quiz[3]
//
//        //出題したクイズを削除する
//        quizData.removeAt(0)
//    }

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
            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
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