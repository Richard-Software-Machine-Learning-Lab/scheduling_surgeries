package org.richard.schedulingsurgeries.view;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;

public class RequestHandler {

     public void solve() throws IOException {
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         try {
             HttpPost request = new HttpPost("http://localhost:8080/timeTable/solve");
             request.addHeader("content-type", "application/json");
             CloseableHttpResponse response = httpClient.execute(request);
         } catch (Exception e) {
             errorHttpRequest();
             e.printStackTrace();
         } finally {
             httpClient.close();
         }
     }

     public int getNumberOperatingRooms() throws IOException {
         int numberOfRooms = 0;
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         try {
             HttpGet request = new HttpGet("http://localhost:8080/rooms");
             request.addHeader("content-type", "application/json");
             CloseableHttpResponse response = httpClient.execute(request);
             String responseJSON = EntityUtils.toString(response.getEntity(),"UTF-8");
             JSONArray rooms = new JSONArray(responseJSON);
             numberOfRooms = rooms.length();
             return numberOfRooms;
         } catch (Exception e) {
             errorHttpRequest();
             e.printStackTrace();
         } finally {
             httpClient.close();
         }
         return numberOfRooms;
     }


    public String getSolution() throws IOException {
        String responseJSON = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/timeTable/solution");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = EntityUtils.toString(response.getEntity(),  "UTF-8");
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public String getSurgeries() throws IOException {
        String responseJSON = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/surgeries");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = EntityUtils.toString(response.getEntity(),  "UTF-8");
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public String getSpeciality(Integer surgeonId) throws IOException {
        String responseJSON = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/surgeons/" + surgeonId);
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = EntityUtils.toString(response.getEntity(),  "UTF-8");
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Integer[] getPatientIds() throws IOException {
        Integer[] responseJSON = new Integer[0];
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/patients");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = parseJsonPatientIds(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Integer[] getSurgeonIds() throws IOException {
        Integer[] responseJSON = new Integer[0];
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/surgeons");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = parseJsonSurgeonIds(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Integer[] getAnesthetistIds() throws IOException {
        Integer[] responseJSON = new Integer[0];
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/anesthetists");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = parseJsonAnesthetistIds(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Integer[] getAnesthesiaTypes() throws IOException {
        Integer[] responseJSON = new Integer[0];
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/anesthesiatypes");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = parseJsonAnesthesiaTypes(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Integer[] getSurgeryTypes() throws IOException {
        Integer[] responseJSON = new Integer[0];
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/surgerytypes");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = parseJsonSurgeryTypes(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public String[] getProcedures() throws IOException {
        String[] responseJSON = new String[0];
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/procedures");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = parseJsonProcedures(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public String[] getSurgeryTypeCodes() throws IOException {
        String[] responseJSON = new String[0];
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/surgerytypes");
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = parseJsonSurgeryTypeCodes(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Integer getDuration(Long surgeonId, Integer anesthesiaTypeCode, Long anesthetistId, String speciality,
                               String surgeryType, String surgeryName) throws IOException {
        Integer durationPrediction = -1;
        org.json.simple.JSONArray jsonArray = new org.json.simple.JSONArray();
        org.json.simple.JSONObject surgery = new org.json.simple.JSONObject();
        surgery.put("surgeon_id", surgeonId.intValue());
        surgery.put("anesthesia_type_code", anesthesiaTypeCode);
        surgery.put("anesthetist_id", anesthetistId.intValue());
        surgery.put("speciality", speciality);
        surgery.put("surgery_type_code", surgeryType);
        surgery.put("procedure_name", surgeryName);
        jsonArray.add(surgery);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("http://localhost:12345/prediction");
            StringEntity params = new StringEntity(jsonArray.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            CloseableHttpResponse response = httpClient.execute(request);
            String responseJSON = EntityUtils.toString(response.getEntity());
            JSONObject newVersionJson = new JSONObject(responseJSON);
            durationPrediction = Math.round(newVersionJson.getFloat("prediction"));
            return durationPrediction ;
        } catch (Exception ex) {
            errorHttpRequest();
            ex.printStackTrace();
        } finally {
            httpClient.close();
        }
        return  -1;
    }

    public Long getAnesthesiaTypeId(Integer code) throws IOException {
        Long responseJSON = -1L;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/anesthesiatypes/" + code);
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = Long.valueOf(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Long getProcedureId(String name) throws IOException {
        Long responseJSON = -1L;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            System.out.println(parseNumberOfWords(name));
            HttpGet request = new HttpGet("http://localhost:8080/procedures/" + parseNumberOfWords(name));
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = Long.valueOf(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public Long getSurgeryTypeId(String name) throws IOException {
        Long responseJSON = -1L;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet("http://localhost:8080/surgerytypes/" + name);
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
            responseJSON = Long.valueOf(EntityUtils.toString(response.getEntity(),  "UTF-8"));
            return responseJSON;
        }
        catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return responseJSON;
    }

    public void postSurgery(Long patientId, Long surgeonId, Long anesthesiaId, Long anesthetistId,
                            Long surgeryTypeId, Long procedureId, Integer duration) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("http://localhost:8080/surgeries/" + patientId + "/" + surgeonId + "/" + anesthesiaId + "/"
                                            + anesthetistId + "/" + surgeryTypeId + "/" + procedureId + "/" + duration);
            request.addHeader("content-type", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);
        } catch (Exception e) {
            errorHttpRequest();
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
    }

    public String parseNumberOfWords(String name) {
          String[] words = name.split("\\s");
          String nameWithSpaces = "";
          if (words.length == 1) {
              nameWithSpaces = words[0];
              return nameWithSpaces;
          }
          for (int wordCounter = 0; wordCounter < words.length - 1; wordCounter++) {
              nameWithSpaces = nameWithSpaces + words[wordCounter] + "%20";
          }
              nameWithSpaces = nameWithSpaces + words[words.length - 1];
          return nameWithSpaces;
    }

    public String[] parseJsonProcedures(String response) {
        JSONArray procedures = new JSONArray(response);
        String[] procedureNames = new String[procedures.length()];
        for (int counter = 0; counter < procedures.length(); counter++) {
            JSONObject procedure = procedures.getJSONObject(counter);
            procedureNames[counter] = procedure.getString("name");
        }
        return procedureNames;
    }

    public Integer[] parseJsonPatientIds(String response) {
        JSONArray patients = new JSONArray(response);
        Integer[] patientIds = new Integer[patients.length()];
        for (int counter = 0; counter < patients.length(); counter++) {
            JSONObject patient = patients.getJSONObject(counter);
            patientIds[counter] = patient.getInt("patientId");
        }
        return patientIds;
    }

    public Integer[] parseJsonSurgeonIds(String response) {
        JSONArray surgeons = new JSONArray(response);
        Integer[] surgeonIds = new Integer[surgeons.length()];
        for (int counter = 0; counter < surgeons.length(); counter++) {
            JSONObject surgeon = surgeons.getJSONObject(counter);
            surgeonIds[counter] = surgeon.getInt("surgeonId");
        }
        return surgeonIds;
    }

    public Integer[] parseJsonAnesthetistIds(String response) {
        JSONArray anesthetists = new JSONArray(response);
        Integer[] anesthetistIds = new Integer[anesthetists.length()];
        for (int counter = 0; counter < anesthetists.length(); counter++) {
            JSONObject anesthetist = anesthetists.getJSONObject(counter);
            anesthetistIds[counter] = anesthetist.getInt("anesthetistId");
        }
        return anesthetistIds;
    }

    public Integer[] parseJsonSurgeryTypes(String response) {
        JSONArray surgeryTypes = new JSONArray(response);
        Integer[] surgeryTypesIds = new Integer[surgeryTypes.length()];
        for (int counter = 0; counter < surgeryTypes.length(); counter++) {
            JSONObject surgeryType = surgeryTypes.getJSONObject(counter);
            surgeryTypesIds[counter] = surgeryType.getInt("surgeryTypeId");
        }
        return surgeryTypesIds;
    }

    public Integer[] parseJsonAnesthesiaTypes(String response) {
           JSONArray anesthesiaTypes = new JSONArray(response);
           Integer[] arrayAnesthesiaCodes = new Integer[anesthesiaTypes.length()];
           for (int counter = 0; counter < anesthesiaTypes.length(); counter++) {
               JSONObject anesthesiaType = anesthesiaTypes.getJSONObject(counter);
               arrayAnesthesiaCodes[counter] = anesthesiaType.getInt("code");
           }
           return arrayAnesthesiaCodes;
    }

    public String[] parseJsonSurgeryTypeCodes(String response) {
        JSONArray codes = new JSONArray(response);
        String[] surgeryTypeCodes = new String[codes.length()];
        for (int counter = 0; counter < codes.length(); counter++) {
            JSONObject procedure = codes.getJSONObject(counter);
            surgeryTypeCodes[counter] = procedure.getString("code");
        }
        return surgeryTypeCodes;
    }

    public void errorHttpRequest(){
        String message = "Error with the HTTP request. " + "\n" + "Review Quarkus connectivity.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
