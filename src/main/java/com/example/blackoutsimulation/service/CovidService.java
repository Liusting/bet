package com.example.blackoutsimulation.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CovidService {
    void checkToken(HttpServletRequest request, HttpServletResponse response);
}
