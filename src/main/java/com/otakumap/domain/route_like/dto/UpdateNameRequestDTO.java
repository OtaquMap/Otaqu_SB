package com.otakumap.domain.route_like.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateNameRequestDTO {

    @NotBlank(message = "제목은 빈칸이 불가합니다.")
    @Size(max = 50, message = "제목은 50자 이하여야 합니다.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
