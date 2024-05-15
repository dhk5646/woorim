package com.hyeyeong.woorim.domain.sample.service;

import com.hyeyeong.woorim.domain.sample.dto.SampleResultDto;
import com.hyeyeong.woorim.domain.sample.dto.SampleSearchDto;
import com.hyeyeong.woorim.domain.sample.entity.Sample;
import com.hyeyeong.woorim.domain.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public SampleResultDto select(SampleSearchDto dto) {
        Sample sample = sampleRepository.findById(dto.getSampleSeq()).orElseThrow(RuntimeException::new);
        return SampleResultDto.of(sample);
    }
}
