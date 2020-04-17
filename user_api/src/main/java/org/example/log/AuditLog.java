package org.example.log;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: hejz
 * @Description: 审计表
 * @Date: 2020/4/17 10:58
 */
@Slf4j
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private String path;
    private Integer status;
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifyTime;
}
