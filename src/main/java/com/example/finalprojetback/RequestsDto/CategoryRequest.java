package com.example.finalprojetback.RequestsDto;

import com.example.finalprojetback.Enums.Status;
import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    private String category;
    private String description;
    private Status status;
}
