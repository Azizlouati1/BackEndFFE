package com.cni.elearning.Controllers.Gemini;

import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("api/gemini")
@RequiredArgsConstructor
public class GeminiController {
    private final GenerativeModel generativeModel;

    @PostMapping("/")
    public String getResponse(@RequestBody String question) throws IOException {
        GenerateContentResponse generateContentResponse = this.generativeModel.generateContent(
                ContentMaker.fromString(question));
        return ResponseHandler.getText(generateContentResponse);
    }
}
