package com.hejz.security.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

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
    /**
     * 异常信息记录到日志记录中,直接打印即可
     */
//    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date ModifyTime;

}
