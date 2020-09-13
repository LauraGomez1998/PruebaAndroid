package co.com.ceiba.mobile.pruebadeingreso.remote.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class UserCompanyRemote {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("catchPhrase")
    @Expose
    private String catchPhrase;

    @SerializedName("bs")
    @Expose
    private String bs;
}
