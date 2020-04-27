package com.alialfayed.nstghfr.viewModel

import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.alialfayed.nstghfr.model.AstghfrModel
import com.alialfayed.nstghfr.utils.AnlyzData
import com.alialfayed.nstghfr.view.activity.HomeActivity
import com.alialfayed.nstghfr.view.fragment.Taspi7AfterSalahFragment

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/30/2020 - 9:51 AM
 */
class Taspi7AfterSalahFrViewModel(
    private var taspi7AfterSalah: Taspi7AfterSalahFragment,
    private var activity: HomeActivity
) : ViewModel() {

    private var basicCounter = 0
    private var position = 0

    private fun setData(): ArrayList<AstghfrModel> {
        val astghfrModel: ArrayList<AstghfrModel> = ArrayList()
        astghfrModel.add(AstghfrModel("30", "", "استغفر الله"))
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                "اللهم أنت السلام ، ومنك السلام ، تباركت يا ذا الجلال والإكرام"
            )
        )
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                " لا إله إلا الله، وحده لا شريك له، له الملك، وله الحمد، وهو على كل شيء قدير، اللهم لا مانع لما أعطيت، ولا معطي لما منعت، ولا ينفع ذا الجد منك الجد"
            )
        )
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                "لا إله إلا الله وحده لا شريك له،له الملك وله الحمد وهو على كل شئ قدير،لا حول ولا قوة إلا بالله،لا إله إلا الله،ولا نعبد إلا إياه ، له النعمة وله الفضل ،وله الثناء الحسن،لا إله إلا الله مخلصين له الدين ولو كره الكافرون"
            )
        )
        astghfrModel.add(AstghfrModel("33", "", "سبحان الله"))
        astghfrModel.add(AstghfrModel("33", "", "الحمد لله"))
        astghfrModel.add(AstghfrModel("33", "", "الله أكبر"))
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                "لا إله إلا الله وحده لا شريك له ، له الملك وله الحمد وهو على كل شيء قدير"
            )
        )
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                "بسم الله الرحمن الرحيم\n{قل هو الله أحد الله الصمد لم يلد ولم يولد ولم يكن له كفوا أحد}"
            )
        )
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                "بسم الله الرحمن الرحيم\n{قل أعوذ برب الفلق من شر ما خلق ومن شر غاسق إذا وقب ومن شر النفاثات في العقد ومن شر حاسد إذا حسد}"
            )
        )
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                "بسم الله الرحمن الرحيم \n{قل أعوذ برب الناس ملك الناس إله الناس من شر الوسواس الخناس الذي يوسوس في صدور الناس من الجنة والناس}"
            )
        )
        astghfrModel.add(
            AstghfrModel(
                "1",
                "",
                "بسم الله الرحمن الرحيم\n{الله لا إله إلا هو الحي القيوم لا تأخذه سنة ولا نوم له ما في السماوات وما في الأرض من ذا الذي يشفع عنده إلا بإذنه يعلم ما بين أيديهم وما خلفهم ولا يحيطون بشيء من علمه إلا بما شاء وسع كرسيه السماوات والأرض ولا يئوده حفظهما وهو العلي العظيم لا إكراه في الدين قد تبين الرشد من الغي فمن يكفر بالطاغوت ويؤمن بالله فقد استمسك بالعروة الوثقى لا انفصام لها والله سميع عليم}"
            )
        )
        astghfrModel.add(
            AstghfrModel(
                "10",
                "",
                "لا إله إلا الله وحده لا شريك له ، له الملك وله الحمد ، يحيي ويميت وهو على كل شئ قدير"
            )
        )
        astghfrModel.add(AstghfrModel("1", "", "اللهم إني أسألك علماً نافعاً ، ورزقاً طيباً"))
        return astghfrModel
    }

    fun btnOnclickCounter(
        txtText: TextView,
        txtBasicCounter: TextView,
        txtCounter: TextView,
        imgBtnOnClick: ImageButton
    ) {
        txtCounter.text = basicCounter.toString()
        txtText.text = setData()[position].getAnstghfrText()
        txtBasicCounter.text = setData()[position].getAnstghfrID()

        imgBtnOnClick.setOnClickListener {
            if (position == 13) {
                position = 0
            }
            basicCounter++
            txtCounter.text = basicCounter.toString()
            txtText.text = setData()[position].getAnstghfrText()
            txtBasicCounter.text = setData()[position].getAnstghfrID()
            AnlyzData.setCounterType(activity, 1)
            if (setData()[position].getAnstghfrID().toInt() == basicCounter) {
                position++
                basicCounter = 0
                txtCounter.text = basicCounter.toString()
                txtText.text = setData()[position].getAnstghfrText()
                txtBasicCounter.text = setData()[position].getAnstghfrID()
            }
        }

    }
}