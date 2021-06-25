package com.example.blackoutsimulation.service.impl;

import com.example.blackoutsimulation.config.TokenWrapper;
import com.example.blackoutsimulation.service.CovidService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

@Service
public class CovidServiceImpl implements CovidService {
    @Override
    public void checkToken(HttpServletRequest resq, HttpServletResponse res) {
        TokenWrapper request = new TokenWrapper(resq, res);

        boolean totalQueriesNumberIsUpdate = true;
        request.setTotalQueriesNumberIsUpdate(totalQueriesNumberIsUpdate);
        String socialCreditCode = request.getSocialCreditCode();
        System.out.println("企业信用代码" + socialCreditCode);


        System.out.println("mac地址:" + request.getMac());

//        RequestWrapper requestWrapper = (RequestWrapper) resq;
//
//        boolean clientIsValid = etlRestfulClientMapper.clientIsValid(etlRestfulClient);
//        if (!clientIsValid) {
//            res.setStatus(403);
//            throw new Exception(ResponseCode.DATA_NOT_AUTHORIZED.getDescribe());
//        }
    }
}
