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
    private var origin1: String? = ""
    private var origin2: String? = ""
    private var origin3: String? = ""

    private var rightAnswerCount = 0
    private var quizCount = 1
    private var genNum: String? = "0" //世代
    private val QUIZ_COUNT = 5  //出題数
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
    /**
     * 出題する問題
     */
    private var pokeData = mutableListOf<MutableList<String>>()
    private var dataGen1 = mutableListOf<MutableList<String>>()
    private var dataGen2 = mutableListOf<MutableList<String>>()
    private var dataGen3 = mutableListOf<MutableList<String>>()
    private var dataGen4 = mutableListOf<MutableList<String>>()
    private var dataGen5 = mutableListOf<MutableList<String>>()
    private var dataGen6 = mutableListOf<MutableList<String>>()
    private var dataGen7 = mutableListOf<MutableList<String>>()
    private var dataGen8 = mutableListOf<MutableList<String>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        // bindingの初期化とついでにsetContentViewも行います
        binding = ActivityQuizBinding.inflate(layoutInflater)
            .apply { setContentView(this.root) }

//        //データベースをコピー
//        val adb = AssetDatabaseOpenHelper(this)
//        adb.openDatabase()

        //CsvReaderクラスをインスタンス化
        val csvReader = CsvReader(this.assets, "datav2.csv")
        //1行目はヘッダーなので飛ばす。
        val csvPokemons = csvReader.readCsv(1)

        //世代を受け取って表示
        genNum = intent.getStringExtra("GEN")
        binding.genNum.text = getString(R.string.gen_num, genNum)

        //気持ち悪い実装になっている
        for((i,pokemon) in csvPokemons.withIndex()){
            if(i<GEN_1){
                val list = pokemon.toMutableList()
                dataGen1.add(list)
            }else if(i<GEN_2){
                val list = pokemon.toMutableList()
                dataGen2.add(list)
            }else if(i<GEN_3){
                val list = pokemon.toMutableList()
                dataGen3.add(list)
            }else if(i<GEN_4){
                val list = pokemon.toMutableList()
                dataGen4.add(list)
            }else if(i<GEN_5){
                val list = pokemon.toMutableList()
                dataGen5.add(list)
            }else if(i<GEN_6){
                val list = pokemon.toMutableList()
                dataGen6.add(list)
            }else if(i<GEN_7){
                val list = pokemon.toMutableList()
                dataGen7.add(list)
            }else{
                val list = pokemon.toMutableList()
                dataGen8.add(list)
            }
        }

        for(pokemon in dataGen1){
                Log.i("[Result]", "content = ${pokemon[0]}")
        }

        //問題のデータをセット
        if(genNum == "1"){
            pokeData = dataGen1
        }else if(genNum == "2"){
            pokeData = dataGen2
        }else if(genNum == "3"){
            pokeData = dataGen3
        }else if(genNum == "4"){
            pokeData = dataGen4
        }else if(genNum == "5"){
            pokeData = dataGen5
        }else if(genNum == "6"){
            pokeData = dataGen6
        }else if(genNum == "7"){
            pokeData = dataGen7
        }else{
            pokeData = dataGen8
        }

        pokeData.shuffle()

        showNextQuiz()
    }

    /**
     * クイズを出題する
     */
    fun showNextQuiz(){
        //カウントラベルの更新
        binding.countLabel.text = getString(R.string.count_label, quizCount, QUIZ_COUNT)

        //クイズを1問取り出す
        val poke = pokeData[0]
        val quiz = mutableListOf<String>()
        quiz += poke[POKE_NAME_EN]
        quiz += poke[POKE_NAME_JA]
        quiz += poke[CHOICE1]
        quiz += poke[CHOICE2]
        quiz += poke[CHOICE3]

        //語源をセット
        if(poke[ORIGIN1_EN] != "") {origin1 = "${poke[ORIGIN1_EN]}：${poke[ORIGIN1_JA]}"}
        if(poke[ORIGIN2_EN] != "") {origin2 = "${poke[ORIGIN2_EN]}：${poke[ORIGIN2_JA]}"}
        if(poke[ORIGIN3_EN] != "") {origin3 = "${poke[ORIGIN3_EN]}：${poke[ORIGIN3_JA]}"}

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
        pokeData.removeAt(0)
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
            $question<br><br>
            [語源]<br>
            $origin1<br>
            $origin2<br>
            $origin3<br><br>
            <a href="https://www.google.com/search?q=$rightAnswer&tbm=isch">画像</a>を開く
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

        //語源をリセット
        origin1 = ""
        origin2 = ""
        origin3 = ""
    }

    /**
     * 出題数をチェックする
     */
    fun checkQuizCount(){
        if(quizCount==QUIZ_COUNT) {
            //結果画面を表示
            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount)
            intent.putExtra("GEN",genNum)
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