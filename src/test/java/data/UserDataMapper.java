package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;
import lombok.Getter;

import java.io.File;

@Getter
public class UserDataMapper {
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("pin")
    private String pin;
    @JsonProperty("mobileNumber")
    private String mobileNumber;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("editPin")
    private String editPin;
    @JsonProperty("editMobileNumber")
    private String editMobileNumber;

    public static UserDataMapper getUserData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "/src/test/resources/UserData.json"), UserDataMapper.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
