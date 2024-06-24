package com.example.uts_tb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

@Suppress("DEPRECATION")
class SimulasiPage : AppCompatActivity() {
    private var tflite: Interpreter? = null
    private lateinit var tvPrediction: TextView
    private lateinit var predictionVal: TextView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.simulasi_page)

        val toolbarSimulasi: Toolbar = findViewById(R.id.toolbarSimulasi)
        val tvUmurInput: EditText = findViewById(R.id.tvUmurInput)
        val spinnerGender: Spinner = findViewById(R.id.spinnerGender)
        val spinnerSmoking: Spinner = findViewById(R.id.spinnerSmoking)
        val spinnerYellowfing: Spinner = findViewById(R.id.spinnerYellowfing)
        val spinnerAnxiety: Spinner = findViewById(R.id.spinnerAnxiety)
        val spinnerPeerPresure: Spinner = findViewById(R.id.spinnerPeerPresure)
        val spinnerChronicDisease: Spinner = findViewById(R.id.spinnerChronicDisease)
        val spinnerFatigue: Spinner = findViewById(R.id.spinnerFatigue)
        val spinnerAllergy: Spinner = findViewById(R.id.spinnerAllergy)
        val spinnerWheezing: Spinner = findViewById(R.id.spinnerWheezing)
        val spinnerAlcohol: Spinner = findViewById(R.id.spinnerAlcohol)
        val spinnerCoughing: Spinner = findViewById(R.id.spinnerCoughing)
        val spinnerShortnessofBreath: Spinner = findViewById(R.id.spinnerShortnessofBreath)
        val spinnerSwallowingDifficulty: Spinner = findViewById(R.id.spinnerSwallowingDifficulty)
        val spinnerChestPain: Spinner = findViewById(R.id.spinnerChestPain)
        val btnPredict: Button = findViewById(R.id.btnPredict)
        tvPrediction = findViewById(R.id.tvPrediction)
        predictionVal = findViewById(R.id.predictionVal)

        // Initialize TensorFlow Lite interpreter
        try {
            tflite = Interpreter(loadModelFile())
        } catch (e: Exception) {
            Log.e("SimulasiPage", "Error initializing TensorFlow Lite interpreter.", e)
        }

        // Set up the toolbar
        setSupportActionBar(toolbarSimulasi)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set up spinners with adapters
        setupSpinner(spinnerGender, R.array.gender_array)
        setupSpinner(spinnerSmoking, R.array.bool_array)
        setupSpinner(spinnerYellowfing, R.array.bool_array)
        setupSpinner(spinnerAnxiety, R.array.bool_array)
        setupSpinner(spinnerPeerPresure, R.array.bool_array)
        setupSpinner(spinnerChronicDisease, R.array.bool_array)
        setupSpinner(spinnerFatigue, R.array.bool_array)
        setupSpinner(spinnerAllergy, R.array.bool_array)
        setupSpinner(spinnerWheezing, R.array.bool_array)
        setupSpinner(spinnerAlcohol, R.array.bool_array)
        setupSpinner(spinnerCoughing, R.array.bool_array)
        setupSpinner(spinnerShortnessofBreath, R.array.bool_array)
        setupSpinner(spinnerSwallowingDifficulty, R.array.bool_array)
        setupSpinner(spinnerChestPain, R.array.bool_array)

        // Predict button logic
        btnPredict.setOnClickListener {
            val inputs = collectInputs(tvUmurInput, spinnerGender, spinnerSmoking, spinnerYellowfing, spinnerAnxiety,
                spinnerPeerPresure, spinnerChronicDisease, spinnerFatigue, spinnerAllergy, spinnerWheezing,
                spinnerAlcohol, spinnerCoughing, spinnerShortnessofBreath, spinnerSwallowingDifficulty, spinnerChestPain)

            val prediction = predict(inputs)
            displayPrediction(prediction)
        }
    }

    private fun setupSpinner(spinner: Spinner, arrayId: Int) {
        ArrayAdapter.createFromResource(
            this,
            arrayId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = assets.openFd("lung_model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun collectInputs(vararg views: Any): FloatArray {
        val age = (views[0] as EditText).text.toString().toFloat()

        // Gender (laki-laki = 0, perempuan = 1)
        val gender = if ((views[1] as Spinner).selectedItemPosition == 0) 0f else 1f

        // Convert yes/no Spinner values to binary (0 or 1)
        fun spinnerToBinary(spinner: Spinner): Float {
            return if (spinner.selectedItemPosition == 0) 0f else 1f
        }

        val smoking = spinnerToBinary(views[2] as Spinner)
        val yellowFingers = spinnerToBinary(views[3] as Spinner)
        val anxiety = spinnerToBinary(views[4] as Spinner)
        val peerPressure = spinnerToBinary(views[5] as Spinner)
        val chronicDisease = spinnerToBinary(views[6] as Spinner)
        val fatigue = spinnerToBinary(views[7] as Spinner)
        val allergy = spinnerToBinary(views[8] as Spinner)
        val wheezing = spinnerToBinary(views[9] as Spinner)
        val alcohol = spinnerToBinary(views[10] as Spinner)
        val coughing = spinnerToBinary(views[11] as Spinner)
        val shortnessOfBreath = spinnerToBinary(views[12] as Spinner)
        val swallowingDifficulty = spinnerToBinary(views[13] as Spinner)
        val chestPain = spinnerToBinary(views[14] as Spinner)

        return floatArrayOf(
            age,
            gender,
            smoking,
            yellowFingers,
            anxiety,
            peerPressure,
            chronicDisease,
            fatigue,
            allergy,
            wheezing,
            alcohol,
            coughing,
            shortnessOfBreath,
            swallowingDifficulty,
            chestPain
        )
    }

    private fun predict(inputs: FloatArray): FloatArray {
        val inputTensor = arrayOf(inputs)
        val outputTensor = Array(1) { FloatArray(1) } // Shape [1, 1]

        // Jalankan inferensi
        tflite?.run(inputTensor, outputTensor)

        // Ambil nilai keluaran
        val predictionValue = outputTensor[0][0]

        // Kembalikan nilai prediksi
        return floatArrayOf(predictionValue)
    }

    private fun displayPrediction(prediction: FloatArray) {
        val predictionValue = prediction[0] // Ambil nilai keluaran

        // 0.5 untuk klasifikasi biner
        val threshold = 0.5

        val predictionText = if (predictionValue > threshold) {
            "Pasien Terkena Kanker Paru-Paru"
        } else {
            "Pasien Tidak Terkena Kanker Paru-Paru"
        }

        // Tampilkan teks prediksi
        tvPrediction.text = predictionText
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        tflite?.close()
        super.onDestroy()
    }
}