package com.hyeyeong.woorim.domain.sample.repository;

import com.hyeyeong.woorim.domain.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {

}
