package com.shani.controller;

import com.shani.domain.Lamp;
import com.shani.repository.LampRepository;
import com.shani.repository.impl.LampRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FrontController extends HttpServlet {

    private static final LampRepository<Long, Lamp> lampRepository = new LampRepositoryImpl();

    public FrontController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String button = req.getParameter("button");
        if ("register".equals(button)) {
            String countryName = req.getParameter("CountryName");
            String roomName = req.getParameter("RoomName");
            Lamp lamp = new Lamp();
            lamp.setRoomName(roomName);
            lamp.setLampCountry(countryName);
            lamp.setWork(false);

            lampRepository.save(lamp);
            doRequest(req, resp);
        }

        if (String.valueOf(button).contains("light")) {
            int updateId = Integer.parseInt(button.substring(5));
            String countryName = "";
            try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipdata.co/country_name?api-key=test").openStream(), "UTF-8").useDelimiter("\\A")) {
                countryName = s.next();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            if (countryName.equalsIgnoreCase(lampRepository.findById((long) updateId).getLampCountry())) {
                lampRepository.updateLight(lampRepository.findById((long) updateId));
                doRequest(req, resp);
            }
            doRequest(req, resp);
        }
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/room");
        req.setAttribute("lamps", lampRepository.findAll());
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
