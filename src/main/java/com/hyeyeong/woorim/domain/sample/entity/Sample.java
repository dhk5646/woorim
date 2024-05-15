package com.hyeyeong.woorim.domain.sample.entity;

import com.hyeyeong.woorim.common.constants.TableConstants;
import com.hyeyeong.woorim.common.entity.BasicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableConstants.SAMPLE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sample extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sampleSeq;

    @Column(columnDefinition = "varchar(255)")
    private String content;

    public static Sample of(String content) {
        return new Sample(null, content);
    }

}
