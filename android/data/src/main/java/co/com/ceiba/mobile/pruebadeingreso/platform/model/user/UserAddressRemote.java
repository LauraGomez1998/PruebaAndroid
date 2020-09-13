package co.com.ceiba.mobile.pruebadeingreso.platform.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAddressRemote {

    @SerializedName("street")
    @Expose
    private String street;

    @SerializedName("suite")
    @Expose
    private String suite;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("zipcode")
    @Expose
    private String zipcode;

    @SerializedName("geo")
    @Expose
    private UserAddressGeoRemote geo;
}
