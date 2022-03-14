package eigodog.dogadoga.android.englishquizforpokemon

import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * @param[assetManager] ?
 * @param[csvFilePath] 対象とするcsvのファイルパス
 */
class CsvReader (assetManager: AssetManager, csvFilePath: String){
    private val assertManager = assetManager
    private val csvFilePath = csvFilePath

    //実際に利用する関数
    /**
     * csvを読み込む
     * @param[skipLine] スキップする行数(デフォルトは0)
     * @param[encoding] エンコード方式(デフォルトはUTF-8)
     * @param[delimiter] 区切り文字(デフォルトは",")
     * @return mutableListOf<List<String>>()
     */
    fun readCsv(
        //スキップする行数
        skipLine: Int = 0,
        //エンコード
        encoding: String = "UTF-8",
        //区切り文字
        delimiter: String = ","
    ): List<List<String>>{
        return this.readCsv(
            this.assertManager.open(this.csvFilePath),
            skipLine,
            encoding,
            delimiter
        )
    }

    private fun readCsv(
        //入力データ
        inputCsv: InputStream,
        //スキップする行数
        skipLine: Int,
        //エンコード方式
        encoding: String,
        //区切り文字
        delimiter: String
        ): List<List<String>> {
            //csvを読み込む変数の宣言
            val csvContents = mutableListOf<List<String>>()
            try{
                //指定されたファイルからの入力をバッファする
                BufferedReader(
                    InputStreamReader(
                        inputCsv, Charset.forName(encoding)
                    )
                ).use {fileReader ->
                    //読み込む行番号を保持
                    var lineNumber: Int = 1
                    fileReader.forEachLine { line->
                        //空の行でなければ
                        if(line.isNotBlank()){
                            //スキップする行以降を読み込む
                            if(lineNumber > skipLine){
                                val contents =
                                    line.split(delimiter).map{ value ->
                                        value.trim()
                                    }
                                csvContents.add(contents)
                            }
                        }
                        lineNumber++
                    }
                }
            }catch (ex: IOException){
                ex.printStackTrace()
            }
            return csvContents
        }
}