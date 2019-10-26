package com.whf.annotation.study.service;

import com.whf.annotation.study.bean.GenRedPacketParam;
import com.whf.annotation.study.bean.GenRedPacketResult;
import com.whf.annotation.study.bean.GrabRedPacketParam;
import com.whf.annotation.study.bean.GrabRedPacketResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Tuple;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RedPacketServiceImpl implements RedPacketService {
    private static final String redPacketPrefix = "redPacket";
    private static final int expireTime = 24 * 60 * 60;
    private static final String minValueStr = "0.01";
    private static final String grabRankRedPacketPrefix = "grabRankRedPacket";

    @Autowired
    private RedisService redisService;

    @Override
    public GrabRedPacketResult grabRedPacket(GrabRedPacketParam grabRedPacketParam) throws Exception {
        String ownerUserId = grabRedPacketParam.getPacketOwnerUserId();
        String userId = grabRedPacketParam.getUserId();
        String uuid = grabRedPacketParam.getPacketUuid();
        String rankKey = String.format("%s_%s_%s",grabRankRedPacketPrefix,ownerUserId,uuid);
        Double zSetScore = redisService.getZSetScore(rankKey, userId);
        GrabRedPacketResult grabRedPacketResult = new GrabRedPacketResult();
        grabRedPacketResult.setUserId(userId);
        if(zSetScore == null) {
            String redPacketKey = String.format("%s_%s_%s",redPacketPrefix, ownerUserId,uuid);
            String money = redisService.listPop(redPacketKey);
            if(StringUtils.isNotBlank(money)) {
                redisService.addZSet(rankKey,userId,Double.parseDouble(money));
                grabRedPacketResult.setMoney(money);
            }
        } else {
            grabRedPacketResult.setMoney(zSetScore.toString());
        }
        Map<String,String> rankMap = new HashMap<>();
        Set<Tuple> allZSet = redisService.getAllZSet(rankKey);
        if(!CollectionUtils.isEmpty(allZSet)) {
            for (Tuple t : allZSet) {
                rankMap.put(String.valueOf(t.getScore()),t.getElement());
            }
        }
        grabRedPacketResult.setGrabList(rankMap);
        return grabRedPacketResult;
    }

    @Override
    public GenRedPacketResult genRedPackage(GenRedPacketParam genRedPacketParam) throws Exception {
        if(genRedPacketParam == null) {
            return null;
        }
        List<String> randomRedPackage =
                getRandomRedPackage(genRedPacketParam.getNumber(), genRedPacketParam.getAmount());
        String uuid = getUUID();
        String redPackageKey = String.format("%s_%s_%s",redPacketPrefix, genRedPacketParam.getUserId(),uuid);
        try {
            redisService.setList(redPackageKey,randomRedPackage);
            redisService.expireKey(redPackageKey,expireTime);
            return GenRedPacketResult.builder()
                    .userId(genRedPacketParam.getUserId())
                    .redPackageUuid(uuid).build();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> getRandomRedPackage(Integer count, BigDecimal amount) {
        BigDecimal leastAmount = amount.subtract(new BigDecimal(minValueStr).multiply(BigDecimal.valueOf(count)));
        if(leastAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        List<BigDecimal> bigDecimals = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            bigDecimals.add(new BigDecimal(minValueStr));
        }
        BigInteger bigInteger = leastAmount.divide(new BigDecimal(minValueStr),2,BigDecimal.ROUND_UP).toBigInteger();
//        for (int i = 0; i < bigInteger.intValue(); i++) {
//            int index = ThreadLocalRandom.current().nextInt(count);
//            BigDecimal old = bigDecimals.get(index);
//            bigDecimals.set(index,old.add(new BigDecimal(minValueStr)));
//        }
        //todo
        int leastNumber = bigInteger.intValue();
        for (int i = 0; i < count; i++) {
            int number = ThreadLocalRandom.current().nextInt(leastNumber);
            if(i == count - 1) {
                number = leastNumber;
            }
            BigDecimal old = bigDecimals.get(i);
            bigDecimals.set(i,old.add((new BigDecimal(minValueStr)).multiply(BigDecimal.valueOf(number))));
            leastNumber -= number;
        }


        List<String> result = new ArrayList<>(count);
        for (BigDecimal b : bigDecimals) {
            result.add(b.toPlainString());
        }
        return result;
    }

    private String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
