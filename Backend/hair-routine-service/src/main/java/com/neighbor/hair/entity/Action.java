package com.neighbor.hair.entity;

import com.neighbor.hair.constraints.annotations.Legend;
import com.neighbor.hair.constraints.legends.LegendType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

@Entity
public class Action extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //may take this out
    private Long id;

    @Nullable
    @NotEmpty
    private String description;  //what is being done with product

    @NotNull
    @NotEmpty
    @Legend(LegendType.HAIRLOCATION)
    private String hairLocation; //where is it applied scalp, tip, strand

    //private Integer frequency;  //how many times in that moment: "{frequency} times"

    @OneToOne(mappedBy = "action")
    private Application application;

    public Action() {
    }

    public Action(@Nullable String description, String hairLocation) {
        this.description = description;
        this.hairLocation = hairLocation;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public String getHairLocation() {
        return hairLocation;
    }

    public void setHairLocation(String hairLocation) {
        this.hairLocation = hairLocation;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
