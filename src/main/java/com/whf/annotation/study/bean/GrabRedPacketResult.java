package com.whf.annotation.study.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrabRedPacketResult implements Serializable {
    private static final long serialVersionUID = 6142778776384080089L;
    private String userId;
    private String money;
    private Map<String,String> grabList;
}
