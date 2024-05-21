package com.hyeyeong.woorim.domain.sample.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SampleQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

}
