package com.example.blackoutsimulation.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.blackoutsimulation.utils.Decrypt;
import com.example.blackoutsimulation.utils.RSADecryptUtil;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenWrapper extends HttpServletRequestWrapper {

    final String token;
    final String socialCreditCode;
    final String businessName;
    final String mac;
    final DecodedJWT jwt;

    boolean totalQueriesNumberIsUpdate = false;

    @SneakyThrows
    public TokenWrapper(HttpServletRequest request, HttpServletResponse res) {
        super(request);
        token = getToken(request);

        if (token == null) {
            res.setStatus(401);
            throw new Exception(ResponseCode.NO_AUTHORIZATION.getDescribe());
        }

        try {
            String target = RSADecryptUtil.decrypt(token);
            jwt = Decrypt.deToken(target);
        } catch (Exception ie) {
            log.error("", ie);
            res.setStatus(401);
            throw new Exception(ResponseCode.INVALID_AUTHORIZATION.getDescribe());
        }


        socialCreditCode = getValue("socialCreditCode");
        businessName = getValue("businessName");
        mac = getValue("mac");
    }

    private String getValue(String key) {
        return this.jwt.getClaim(key).asString();
    }

    private String getToken(HttpServletRequest request) {
        String accessToken = request.getParameter("access_token");
        String token4Header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return accessToken == null ? token4Header : accessToken;
    }

    public String getToken() {
        return token;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getMac() {
        return mac;
    }

    public boolean isTotalQueriesNumberIsUpdate() {
        return totalQueriesNumberIsUpdate;
    }

    public void setTotalQueriesNumberIsUpdate(boolean totalQueriesNumberIsUpdate) {
        this.totalQueriesNumberIsUpdate = totalQueriesNumberIsUpdate;
    }

}
