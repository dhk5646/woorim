package com.hyeyeong.woorim.domain.sample.dto;

import com.hyeyeong.woorim.domain.sample.entity.Sample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleDto {
    private Long sampleSeq;
    private String content;

    public static SampleDto of(Sample sample) {
        return new SampleDto(sample.getSampleSeq(), sample.getContent());
    }
}
