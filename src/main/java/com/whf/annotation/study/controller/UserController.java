package com.whf.annotation.study.controller;

import com.whf.annotation.study.bean.*;
import com.whf.annotation.study.service.RedPacketServiceImpl;
import com.whf.annotation.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedPacketServiceImpl redPackageService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/main")
    public String testHello() {
        return userService.getUser();
    }


    @RequestMapping(value = "/genRedPacket",method = RequestMethod.POST)
    @ResponseBody
    public Result<GenRedPacketResult> genRedPacket(@Validated @RequestBody GenRedPacketParam genRedPacketParam){
        Result<GenRedPacketResult> result = new Result<>();
        try {
            GenRedPacketResult genRedPacketResult = redPackageService.genRedPackage(genRedPacketParam);
            if(genRedPacketResult != null) {
                result.makeSuccessResult("success").setData(genRedPacketResult);
            } else {
                return result.makeFailedResult("system error");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return result.makeFailedResult("system error");
        }
        return result;
    }

    @RequestMapping(value = "/grabRedPacket",method = RequestMethod.POST)
    @ResponseBody
    public Result<GrabRedPacketResult> grabRedPacket(@RequestBody GrabRedPacketParam grabRedPacketParam) {
        Result<GrabRedPacketResult> result = new Result<>();
        try {
            GrabRedPacketResult grabRedPacketResult = redPackageService.grabRedPacket(grabRedPacketParam);
            if(grabRedPacketResult != null) {
                result.makeSuccessResult("success").setData(grabRedPacketResult);
            } else {
                return result.makeFailedResult("system error");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return result.makeFailedResult("system error");
        }
        return result;
    }



}
