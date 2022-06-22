package com.easemob.agora.api;

import com.easemob.agora.model.AppUser;
import com.easemob.agora.model.ResCode;
import com.easemob.agora.model.ResponseParam;
import com.easemob.agora.model.TokenInfo;
import com.easemob.agora.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/app/user/register")
    public ResponseEntity register(@RequestBody @Valid AppUser appUser) {
        ResponseParam responseParam = new ResponseParam();
        appUserService.registerUser(appUser);
        responseParam.setCode(ResCode.RES_OK);
        return ResponseEntity.ok(responseParam);
    }

    @PostMapping("/app/user/login")
    public ResponseEntity login(@RequestBody @Valid AppUser appUser) {
        ResponseParam responseParam = new ResponseParam();

        TokenInfo token = appUserService.loginUser(appUser);
        responseParam.setAccessToken(token.getToken());
        responseParam.setExpireTimestamp(token.getExpireTimestamp());
        responseParam.setChatUserName(token.getChatUserName());
        responseParam.setAgoraUid(token.getAgoraUid());

        return ResponseEntity.ok(responseParam);
    }
}