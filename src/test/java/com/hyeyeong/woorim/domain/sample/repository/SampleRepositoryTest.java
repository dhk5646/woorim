package com.hyeyeong.woorim.domain.sample.repository;

import com.hyeyeong.woorim.domain.sample.entity.Sample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class SampleRepositoryTest {

    @Autowired
    SampleRepository sampleRepository;

    @Test
    @DisplayName("sampleRepository 가 정상적으로 스프링에서 처리되고, 의존성 주입에 문제가 없는지를 확인한다.")
    public void sampleRepository_프록시_생성_확인() {
        Assertions.assertTrue(AopUtils.isAopProxy(sampleRepository));
    }

    @Test
    @DisplayName("샘플 데이터를 조회합니다.")
    public void 샘플데이터_조회() {
        // given
        Long sampleSeq = 100L;

        // when
        Optional<Sample> sample = sampleRepository.findById(sampleSeq);

        // then
        Assertions.assertTrue(sample.isPresent());
    }

    @Test
    @DisplayName("데이터를 추가합니다.")
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(value -> {
            Sample sample = Sample.of("Sample contents: " + value);
            sampleRepository.save(sample);
        });
    }
}
