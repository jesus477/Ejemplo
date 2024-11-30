package com.utl.idgs701.proyecto.appservice;

import com.utl.idgs701.proyectoas.mvvm.LibrosViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LibroExternoAppService {
    private static final String API_URL = "http://172.20.10.4:8090/Biblioteca/api/libros/getAllViewModel"; 

    public List<LibrosViewModel> getAllExterno() {
        List<LibrosViewModel> librosViewModels = new ArrayList<>();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

            Gson gson = new Gson();
            librosViewModels = gson.fromJson(sb.toString(), new TypeToken<List<LibrosViewModel>>(){}.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return librosViewModels;
    }
}
