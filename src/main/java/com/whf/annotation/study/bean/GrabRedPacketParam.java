package com.whf.annotation.study.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrabRedPacketParam implements Serializable {
    private static final long serialVersionUID = -2901947098177970112L;
    @NotNull
    private String userId;
    @NotNull
    private String packetOwnerUserId;
    @NotNull
    private String packetUuid;
}
