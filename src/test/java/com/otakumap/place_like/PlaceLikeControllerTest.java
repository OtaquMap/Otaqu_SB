package com.otakumap.place_like;

import com.otakumap.domain.place_like.controller.PlaceLikeController;
import com.otakumap.domain.place_like.dto.PlaceLikeResponseDTO;
import com.otakumap.domain.place_like.service.PlaceLikeCommandService;
import com.otakumap.domain.place_like.service.PlaceLikeQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class PlaceLikeControllerTest {

    @Mock
    private PlaceLikeQueryService placeLikeQueryService;

    @Mock
    private PlaceLikeCommandService placeLikeCommandService;

    @InjectMocks
    private PlaceLikeController placeLikeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(placeLikeController).build();
    }

    @Test
    void testGetPlaceLikeList() throws Exception {
        Long userId = 1L;
        Long lastId = 0L;
        int limit = 10;

        // PlaceLikePreViewDTO 객체 생성
        PlaceLikeResponseDTO.PlaceLikePreViewDTO placeLike1 = new PlaceLikeResponseDTO.PlaceLikePreViewDTO(1L, 1L, 101L, true);
        PlaceLikeResponseDTO.PlaceLikePreViewDTO placeLike2 = new PlaceLikeResponseDTO.PlaceLikePreViewDTO(2L, 1L, 102L, false);

        // PlaceLikePreViewListDTO 객체 생성
        List<PlaceLikeResponseDTO.PlaceLikePreViewDTO> placeLikes = Arrays.asList(placeLike1, placeLike2);
        PlaceLikeResponseDTO.PlaceLikePreViewListDTO placeLikeListDTO = new PlaceLikeResponseDTO.PlaceLikePreViewListDTO(placeLikes, true, 2L);

        when(placeLikeQueryService.getPlaceLikeList(userId, lastId, limit)).thenReturn(placeLikeListDTO);

        mockMvc.perform(get("/api/users/1/saved-places")
                        .param("lastId", "0")
                        .param("limit", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.placeLikes[0].id").value(1))
                .andExpect(jsonPath("$.result.placeLikes[0].userId").value(1))
                .andExpect(jsonPath("$.result.placeLikes[0].placeId").value(101))
                .andExpect(jsonPath("$.result.placeLikes[0].isFavorite").value(true));

    }

    @Test
    void testDeletePlaceLike() throws Exception {
        List<Long> placeIds = Arrays.asList(1L, 2L);

        // 삭제 동작 모의
        mockMvc.perform(delete("/api/saved-places")
                        .param("placeIds", "1,2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // "성공입니다."라는 메시지가 반환되면 이를 검증
                .andExpect(jsonPath("$.message").value("성공입니다."));
    }

}
