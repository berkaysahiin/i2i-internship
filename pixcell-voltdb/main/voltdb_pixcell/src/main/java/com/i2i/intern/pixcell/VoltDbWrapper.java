package com.i2i.intern.pixcell;

import org.voltdb.client.*;
import java.io.IOException;
import java.util.Arrays;


public class VoltDbWrapper {

    private final String IP = "35.198.145.16";
    private final int PORT = 32776;

    private final Client clientInstance;

    public VoltDbWrapper() {
        ClientConfig config = new ClientConfig();
        clientInstance = ClientFactory.createClient(config);
        try {
            clientInstance.createConnection(IP, PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getID(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetCustomerIdByMSISDN", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getPackageInternet(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetPackageData", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getPackageSms(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetPackageSms", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getPackageMinutes(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetPackageMinutes", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getPackagePeriod(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetPackagePeriod", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getPackagePrice(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetPackagePrice", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public String getPackageName(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetPackageName", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (String) response.getResults()[0].getString(0);
        }
        return "";
    }

    public int getInternetBalance(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetDataDataForCustomer", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getMinutesBalance(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetMinutesDataForCustomer", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getSmsBalance(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetSmsDataForCustomer", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public int getMoneyBalance(long MSISDN) {
        ClientResponse response = null;

        try {
            response = clientInstance.callProcedure("GetMoneyDataForCustomer", MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (checkResponse(response)) {
            return (int) response.getResults()[0].getLong(0);
        }
        return -1;
    }

    public void setMinutesBalance(int amount_minutes, long MSISDN) {
        ClientResponse response = null;
        try {
            response = clientInstance.callProcedure("UpdateCustomerMinutes", amount_minutes, MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setInternetBalance(int amount_internet, long MSISDN) {
        ClientResponse response = null;
        try {
            response = clientInstance.callProcedure("UpdateCustomerData", amount_internet, MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setSmsBalance(int amount_sms, long MSISDN) {
        ClientResponse response = null;
        try {
            response = clientInstance.callProcedure("UpdateCustomerSms", amount_sms, MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setMoneyBalance(int amount_money, long MSISDN) {
        ClientResponse response = null;
        try {
            response = clientInstance.callProcedure("UpdateCustomerMoney", amount_money, MSISDN);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private boolean checkResponse(ClientResponse response) {
        if (response.getStatus() == ClientResponse.SUCCESS &&
                response.getResults()[0].advanceRow()
        ) {
            return true;
        }
        System.out.println(Arrays.toString(response.getResults()));
        return false;
    }

}

