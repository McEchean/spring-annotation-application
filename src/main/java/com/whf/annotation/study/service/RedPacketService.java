package com.whf.annotation.study.service;

import com.whf.annotation.study.bean.GenRedPacketParam;
import com.whf.annotation.study.bean.GenRedPacketResult;
import com.whf.annotation.study.bean.GrabRedPacketParam;
import com.whf.annotation.study.bean.GrabRedPacketResult;

public interface RedPacketService {
    public GenRedPacketResult genRedPackage(GenRedPacketParam genRedPacketParam) throws Exception;

    public GrabRedPacketResult grabRedPacket(GrabRedPacketParam grabRedPacketParam) throws Exception;
}
