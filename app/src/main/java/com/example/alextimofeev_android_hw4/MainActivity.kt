package com.example.alextimofeev_android_hw4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.alextimofeev_android_hw4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        execute()
    }


private fun execute() {
    var checkName=false
    var checkPhone=false
    var checkGender=false
    var checkNotification=false
    var checkAuthorization=false
    var checkProductNotification=false

    val randomProgression = Random.nextInt(101)
    val pointsProgress = binding.linearProgress
    pointsProgress.progress=randomProgression

    val numberPointsText=binding.numberOfPoints
    numberPointsText.setText("$randomProgression/100")

   //Проврека валидности имени
    val nameText=binding.nameText
    nameText.addTextChangedListener {
        val name=it.toString()
        checkName=name.isNotEmpty() &&name.length <= 40
        checkAllConditions(checkName,
            checkPhone,
            checkGender,
            checkNotification,
            checkAuthorization,
            checkProductNotification)

    }

    //Проврека валидности номера телефона
    val phoneText=binding.phoneText
    phoneText.addTextChangedListener {
        val phone=it.toString()
        checkPhone=phone.isNotEmpty() &&phone.length <= 13
        checkAllConditions(checkName,
            checkPhone,
            checkGender,
            checkNotification,
            checkAuthorization,
            checkProductNotification)

    }

    //Проверка выбора в radio group
    val radioGroup=binding.radioGroup
    radioGroup.setOnCheckedChangeListener { group, checkedId ->
        checkGender=checkedId != -1
        checkAllConditions(checkName,
            checkPhone,
            checkGender,
            checkNotification,
            checkAuthorization,
            checkProductNotification)

    }


    //Проверка выбора в чекбоксах
    val switchNotification=binding.switchNotification
    val authorizationNotification=binding.authorizationNotification
    val notificationAboutProducts=binding.notificationAboutProducts

    //Свойства по умолчанию
    authorizationNotification.isChecked=false
    authorizationNotification.isClickable=false
    authorizationNotification.isEnabled=false
    //Свойства по умолчанию
    notificationAboutProducts.isChecked=false
    notificationAboutProducts.isClickable=false
    notificationAboutProducts.isEnabled=false

   // Проверка кликера доступности чекбоксов.
    switchNotification.setOnCheckedChangeListener { buttonView, isChecked ->
        if (isChecked){

            authorizationNotification.isEnabled=true
            authorizationNotification.isClickable=true
            notificationAboutProducts.isEnabled=true
            notificationAboutProducts.isClickable=true
            checkNotification=true
            checkAllConditions(checkName,
                checkPhone,
                checkGender,
                checkNotification,
                checkAuthorization,
                checkProductNotification)


        }else{
            authorizationNotification.isChecked=false
            authorizationNotification.isClickable=false
            authorizationNotification.isEnabled=false
            notificationAboutProducts.isChecked=false
            notificationAboutProducts.isClickable=false
            notificationAboutProducts.isEnabled=false
            checkNotification=false
            checkAllConditions(checkName,
                checkPhone,
                checkGender,
                checkNotification,
                checkAuthorization,
                checkProductNotification)

        }

    }

    // Проверка выбора чекбокса авторизации
     authorizationNotification.setOnCheckedChangeListener { buttonView, isChecked ->
        if(isChecked){
            checkAuthorization=true
            checkAllConditions(checkName,
                checkPhone,
                checkGender,
                checkNotification,
                checkAuthorization,
                checkProductNotification)
        }else{
            checkAuthorization=false
            checkAllConditions(checkName,
                checkPhone,
                checkGender,
                checkNotification,
                checkAuthorization,
                checkProductNotification)
        }
     }


    // Проверка выбора чекбокса уведомлений о новом продукте
    notificationAboutProducts.setOnCheckedChangeListener { buttonView, isChecked ->
        if(isChecked){
            checkProductNotification=true
            checkAllConditions(checkName,
                checkPhone,
                checkGender,
                checkNotification,
                checkAuthorization,
                checkProductNotification)
        }else{
            checkProductNotification=false
            checkAllConditions(checkName,
                checkPhone,
                checkGender,
                checkNotification,
                checkAuthorization,
                checkProductNotification)
        }
    }


        binding.saveButton.setOnClickListener {
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show()
    }


}

    // В случае выполнения условий кнопка Save становится доступной
    private fun checkAllConditions(
        checkName: Boolean,
        checkPhone: Boolean,
        checkGender: Boolean,
        checkNotification: Boolean,
        checkAuthorization: Boolean,
        checkProductNotification: Boolean
    ) {
        val saveButton=binding.saveButton

        saveButton.isEnabled=checkName && checkPhone && checkGender && (!checkNotification || (checkNotification &&(checkAuthorization||checkProductNotification)))

    }

}






