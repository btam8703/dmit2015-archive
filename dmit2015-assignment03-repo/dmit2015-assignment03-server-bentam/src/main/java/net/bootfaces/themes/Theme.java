package net.bootfaces.themes;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 This Theme class is for bootfaces
 @author Benjamin Tam
 @version 1.0
 @since 2020-03-11
 */

@Named
@SessionScoped
public class Theme implements Serializable {
    private static final long serialVersionUID = 1L;

    private String currentTheme = "default";

    private String internalTheme = "default";

    private String customTheme = "";

    public String getCurrentTheme() {
        return currentTheme;
    }

    public String getInternalTheme() {
        return internalTheme;
    }

    public String getCustomTheme() {
        return customTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
        if (currentTheme.equalsIgnoreCase("Freelancer")) {
            internalTheme = "other";
            customTheme = currentTheme;
        } else if (currentTheme.equalsIgnoreCase("Grayscale")) {
            internalTheme = "other";
            customTheme = currentTheme;
        } else {
            internalTheme = currentTheme;
            customTheme = "";
        }
    }

    public void selectTheme() {
    }

}