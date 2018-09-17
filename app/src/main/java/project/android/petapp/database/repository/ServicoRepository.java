package project.android.petapp.database.repository;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import project.android.petapp.R;
import project.android.petapp.database.entities.Servico;

public class ServicoRepository {
    private static final String TAG = ServicoRepository.class.getSimpleName();

    public static List<Servico> getAll(Resources resources) {

        String json = readRawJson(resources);
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<Servico>>() {}.getType();
        return gson.fromJson(json, productListType);
    }

    private static String readRawJson(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.servicos);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error closing the input stream.", exception);
            }
        }
        return writer.toString();
    }
}