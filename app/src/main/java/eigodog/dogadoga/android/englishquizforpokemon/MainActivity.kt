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
        val gen2Button = findViewById<Button>(R.id.gen2Button)
        val gen3Button = findViewById<Button>(R.id.gen3Button)
        val gen4Button = findViewById<Button>(R.id.gen4Button)
        val gen5Button = findViewById<Button>(R.id.gen5Button)
        val gen6Button = findViewById<Button>(R.id.gen6Button)
        val gen7Button = findViewById<Button>(R.id.gen7Button)
        val gen8Button = findViewById<Button>(R.id.gen8Button)

        gen1Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "1")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }

        gen2Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "2")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }

        gen3Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "3")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }

        gen4Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "4")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }

        gen5Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "5")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }

        gen6Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "6")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }

        gen7Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "7")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }

        gen8Button.setOnClickListener{
            //Intentオブジェクト生成、遷移画面定義
            val intent2Quiz = Intent(this, QuizActivity::class.java)
            //Intentオブジェクトに値をプット(第一引数はキー，第二引数は渡したい変数)
            intent2Quiz.putExtra("GEN", "8")
            //QuizActivity実行
            startActivity(intent2Quiz)
        }
    }
}