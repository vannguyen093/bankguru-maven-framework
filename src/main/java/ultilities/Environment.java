package ultilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:environmentConfig/${env}.properties"})
public interface Environment extends Config {
    @Key("App.Url")
    String appUrl();

    @Key("App.User")
    String appUser();

    @Key("App.Pass")
    String appPass();
}
