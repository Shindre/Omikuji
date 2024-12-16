package jp.wings.nikkeibp.omikuji

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import jp.wings.nikkeibp.omikuji.databinding.FortuneBinding
import jp.wings.nikkeibp.omikuji.databinding.OmikujiBinding

class OmikujiActivity : AppCompatActivity() {

    // おみくじ棚の配列
    val omikujiShelf = Array<OmikujiParts>(20)
    { OmikujiParts(R.drawable.result2, R.string.contents1) }

    // おみくじ番号保管用
    var omikujiNumber = -1

    val omikujiBox = OmikujiBox()

    lateinit var binding: OmikujiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OmikujiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val value = pref.getBoolean("button", false)

        binding.button.visibility = if (value) View.VISIBLE else View.INVISIBLE
        omikujiBox.omikujiView = binding.imageView

        // おみくじ棚の準備
        omikujiShelf[0].drawID = R.drawable.result1
        omikujiShelf[0].fortuneID = R.string.contents2

        omikujiShelf[1].drawID = R.drawable.result3
        omikujiShelf[1].fortuneID = R.string.contents9

        omikujiShelf[2].fortuneID = R.string.contents3
        omikujiShelf[3].fortuneID = R.string.contents4
        omikujiShelf[4].fortuneID = R.string.contents5
        omikujiShelf[5].fortuneID = R.string.contents6
        omikujiShelf[6].fortuneID = R.string.contents7
        omikujiShelf[7].fortuneID = R.string.contents8
        omikujiShelf[8].fortuneID = R.string.contents9
        omikujiShelf[9].fortuneID = R.string.contents3
        omikujiShelf[10].fortuneID = R.string.contents4
        omikujiShelf[11].fortuneID = R.string.contents5
        omikujiShelf[12].fortuneID = R.string.contents6
        omikujiShelf[13].fortuneID = R.string.contents7
        omikujiShelf[14].fortuneID = R.string.contents8
        omikujiShelf[15].fortuneID = R.string.contents9
        omikujiShelf[16].fortuneID = R.string.contents3
        omikujiShelf[17].fortuneID = R.string.contents4
        omikujiShelf[18].fortuneID = R.string.contents5
        omikujiShelf[19].fortuneID = R.string.contents6
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            if (omikujiNumber <0 && omikujiBox.finish){
                drawResult()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val toast = Toast.makeText(this, item.title, Toast.LENGTH_LONG)
//        toast.show()
        if (item?.itemId == R.id.item1){
            val intent = Intent(this, OmikujiPreferenceActivity::class.java)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun onButtonClick(v: View){
        omikujiBox.shake()
    }

    fun drawResult(){
        // おみくじ番号の取得
        omikujiNumber = omikujiBox.number

        // おみくじ棚の配列から、omikujiPartsを取得
        val op = omikujiShelf[omikujiNumber]

        // レイアウトを運勢表示に変える
        val fortuneBinding = FortuneBinding.inflate(layoutInflater)
        setContentView(fortuneBinding.root)

        // 画像とテキストを変更
        fortuneBinding.imageView2.setImageResource(op.drawID)
        fortuneBinding.textView.setText(op.fortuneID)
    }
}