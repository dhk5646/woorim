package com.hyeyeong.woorim.domain.sample.dto;

import com.hyeyeong.woorim.domain.sample.entity.Sample;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SampleResultDto {
    private Long sampleSeq;
    private String content;

    public static SampleResultDto of(Sample sample) {
        return new SampleResultDto(sample.getSampleSeq(), sample.getContent());
    }
}
