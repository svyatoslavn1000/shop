package com.geekbrains.decembermarket.utils.valid_group_ex;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Item {
    @Size(min = 4, message = "title is too short", groups = {EditGroup.class, RegisterGroup.class})
    private String title;

    @Size(min = 4, message = "value is too short", groups = {RegisterGroup.class})
    private String value;
}

