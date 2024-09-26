package com.andre.otakufav_anime.ui.web_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.andre.otakufav_anime.viewModel.MainViewModel
import com.andre.otakufav_anime.databinding.FragmentWebViewBinding

class YouTubeWebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = binding.webView

        // WebView Einstellungen
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        // Verhindert das Öffnen von YouTube im Browser
        webView.webViewClient = WebViewClient()

        // Lade die YouTube-URL
        webView.loadUrl("https://www.youtube.com")

        viewModel.randomAnime.observe(viewLifecycleOwner) {
            binding.webView.loadUrl(it.trailer.deutsch)
        }
    }
}