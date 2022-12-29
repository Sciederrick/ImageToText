package ke.derrick.imagetotext.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.Text.TextBlock
import ke.derrick.imagetotext.databinding.FragmentTextResultBinding
import ke.derrick.imagetotext.viewmodels.SharedViewModel


class TextResultFragment : Fragment() {
    private var _binding:FragmentTextResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel.textBlocks.observe(viewLifecycleOwner) {
            Log.d(TAG, "${it.size}")
            processTextRecognitionResult(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun processTextRecognitionResult(blocks: List<TextBlock>) {
        var resultText = ""
        for (i in blocks.indices) {
            val lines: List<Text.Line> = blocks[i].lines
            for (j in lines.indices) {
                val elements: List<Text.Element> = lines[j].elements
                var lineOfText = ""
                for (k in elements.indices) {
                    lineOfText += elements[k].text + "  "
                    Log.d(TAG, elements[k].text)

                }
                lineOfText += "\n"
                resultText += lineOfText
            }
        }
        binding.tvResult.text = resultText ?: "nothing to show"
    }

    companion object {
        const val TAG = "TextResultFragment"
    }

}