package com.hyeyeong.woorim.domain.sample.controller;

import com.hyeyeong.woorim.domain.sample.dto.SampleDto;
import com.hyeyeong.woorim.domain.sample.dto.SampleSearchDto;
import com.hyeyeong.woorim.domain.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@RequiredArgsConstructor
@Slf4j
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/ex2")
    public void exModel(Model model) {
        model.addAttribute("sampleList", sampleService.selectAll());
    }
}
