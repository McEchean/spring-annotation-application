package com.whf.annotation.study.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class GenRedPacketResult implements Serializable {
    private static final long serialVersionUID = 3084240280977737965L;
    private String userId;
    private String redPackageUuid;
}
