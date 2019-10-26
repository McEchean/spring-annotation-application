package com.whf.annotation.study.listener;

public class RedPacketListener extends Listener {

    private String subscribeTopic = "__keyspace@0__:redPacket*";

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        super.onPMessage(pattern, channel, message);
        System.out.println(String.format("pattern : %s, channel : %s , message : %s",pattern,channel,message));
        // channel : __keyspace@0__:redPacket_1_ahsdkfhalsdjfoaweasdhfanwel
        String info = channel.split("redPacket_")[1];
        String userId  = info.split("_")[0];
        String redPacketUuid = info.split("_")[1];
        //todo something async ....
    }

    @Override
    public String topic() {
        return subscribeTopic;
    }
}
